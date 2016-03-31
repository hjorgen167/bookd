package bookd.model;

public class Covers {
	private int id;
	private String ASIN;
	private String largeThumbnail;
	private String mediumThumbnail;
	private String smallThumbnail;
	
	public Covers(String aSIN, String largeThumbnail, String mediumThumbnail, String smallThumbnail) {
		super();
		ASIN = aSIN;
		this.largeThumbnail = largeThumbnail;
		this.mediumThumbnail = mediumThumbnail;
		this.smallThumbnail = smallThumbnail;
	}
	
	public Covers(int id, String aSIN, String largeThumbnail, String mediumThumbnail, String smallThumbnail) {
		super();
		this.id = id;
		ASIN = aSIN;
		this.largeThumbnail = largeThumbnail;
		this.mediumThumbnail = mediumThumbnail;
		this.smallThumbnail = smallThumbnail;
	}

	public int getId() {
		return id;
	}

	public String getASIN() {
		return ASIN;
	}

	public String getLargeThumbnail() {
		return largeThumbnail;
	}

	public String getMediumThumbnail() {
		return mediumThumbnail;
	}

	public String getSmallThumbnail() {
		return smallThumbnail;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setASIN(String aSIN) {
		ASIN = aSIN;
	}

	public void setLargeThumbnail(String largeThumbnail) {
		this.largeThumbnail = largeThumbnail;
	}

	public void setMediumThumbnail(String mediumThumbnail) {
		this.mediumThumbnail = mediumThumbnail;
	}

	public void setSmallThumbnail(String smallThumbnail) {
		this.smallThumbnail = smallThumbnail;
	}
		
}
