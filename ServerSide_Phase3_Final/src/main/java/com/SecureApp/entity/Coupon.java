package com.SecureApp.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "coupons")
public class Coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long Id;

	@Column(name = "comp_id")
	private long companyId;

	@Column(name = "title")
	private String title;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "amount")
	private int amount;

	@Column(name = "type")
	private String type;

	@Column(name = "message")
	private String message;

	@Column(name = "price")
	private double price;

	@Column(name = "image")
	private String image;
	
	@Column(name = "avaliable")
	private boolean avaliable;

//	@ManyToMany(fetch=FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
//	@JoinTable(
//			name="customer_coupon",
//			joinColumns=@JoinColumn(name="coupon_id"),
//			inverseJoinColumns=@JoinColumn(name="cust_id")
//			)
//	private List<User> users;

//	@ManyToMany(fetch=FetchType.LAZY, mappedBy="coupons")
//	private List<User> users;

	public Coupon() {
		this.setAvaliable(this.isAvailableCheck(getAmount()));
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
		this.avaliable=this.isAvailableCheck(this.amount);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

//	public List<User> getUsers() {
//		return users;
//	}
//
//	public void setUsers(List<User> users) {
//		this.users = users;
//	}


	
	public boolean isAvailableCheck(int amount) {

		if (amount <= 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isAvaliable() {
		return avaliable;
	}

	public void setAvaliable(boolean avaliable) {
		this.avaliable = avaliable;
	}

	@Override
	public String toString() {
		return "Coupon [Id=" + Id + ", companyId=" + companyId + ", title=" + title + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", amount=" + amount + ", type=" + type + ", message=" + message + ", price="
				+ price + ", image=" + image + "]";
	}
}
