package com.lp.techDemo.metaq.producer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lp.techDemo.metaq.entity.MetaqDemoEntity;
import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.MessageSessionFactory;
import com.taobao.metamorphosis.client.MetaClientConfig;
import com.taobao.metamorphosis.client.MetaMessageSessionFactory;
import com.taobao.metamorphosis.client.extension.spring.MessageBuilder;
import com.taobao.metamorphosis.client.extension.spring.MetaqTemplate;
import com.taobao.metamorphosis.client.producer.MessageProducer;
import com.taobao.metamorphosis.client.producer.SendResult;
import com.taobao.metamorphosis.utils.ZkUtils.ZKConfig;

public class MetaqDemoProducer {

	public static void main(String[] args) throws Exception {
//		sendWithPureMetaq();
		sendWithSpring();
	}
	
	static void sendWithPureMetaq() throws Exception{
		final MetaClientConfig metaClientConfig = new MetaClientConfig();
        final ZKConfig zkConfig = new ZKConfig();
        //设置zookeeper地址
        zkConfig.zkConnect = "127.0.0.1:2181";
        metaClientConfig.setZkConfig(zkConfig);
        // New session factory,强烈建议使用单例
        MessageSessionFactory sessionFactory = new MetaMessageSessionFactory(metaClientConfig);
        // create producer,强烈建议使用单例
        MessageProducer producer = sessionFactory.createProducer();
        // publish topic
        final String topic = "test";
        producer.publish(topic);
        for (int i = 0; i < 50; i++){
        	// send message
            SendResult sendResult = producer.sendMessage(new Message(topic, (topic + i).getBytes()));
            // check result
            if (!sendResult.isSuccess()) {
                System.err.println("Send message failed,error message:" + sendResult.getErrorMessage());
            }
            else {
                System.out.println("Send message successfully,sent to " + sendResult.getPartition());
            }
        }
        
	}
	
	static void sendWithSpring(){
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("metaq-producer.xml");
		c.start();
		MetaqTemplate mt = (MetaqTemplate) c.getBean("metaqTemplate");
		for (long i = 0; i < 50; i++){
			MetaqDemoEntity entity = new MetaqDemoEntity();
			entity.setSendOrder(i);
			try {
				mt.send(MessageBuilder.withTopic("mytopic").withBody(entity));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		c.close();
	}

}
