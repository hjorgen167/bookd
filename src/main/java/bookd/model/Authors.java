package bookd.model;

public class Authors {
	private int id;
	private String ASIN;
	private String author;
	
	public Authors(String aSIN, String author) {
		super();
		ASIN = aSIN;
		this.author = author;
	}
	
	public Authors(int id, String aSIN, String author) {
		super();
		this.id = id;
		ASIN = aSIN;
		this.author = author;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setASIN(String aSIN) {
		ASIN = aSIN;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getId() {
		return id;
	}
	public String getASIN() {
		return ASIN;
	}
	public String getAuthor() {
		return author;
	}
        
}
