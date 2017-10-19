package com.flypaas.dao;

import java.util.List;

import com.flypaas.entity.Ring;

public interface RingDao {
	
	public List<Ring> getRingListBySid(Ring ring);
	
	public void add(Ring ring);
	
	public Ring get(Ring ring);
	
	public Ring getRingByIdSid(Ring ring);
	
	public void update(Ring ring);
	
	public void delete(Ring ring);
}
