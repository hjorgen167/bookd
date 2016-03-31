package bookd.model;

import java.sql.Date;

public class Reviews {
	  final int reviewId;
	  final int userId;
	  final String ASIN;
	  final String reviewText;
	  final float rating;
	  final String summary;
	  final Date created;
	  
	public Reviews(int reviewId, int userId, String aSIN, String reviewText, float rating, String summary,
			Date created) {
		super();
		this.reviewId = reviewId;
		this.userId = userId;
		ASIN = aSIN;
		this.reviewText = reviewText;
		this.rating = rating;
		this.summary = summary;
		this.created = created;
	}

	public int getReviewId() {
		return reviewId;
	}

	public int getUserId() {
		return userId;
	}

	public String getASIN() {
		return ASIN;
	}

	public String getReviewText() {
		return reviewText;
	}

	public float getRating() {
		return rating;
	}

	public String getSummary() {
		return summary;
	}

	public Date getCreated() {
		return created;
	}
	  
	  
	  
}
