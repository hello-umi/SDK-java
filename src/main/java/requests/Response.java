package requests;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Response {
	
	private int code;
	private String body;
	private JsonObject json;
	
	Response(int code, String body) {
		super();
		this.code = code;
		this.body = body;
	}
	
	public int getStatusCode() {
		return this.code;
	}
	
	public String getBody() {
		return this.body;
	}
	
	public JsonObject getJson() {
		if (this.json != null)
			this.json = new JsonParser().parse(this.body).getAsJsonObject();
		return this.json;
	}

}
