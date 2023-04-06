package com.sharekhan.sample;

import com.sharekhan.SharekhanConnect;
import com.sharekhan.http.exceptions.SharekhanAPIException;

public class Test {

	public static void main(String[] args) throws SharekhanAPIException {
		
		String apiKey = "enter-your-api-key";
		String accessToken = "enter-your-accesstoken";
		
		try {
			SharekhanConnect sharekhanConnect = new SharekhanConnect(apiKey,accessToken);
			
			Example examples = new Example();
			
			examples.getLoginURL(sharekhanConnect);
//			examples.generateSession(sharekhanConnect);
//			examples.generateSessionWithoutVersionId(sharekhanConnect);
//			examples.placeOrder(sharekhanConnect);
//			examples.modifyOrder(sharekhanConnect);
//			examples.cancelOrder(sharekhanConnect);
//			examples.getFunds(sharekhanConnect);
//			examples.getOrder(sharekhanConnect);
//			examples.getPosition(sharekhanConnect);           
//			examples.orderHistory(sharekhanConnect);
//			examples.getTrades(sharekhanConnect);            
//			examples.getHolding(sharekhanConnect);
//			examples.getActiveScript(sharekhanConnect);
//			examples.getHistorical(sharekhanConnect);


			
			
//			examples.sharekhanWebSocketUsage(accessToken);
			
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
}

