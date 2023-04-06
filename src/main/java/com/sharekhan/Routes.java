package com.sharekhan;

import java.util.HashMap;
import java.util.Map;

public class Routes {
	
	public Map<String,String> routes;
	private static String _rootUrl = "https://api.sharekhan.com/skapi/services";
	private static String _loginUrl = "https://api.sharekhan.com/skapi/auth/login.html?";
	private static String _swsurl = "wss://stream.sharekhan.com/skstream/api/stream?" ;
	
	@SuppressWarnings("serial")
	public Routes() {
		routes = new HashMap<String, String>(){
			{
				
			put("api.token","/access/token");
			put("api.order.fund","/limitstmt");
			put("api.order.place","/orders");
			put("api.order.modify","/orders");
			put("api.order.cancel","/orders");
//			put("api.order.cancelId","/cancelOrder");
			put("api.order.report","/reports");
			put("api.order.position","/trades");
			put("api.order.history","/reports");
			put("api.order.trade","/reports");
			put("api.order.holding","/holdings");
			put("api.order.master","/master");
//			put("api.order.symbol","/master/csv");
			put("api.order.historical","/historical");
			}
		};
	}

	public String get(String key) {
		return _rootUrl + routes.get(key);
	}
	
	public String getLoginUrl() {
		return _loginUrl;
	}

	public String getSWsurl() {
		return _swsurl;
	}
}
