package com.flypaas.model;
public class TbRsConfig{
    private String tKey;
    private String oldKey;
    private Long id;
    private String tValue;
    private String tNote;

    public String gettKey() {
        return tKey;
    }

    public void settKey(String tKey) {
        this.tKey = tKey == null ? null : tKey.trim();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String gettNote() {
		return tNote;
	}

	public void settNote(String tNote) {
		this.tNote = tNote;
	}

	public String gettValue() {
		return tValue;
	}

	public void settValue(String tValue) {
		this.tValue = tValue;
	}

	public String getOldKey() {
		return oldKey;
	}

	public void setOldKey(String oldKey) {
		this.oldKey = oldKey;
	}
}