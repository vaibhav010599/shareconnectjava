package com.sharekhan.Ticker;

import com.sharekhan.http.exceptions.SharekhanAPIException;

public interface SharekhanWSOnError {
	public void onError(Exception exception);

	public void onError(SharekhanAPIException sharekhanAPIException);

	void onError(String error);
}
