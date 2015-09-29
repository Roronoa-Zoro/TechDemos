package com.lp.techDemo.disruptor.exceptionHandler;

import com.lmax.disruptor.ExceptionHandler;
import com.lp.techDemo.disruptor.entity.LongEvent;

public class LongEventExceptionHandler implements ExceptionHandler<LongEvent> {

	@Override
	public void handleEventException(Throwable ex, long sequence, LongEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleOnStartException(Throwable ex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleOnShutdownException(Throwable ex) {
		// TODO Auto-generated method stub
		
	}

}
