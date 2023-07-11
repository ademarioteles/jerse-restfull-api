package com.lojinhateles.program.service;

import java.util.List;

import com.lojinhateles.program.model.Category;

public interface ObjectService <T> {
	
	
	List<T> getAll();
	T getObject(T object);
	T getById(Integer id);
	void save(T object);
	void delete(T object);
	void deleteById(Integer id);
	int total();
	void update(T object);
	
	
}
