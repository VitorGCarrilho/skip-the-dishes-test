package com.skipthedishes.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Vitor Carrilho - 18/03/2018
 *
 */
@Entity
@Table(name="STORE")
public class Store {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private long id;
	
	@NotNull
	@NotEmpty
	@Size(min=2, max=60)
	@Column(name="NAME")
	private String name;
	
	@NotNull
	@NotEmpty
	@Size(min=2, max=500)
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="COUSINE_ID")
	private long cousineId;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}
	
	public long getCousineId() {
		return cousineId;
	}
		
}
