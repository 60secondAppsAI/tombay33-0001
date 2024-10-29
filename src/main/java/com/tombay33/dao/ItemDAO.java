package com.tombay33.dao;

import java.util.List;

import com.tombay33.dao.GenericDAO;
import com.tombay33.domain.Item;





public interface ItemDAO extends GenericDAO<Item, Integer> {
  
	List<Item> findAll();
	






}


