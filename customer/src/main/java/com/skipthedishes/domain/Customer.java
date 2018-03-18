package com.skipthedishes.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Vitor Carrilho - 18/03/2018
 *
 */
@Entity
@Table(name="CUSTOMER", uniqueConstraints = @UniqueConstraint(columnNames = { "EMAIL" }))
public class Customer implements Serializable {
	
	private static final long serialVersionUID = 90268419925390425L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private long id;
	
	@NotNull
	@NotEmpty
	@Size(min=5, max=60)
	@Column(name="EMAIL")
	private String email;
	
	@NotNull
	@NotEmpty
	@Size(min=2, max=60)
	@Column(name="NAME")
	private String name;
	
	@NotNull
	@NotEmpty
	@Size(min=5, max=60)
	@Column(name="ADDRESS")
	private String address;
	
	@NotNull
	private Date creation;
	
	@NotNull
	@NotEmpty
	@Size(min=5, max=60)
	@Column(name="PASSWORD")
	private String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}	
}
