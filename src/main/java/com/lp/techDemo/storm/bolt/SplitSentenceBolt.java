package com.lp.techDemo.storm.bolt;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class SplitSentenceBolt implements IRichBolt {

	private static final long serialVersionUID = -8507845818919766420L;
	private OutputCollector collector;
	private static final Logger LOG = LoggerFactory.getLogger(SplitSentenceBolt.class);
	
	@SuppressWarnings("rawtypes")
	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		
		this.collector = collector;
	}

	@Override
	public void execute(Tuple input) {
		String str = input.getString(0);
		LOG.info("SplitSentenceBolt-[{}] get string [{}]", Thread.currentThread().getName(), str);
		if (StringUtils.isNotBlank(str)) {
			String[] words = str.split(" ");
			for (String word : words) {
				collector.emit(input, new Values(word));
			}
		}
		collector.ack(input);
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("singleWord"));

	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		
	}

}
