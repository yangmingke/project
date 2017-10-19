package com.flypaas.model;

public class TbRsIsp {
    private Integer id;

    private String isp;

    private Integer ispid;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp == null ? null : isp.trim();
    }

    public Integer getIspid() {
        return ispid;
    }

    public void setIspid(Integer ispid) {
        this.ispid = ispid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}