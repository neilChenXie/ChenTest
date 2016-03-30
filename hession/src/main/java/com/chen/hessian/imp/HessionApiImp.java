package com.chen.hessian.imp;

import com.caucho.hessian.server.HessianServlet;
import com.chen.hessian.api.HessianApi;

public class HessionApiImp extends HessianServlet implements HessianApi {
	
	@Override
	public String say() {
		return "hesson: hello world";
	}
}
