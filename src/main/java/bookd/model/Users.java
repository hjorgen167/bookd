package bookd.model;

public class Users {
	final String userId;
	final String name;
	
	public Users(String userId, String name) {
		super();
		this.userId = userId;
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public String getName() {
		return name;
	}
}
