package com.lp.techDemo.storm.spout;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.spout.Scheme;
import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;

import com.taobao.gecko.core.util.LinkedTransferQueue;
import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.MessageSessionFactory;
import com.taobao.metamorphosis.client.MetaClientConfig;
import com.taobao.metamorphosis.client.MetaMessageSessionFactory;
import com.taobao.metamorphosis.client.consumer.ConsumerConfig;
import com.taobao.metamorphosis.client.consumer.MessageConsumer;
import com.taobao.metamorphosis.client.consumer.MessageListener;
import com.taobao.metamorphosis.exception.MetaClientException;


/**
 * 
 * 
 */
public class MetaSpout extends BaseRichSpout {
    private static final long serialVersionUID = 4382748324382L;
    public static final String FETCH_MAX_SIZE = "meta.fetch.max_size";

    public static final String TOPIC = "meta.topic";

    public static final int DEFAULT_MAX_SIZE = 128 * 1024;

    private transient MessageConsumer messageConsumer;

    private transient MessageSessionFactory sessionFactory;

    private final MetaClientConfig metaClientConfig;

    private final ConsumerConfig consumerConfig;

    static final Logger log = LoggerFactory.getLogger(MetaSpout.class);

    private final Scheme scheme;

    /**
     * Time in milliseconds to wait for a message from the queue if there is no
     * message ready when the topology requests a tuple (via
     * {@link #nextTuple()}).
     */
    public static final long WAIT_FOR_NEXT_MESSAGE = 1L;

    //multi thread env, each thread has separate below objects
    private transient ConcurrentHashMap<Long, Message> id2wrapperMap;
    private transient LinkedTransferQueue<Message> messageQueue;

    private transient SpoutOutputCollector collector;

    public MetaSpout(final MetaClientConfig metaClientConfig, final ConsumerConfig consumerConfig, final Scheme scheme) {
        super();
        this.metaClientConfig = metaClientConfig;
        this.consumerConfig = consumerConfig;
        this.scheme = scheme;
    }

    //any thread will invoke this method, it means thread-1 invoke once, thread-2 invoke again
    @SuppressWarnings("rawtypes")
    @Override
    public void open(final Map conf, final TopologyContext context, final SpoutOutputCollector collector) {
        final String topic = (String) conf.get(TOPIC);
        if (topic == null) {
            throw new IllegalArgumentException(TOPIC + " is null");
        }
        Integer maxSize = (Integer) conf.get(FETCH_MAX_SIZE);
        if (maxSize == null) {
            log.warn("Using default FETCH_MAX_SIZE");
            maxSize = DEFAULT_MAX_SIZE;
        }
        this.id2wrapperMap = new ConcurrentHashMap<Long, Message>();
        this.messageQueue = new LinkedTransferQueue<Message>();
        try {
            this.collector = collector;
            this.setUpMeta(topic, maxSize);
        }
        catch (final MetaClientException e) {
            log.error("Setup meta consumer failed", e);
        }
    }


    private void setUpMeta(final String topic, final Integer maxSize) throws MetaClientException {
        this.sessionFactory = new MetaMessageSessionFactory(metaClientConfig);
        this.messageConsumer = sessionFactory.createConsumer(consumerConfig);
        this.messageConsumer.subscribe(topic, maxSize, new MessageListener() {

            @Override
            public void recieveMessages(final Message message) {
                id2wrapperMap.put(message.getId(), message);
                messageQueue.offer(message);
            }

            @Override
            public Executor getExecutor() {
                return null;
            }
        }).completeSubscribe();
    }


    @Override
    public void close() {
        try {
            messageConsumer.shutdown();
        }
        catch (final MetaClientException e) {
            log.error("Shutdown consumer failed", e);
        }
        try {
            sessionFactory.shutdown();
        }
        catch (final MetaClientException e) {
            log.error("Shutdown session factory failed", e);
        }

    }


    @Override
    public void nextTuple() {
        if (messageConsumer != null) {
            try {

                final Message message = messageQueue.poll(WAIT_FOR_NEXT_MESSAGE, TimeUnit.MILLISECONDS);
                if (message == null) {
                    return;
                }
                this.collector.emit(scheme.deserialize(message.getData()), message.getId());
                System.err.println(Thread.currentThread().getName() + " sending =========================="+scheme.deserialize(message.getData()));
            }
            catch (final InterruptedException e) {
                // interrupted while waiting for message, big deal
            }
        }
    }


    @Override
    public void ack(final Object msgId) {
    	System.err.println("in ack==========================================="+msgId);
        if (msgId instanceof Long) {
            final long id = (Long) msgId;
            final Message wrapper = id2wrapperMap.remove(id);
            if (wrapper == null) {
                log.warn("don't know how to ack[{}, {}]", msgId.getClass().getName(), msgId);
                return;
            }
        }
        else {
            log.warn("don't know how to ack[{}, {}]", msgId.getClass().getName(), msgId);
        }

    }


    @Override
    public void fail(final Object msgId) {
        if (msgId instanceof Long) {
            final long id = (Long) msgId;
            final Message message = id2wrapperMap.remove(id);
            if (message == null) {
                log.warn("don't know how to reject[{}, {}]", msgId.getClass().getName(), msgId);
                return;
            }
            log.error("error when processing msg[{}]", scheme.deserialize(message.getData()));
        }
        else {
            log.warn("don't know how to reject[{}, {}]", msgId.getClass().getName(), msgId);
        }

    }


    @Override
    public void declareOutputFields(final OutputFieldsDeclarer declarer) {
        declarer.declare(scheme.getOutputFields());
    }


	public boolean isDistributed() {
        return true;
    }

}
