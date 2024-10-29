package com.tombay33.dao;

import java.util.List;

import com.tombay33.dao.GenericDAO;
import com.tombay33.domain.Feedback;





public interface FeedbackDAO extends GenericDAO<Feedback, Integer> {
  
	List<Feedback> findAll();
	






}


