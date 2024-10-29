package com.tombay33.dao;

import java.util.List;

import com.tombay33.dao.GenericDAO;
import com.tombay33.domain.Bid;





public interface BidDAO extends GenericDAO<Bid, Integer> {
  
	List<Bid> findAll();
	






}


