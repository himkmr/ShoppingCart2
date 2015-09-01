package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PRODUCT database table.
 * 
 */
@Entity
@Table(name="PRODUCT", schema="TESTDB")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="ADD_COL1")
	private String addCol1;

	private String description;

	private String name;

	private double price;

	@Column(name="QUANTITY_AVAILABLE")
	private BigDecimal quantityAvailable;

	public Product() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddCol1() {
		return this.addCol1;
	}

	public void setAddCol1(String addCol1) {
		this.addCol1 = addCol1;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public BigDecimal getQuantityAvailable() {
		return this.quantityAvailable;
	}

	public void setQuantityAvailable(BigDecimal quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

}