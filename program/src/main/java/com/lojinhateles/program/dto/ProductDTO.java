package com.lojinhateles.program.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lojinhateles.program.model.Category;
import com.lojinhateles.program.model.Orders;
import com.lojinhateles.program.model.Product;

public class ProductDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String url;
	private Integer stock;
	private Double price;
	private List<Orders> orders = new ArrayList<Orders>();
	private Category category;

	public ProductDTO(Product prod){
		this.id = prod.getId();
		this.name = prod.getName();
		this.url = prod.getUrl();
		this.stock = prod.getStock();
		this.price = prod.getPrice();
		prod.getOrders().forEach(x->x.getProducts().clear());
		this.orders = prod.getOrders();
		this.category = prod.getCategory();
	}
	
	public ProductDTO(){
		
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", url=" + url + ", stock=" + stock + ", price=" + price
				+ ", orders=" + orders + ", category=" + category + "]";
	}
	
	

}
