package com.lp.techDemo.processor.reader;

import com.lp.techDemo.processor.message.ProcessorMessage;

public class StringMessageReader implements ProcessorMessageReader {

	@Override
	public ProcessorMessage getMessage() {
		ProcessorMessage pm = new ProcessorMessage();
		pm.setPayload(System.currentTimeMillis());
		return pm;
	}

}
