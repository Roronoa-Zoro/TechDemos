package com.lp.techDemo.storm.bolt;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

public class WordCountBolt extends BaseRichBolt {

	private static final long serialVersionUID = -7651722928482965651L;
	private static final Logger LOG = LoggerFactory.getLogger(WordCountBolt.class);
	Map<String, Integer> counts = new HashMap<String, Integer>();
	private OutputCollector collector;

	@SuppressWarnings("rawtypes")
	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		LOG.info("WordCountBolt set collector==================================================");
		this.collector = collector;
	}

	@Override
	public void execute(Tuple tuple) {
		String word = tuple.getString(0);
		LOG.info("WordCountBolt-[{}] increase [{}] number", Thread.currentThread().getName(), word);
		Integer count = counts.get(word);
		if (count == null)
			count = 0;
		count++;
		counts.put(word, count);
		collector.ack(tuple);
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
//		declarer.declare(new Fields("word", "count"));
	}

	@Override
	public void cleanup() {
        for(Map.Entry<String, Integer> entry : counts.entrySet()){
             System.err.println(entry.getKey()+": "+entry.getValue());
        }
	}

}
