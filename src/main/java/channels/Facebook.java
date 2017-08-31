package channels;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import requests.Request;

public class Facebook extends Channel {

	private Facebook() {
		super();
	}

	private Facebook(String name, String token) {
		super(name);
		this.setToken(token);
	}

	public void save() {
		Request.patch(BASE_URL + this.getId() + "/", new Gson().toJson(this, Facebook.class));
	}

	public static Facebook get(int id) {
		JsonObject json = Request.get(BASE_URL + id + "/").getJson().getAsJsonObject("channel");
		return  new Gson().fromJson(json, Facebook.class);
	}

	public static Facebook create(String name, String token) {
		Facebook facebook = new Facebook(name, token);
		String str = new Gson().toJson(facebook, Facebook.class);
		JsonObject json = Request.post(BASE_URL, str).getJson().getAsJsonObject("channel");
		return new Gson().fromJson(json, Facebook.class);
	}

}
