package com.tombay33.dao;

import java.util.List;

import com.tombay33.dao.GenericDAO;
import com.tombay33.domain.Category;





public interface CategoryDAO extends GenericDAO<Category, Integer> {
  
	List<Category> findAll();
	






}


