package com.skipthedishes.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Vitor Carrilho - 18/03/2018
 *
 */
@Entity
@Table(name="COUSINE")
public class Cousine implements Serializable {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = -4704087930822194692L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private long id;
	
	@NotNull
	@NotEmpty
	@Size(min=2, max=60)
	@Column(name="NAME")
	private String name;
	
	@OneToMany
	@JoinColumn(name = "COUSINE_ID")
	@JsonIgnore
	private List<Store> storeList;
	

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public List<Store> getStoreList() {
		return storeList;
	}
}
