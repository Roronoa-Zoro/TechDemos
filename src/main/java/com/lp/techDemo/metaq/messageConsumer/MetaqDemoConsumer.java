package com.lp.techDemo.metaq.messageConsumer;

import java.util.concurrent.Executor;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.MessageSessionFactory;
import com.taobao.metamorphosis.client.MetaClientConfig;
import com.taobao.metamorphosis.client.MetaMessageSessionFactory;
import com.taobao.metamorphosis.client.consumer.ConsumerConfig;
import com.taobao.metamorphosis.client.consumer.MessageConsumer;
import com.taobao.metamorphosis.client.consumer.MessageListener;
import com.taobao.metamorphosis.utils.ZkUtils.ZKConfig;

public class MetaqDemoConsumer {

	public static void main(String[] args) throws Exception {
		start1();
	}
	
	static void consumeMsg() throws Exception{
		final MetaClientConfig metaClientConfig = new MetaClientConfig();
        final ZKConfig zkConfig = new ZKConfig();
        //设置zookeeper地址
        zkConfig.zkConnect = "127.0.0.1:2181";
        metaClientConfig.setZkConfig(zkConfig);
        // New session factory,强烈建议使用单例
        MessageSessionFactory sessionFactory = new MetaMessageSessionFactory(metaClientConfig);
        // subscribed topic
        final String topic = "test";
        // consumer group
        final String group = "mytopic";
        // create consumer,强烈建议使用单例
        MessageConsumer consumer = sessionFactory.createConsumer(new ConsumerConfig(group));
        // subscribe topic
        consumer.subscribe(topic, 16384, new MessageListener() {

            public void recieveMessages(Message message) {
                System.out.println("consumer1 receive message " + new String(message.getData()));
            }


            public Executor getExecutor() {
                // Thread pool to process messages,maybe null.
                return null;
            }
        });
        // complete subscribe
        consumer.completeSubscribe();
        
        MessageConsumer consumer2 = sessionFactory.createConsumer(new ConsumerConfig(group));
        // subscribe topic
        consumer2.subscribe(topic, 16384, new MessageListener() {

            public void recieveMessages(Message message) {
                System.out.println("consumer2 receive message " + new String(message.getData()));
            }


            public Executor getExecutor() {
                // Thread pool to process messages,maybe null.
                return null;
            }
        });
        // complete subscribe
        consumer.completeSubscribe();
	}

	static void start1(){
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("metaq-consumer.xml");
		c.start();
		System.err.println("start1 is ready..................................................");
	}
	
	
}
