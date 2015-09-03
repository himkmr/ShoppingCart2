package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PRODUCT_PAYMENT_HISTORY database table.
 * 
 */
@Entity
@Table(name="PRODUCT_PAYMENT_HISTORY")
@NamedQuery(name="ProductPaymentHistory.findAll", query="SELECT p FROM ProductPaymentHistory p")
public class ProductPaymentHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="BILLING_ADDRESS")
	private String billingAddress;

	@Column(name="BILLING_STATE")
	private String billingState;

	@Column(name="CARD_USED")
	private BigDecimal cardUsed;

	@Column(name="ORDER_ID")
	private BigDecimal orderId;

	@Column(name="PAYMENT_AMOUNT")
	private BigDecimal paymentAmount;

	@Column(name="SHIPIING_CITY")
	private String shipiingCity;

	@Column(name="SHIPPING_ADDRESS")
	private String shippingAddress;

	@Column(name="SHIPPING_STATE")
	private String shippingState;

	private String username;

	public ProductPaymentHistory() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBillingAddress() {
		return this.billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getBillingState() {
		return this.billingState;
	}

	public void setBillingState(String billingState) {
		this.billingState = billingState;
	}

	public BigDecimal getCardUsed() {
		return this.cardUsed;
	}

	public void setCardUsed(BigDecimal cardUsed) {
		this.cardUsed = cardUsed;
	}

	public BigDecimal getOrderId() {
		return this.orderId;
	}

	public void setOrderId(BigDecimal orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getPaymentAmount() {
		return this.paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getShipiingCity() {
		return this.shipiingCity;
	}

	public void setShipiingCity(String shipiingCity) {
		this.shipiingCity = shipiingCity;
	}

	public String getShippingAddress() {
		return this.shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getShippingState() {
		return this.shippingState;
	}

	public void setShippingState(String shippingState) {
		this.shippingState = shippingState;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}