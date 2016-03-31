package bookd.model;

public class PricingInfo {
	private int id;
	private String ASIN;
	private String listPrice;
	private String listPriceCurrencyCode;
	private String retailPrice;
	private String retailPriceCurrencyCode;
	
	public PricingInfo(String aSIN, String listPrice, String listPriceCurrencyCode, String retailPrice,
			String retailPriceCurrencyCode) {
		super();
		ASIN = aSIN;
		this.listPrice = listPrice;
		this.listPriceCurrencyCode = listPriceCurrencyCode;
		this.retailPrice = retailPrice;
		this.retailPriceCurrencyCode = retailPriceCurrencyCode;
	}
	
	public PricingInfo(int id, String aSIN, String listPrice, String listPriceCurrencyCode, String retailPrice,
			String retailPriceCurrencyCode) {
		super();
		this.id = id;
		ASIN = aSIN;
		this.listPrice = listPrice;
		this.listPriceCurrencyCode = listPriceCurrencyCode;
		this.retailPrice = retailPrice;
		this.retailPriceCurrencyCode = retailPriceCurrencyCode;
	}
	
	public int getId() {
		return id;
	}
	public String getASIN() {
		return ASIN;
	}
	public String getListPrice() {
		return listPrice;
	}
	public String getListPriceCurrencyCode() {
		return listPriceCurrencyCode;
	}
	public String getRetailPrice() {
		return retailPrice;
	}
	public String getRetailPriceCurrencyCode() {
		return retailPriceCurrencyCode;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setASIN(String aSIN) {
		ASIN = aSIN;
	}
	public void setListPrice(String listPrice) {
		this.listPrice = listPrice;
	}
	public void setListPriceCurrencyCode(String listPriceCurrencyCode) {
		this.listPriceCurrencyCode = listPriceCurrencyCode;
	}
	public void setRetailPrice(String retailPrice) {
		this.retailPrice = retailPrice;
	}
	public void setRetailPriceCurrencyCode(String retailPriceCurrencyCode) {
		this.retailPriceCurrencyCode = retailPriceCurrencyCode;
	}
	
	
}
