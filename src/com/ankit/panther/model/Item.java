package com.ankit.panther.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the item database table.
 * 
 */
@Entity
@Table(name="item")
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=100)
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="due_date_dttm")
	private Date dueDateDttm;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ins_dttm")
	private Date insDttm;

	@Column(name="is_completed")
	private Boolean isCompleted;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_mod_dttm")
	private Date lastModDttm;

	@Column(length=60)
	private String title;

	//uni-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user")
	private User userBean;

	public Item() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDateDttm() {
		return this.dueDateDttm;
	}

	public void setDueDateDttm(Date dueDateDttm) {
		this.dueDateDttm = dueDateDttm;
	}

	public Date getInsDttm() {
		return this.insDttm;
	}

	public void setInsDttm(Date insDttm) {
		this.insDttm = insDttm;
	}

	public Boolean getIsCompleted() {
		return this.isCompleted;
	}

	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public Date getLastModDttm() {
		return this.lastModDttm;
	}

	public void setLastModDttm(Date lastModDttm) {
		this.lastModDttm = lastModDttm;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUserBean() {
		return this.userBean;
	}

	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}

}