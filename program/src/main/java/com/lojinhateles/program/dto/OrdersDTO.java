package com.lojinhateles.program.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.lojinhateles.program.enums.SituacionOrder;
import com.lojinhateles.program.model.Consumer;
import com.lojinhateles.program.model.Orders;
import com.lojinhateles.program.model.Product;

public class OrdersDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Consumer consumer;
	private SituacionOrder situation;
	private Double total;
	private Set<Product> product = new HashSet<Product>();

	public OrdersDTO() {
	}

	public OrdersDTO(Orders orde) {
		this.consumer = orde.getConsumer();
		this.situation = orde.getSituation();
		this.total = orde.getTotal();
		orde.getProducts().forEach(x -> x.getOrders().clear());
		this.product = orde.getProducts();
	}

	public Orders build(Orders order) {
		order = new Orders();
		order.setId(order.getId());
		order.setSituation(order.getSituation());
		order.setTotal(order.getTotal());
		return order;
	}

	@Override
	public String toString() {
		return "OrdersDTO [id=" + id + ", consumer=" + consumer + ", situation=" + situation + ", total=" + total
				+ ", produtos=" + product + "]";
	}

	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}

	public SituacionOrder getSituation() {
		return situation;
	}

	public void setSituation(SituacionOrder situation) {
		this.situation = situation;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

}
