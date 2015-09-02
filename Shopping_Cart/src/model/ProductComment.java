package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PRODUCT_COMMENTS database table.
 * 
 */
@Entity
@Table(name="PRODUCT_COMMENTS")
@NamedQuery(name="ProductComment.findAll", query="SELECT p FROM ProductComment p")
public class ProductComment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String comments;

	@Column(name="PRODUCT_ID")
	private String productId;

	private BigDecimal ratings;

	@Column(name="USER_NAME")
	private String userName;

	public ProductComment() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public BigDecimal getRatings() {
		return this.ratings;
	}

	public void setRatings(BigDecimal ratings) {
		this.ratings = ratings;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}