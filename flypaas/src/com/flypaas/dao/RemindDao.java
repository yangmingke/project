package com.flypaas.dao;

import com.flypaas.entity.Remind;

public interface RemindDao {

	public void add(Remind remind);
	public int get(String sid);
	public void update(Remind remind);
	public Remind getRemind(String sid);
}
