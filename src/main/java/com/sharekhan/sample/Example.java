package com.sharekhan.sample;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sharekhan.SharekhanConnect;
import com.sharekhan.Ticker.SharekhanWSOnConnect;
import com.sharekhan.Ticker.SharekhanWSOnDisconnect;
import com.sharekhan.Ticker.SharekhanWSOnError;
import com.sharekhan.Ticker.SharekhanWSOnTicks;
import com.sharekhan.Ticker.SharekhanWebsocket;
import com.sharekhan.http.exceptions.SharekhanAPIException;
import com.sharekhan.model.OrderParams;

public class Example {

	public void getLoginURL(SharekhanConnect sharekhanConnect) throws SharekhanAPIException, IOException {
		String apiKey = "Enter-apiKey";
		String version_id= null;
		String response = (sharekhanConnect).getLoginURL(apiKey,version_id);
		System.out.print(response);
	}
	
	public void generateSession(SharekhanConnect sharekhanConnect) throws SharekhanAPIException, IOException {
		String apiKey = "Enter-apiKey";
		String requestToken = "Enter-requestToken";
		Long userId = (long) 12345;
		String secretKey ="Enter-secretKey";
		Long versionId=(long) 1006;
		
		JSONObject response = sharekhanConnect.generateSession(apiKey,requestToken,userId,secretKey,versionId);
		System.out.print(response.toString(4));
		
	}
	
	public void generateSessionWithoutVersionId(SharekhanConnect sharekhanConnect) throws SharekhanAPIException, IOException {
		String apiKey = "Enter-apiKey";
		String requestToken = "Enter-requestToken";
		Long userId = (long) 12345;
		String secretKey ="Enter-secretKey";
		
		JSONObject response = sharekhanConnect.generateSessionWithoutVersionId(apiKey,requestToken,userId,secretKey);
		System.out.print(response.toString(4));
		
	}
	

	public void placeOrder(SharekhanConnect sharekhanConnect) throws SharekhanAPIException, IOException {

		OrderParams orderParams = new OrderParams();
		 orderParams.customerId = (long) 1487617;
			orderParams.scripCode = 1052;
			orderParams.disclosedQty = (long) 0;
			orderParams.validity = "GFD";
			orderParams.quantity = (long) 1;
			orderParams.exchange = "RN";
			orderParams.orderType ="NORMAL";
			orderParams.tradingSymbol = "USDINR";
			orderParams.productType = "investment";
			orderParams.transactionType = "B";
			orderParams.price = "82.0050";
			orderParams.triggerPrice = "0";
			orderParams.rmsCode= "ANY";
			orderParams.afterHour= "N";
			orderParams.channelUser="85bharatkumarmk";
			orderParams.requestType="NEW";
			orderParams.instrumentType="FUTCUR";
			orderParams.strikePrice="-1";
			orderParams.optionType="XX";
			orderParams.expiry="31/03/2023";

		JSONObject order = sharekhanConnect.placeOrder(orderParams);
		System.out.println(order.toString(4));
	
	}
	
	public void modifyOrder(SharekhanConnect sharekhanConnect) throws SharekhanAPIException, IOException {

		OrderParams orderParams = new OrderParams();
		orderParams.orderId = "259131605";
		 orderParams.customerId = (long) 1487617;
		 orderParams.scripCode = 1052;
			orderParams.disclosedQty = (long) 0;
			orderParams.validity = "GFD";
			orderParams.quantity = (long) 2;
			orderParams.exchange = "RN";
			orderParams.orderType ="NORMAL";
			orderParams.tradingSymbol = "USDINR";
			orderParams.productType = "investment";
			orderParams.transactionType = "B";
			orderParams.price = "82.1075";
			orderParams.triggerPrice = "0";
			orderParams.rmsCode= "SKNSECURR2";
			orderParams.afterHour= "N";
			orderParams.channelUser="85bharatkumarmk";
			orderParams.requestType="MODIFY";
			orderParams.instrumentType="FUTCUR";
			orderParams.strikePrice="-1";
			orderParams.optionType="XX";
			orderParams.expiry="31/03/2023";
		
		JSONObject order = sharekhanConnect.modifyorder(orderParams);
		System.out.println(order.toString(4));
		
	}
	
	public void cancelOrder(SharekhanConnect sharekhanConnect) throws SharekhanAPIException, IOException {

		OrderParams orderParams = new OrderParams();
		orderParams.orderId = "259131605";
		 orderParams.customerId = (long) 1487617;
		 orderParams.scripCode = 1052;
			orderParams.disclosedQty = (long) 0;
			orderParams.validity = "GFD";
			orderParams.quantity = (long) 2;
			orderParams.exchange = "RN";
			orderParams.orderType ="NORMAL";
			orderParams.tradingSymbol = "USDINR";
			orderParams.productType = "investment";
			orderParams.transactionType = "B";
			orderParams.price = "82.1075";
			orderParams.triggerPrice = "0";
			orderParams.rmsCode= "SKNSECURR2";
			orderParams.afterHour= "N";
			orderParams.channelUser="85bharatkumarmk";
			orderParams.requestType="CANCEL";
			orderParams.instrumentType="FUTCUR";
			orderParams.strikePrice="-1";
			orderParams.optionType="XX";
			orderParams.expiry="31/03/2023";
		
	
		JSONObject order = sharekhanConnect.cancelOrder(orderParams);
		System.out.println(order.toString(4));
		
	}
	
	public void getFunds(SharekhanConnect sharekhanConnect) throws SharekhanAPIException, IOException {
		String exchange = "MX";
		Long customerId = (long) 1487617;
		JSONObject response = sharekhanConnect.getFunds(exchange,customerId);
	}
	
    public void getOrder(SharekhanConnect sharekhanConnect) throws SharekhanAPIException, IOException {
		
		Long customerId = (long) 1487617;
		JSONObject response = sharekhanConnect.getOrder(customerId);
	}
	
	public void getPosition(SharekhanConnect sharekhanConnect) throws SharekhanAPIException, IOException {
		
		Long customerId = (long) 1487617;
		JSONObject response = sharekhanConnect.getPosition(customerId);
	}
	
	public void orderHistory(SharekhanConnect sharekhanConnect) throws SharekhanAPIException, IOException {
		String exchange = "RN";
		Long customerId = (long) 1487617;
		String orderId="259131605";
		JSONObject response = sharekhanConnect.orderHistory(exchange,customerId,orderId);
	}
	
	public void getTrades(SharekhanConnect sharekhanConnect) throws SharekhanAPIException, IOException {
		String exchange = "NC";
		Long customerId = (long) 3397270;
		String orderId="585964835";
		JSONObject response = sharekhanConnect.getTrades(exchange,customerId,orderId);
	}
	
    public void getHolding(SharekhanConnect sharekhanConnect) throws SharekhanAPIException, IOException {
		
		Long customerId = (long) 1487617;
		JSONObject response = sharekhanConnect.getHolding(customerId);
	}

    public void getActiveScript(SharekhanConnect sharekhanConnect) throws SharekhanAPIException, IOException {
	
	String exchange = "NC";
		JSONObject response = sharekhanConnect.getActiveScript(exchange);
	}
    
    public void getHistorical(SharekhanConnect sharekhanConnect) throws SharekhanAPIException, IOException {
		
    	String exchange = "MX";
    	String scripCode = "251800";
    	String interval="daily";
    		JSONObject response = sharekhanConnect.getHistorical(exchange,scripCode,interval);
    	}

   public void sharekhanWebSocketUsage(String accessToken)
	
			throws SharekhanAPIException {

		final SharekhanWebsocket sharekhanWebsocket = new SharekhanWebsocket(accessToken);
		
//		Subscribe request
		JSONObject jsonObject = new JSONObject();
		JSONArray keyArray = new JSONArray();
		JSONArray valueArray = new JSONArray();

		jsonObject.put("action", "subscribe");
		keyArray.put("feed");
		valueArray.put("");
		jsonObject.put("key", keyArray);
		jsonObject.put("value", valueArray);
		final JSONObject subscribe = jsonObject;

//		Feed Request
		JSONObject jsonObject1 = new JSONObject();
		JSONArray keyArray1 = new JSONArray();
		JSONArray valueArray1 = new JSONArray();

		jsonObject1.put("action", "feed");
		keyArray1.put("ltp");
		valueArray1.put("MX250057");
		jsonObject1.put("key", keyArray1);
		jsonObject1.put("value", valueArray1);
		
		final JSONObject feed = jsonObject1;
		
		
//		Unsubscribe request
		JSONObject jsonObject2 = new JSONObject();
		JSONArray keyArray2 = new JSONArray();
		JSONArray valueArray2 = new JSONArray();

		jsonObject2.put("action", "unsubscribe");
		keyArray2.put("feed");
		valueArray2.put("NC22,NF37833,NF37834,MX253461,RN7719");
		jsonObject2.put("key", keyArray2);
		jsonObject2.put("value", valueArray2);
		
		JSONObject unsubscribe = jsonObject2;

		sharekhanWebsocket.setOnConnectedListener(new  SharekhanWSOnConnect() {
			@Override
			public void onConnected() {
				sharekhanWebsocket.subscribe(subscribe);
				System.out.println("subscribe request sent!");
				sharekhanWebsocket.subscribe(feed);
//				System.out.println("feed request sent!");
//				smartWebsocket.unsubscribe(unsubscribe);
//				System.out.println("unsubscribe request sent!");
		
			
			}
		});
		
		sharekhanWebsocket.setOnTickerArrivalListener(new SharekhanWSOnTicks() {
		    @Override
		    public void onTicks(JSONObject ticks) {
		    
		        System.out.println("Ticker data received: " + ticks.toString(4));
		        
		    }
		});

		
		sharekhanWebsocket.setOnDisconnectedListener(new SharekhanWSOnDisconnect() {
			@Override
			public void onDisconnected() {
				System.out.println("Disconnected");
			}
		});

		/** Set error listener to listen to errors. */
		sharekhanWebsocket.setOnErrorListener(new SharekhanWSOnError() {
			@Override
			public void onError(Exception exception) {
				System.out.println("onError: " + exception.getMessage());
			}

			@Override
			public void onError(SharekhanAPIException sharekhanAPIException) {
				System.out.println("onError: " + sharekhanAPIException.getMessage());
			}

			@Override
			public void onError(String error) {
				System.out.println("onError: " + error);
			}
		});

		
		
		sharekhanWebsocket.connect();
		
		// check whether the connection is open or not
		if (sharekhanWebsocket.isConnectionOpen()) {
		    System.out.println("WebSocket connection is established!");
		} else {
		    System.out.println("WebSocket connection is not established!");
		    
		}
		
		
		
	}
}
