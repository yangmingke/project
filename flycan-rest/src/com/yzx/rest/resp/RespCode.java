package com.yzx.rest.resp;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlEnum
public enum RespCode {
	@XmlEnumValue("0")
	NODEFINE(0), @XmlEnumValue("200")
	PASS_OK(200), @XmlEnumValue("201")
	PASS_NODATA(201), @XmlEnumValue("500")
	EX_PARAM(500), @XmlEnumValue("501")
	EX_APP(501), @XmlEnumValue("502")
	EX_AUTH(502), @XmlEnumValue("600")
	EX_UNKNOWN(600);
	private int code;

	RespCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}

	public static RespCode valueOf(int code) {
		switch (code) {
		case 0:
			return NODEFINE;
		case 200:
			return PASS_OK;
		case 201:
			return PASS_NODATA;
		case 500:
			return EX_PARAM;
		case 501:
			return EX_APP;
		case 502:
			return EX_AUTH;
		case 600:
			return EX_UNKNOWN;
		default:
			return null;
		}
	}
}
