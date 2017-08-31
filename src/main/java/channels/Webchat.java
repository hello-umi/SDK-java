package channels;

import java.awt.Color;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import requests.Request;

public class Webchat extends Channel {
	
	@SerializedName("presence_name")
	private String presenceName;
	private String status;
	@SerializedName("back_color")
	private Color backColor;
	@SerializedName("accent_color")
	private Color accentColor;
	@SerializedName("primary_text_color")
	private Color primaryTextColor;

	private Webchat() {
		super();
	}

	private Webchat(String name, String presenceName, String status, Color backColor, Color accentColor, Color
			primaryTextColor) {
		super(name);
		this.presenceName = presenceName;
		this.status = status;
		this.backColor = backColor;
		this.accentColor = accentColor;
		this.primaryTextColor = primaryTextColor;
	}

	public String getPresenceName() {
		return presenceName;
	}

	public void setPresenceName(String presenceName) {
		this.presenceName = presenceName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Color getBackColor() {
		return backColor;
	}

	public void setBackColor(Color backColor) {
		this.backColor = backColor;
	}

	public Color getAccentColor() {
		return accentColor;
	}

	public void setAccentColor(Color accentColor) {
		this.accentColor = accentColor;
	}

	public Color getPrimaryTextColor() {
		return primaryTextColor;
	}

	public void setPrimaryTextColor(Color primaryTextColor) {
		this.primaryTextColor = primaryTextColor;
	}

	public void save() {
		Request.patch(BASE_URL + this.getId() + "/", new Gson().toJson(this, Webchat.class));
	}

	public static Webchat get(int id) {
		JsonObject json = Request.get(BASE_URL + id + "/").getJson().getAsJsonObject("channel");
		return  new Gson().fromJson(json, Webchat.class);
	}

	public static Webchat create(String name, String presenceName, String status, Color backColor, Color accentColor,
								 Color primaryTextColor) {
		Webchat webchat = new Webchat(name, presenceName, status, backColor, accentColor, primaryTextColor);
		String str = new Gson().toJson(webchat, Webchat.class);
		JsonObject json = Request.post(BASE_URL, str).getJson().getAsJsonObject("channel");
		return new Gson().fromJson(json, Webchat.class);
	}

}
