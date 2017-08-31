package payments;

import java.time.LocalDateTime;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import requests.Request;

public class Payment {

	private static final String BASE_URL = Request.BASE_URL + "payments/";

	private int id;
	private List<Concept> concepts;
	private float total;
	private boolean payed;
	@SerializedName("ticket_id")
	private int ticketId;
	@SerializedName("created_at")
	private LocalDateTime createdAt;
	@SerializedName("payed_at")
	private LocalDateTime payedAt;
	private String currency;
	private String url;
	
	private Payment() {}

	private Payment(int ticketId, String currency, List<Concept> concepts) {
		this.concepts = concepts;
		float total = 0;
		for (Concept concept:this.concepts)
			total += concept.getAmount();
		this.total = total;
		this.payed = false;
		this.ticketId = ticketId;
		this.createdAt = LocalDateTime.now();
		this.currency = currency;
	}

	public String asJson() {
		return new Gson().toJson(this, Payment.class);
	}

	public static Payment create(int ticketId, String currency, List<Concept> concepts) {
		Payment payment = new Payment(ticketId, currency, concepts);
		JsonObject json = Request.post(BASE_URL, payment.asJson()).getJson().getAsJsonObject("payment");
		return new Gson().fromJson(json, Payment.class);
	}

	public static Payment get(int id) {
		JsonObject json = Request.get(BASE_URL + id + "/").getJson().getAsJsonObject("payment");
		return  new Gson().fromJson(json, Payment.class);
	}

	public int getId() {
		return id;
	}

	public List<Concept> getConcepts() {
		return concepts;
	}

	public float getTotal() {
		return total;
	}

	public boolean isPayed() {
		return payed;
	}

	public int getTicketId() {
		return ticketId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getPayedAt() {
		return payedAt;
	}

	public String getCurrency() {
		return currency;
	}

	public String getUrl() {
		return url;
	}

	class Concept {
		
		private String concept;
		private float amount;
		
		private Concept() {}

		public Concept(String concept, float amount) {
			this.concept = concept;
			this.amount = amount;
		}

		public String getConcept() {
			return concept;
		}

		public float getAmount() {
			return amount;
		}
	}
}
