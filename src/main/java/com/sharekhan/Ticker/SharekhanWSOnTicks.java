package com.sharekhan.Ticker;

import org.json.JSONObject;

public interface SharekhanWSOnTicks {
	void onTicks(JSONObject ticks);
	
}
