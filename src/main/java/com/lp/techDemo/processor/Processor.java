package com.lp.techDemo.processor;

import com.lp.techDemo.processor.message.ProcessorMessage;

public interface Processor {

	void start();
	void cleanUp();
	void doProcess(ProcessorMessage obj);
}
