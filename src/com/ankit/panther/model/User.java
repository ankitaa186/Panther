package com.ankit.panther.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="insert_dttm", nullable=false)
	private Date insertDttm;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_mod_dttm", nullable=false)
	private Date lastModDttm;

	@Column(nullable=false, length=200)
	private String password;

	@Column(nullable=false, length=45)
	private String username;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getInsertDttm() {
		return this.insertDttm;
	}

	public void setInsertDttm(Date insertDttm) {
		this.insertDttm = insertDttm;
	}

	public Date getLastModDttm() {
		return this.lastModDttm;
	}

	public void setLastModDttm(Date lastModDttm) {
		this.lastModDttm = lastModDttm;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}