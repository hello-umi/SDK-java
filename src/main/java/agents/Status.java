package agents;

public enum Status {

	ONLINE("online"),
	OFFLINE("offline"),
	BUSY("busy");
	
	private String name;

	public static Status fromString(String name) {
		switch(name){
		case "online":
			return ONLINE;
		case "offline":
			return OFFLINE;
		case "busy":
			return BUSY;
		default:
			return OFFLINE;
		}
	}
	
	@Override
	public String toString(){
		return this.name;
	}
	
	Status(String name) {
		this.name = name;
	}
}
