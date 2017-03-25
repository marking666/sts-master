package com.system.software.solutions.sts.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.software.solutions.sts.dao.MainDao;
import com.system.software.solutions.sts.model.AbstractEntity;
import com.system.software.solutions.sts.model.CustomEntity;
import com.system.software.solutions.sts.model.Main;
import com.system.software.solutions.sts.model.User;
import com.system.software.solutions.sts.service.MainService;

@Service("mainService")
@Transactional(value="transactionManagerMSsql")
public class MainServiceImpl implements MainService {

	@Autowired
	private MainDao dao;
	
	@Override
	public void saveMain(Main Main) {
		System.out.println("entra a save Main+++++");
		dao.save(Main);
	}

	
	@Override
	public List<Main> findAll() {
		 List<Main> list =new ArrayList<Main>();
		 list=dao.findAll();
		return list;
	}

	@Override
	public List<Main> find(String term,String col,int pageSize,int pageNumber) {
		 List<Main> list =new ArrayList<Main>();
		 list=dao.findAll(term,col,pageSize,pageNumber);
		return list;
	}

	@Override
	public AbstractEntity<Main> getEntity(String term, String col,String order, int pageSize, int pageNumber) {
		return null;//dao.getEntity(term, col, order, pageSize, pageNumber);
	}

	@Override
	public CustomEntity<Main> getEntity(HttpServletRequest request, HttpServletResponse response) {

		 HttpSession session=request.getSession();
   	 boolean edit= session.getAttribute("edit")==null?false:Boolean.parseBoolean(String.valueOf(session.getAttribute("edit")));
	 String col= request.getParameter("col");
   	 String order=request.getParameter("order");
    
   	 int pageNumber =Integer.parseInt(request.getParameter("page"));
   	 int pageSize=Integer.parseInt(request.getParameter("pageSize"));
   	 String filter= request.getParameter("filter");
 

   	ObjectMapper  objectMapper =new ObjectMapper();
	List<HashMap> filters = new ArrayList<HashMap>();
   	if(filter!=null)
   	{
   	try {
	 
   		 filters =
   	   			objectMapper.readValue(filter, objectMapper.getTypeFactory().constructCollectionType(List.class, HashMap.class));
	 
   	
	 	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  
   	}
   	 CustomEntity<Main> grid=dao.getEntity(filters,col,order, pageSize, pageNumber);
   	grid.setEditable(edit);
  return grid;
	}


	@Override
	public void update(Main entity) {
	dao.update(entity);
		
	}

	
	public Main findById(long id) {		 
		return dao.getMain(id);
	}
	
}
