package channels;

import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import requests.Request;

abstract class Channel {

	static final String BASE_URL = Request.BASE_URL + "channels/";

	private int id;
	private String name;
	private boolean active;
	@SerializedName("created_at")
	private LocalDateTime createdAt;
	private String token;

	Channel(){}

	Channel(String name){
		this.name = name;
		this.active = true;
		this.createdAt = LocalDateTime.now();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isActive() {
		return active;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setName(String name) {
		this.name = name;
	}

	void setToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void delete() {
		Request.delete(BASE_URL + this.id + "/");
	}

	public static Channel get(int id) {
		JsonObject json = Request.get(BASE_URL + id + "/").getJson().getAsJsonObject("channel");
		return  new Gson().fromJson(json, Channel.class);
	}

}
