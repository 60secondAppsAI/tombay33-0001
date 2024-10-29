package com.tombay33.dao;

import java.util.List;

import com.tombay33.dao.GenericDAO;
import com.tombay33.domain.ItemCategory;





public interface ItemCategoryDAO extends GenericDAO<ItemCategory, Integer> {
  
	List<ItemCategory> findAll();
	






}


