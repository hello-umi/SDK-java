package agents;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import config.Config;
import requests.Request;

public class Agent {

	private static final String BASE_URL = Config.BASE_URL + "agents/";
	
	private int id;
	@SerializedName("first_name")
	private String firstName;
	@SerializedName("last_name")
	private String lastName;
	private String email;
	private Status status;
	
	
	private Agent() {}
	
	private Agent(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.status = Status.OFFLINE;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public Status getStatus() {
		return status;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setStatus(Status status) {
		JsonObject json = new JsonObject();
		json.addProperty("status", status.toString());
		Request.put(BASE_URL + this.id + "/", json.getAsString());
		this.status = status;
	}
	
	public void save() {
		Request.patch(BASE_URL + this.id + "/", new Gson().toJson(this, Agent.class));
	}
	
	public void delete() {
		Request.delete(BASE_URL + this.id + "/");
	}
	
	public static Agent create(String firstName, String lastName, String email, String password) {
		Agent agent = new Agent(firstName, lastName, email);
		JsonObject json = new Gson().toJsonTree(agent, Agent.class).getAsJsonObject();
		json.addProperty("password", password);
		json = Request.post(BASE_URL, json.getAsString()).getJson().getAsJsonObject("agent");
		return  new Gson().fromJson(json, Agent.class);
	}
	
	public static Agent get(int id) {
		JsonObject json = Request.get(BASE_URL + id + "/").getJson().getAsJsonObject("agent");
		return  new Gson().fromJson(json, Agent.class);
	}

}
