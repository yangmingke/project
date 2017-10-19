package com.yzx.rest;

import java.util.Arrays;
import java.util.List;

import ly.bit.nsq.lookupd.AbstractLookupd;

public class MyLookupd extends AbstractLookupd{

	public MyLookupd(String addr){
		this.addr=addr;
	}
	@Override
	public List<String> query(String topic) {
		// TODO Auto-generated method stub
		return Arrays.asList("192.168.1.11:4150");
	}
}
