package com.lp.techDemo.metaq.messageConsumer;

import com.lp.techDemo.metaq.entity.MetaqDemoEntity;
import com.taobao.metamorphosis.client.extension.spring.DefaultMessageListener;
import com.taobao.metamorphosis.client.extension.spring.MetaqMessage;

public class DemoMessageListener extends DefaultMessageListener<MetaqDemoEntity> {

	@Override
	public void onReceiveMessages(MetaqMessage<MetaqDemoEntity> msg) {
		//simulate logic, just print out argument
		
		System.err.println(System.getProperty("consumer") + " processing partition:" + msg.getPartition().getPartition() + 
				", send order is:" + msg.getBody().getSendOrder());
	}

}
