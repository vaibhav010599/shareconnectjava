package com.sharekhan.http;


import java.io.IOException;

import java.net.Proxy;

import java.util.concurrent.TimeUnit;

import org.json.JSONException;

import org.json.JSONObject;

import com.sharekhan.SharekhanConnect;
import com.sharekhan.http.exceptions.SharekhanAPIException;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class SharekhanAPIRequestHandler {
	
	private OkHttpClient client;

	
	public SharekhanAPIRequestHandler(Proxy proxy) {
		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		builder.connectTimeout(10000, TimeUnit.MILLISECONDS);
		if (proxy != null) {
			builder.proxy(proxy);
		}

		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
		logging.setLevel(HttpLoggingInterceptor.Level.BODY);
		if (SharekhanConnect.ENABLE_LOGGING) {
			client = builder.addInterceptor(logging).build();
		} else {
			client = builder.build();
		}
	}
	
	public JSONObject postRequest(String url, JSONObject params)
			throws IOException, JSONException, SharekhanAPIException {
		Request request = createPostRequest( url, params);
		Response response = client.newCall(request).execute();
		String body = response.body().string();
		return new SharekhanAPIResponseHandler().handle(response, body);

	}
	
//	To generate accessToken
	public Request createPostRequest( String url, JSONObject params) {
		try {
			MediaType JSON = MediaType.parse("application/json; charset=utf-8");
			RequestBody body = RequestBody.create(params.toString(), JSON);
			Request request = new Request.Builder().url(url).post(body).header("Content-Type", "application/json").build();
			return request;
		} catch (Exception e) {
			System.out.println("exception createPostRequest");
			System.out.println(e.getMessage());
			return null;
		}
	}
	

	public JSONObject postRequest( String apiKey,String url, JSONObject params, String accessToken)
			throws IOException, SharekhanAPIException, JSONException {
		Request request = createPostRequest( apiKey,url, params, accessToken);
//		System.out.println("API_KEY:  "+apiKey);
		Response response = client.newCall(request).execute();
		String body = response.body().string();
		return new SharekhanAPIResponseHandler().handle(response, body);
	}
	
	public Request createPostRequest( String apiKey,String url, JSONObject params, String accessToken) {
		try {

			MediaType JSON = MediaType.parse("application/json; charset=utf-8");
			RequestBody body = RequestBody.create(params.toString(), JSON);
			Request request = new Request.Builder().url(url).post(body).header("Content-Type", "application/json")
					.header("api-key", apiKey)
					.header("access-token", accessToken).build();
			return request;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public JSONObject getRequest( String apiKey, String url,String accessToken)
			throws IOException, SharekhanAPIException, JSONException {
		Request request = createGetRequest(apiKey, url,accessToken);
		Response response = client.newCall(request).execute();
		String body = response.body().string();
		return new SharekhanAPIResponseHandler().handle(response, body);
	}

	public Request createGetRequest(String apiKey,String url, String accessToken) throws IOException {

		HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();	
		return new Request.Builder().url(httpBuilder.build())
				.header("Content-Type", "application/json")
				.header("api-key", apiKey)
				.header("access-token", accessToken).build();
	}
	
}
