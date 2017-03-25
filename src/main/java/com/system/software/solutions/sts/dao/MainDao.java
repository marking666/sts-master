package com.system.software.solutions.sts.dao;

import java.util.HashMap;
import java.util.List;

import com.system.software.solutions.sts.model.CustomEntity;
import com.system.software.solutions.sts.model.Main;
 

public interface MainDao {

 
	void save(Main Main);
	public Main getMain(long id);
	public void update(Main entity);
	public List<Main> findAll(String term,String col,int pageSize,int pageNumber);
	List<Main> findAll();

	CustomEntity<Main> getEntity(String term, String col,String order, int pageSize, int pageNumber);

	CustomEntity<Main> getEntity(List<HashMap> filters,String col, String order, int pageSize, int pageNumber);

}

