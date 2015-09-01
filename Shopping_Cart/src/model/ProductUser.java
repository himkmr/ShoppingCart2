package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PRODUCT_USER database table.
 * 
 */
@Entity
@Table(name="PRODUCT_USER", schema="TESTDB")
@NamedQuery(name="ProductUser.findAll", query="SELECT p FROM ProductUser p")
public class ProductUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String city;

	private String email;

	private String fullname;

	private String password;

	@Column(name="\"STATE\"")
	private String state;

	private String streetaddress;

	private String username;

	private String zip;

	public ProductUser() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreetaddress() {
		return this.streetaddress;
	}

	public void setStreetaddress(String streetaddress) {
		this.streetaddress = streetaddress;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

}