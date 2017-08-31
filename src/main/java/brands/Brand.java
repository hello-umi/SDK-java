package brands;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import requests.Request;

public class Brand {
	
	private static final String BASE_URL = Request.BASE_URL + "brand/";

	private int id;
	private String name;
	private String phone;
	private String email;
	private String cif;
	private String address;
	private String zipcode;
	private String city;
	private String country;
	@SerializedName("auto_assign")
	private boolean autoAssign;
	@SerializedName("automatic_tickets")
	private boolean automaticTickets;
	private String vat;
	
	private Brand() {}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getCif() {
		return cif;
	}

	public String getAddress() {
		return address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public boolean isAutoAssign() {
		return autoAssign;
	}

	public boolean isAutomaticTickets() {
		return automaticTickets;
	}

	public String getVat() {
		return vat;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setAutoAssign(boolean autoAssign) {
		this.autoAssign = autoAssign;
	}

	public void setAutomaticTickets(boolean automaticTickets) {
		this.automaticTickets = automaticTickets;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public void save() {
		Request.patch(BASE_URL, new Gson().toJson(this, Brand.class));
	}

	public static Brand get() {
		JsonObject json = Request.get(BASE_URL).getJson().getAsJsonObject("brand");
		return  new Gson().fromJson(json, Brand.class);
	}
	
}
