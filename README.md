//  Instantiate the SmartConnect class 
	
	SmartConnect smartConnect = new SmartConnect();
	
//	Instantiate the Example class to perform the testing for all the methods.
	
	Example examples = new Example();
	
//	getLoginUrl --> this will provide you with the login url which can be used to login in the espresso account

	public void getLoginURL(SmartConnect smartConnect) throws SmartAPIException, IOException {
		String apiKey = "Da8WmWZsE9VjVZJPH0cK1oVMZotE70O7";
		String version_id= "1006"; (optional)
		String response = smartConnect.getLoginURL(apiKey);
		System.out.print(response);
	}
	

	
//	GenerateSession--> provide the requestToken received after successful login with apiKey, state and secret key
    This will provide the accessToken after the decrypt and encrypt part.
	
	public void generateSession(SmartConnect smartConnect) throws SmartAPIException, IOException {
		String apiKey = "Da8WmWZsE9VjVZJPH0cK1oVMZotE70O7";
		String requestToken = "eQuCj7mnO0hQXCQIGcf3wFBXxDujoj8l6WJiXn2N0I617aLgPBPFa01ITQG8Ej_mhCTra0kafO9c";
		Long userId = (long) 12345;
		Long versionId=(long) 1006;
		String secretKey ="Qev3Aeidb8kRJYeB3MNhQrIBK8l2KWeW";
		
		JSONObject response = smartConnect.generateSession(apiKey,requestToken,userId,secretKey,versionId);
		System.out.print(response.toString(4));
		
	}
	
//    GenerateSession without versionId--> provide the requestToken received after successful login with apiKey, state and secret key
    This will provide the accessToken after the decrypt and encrypt part.
	
	public void generateSession(SmartConnect smartConnect) throws SmartAPIException, IOException {
		String apiKey = "Da8WmWZsE9VjVZJPH0cK1oVMZotE70O7";
		String requestToken = "eQuCj7mnO0hQXCQIGcf3wFBXxDujoj8l6WJiXn2N0I617aLgPBPFa01ITQG8Ej_mhCTra0kafO9c";
		Long userId = (long) 12345;
		String secretKey ="Qev3Aeidb8kRJYeB3MNhQrIBK8l2KWeW";
		
		JSONObject response = smartConnect.generateSession(apiKey,requestToken,userId,secretKey);
		System.out.print(response.toString(4));
		
	}
	
//	add apiKey n accessToken in the smartConnect constructor
	
	SmartConnect smartConnect = new SmartConnect("<api-key>","<access-token>");
	
//	Place Order
	
	public void placeOrder(SmartConnect smartConnect) throws SmartAPIException, IOException {
			OrderParams orderParams = new OrderParams();
		    orderParams.customerId = (long) 20212189;
			orderParams.scripCode = 2475;
			orderParams.disclosedQty = (long) 0;
			orderParams.validity = "GFD";
			orderParams.quantity = (long) 1;
			orderParams.symbolToken = "1660";
			orderParams.exchange = "NC";
			orderParams.orderType ="NORMAL";
			orderParams.tradingSymbol = "ONGC";
			orderParams.productType = "CNF";
			orderParams.transactionType = "B";
			orderParams.price = "139.85";
			orderParams.triggerPrice = "0";
			orderParams.rmsCode= "ANY";
			orderParams.afterHour= "N";
			orderParams.channelUser="20212189";
			orderParams.productType="CNC";
			orderParams.requestType="NEW";
			orderParams.instrumentType="FUTCUR";
			orderParams.strikePrice="-1";
			orderParams.optionType="XX";
			orderParams.expiry="31/03/2023";

		JSONObject order = smartConnect.placeOrder(orderParams);
	
	}
	
//	Modify Order
	
	public void modifyOrder(SmartConnect smartConnect) throws SmartAPIException, IOException {

		OrderParams orderParams = new OrderParams();
		orderParams.orderId = "3475641";
		orderParams.customerId=(long) 20212189;
		orderParams.scripCode=2475;
		orderParams.tradingSymbol = "ONGC";
		orderParams.exchange = "NC";
		orderParams.transactionType="B";
		orderParams.quantity = (long) 1;
		orderParams.disclosedQty=(long) 0;
		orderParams.executedQty=(long) 0;
		orderParams.price = "156";
		orderParams.triggerPrice="0";
		orderParams.rmsCode="SKNSE1";
		orderParams.afterHour="N";
		orderParams.orderType = "NORMAL";
		orderParams.channelUser="20212189";
		orderParams.validity="GFD";
		orderParams.requestType="MODIFY";
		orderParams.productType = "CNC";
		orderParams.instrumentType="FUTCUR";
			orderParams.strikePrice="-1";
			orderParams.optionType="XX";
			orderParams.expiry="31/03/2023";
	
		
		JSONObject order = smartConnect.modifyorder(orderParams);
		
	}
	
//	Cancel order
	
	public void cancelOrder(SmartConnect smartConnect) throws SmartAPIException, IOException {

		OrderParams orderParams = new OrderParams();
		orderParams.orderId = "3475641";
		orderParams.customerId=(long) 20212189;
		orderParams.scripCode=2475;
		orderParams.tradingSymbol = "ONGC";
		orderParams.exchange = "NC";
		orderParams.transactionType="B";
		orderParams.quantity = (long) 1;
		orderParams.disclosedQty=(long) 0;
		orderParams.executedQty=(long) 0;
		orderParams.price = "156";
		orderParams.triggerPrice="0";
		orderParams.rmsCode="SKNSE1";
		orderParams.afterHour="N";
		orderParams.orderType = "NORMAL";
		orderParams.channelUser="20212189";
		orderParams.validity="GFD";
		orderParams.requestType="CANCEL";
		orderParams.productType = "CNC";
		orderParams.instrumentType="FUTCUR";
			orderParams.strikePrice="-1";
			orderParams.optionType="XX";
			orderParams.expiry="31/03/2023";
	
		JSONObject order = smartConnect.cancelOrder(orderParams);
		
	}
	
//	Funds --> limit_statement
	
	public void getFunds(SmartConnect smartConnect) throws SmartAPIException, IOException {
		String exchange = "NC";
		Long customerId = (long) 20212189;
		JSONObject response = smartConnect.getFunds(exchange,customerId);
	}
	
//	Order --> orders_history
	
	public void getOrder(SmartConnect smartConnect) throws SmartAPIException, IOException {
		
		Long customerId = (long) 20212189;
		JSONObject response = smartConnect.getOrder(customerId);
	}
	
//	Position --> trades_history
	
	public void getPosition(SmartConnect smartConnect) throws SmartAPIException, IOException {
		
		Long customerId = (long) 20212189;
		JSONObject response = smartConnect.getPosition(customerId);
	}
	
//	Order History -->order_history
	
	public void orderHistory(SmartConnect smartConnect) throws SmartAPIException, IOException {
		String exchange = "NC";
		Long customerId = (long) 20212189;
		String orderId="3475641";
		JSONObject response = smartConnect.orderHistory(exchange,customerId,orderId);
	}
	
//	Trade --> trade_history
	
	public void getTrades(SmartConnect smartConnect) throws SmartAPIException, IOException {
		String exchange = "NC";
		Long customerId = (long) 20212189;
		String orderId="3475641";
		JSONObject response = smartConnect.getTrades(exchange,customerId,orderId);
	}
	
//	Holdings --> holdings
	
	public void getHolding(SmartConnect smartConnect) throws SmartAPIException, IOException {
		
		Long customerId = (long) 20212189;
		JSONObject response = smartConnect.getHolding(customerId);
	}
	
//	Active Scripts --> master
	 
	 public void getActiveScript(SmartConnect smartConnect) throws SmartAPIException, IOException {
		
    	String exchange = "NC";
    		JSONObject response = smartConnect.getActiveScript(exchange);
    	}
    	

	
//	Historical
	
	public void getHistorical(SmartConnect smartConnect) throws SmartAPIException, IOException {
		
    	String exchange = "MX";
    	String scripCode = "251800";
    	String interval="daily";
    		JSONObject response = smartConnect.getHistorical(exchange,scripCode,interval);
    	}

//   WEBSOCKET

        public void smartWebSocketUsage(String accessToken)
	
			throws SmartAPIException {

		final SmartWebsocket smartWebsocket = new SmartWebsocket(accessToken);
		
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

		smartWebsocket.setOnConnectedListener(new  SmartWSOnConnect() {
			@Override
			public void onConnected() {
				smartWebsocket.subscribe(subscribe);
				System.out.println("subscribe request sent!");
				smartWebsocket.subscribe(feed);
//				System.out.println("feed request sent!");
//				smartWebsocket.unsubscribe(unsubscribe);
//				System.out.println("unsubscribe request sent!");
		
			
			}
		});
		
		smartWebsocket.setOnTickerArrivalListener(new SmartWSOnTicks() {
		    @Override
		    public void onTicks(JSONObject ticks) {
		    
		        System.out.println("Ticker data received: " + ticks.toString(4));
		        
		    }
		});

		
		smartWebsocket.setOnDisconnectedListener(new SmartWSOnDisconnect() {
			@Override
			public void onDisconnected() {
				System.out.println("Disconnected");
			}
		});

		/** Set error listener to listen to errors. */
		smartWebsocket.setOnErrorListener(new SmartWSOnError() {
			@Override
			public void onError(Exception exception) {
				System.out.println("onError: " + exception.getMessage());
			}

			@Override
			public void onError(SmartAPIException smartAPIException) {
				System.out.println("onError: " + smartAPIException.getMessage());
			}

			@Override
			public void onError(String error) {
				System.out.println("onError: " + error);
			}
		});

		
		
		smartWebsocket.connect();
		
		// check whether the connection is open or not
		if (smartWebsocket.isConnectionOpen()) {
		    System.out.println("WebSocket connection is established!");
		} else {
		    System.out.println("WebSocket connection is not established!");
		    
		}
		
		
		
	}

	
	
	
	