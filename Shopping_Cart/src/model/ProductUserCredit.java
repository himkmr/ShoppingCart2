package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PRODUCT_USER_CREDIT database table.
 * 
 */
@Entity
@Table(name="PRODUCT_USER_CREDIT")
@NamedQuery(name="ProductUserCredit.findAll", query="SELECT p FROM ProductUserCredit p")
public class ProductUserCredit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private BigDecimal credit;

	private String username;

	public ProductUserCredit() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getCredit() {
		return this.credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}