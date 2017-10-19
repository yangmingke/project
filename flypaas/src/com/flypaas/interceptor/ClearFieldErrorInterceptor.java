package com.flypaas.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ClearFieldErrorInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = -7333578389173172936L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionSupport actionSuport = (ActionSupport) invocation.getAction();
		actionSuport.clearErrorsAndMessages();
		String resultCode = invocation.invoke();
		return resultCode;
	}

}
