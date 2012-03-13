package vyn.json.parse;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import vyn.cs.ChatActivity;
import vyn.json.online.Online;
import vyn.json.online.ResponseRoot;
import android.util.Log;

import com.google.gson.Gson;

public class JSONParse {
	/*
	 * return ArrayList
	 */
	public ArrayList<String> parseJsonTestActivity(JSONObject[] data, String string) {
		Log.d("data jsonnya = ", data[0].toString());
		Gson gson = new Gson();
		ResponseRoot response = gson.fromJson(data[0].toString(),
				ResponseRoot.class);

		Log.d("results", response.toString());
		List<Online> results = response.online;
		Log.d("results", results.toString());
		ArrayList<String> userOnline = new ArrayList<String>();
		for (Online result : results) {
			userOnline.add(result.username);
		}
		return userOnline;
	};
	public String parseDataJsonChat(JSONObject[] data, String string) {
		String chat = null;
		try {
			chat = data[0].getString(string).toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chat;
	}

	public String parseOneFieldJson(JSONObject[] data, String key) {
		String value = null;
		try {
			value = data[0].getString(key).toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;	
	};
}
