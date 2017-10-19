package com.flypaas.entity;
import org.apache.ibatis.type.Alias;

@Alias(value="TariffChEn")
public class TariffChEn {
	private String prefix ; 
	private String en_name;
	private String ch_name;
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getEn_name() {
		return en_name;
	}
	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}
	public String getCh_name() {
		return ch_name;
	}
	public void setCh_name(String ch_name) {
		this.ch_name = ch_name;
	}
	
}
