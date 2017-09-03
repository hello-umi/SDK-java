package tickets;

import java.time.LocalDateTime;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import config.Config;
import requests.Request;

public class Ticket {

	private static final String BASE_URL = Config.BASE_URL + "tickets/";

	private int id;
	@SerializedName("customer_id")
	private int customerId;
	@SerializedName("short_description")
	private String shortDescription;
	private String description;
	private Status status;
	@SerializedName("created_at")
	private LocalDateTime createdAt;
	@SerializedName("finished_at")
	private LocalDateTime finishedAt;
	private List<String> tags;
	
	private Ticket() {}

	private Ticket(int customerId, String shortDescription, String description, List<String> tags) {
		this.customerId = customerId;
		this.shortDescription = shortDescription;
		this.description = description;
		this.status = Status.PENDING;
		this.createdAt = LocalDateTime.now();
		this.tags = tags;
	}

	public int getId() {
		return id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public String getDescription() {
		return description;
	}

	public Status getStatus() {
		return status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getFinishedAt() {
		return finishedAt;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String asJson() {
		return new Gson().toJson(this, Ticket.class);
	}

	public static Ticket create(int customerId, String shortDescription, String description, List<String> tags) {
		Ticket ticket = new Ticket(customerId, shortDescription, description, tags);
		JsonObject json = Request.post(BASE_URL, ticket.asJson()).getJson().getAsJsonObject("ticket");
		return new Gson().fromJson(json, Ticket.class);
	}

	public void save() {
		Request.patch(BASE_URL + this.id + "/", new Gson().toJson(this, Ticket.class));
	}

	public static Ticket get(int id) {
		JsonObject json = Request.get(BASE_URL + id + "/").getJson().getAsJsonObject("ticket");
		return  new Gson().fromJson(json, Ticket.class);
	}
		
}
