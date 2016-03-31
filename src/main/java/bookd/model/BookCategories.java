package bookd.model;

public class BookCategories {
	final String ASIN;
	final int categoryId;
	
	public BookCategories(String aSIN, int categoryId) {
		super();
		ASIN = aSIN;
		this.categoryId = categoryId;
	}
	public String getASIN() {
		return ASIN;
	}
	public int getCategoryId() {
		return categoryId;
	}
}
