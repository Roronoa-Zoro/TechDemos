package com.lp.techDemo.storm.scheme;

import backtype.storm.spout.Scheme;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class StringScheme implements Scheme {

	private static final long serialVersionUID = -2207687358550271751L;

	public List<Object> deserialize(byte[] bytes) {
        try {
            return new Values(new String(bytes, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public Fields getOutputFields() {
        return new Fields("str");
    }
}
