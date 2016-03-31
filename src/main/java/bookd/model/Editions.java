package bookd.model;

public class Editions {
    private int id;
	private String ASIN;
	private String edition;
	private String locale;
	public Editions(int id, String aSIN, String edition, String locale) {
		super();
		this.id = id;
		ASIN = aSIN;
		this.edition = edition;
		this.locale = locale;
	}
	public Editions(String aSIN, String edition, String locale) {
		super();
		ASIN = aSIN;
		this.edition = edition;
		this.locale = locale;
	}
	public int getId() {
		return id;
	}
	public String getASIN() {
		return ASIN;
	}
	public String getEdition() {
		return edition;
	}
	public String getLocale() {
		return locale;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setASIN(String aSIN) {
		ASIN = aSIN;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
}
