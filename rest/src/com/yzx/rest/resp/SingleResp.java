package com.yzx.rest.resp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
// @XmlSeeAlso(value={Teacher.class})
public class SingleResp extends BaseResp {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2541648368034605238L;

	/*@XmlElement
	private RealObject data;

	public RealObject getData() {
		return data;
	}

	public void setData(RealObject data) {
		this.data = data;
	}*/
}
