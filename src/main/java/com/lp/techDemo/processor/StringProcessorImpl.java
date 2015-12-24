package com.lp.techDemo.processor;

import com.lp.techDemo.processor.message.ProcessorMessage;

public class StringProcessorImpl extends AbstractProcessor {

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void cleanUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doProcess(ProcessorMessage obj) {
		System.err.println(Thread.currentThread().getName() + "-" + processorName + " doProcess " + obj.getPayload());
	}

}
