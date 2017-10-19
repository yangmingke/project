package com.flypaas.model;

public class RouteAnalysis {
	private String srcIp;
	private String destIp;
	private String nbrQuality;
	private String weightQuality;
	private String bestPath;
	public String getNbrQuality() {
		return nbrQuality;
	}
	public void setNbrQuality(String nbrQuality) {
		this.nbrQuality = nbrQuality;
	}
	public String getWeightQuality() {
		return weightQuality;
	}
	public void setWeightQuality(String weightQuality) {
		this.weightQuality = weightQuality;
	}
	public String getBestPath() {
		return bestPath;
	}
	public void setBestPath(String bestPath) {
		this.bestPath = bestPath;
	}
	public String getDestIp() {
		return destIp;
	}
	public void setDestIp(String destIp) {
		this.destIp = destIp;
	}
	public String getSrcIp() {
		return srcIp;
	}
	public void setSrcIp(String srcIp) {
		this.srcIp = srcIp;
	}
}
