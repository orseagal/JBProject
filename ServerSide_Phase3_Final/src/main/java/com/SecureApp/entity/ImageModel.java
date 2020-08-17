package com.SecureApp.entity;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class ImageModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private String type;

	@Column(name="pic_byte" ,length=1000)
	private byte[] pic_byte;

	public ImageModel(String name, String type, byte[] pic_byte) {
		this.name=name;
		this.type=type;
		this.pic_byte=pic_byte;
		
	}
	
	public ImageModel() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getPicByte() {
		return pic_byte;
	}

	public void setPicByte(byte[] picByte) {
		this.pic_byte = picByte;
	}

}
