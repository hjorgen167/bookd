package bookd.model;

public class Users {
	private String userId;
	private String name;
	
	public Users(String userId, String name) {
		super();
		this.userId = userId;
		this.name = name;
	}
	
	public Users(String userId) {
		// TODO Auto-generated constructor stub
		super();
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}
	public String getName() {
		return name;
	}
}
