package com.lp.techDemo.disruptor.producer;

import com.lmax.disruptor.EventTranslatorTwoArg;
import com.lmax.disruptor.RingBuffer;
import com.lp.techDemo.disruptor.entity.LongEvent;
import java.util.Date;
import java.nio.ByteBuffer;

public class LongEventProducerWithTranslator {

	private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer){
        this.ringBuffer = ringBuffer;
    }
    
    private static final EventTranslatorTwoArg<LongEvent, ByteBuffer, Date> TRANSLATOR = 
    		new EventTranslatorTwoArg<LongEvent, ByteBuffer, Date>(){

		@Override
		public void translateTo(LongEvent event, long sequence, ByteBuffer arg0, Date arg1) {
			event.setDate(arg1);
			event.setValue(arg0.getLong(0));
			
		}
    };
    
    public void onData(ByteBuffer bb, Date date){
        ringBuffer.publishEvent(TRANSLATOR, bb, date);
    }
}
