package bookd.model;

public class Identifiers {
	private int id;
	private String ASIN;
	private String EAN;
	private String EISBN;
	private String ISBN;
	private String partNumber;
	private String MPN;
	
	public Identifiers(int id, String aSIN, String eAN, String eISBN, String iSBN, String partNumber, String mPN) {
		super();
		this.id = id;
		ASIN = aSIN;
		EAN = eAN;
		EISBN = eISBN;
		ISBN = iSBN;
		this.partNumber = partNumber;
		MPN = mPN;
	}

	public Identifiers(String aSIN, String eAN, String eISBN, String iSBN, String partNumber, String mPN) {
		super();
		ASIN = aSIN;
		EAN = eAN;
		EISBN = eISBN;
		ISBN = iSBN;
		this.partNumber = partNumber;
		MPN = mPN;
	}

	public int getId() {
		return id;
	}

	public String getASIN() {
		return ASIN;
	}

	public String getEAN() {
		return EAN;
	}

	public String getEISBN() {
		return EISBN;
	}

	public String getISBN() {
		return ISBN;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public String getMPN() {
		return MPN;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setASIN(String aSIN) {
		ASIN = aSIN;
	}

	public void setEAN(String eAN) {
		EAN = eAN;
	}

	public void setEISBN(String eISBN) {
		EISBN = eISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public void setMPN(String mPN) {
		MPN = mPN;
	}	
}
