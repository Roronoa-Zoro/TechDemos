package com.lp.techDemo.storm.topology;

import com.lp.techDemo.storm.bolt.SplitSentenceBolt;
import com.lp.techDemo.storm.bolt.StringPrintBolt;
import com.lp.techDemo.storm.bolt.WordCountBolt;
import com.lp.techDemo.storm.scheme.StringScheme;
import com.lp.techDemo.storm.spout.MetaSpout;
import com.lp.techDemo.storm.spout.StringSenderSpout;
import com.taobao.metamorphosis.client.MetaClientConfig;
import com.taobao.metamorphosis.client.consumer.ConsumerConfig;
import com.taobao.metamorphosis.utils.ZkUtils.ZKConfig;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

public class TopologyMain {

	public static void main(String[] args) throws Exception {
		metaqTopology(args);
//		wordCountTopology(args);
	}
	
	static void wordCountTopology(String[] args) throws Exception {
		TopologyBuilder builder = new TopologyBuilder();

	    builder.setSpout("stringSender", new StringSenderSpout(), 1);

	    builder.setBolt("splitSentenceBolt", new SplitSentenceBolt(), 2).shuffleGrouping("stringSender");
	    builder.setBolt("wordCounter", new WordCountBolt(), 2).fieldsGrouping("splitSentenceBolt", new Fields("singleWord"));
	    Config conf = new Config();
	    conf.setDebug(true);


	    if (args != null && args.length > 0) {
	      conf.setNumWorkers(3);

	      StormSubmitter.submitTopologyWithProgressBar(args[0], conf, builder.createTopology());
	    }
	    else {
	      conf.setMaxTaskParallelism(3);

	      LocalCluster cluster = new LocalCluster();
	      cluster.submitTopology("wordCountTopology", conf, builder.createTopology());

	      Thread.sleep(10000);

	      cluster.shutdown();
	    }
	}
	static void metaqTopology(String[] args) throws Exception {
		TopologyBuilder builder = new TopologyBuilder();
		
		final MetaClientConfig metaClientConfig = new MetaClientConfig();
        final ZKConfig zkConfig = new ZKConfig();
        //设置zookeeper地址
        zkConfig.zkConnect = "127.0.0.1:2181";
        metaClientConfig.setZkConfig(zkConfig);
        
	    builder.setSpout("metaConsumer", new MetaSpout(metaClientConfig, new ConsumerConfig("metaTopology"), new StringScheme()), 2);

	    builder.setBolt("stringPrinter", new StringPrintBolt(), 2).shuffleGrouping("metaConsumer");
//	    builder.setBolt("stringPrinter", new StringPrintBolt(), 2).fieldsGrouping("stringSender", new Fields("word"));
	    Config conf = new Config();
	    conf.setDebug(true);


	    if (args != null && args.length > 0) {
	      conf.setNumWorkers(3);

	      StormSubmitter.submitTopologyWithProgressBar(args[0], conf, builder.createTopology());
	    }
	    else {
	      conf.setMaxTaskParallelism(3);
	      conf.put("meta.topic", "test");
	      LocalCluster cluster = new LocalCluster();
	      cluster.submitTopology("metaqTopology", conf, builder.createTopology());

	      Thread.sleep(1000000);

	      cluster.shutdown();
	    }
	}
	
	static void stringTopology(String[] args) throws Exception {
		TopologyBuilder builder = new TopologyBuilder();

	    builder.setSpout("stringSender", new StringSenderSpout(), 1);

//	    builder.setBolt("stringPrinter", new StringPrintBolt(), 2).shuffleGrouping("stringSender");
	    builder.setBolt("stringPrinter", new StringPrintBolt(), 2).fieldsGrouping("stringSender", new Fields("word"));
	    Config conf = new Config();
	    conf.setDebug(true);


	    if (args != null && args.length > 0) {
	      conf.setNumWorkers(3);

	      StormSubmitter.submitTopologyWithProgressBar(args[0], conf, builder.createTopology());
	    }
	    else {
	      conf.setMaxTaskParallelism(3);

	      LocalCluster cluster = new LocalCluster();
	      cluster.submitTopology("string-demo", conf, builder.createTopology());

	      Thread.sleep(10000);

	      cluster.shutdown();
	    }
	}

}
