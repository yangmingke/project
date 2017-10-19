package com.network.monitor.tcpserver;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class ServerProtocolCodecFactory implements ProtocolCodecFactory {
    
	 private ProtocolEncoder encoder;

	 private ProtocolDecoder decoder;
	 
	 public ServerProtocolCodecFactory(ProtocolEncoder encoder,ProtocolDecoder decoder){
		 this.encoder = encoder;
		 this.decoder = decoder;
	 }

	public ProtocolEncoder getEncoder() {
		return encoder;
	}

	public void setEncoder(ProtocolEncoder encoder) {
		this.encoder = encoder;
	}

	public ProtocolDecoder getDecoder() {
		return decoder;
	}

	public void setDecoder(ProtocolDecoder decoder) {
		this.decoder = decoder;
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return this.decoder;
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return this.encoder;
	}
	 
}
