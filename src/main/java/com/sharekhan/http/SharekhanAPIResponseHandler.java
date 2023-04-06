package com.sharekhan.http;

import java.io.IOException;

import org.json.JSONException;

import org.json.JSONObject;

import com.sharekhan.http.exceptions.DataException;
import com.sharekhan.http.exceptions.GeneralException;
import com.sharekhan.http.exceptions.InputException;
import com.sharekhan.http.exceptions.NetworkException;
import com.sharekhan.http.exceptions.OrderException;
import com.sharekhan.http.exceptions.PermissionException;
import com.sharekhan.http.exceptions.SharekhanAPIException;
import com.sharekhan.http.exceptions.TokenException;

import okhttp3.Response;
public class SharekhanAPIResponseHandler {

	
	public JSONObject handle(Response response, String body) throws IOException, SharekhanAPIException, JSONException {
//		System.out.println("***************************");
//		System.out.println(response );
//		System.out.println(body );
		if (response.header("Content-Type").contains("json")) {
			JSONObject jsonObject = new JSONObject(body);


			if (!(jsonObject.has("status") || jsonObject.has("success"))) {
				if (jsonObject.has("errorcode")) {
					throw dealWithException(jsonObject, jsonObject.getString("errorcode"));
				}
			}
			// System.out.println(jsonObject);
			return jsonObject;
		}
		else if (response.header("Content-Type").contains("text/csv")) {

			System.out.println("body: "+body);
	        JSONObject jsonObject = new JSONObject(body);
	        return jsonObject;
//	        throw new DataException("Unexpected content type received from server: " + response.header("Content-Type")
//	            + " " + responseBody, "AG8001");
	        
	    } else {
			throw new DataException("Unexpected content type received from server: " + response.header("Content-Type")
					+ " " + response.body().string(), "AG8001");
		}
	}

	private SharekhanAPIException dealWithException(JSONObject jsonObject, String code) throws JSONException {

		switch (code) {
		// if there is a token exception, generate a signal to logout the user.
		case "AG8003":
		case "AB8050":
		case "AB8051":
		case "AB1010":
		
			return new TokenException(jsonObject.getString("message"), code);

		case "AG8001":
		case "AG8002":
			return new DataException(jsonObject.getString("message"), code);

		case "AB1004":
		case "AB2000":
			return new GeneralException(jsonObject.getString("message"), code);

		case "AB1003":
		case "AB1005":
		case "AB1012":
		case "AB1002":
			return new InputException(jsonObject.getString("message"), code);

		case "AB1008":
		case "AB1009":
		case "AB1013":
		case "AB1014":
		case "AB1015":
		case "AB1016":
		case "AB1017":
			return new OrderException(jsonObject.getString("message"), code);

		case "NetworkException":
			return new NetworkException(jsonObject.getString("message"), code);

		case "AB1000":
		case "AB1001":
		case "AB1011":
			return new PermissionException(jsonObject.getString("message"), code);

		default:
			return new SharekhanAPIException(jsonObject.getString("data not found"));
		}
	}

}
