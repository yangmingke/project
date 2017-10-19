package com.flypaas.context;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

public class MySessionContext {
	private static MySessionContext instance = new MySessionContext();
	public static HashMap<String, Object> mymap = new HashMap<String, Object>();

	public static MySessionContext getInstance() {
		return instance;
	}

	public synchronized void AddSession(HttpSession session) {
		if (session != null) {
			mymap.put(session.getId(), session);
		}
	}

	public synchronized void DelSession(HttpSession session) {
		if (session != null) {
			mymap.remove(session.getId());
		}
	}

	public synchronized HttpSession getSession(String session_id) {
		if (session_id == null)
			return null;
		return (HttpSession) mymap.get(session_id);
	}

}
