package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PRODUCT_ORDER_HISTORY database table.
 * 
 */
@Entity
@Table(name="PRODUCT_ORDER_HISTORY")
@NamedQuery(name="ProductOrderHistory.findAll", query="SELECT p FROM ProductOrderHistory p")
public class ProductOrderHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="OREDER_ID")
	private BigDecimal orederId;

	@Column(name="PRODUCT_NAME")
	private String productName;

	@Column(name="PRODUCT_PRICE")
	private BigDecimal productPrice;

	@Column(name="PRODUCT_QUANTITY")
	private String productQuantity;

	@Column(name="USER_NAME")
	private String userName;

	public ProductOrderHistory() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getOrederId() {
		return this.orederId;
	}

	public void setOrederId(BigDecimal orederId) {
		this.orederId = orederId;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getProductPrice() {
		return this.productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductQuantity() {
		return this.productQuantity;
	}

	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}