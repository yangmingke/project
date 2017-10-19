package com.network.monitor.util.file;

public class BizException extends RuntimeException {
	private static final long serialVersionUID = 8338569733486476724L;
	private final int code;
	private final String extMsg;

	public BizException(String msg) {
		this(0, msg, null);
	}

	public BizException(int code, String msg) {
		this(code, msg, null);
	}

	public BizException(int code, String msg, String extMsg) {
		super(msg);
		this.code = code;
		this.extMsg = extMsg;
	}

	public int getCode() {
		return code;
	}

	public String getExtMsg() {
		return extMsg;
	}
}
