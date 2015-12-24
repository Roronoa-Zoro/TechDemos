package com.lp.techDemo.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lp.techDemo.processor.message.ProcessorMessage;

public abstract class AbstractProcessor implements Processor {

	private static final Logger log = LoggerFactory.getLogger(AbstractProcessor.class);
	
	public AbstractProcessor next;
	protected boolean forcedRun = false;
	protected String processorName;
	
	public boolean isForcedRun() {
		return forcedRun;
	}

	public void setForcedRun(boolean forcedRun) {
		this.forcedRun = forcedRun;
	}

	public AbstractProcessor getNext() {
		return next;
	}

	public void setNext(AbstractProcessor next) {
		this.next = next;
	}

	public String getProcessorName() {
		return processorName;
	}

	public void setProcessorName(String processorName) {
		this.processorName = processorName;
	}

	@Override
	public abstract void start();

	@Override
	public abstract void cleanUp();

	@Override
	public abstract void doProcess(ProcessorMessage obj);
	
	public void startProcessor() {
		start();
		log.info("processor [{}] is started", processorName);
		if (next != null) {
			next.startProcessor();
		}
	}
	
	public void stopProcessor() {
		cleanUp();
		log.info("processor [{}] is stopped", processorName);
		if (next != null) {
			next.cleanUp();
		}
	}
	
	public void processing(ProcessorMessage obj) throws Throwable {
		try{
			
			if (obj.getT() != null && !forcedRun) {
				throw obj.getT();
			}
			//有异常发送,并且当前是forcedRun的processor,则直接执行
			doProcess(obj);
			if (next != null) {
				next.processing(obj);
			}
		}catch (Throwable e) {
			AbstractProcessor ep = obj.getErrorProcessor();
			if (ep == null) {
				//this processor get exception
				obj.setErrorProcessor(this);
				obj.setT(e);
				
				AbstractProcessor tmp = next;
				boolean needForceRun = false;
				while (tmp != null) {
					if (tmp.forcedRun) {
						needForceRun = true;
						break;
					}
				}
				//下游有forcedRun的processor
				if (needForceRun) {
					log.error("error happens, force to execute next processor", e);
					obj.setT(e);
					next.processing(obj);
				}else {
					throw e;
				}
			} else {
				//判断 出exception 的processor是当前的processor的上游processor还是下游processor
				AbstractProcessor tmp = next;
				while (tmp != null) {
					if (tmp == ep) {
						//出exception的processor是下游的processor,说明异常是反上来的,直接向上回退(抛出)异常
						throw obj.getT();
					}
					tmp = tmp.next;
				}
				//出exception的processor是上游的processor,说明下游有forcedRun的processor
				if (next != null) {
					next.processing(obj);
				}
			}
		}
	}

}
