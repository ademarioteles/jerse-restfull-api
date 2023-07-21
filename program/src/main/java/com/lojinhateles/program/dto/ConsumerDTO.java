package com.lojinhateles.program.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.lojinhateles.program.model.Adress;
import com.lojinhateles.program.model.Consumer;
import com.lojinhateles.program.model.Orders;

public class ConsumerDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String cpf;
	private String email;
	private String password;
	private Date dateCreate;
	private String phone;
	private Adress adress;
	private List<Orders> orders;

	public ConsumerDTO(Consumer consumer) {
		this.id = consumer.getId();
		this.name = consumer.getName();
		this.cpf = consumer.getCpf();
		this.email = consumer.getEmail();
		this.password = consumer.getPassword();
		this.dateCreate = consumer.getDateCreate();
		this.phone = consumer.getPhone();
		this.adress = consumer.getAdress();
		consumer.getOrders().forEach(x->x.getProducts().forEach(y->y.getOrders().clear()));
		consumer.getOrders().forEach(x->x.setConsumer(null));
		this.orders = consumer.getOrders();
	}

	public ConsumerDTO() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "ConsumerDTO [id=" + id + ", name=" + name + ", cpf=" + cpf + ", email=" + email + ", password="
				+ password + ", dateCreate=" + dateCreate + ", phone=" + phone + ", adress=" + adress + ", orders="
				+ orders + "]";
	}
	
	
}
