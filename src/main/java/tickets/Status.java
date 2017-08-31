package tickets;

public enum Status {
	
	PENDING("pending", 0),
	ATTENTION("attention", 1),
	COMPLETED("completed", 2),
	REJECTED("rejected", 3);

	private String name;
	private int code;
	
	@Override
	public String toString(){
		return this.name;
	}
	
	public int getCode(){
		return this.code;
	}
	
	public static Status fromString(String name) {
		switch(name){
		case "pending":
			return PENDING;
		case "attention":
			return ATTENTION;
		case "completed":
			return COMPLETED;
		case "rejected":
			return REJECTED;
		default:
			return PENDING;
		}
	}

	Status(String name, int code) {
		this.name = name;
		this.code = code;
	}
	
}
