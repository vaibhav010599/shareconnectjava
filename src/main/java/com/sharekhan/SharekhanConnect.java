package com.sharekhan;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONObject;

import com.sharekhan.http.SharekhanAPIRequestHandler;
import com.sharekhan.http.exceptions.SharekhanAPIException;
import com.sharekhan.model.OrderParams;

public class SharekhanConnect {
	
	public static boolean ENABLE_LOGGING = false;
	
	private String apiKey;
	private String accessToken;
	public String secretKey;
	
	private SharekhanAPIRequestHandler sharekhanAPIRequestHandler = new SharekhanAPIRequestHandler(null);
	
private Routes routes = new Routes();
	
	public SharekhanConnect() {
	}
	
	
	public SharekhanConnect(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
	public SharekhanConnect(String apiKey,String accessToken) {
		this.apiKey = apiKey;
		this.accessToken = accessToken;
		
		
	}
	
	
	@SuppressWarnings("unused")
	public String getLoginURL(String apiKey,String version_id) throws NullPointerException {
		
		String baseUrl = routes.getLoginUrl() + "api_key="+ apiKey ;

		baseUrl += "&user_id=12345";
		if(version_id!=null) {
			baseUrl += "&version_id=" + version_id;
			
			return baseUrl;
		}else {
			System.out.println("no version id");
		}
			
			return baseUrl;
		
	}


	private static final String CHARSET_NAME = "UTF-8";
	public String encodeToken(String key, String token) throws GeneralSecurityException {
		byte[] rawKey = key.getBytes(Charset.forName(CHARSET_NAME));
		if (rawKey.length != 32) {
			throw new IllegalArgumentException("Invalid key size."); 
			} 
		SecretKeySpec keySpec = new SecretKeySpec(rawKey, "AES");
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, new GCMParameterSpec(128, new byte[16]));
		byte[] encrypted = cipher.doFinal(token.getBytes(Charset.forName(CHARSET_NAME)));
		String encoded = Base64.getUrlEncoder().withoutPadding().encodeToString(encrypted);
		try { 
			return URLEncoder.encode(encoded, CHARSET_NAME);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block 
				e.printStackTrace();
				} return encoded; 
				} 
	public String decodeToken(String key, String encodedToken) throws GeneralSecurityException {
	    String decoded = null;
	    try {
	        decoded = URLDecoder.decode(encodedToken, CHARSET_NAME);
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    } 

	    byte[] rawKey = key.getBytes(Charset.forName(CHARSET_NAME));
	    if (rawKey.length != 32) {
	        throw new IllegalArgumentException("Invalid key size.");
	    }

	    SecretKeySpec keySpec = new SecretKeySpec(rawKey, "AES");
	    Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
	    cipher.init(Cipher.DECRYPT_MODE, keySpec, new GCMParameterSpec(128, new byte[16]));

	    byte[] encrypted = null;
	    try {
	        encrypted = Base64.getUrlDecoder().decode(decoded);
	    } catch (IllegalArgumentException ex1) {
	        try {
	            encrypted = Base64.getDecoder().decode(decoded);
	        } catch (IllegalArgumentException ex2) {
	            throw new IllegalArgumentException("Invalid Base64 encoding.");
	        }
	    }

	    byte[] decrypted = cipher.doFinal(encrypted);
	    return new String(decrypted, Charset.forName(CHARSET_NAME)); 
	}
		
	public JSONObject generateSession( String apiKey,String request_Token, Long user_id,String secretKey,Long version_id) {
		try {
			
			String url = routes.get("api.token");
			System.out.println(url);
		
			String token= request_Token;
			String key=secretKey;
			String decData=decodeToken(key,token);
			String[] result = decData.split("\\|");
//		    for(String s : result){System.out.println(s);} 
		    String manipulatedcode = result[1] +"|"+ result[0];
		    String encData= encodeToken(key,manipulatedcode);
		   
		    String requestToken=encData;
		    System.out.println(requestToken);
		
			JSONObject params = new JSONObject();
			
			params.put("apiKey", apiKey);
			
			params.put("requestToken", requestToken);
			
			params.put("userId", user_id);
			
			params.put("versionId", version_id);
			
			
			
			JSONObject response = sharekhanAPIRequestHandler.postRequest(url,params);
			return response ;
		} catch (Exception | SharekhanAPIException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}
	
//	ENCRYPTION/DECRYPTION WITHOUT VERSION ID
	public  String decryptionMethod(String key, String encryptedData) throws GeneralSecurityException {
		byte[] raw = key.getBytes(Charset.forName("UTF-8"));
//		byte[] original = null;
		if (raw.length != 32) {
			throw new IllegalArgumentException("Invalid key size.");
		}
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		 Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		 cipher.init(Cipher.DECRYPT_MODE, skeySpec, new
		GCMParameterSpec(128, new byte[16]));
		 byte[] original = 
		cipher.doFinal(Base64.getDecoder().decode(encryptedData));
		 return new String(original, Charset.forName("UTF-8"));
	}

	public  String encryptionMethod(String key, String nonEncryptedData) throws GeneralSecurityException {
		byte[] raw = key.getBytes(Charset.forName("UTF-8"));
		
		if (raw.length != 32) {
			throw new IllegalArgumentException("Invalid key size.");
		}
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		 Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		 cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new
		GCMParameterSpec(128, new byte[16]));
		 return
		Base64.getEncoder().encodeToString(cipher.doFinal(nonEncryptedData.getBytes(Charset.forName("UTF-8"))));
		 }
	
	public JSONObject generateSessionWithoutVersionId( String apiKey,String request_Token, Long user_id,String secretKey) {
		try {
			
			String url = routes.get("api.token");
			System.out.println(url);
		
			String token= request_Token;
			String key=secretKey;
			String decData=decryptionMethod(key,token);
			String[] result = decData.split("\\|");
//		    for(String s : result){System.out.println(s);} 
		    String manipulatedcode = result[1] +"|"+ result[0];
		    String encData= encryptionMethod(key,manipulatedcode);
		   
		    String requestToken=encData;
//		    System.out.println(requestToken);
		
			JSONObject params = new JSONObject();
			
			params.put("apiKey", apiKey);
			
			params.put("requestToken", requestToken);
			
			params.put("userId", user_id);
			
			JSONObject response = sharekhanAPIRequestHandler.postRequest(url,params);
			return response ;
		} catch (Exception | SharekhanAPIException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}
	
	public JSONObject placeOrder(OrderParams orderParams) {
		try {
			
			String url = routes.get("api.order.place");
			System.out.println(url);
			
			JSONObject params = new JSONObject();
			
			if(orderParams.customerId!=null)
				params.put("customerId", orderParams.customerId);
			
			if(orderParams.scripCode!=null)
				params.put("scripCode", orderParams.scripCode);
			
			if(orderParams.tradingSymbol!=null)
				params.put("tradingSymbol", orderParams.tradingSymbol);
			
			if(orderParams.exchange!=null)
				params.put("exchange", orderParams.exchange);
			
			if(orderParams.transactionType!=null)
				params.put("transactionType", orderParams.transactionType);
			
			if(orderParams.quantity!=null)
				params.put("quantity", orderParams.quantity);
			
			if(orderParams.disclosedQty!=null)
				params.put("disclosedQty", orderParams.disclosedQty);
			
			if(orderParams.price!=null)
				params.put("price", orderParams.price);
			
			if(orderParams.triggerPrice!=null)
				params.put("triggerPrice", orderParams.triggerPrice);
			
			if(orderParams.rmsCode!=null)
				params.put("rmsCode", orderParams.rmsCode);
			
			if(orderParams.afterHour!=null)
				params.put("afterHour", orderParams.afterHour);
			
			if(orderParams.orderType!=null)
				params.put("orderType", orderParams.orderType);
			
			if(orderParams.channelUser!=null)
				params.put("channelUser", orderParams.channelUser);
			
			if(orderParams.validity!=null)
				params.put("validity", orderParams.validity);
			
			if(orderParams.gtdd!=null)
				params.put("gtdd", orderParams.gtdd);
			
			if(orderParams.requestType!=null)
				params.put("requestType", orderParams.requestType);
			
			if(orderParams.productType!=null)
				params.put("productType", orderParams.productType);
			
			if(orderParams.instrumentType!=null)
				params.put("instrumentType", orderParams.instrumentType);
			
			if(orderParams.strikePrice!=null)
				params.put("strikePrice", orderParams.strikePrice);
			
			if(orderParams.optionType!=null)
				params.put("optionType", orderParams.optionType);
			
			if(orderParams.expiry!=null)
				params.put("expiry", orderParams.expiry);
			JSONObject jsonObject = null;
			
				jsonObject = sharekhanAPIRequestHandler.postRequest(this.apiKey,url, params, this.accessToken);
				return jsonObject;
			
		} catch (Exception | SharekhanAPIException e) {
			System.out.println(e.getMessage());
			return null;
		
			
		
	}
		
	}

	public JSONObject modifyorder(OrderParams orderParams) {
		try {
			String url = routes.get("api.order.modify");

			JSONObject params = new JSONObject();
			
			if (orderParams.orderId != null)
				params.put("orderId", orderParams.orderId);
			
			if(orderParams.customerId!=null)
				params.put("customerId", orderParams.customerId);
			
			if(orderParams.scripCode!=null)
				params.put("scripCode", orderParams.scripCode);
			
			if(orderParams.tradingSymbol!=null)
				params.put("tradingSymbol", orderParams.tradingSymbol);
			
			if(orderParams.exchange!=null)
				params.put("exchange", orderParams.exchange);
			
			if(orderParams.transactionType!=null)
				params.put("transactionType", orderParams.transactionType);
			
			if(orderParams.quantity!=null)
				params.put("quantity", orderParams.quantity);
			
			if(orderParams.disclosedQty!=null)
				params.put("disclosedQty", orderParams.disclosedQty);
			
			if(orderParams.executedQty!=null)
				params.put("executedQty", orderParams.executedQty);
			
			if(orderParams.price!=null)
				params.put("price", orderParams.price);
			
			if(orderParams.triggerPrice!=null)
				params.put("triggerPrice", orderParams.triggerPrice);
			
			if(orderParams.rmsCode!=null)
				params.put("rmsCode", orderParams.rmsCode);
			
			if(orderParams.afterHour!=null)
				params.put("afterHour", orderParams.afterHour);
			
			if(orderParams.orderType!=null)
				params.put("orderType", orderParams.orderType);
			
			if(orderParams.channelUser!=null)
				params.put("channelUser", orderParams.channelUser);
			
			if(orderParams.validity!=null)
				params.put("validity", orderParams.validity);
			
			if(orderParams.requestType!=null)
				params.put("requestType", orderParams.requestType);
			
			if(orderParams.productType!=null)
				params.put("productType", orderParams.productType);
			
			if(orderParams.instrumentType!=null)
				params.put("instrumentType", orderParams.instrumentType);
			
			if(orderParams.strikePrice!=null)
				params.put("strikePrice", orderParams.strikePrice);
			
			if(orderParams.optionType!=null)
				params.put("optionType", orderParams.optionType);
			
			if(orderParams.expiry!=null)
				params.put("expiry", orderParams.expiry);
			

			JSONObject jsonObject = sharekhanAPIRequestHandler.postRequest(this.apiKey, url, params, this.accessToken);
			return jsonObject;
		} catch (Exception | SharekhanAPIException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public JSONObject cancelOrder(OrderParams orderParams) {
		try {
			String url = routes.get("api.order.cancel");
			JSONObject params = new JSONObject();
			if (orderParams.orderId != null)
				params.put("orderId", orderParams.orderId);
			
			if(orderParams.customerId!=null)
				params.put("customerId", orderParams.customerId);
			
			if(orderParams.scripCode!=null)
				params.put("scripCode", orderParams.scripCode);
			
			if(orderParams.tradingSymbol!=null)
				params.put("tradingSymbol", orderParams.tradingSymbol);
			
			if(orderParams.exchange!=null)
				params.put("exchange", orderParams.exchange);
			
			if(orderParams.transactionType!=null)
				params.put("transactionType", orderParams.transactionType);
			
			if(orderParams.quantity!=null)
				params.put("quantity", orderParams.quantity);
			
			if(orderParams.disclosedQty!=null)
				params.put("disclosedQty", orderParams.disclosedQty);
			
			if(orderParams.executedQty!=null)
				params.put("executedQty", orderParams.executedQty);
			
			if(orderParams.price!=null)
				params.put("price", orderParams.price);
			
			if(orderParams.triggerPrice!=null)
				params.put("triggerPrice", orderParams.triggerPrice);
			
			if(orderParams.rmsCode!=null)
				params.put("rmsCode", orderParams.rmsCode);
			
			if(orderParams.afterHour!=null)
				params.put("afterHour", orderParams.afterHour);
			
			if(orderParams.orderType!=null)
				params.put("orderType", orderParams.orderType);
			
			if(orderParams.channelUser!=null)
				params.put("channelUser", orderParams.channelUser);
			
			if(orderParams.validity!=null)
				params.put("validity", orderParams.validity);
			
			if(orderParams.requestType!=null)
				params.put("requestType", orderParams.requestType);
			
			if(orderParams.productType!=null)
				params.put("productType", orderParams.productType);
			if(orderParams.instrumentType!=null)
				params.put("instrumentType", orderParams.instrumentType);
			
			if(orderParams.strikePrice!=null)
				params.put("strikePrice", orderParams.strikePrice);
			
			if(orderParams.optionType!=null)
				params.put("optionType", orderParams.optionType);
			
			if(orderParams.expiry!=null)
				params.put("expiry", orderParams.expiry);

			

			JSONObject jsonObject = sharekhanAPIRequestHandler.postRequest(this.apiKey, url, params, this.accessToken);
			return jsonObject;
		} catch (Exception | SharekhanAPIException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

//	API retrieves all orders for the day.
	public JSONObject getOrder(Long customerId) {
		try {
			String url = routes.get("api.order.report")+ "/" + customerId;
			System.out.println(url);
			JSONObject response = sharekhanAPIRequestHandler.getRequest(this.apiKey, url,this.accessToken);
			System.out.println(response.toString(4));
			
			return response;
		} catch (Exception | SharekhanAPIException e) {
			System.out.println("Exception#: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
//	This API retrieves all positions open for the day.
	public JSONObject getPosition(Long customerId) {
		try {
			String url = routes.get("api.order.position")+ "/" + customerId;
			System.out.println(url);
	JSONObject response = sharekhanAPIRequestHandler.getRequest(this.apiKey, url, this.accessToken);
	System.out.println(response.toString(4));
			return response;
		} catch (Exception | SharekhanAPIException e) {
			System.out.println("Exception#: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	

//	The API can retrieve an order's history.
	public JSONObject orderHistory(String exchange,Long customerId,String orderId ) {
		try {
			String url = routes.get("api.order.history")+ "/" + exchange + "/" +customerId+ "/" +orderId;
			System.out.println(url);
			JSONObject response = sharekhanAPIRequestHandler.getRequest(this.apiKey, url, this.accessToken);
			System.out.println(response.toString(4));
			return response;
		} catch (Exception | SharekhanAPIException e) {
			System.out.println("Exception#: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
//	This API retrieve trade of an order
	public JSONObject getTrades(String exchange,Long customerId,String orderId) {
		try {
			String url = routes.get("api.order.trade")+ "/" + exchange + "/" +customerId+ "/" +orderId + "/trades";
			System.out.println(url);
			JSONObject response = sharekhanAPIRequestHandler.getRequest(this.apiKey, url, this.accessToken);
			System.out.println(response.toString(4));
			return response;
		} catch (Exception | SharekhanAPIException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public JSONObject getHolding(Long customerId) {
		try {
			String url = routes.get("api.order.holding") + "/" +customerId;
			System.out.println(url);
			JSONObject response = sharekhanAPIRequestHandler.getRequest( this.apiKey,url, this.accessToken);
			System.out.println(response.toString(4));
			return response;
		} catch (Exception | SharekhanAPIException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
//	This API provides only active scripts for the day.
	public JSONObject getActiveScript(String exchange) {
		try {
			String url = routes.get("api.order.master")+ "/" +exchange;
			System.out.println(url);
			JSONObject response = sharekhanAPIRequestHandler.getRequest(this.apiKey, url, this.accessToken);
			System.out.println(response.toString(4));
			return response;
		} catch (Exception | SharekhanAPIException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public JSONObject getHistorical(String exchange,String scripcode,String interval) {
		try {
			String url = routes.get("api.order.historical")+ "/" +exchange + "/" +scripcode + "/" +interval;
			System.out.println(url);
			JSONObject response = sharekhanAPIRequestHandler.getRequest( this.apiKey,url, this.accessToken);
			System.out.println(response.toString(4));
			return response;
		} catch (Exception | SharekhanAPIException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public JSONObject getFunds(String exchange,Long customerId) {
		try {
			String url = routes.get("api.order.fund")+ "/" +exchange+ "/" +customerId;
			System.out.println(url);
			JSONObject response = sharekhanAPIRequestHandler.getRequest(this.apiKey, url, this.accessToken);
			System.out.println(response.toString(4));
			return response;
		} catch (Exception | SharekhanAPIException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
