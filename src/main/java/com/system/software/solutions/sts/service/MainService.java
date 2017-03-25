package com.system.software.solutions.sts.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.software.solutions.sts.model.AbstractEntity;
import com.system.software.solutions.sts.model.CustomEntity;
import com.system.software.solutions.sts.model.Main;
 
public interface MainService {
 
	void saveMain(Main Main);
	void update(Main entity);
	Main findById(long id);
	List<Main> findAll();
	List<Main> find(String term,String col,int pageSize,int pageNumber) ;
	AbstractEntity<Main> getEntity(String term, String col,String order, int pageSize, int pageNumber);
	CustomEntity<Main> getEntity(HttpServletRequest request, HttpServletResponse response); 
	
}
