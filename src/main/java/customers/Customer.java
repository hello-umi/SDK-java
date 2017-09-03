package customers;

import java.sql.Date;
import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import agents.Agent;
import config.Config;
import requests.Request;

public class Customer {
	
	private static final String BASE_URL = Config.BASE_URL + "customers/";

	private int id;
	private String name;
	private String phone;
	@SerializedName("channel_id")
	private int channelId;
	private String avatar;
	private String email;
	private String city;
	@SerializedName("postal_code")
	private String postalCode;
	@SerializedName("register_date")
	private LocalDateTime registerDate;
	private Date birthdate;
	private String address;
	private String country;
	@SerializedName("last_message")
	private LocalDateTime lastMessage;
	private boolean unread;
	private boolean archived;
	private boolean blocked;
	@SerializedName("agent_id")
	private int agentId;
	@SerializedName("ticket_id")
	private int ticketId;
	
	private Customer() {}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public int getChannelId() {
		return channelId;
	}

	public String getAvatar() {
		return avatar;
	}

	public String getEmail() {
		return email;
	}

	public String getCity() {
		return city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public LocalDateTime getRegisterDate() {
		return registerDate;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public String getAddress() {
		return address;
	}

	public String getCountry() {
		return country;
	}

	public LocalDateTime getLastMessage() {
		return lastMessage;
	}

	public boolean isUnread() {
		return unread;
	}

	public boolean isArchived() {
		return archived;
	}

	public int getAgentId() {
		return agentId;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void sendText(String message) {
		JsonObject json = new JsonObject();
		json.addProperty("message", message);
		Request.post(BASE_URL + this.id + "/send_text/", json.getAsString());
	}

	public void sendLocation(float latitude, float longitude) {
		JsonObject json = new JsonObject();
		json.addProperty("latitude", latitude);
		json.addProperty("longitude", longitude);
		Request.post(BASE_URL + this.id + "/send_location/", json.getAsString());
	}

	public void sendImage(String url, String caption) {
		JsonObject json = new JsonObject();
		json.addProperty("caption", caption);
		json.addProperty("url", url);
		Request.post(BASE_URL + this.id + "/send_text/", json.getAsString());
	}

	public void archive() {
		Request.put(BASE_URL + this.id + "/archive/");
		this.archived = true;
	}

	public void unarchive() {
		Request.put(BASE_URL + this.id + "/unarchive/");
		this.archived = false;
	}

	public void block() {
		Request.put(BASE_URL + this.id + "/block/");
		this.blocked = true;
	}

	public void unblock() {
		Request.put(BASE_URL + this.id + "/unblock/");
		this.blocked = false;
	}

	public void assign() {
		Request.put(BASE_URL + this.id + "/assign/");
		this.agentId = Config.getAgentId();
	}

	public void unassign() {
		Request.put(BASE_URL + this.id + "/unassign/");
		this.agentId = 0;
	}

	public void save() {
		Request.patch(BASE_URL + this.id + "/", new Gson().toJson(this, Customer.class));
	}
	
	public static Customer get(int id) {
		JsonObject json = Request.get(BASE_URL + id + "/").getJson().getAsJsonObject("customer");
		return  new Gson().fromJson(json, Customer.class);
	}
	
}
