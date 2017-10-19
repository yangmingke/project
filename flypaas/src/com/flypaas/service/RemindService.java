package com.flypaas.service;

import com.flypaas.entity.Remind;

public interface RemindService {
	public void add(Remind remind);
	public int get(String sid);
	public Remind getRemind(String sid);
	public void update(Remind remind);
}
