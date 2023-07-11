package com.lojinhateles.program.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "adress")
@XmlRootElement
public class Adress implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Integer cep;
	private String place;

	public Adress() {
	}

	public Adress(Integer cep, String place) {
		this.cep = cep;
		this.place = place;
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@Override
	public String toString() {
		return "Adress [cep=" + cep + ", place=" + place + "]";
	}

}
