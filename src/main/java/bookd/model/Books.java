package bookd.model;

import java.util.Date;

public class Books {
	private String ASIN;
	private String model;
	private String title;
	private String binding;
	private String editorialReview;
	private String editorialReviews;
	private int pages;
	private String publisher;
	private Date publicationDate;
	private Date releaseDate;
	private String region;
	public Books(String aSIN, String model, String title, String binding, String editorialReview,
			String editorialReviews, int pages, String publisher, Date publicationDate, Date releaseDate,
			String region) {
		super();
		ASIN = aSIN;
		this.model = model;
		this.title = title;
		this.binding = binding;
		this.editorialReview = editorialReview;
		this.editorialReviews = editorialReviews;
		this.pages = pages;
		this.publisher = publisher;
		this.publicationDate = publicationDate;
		this.releaseDate = releaseDate;
		this.region = region;
	}
	
	public Books(String aSIN) {
		super();
		this.ASIN = aSIN;
	}
	
	public String getASIN() {
		return ASIN;
	}
	public String getModel() {
		return model;
	}
	public String getTitle() {
		return title;
	}
	public String getBinding() {
		return binding;
	}
	public String getEditorialReview() {
		return editorialReview;
	}
	public String getEditorialReviews() {
		return editorialReviews;
	}
	public int getPages() {
		return pages;
	}
	public String getPublisher() {
		return publisher;
	}
	public Date getPublicationDate() {
		return publicationDate;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public String getRegion() {
		return region;
	}
	public void setASIN(String aSIN) {
		ASIN = aSIN;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setBinding(String binding) {
		this.binding = binding;
	}
	public void setEditorialReview(String editorialReview) {
		this.editorialReview = editorialReview;
	}
	public void setEditorialReviews(String editorialReviews) {
		this.editorialReviews = editorialReviews;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	
	
}
