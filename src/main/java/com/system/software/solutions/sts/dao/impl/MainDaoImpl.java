package com.system.software.solutions.sts.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.system.software.solutions.sts.dao.MainDao;
import com.system.software.solutions.sts.model.CustomEntity;
import com.system.software.solutions.sts.model.Main;

@Repository("mainDao")
public class MainDaoImpl implements MainDao {

	static final Logger logger = LoggerFactory.getLogger(MainDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactoryMSSql;

	@Override
	public void save(Main main) {

		Session session = sessionFactoryMSSql.getCurrentSession();
		session.persist(main);

	}
	
	public Main getMain(long id)
	{
		Session session = sessionFactoryMSSql.getCurrentSession();
		
		return (Main) session.get(Main.class, id);  
	}
	@Override
	public void update(Main entity) {
		Session session = sessionFactoryMSSql.getCurrentSession();
		session.update(entity);
	}

	@Override
	public List<Main> findAll() {
		Session session = sessionFactoryMSSql.getCurrentSession();

		List<Main> users = (List<Main>) session.createQuery("from Main").list();

		// No need to fetch userProfiles since we are not showing them on list
		// page. Let them lazy load.
		// Uncomment below lines for eagerly fetching of userProfiles if you
		// want.
		/*
		 * for(User user : users){ Hibernate.initialize(user.getUserProfiles());
		 * }
		 */
		return users;
	}

	// http://www.mkyong.com/hibernate/hibernate-criteria-examples/

	@Override
	public List<Main> findAll(String term, String col, int pageSize, int pageNumber) {

		Session session = sessionFactoryMSSql.getCurrentSession();

		Criteria criteria = session.createCriteria(Main.class);

		if (col != null && !col.isEmpty())
			criteria.add(Restrictions.like(col, "%" + term + "%"));

		criteria.setProjection(Projections.rowCount());

		Long resultCount = (Long) criteria.uniqueResult();
		int qtdPaginas = (resultCount.intValue() / pageSize) + 1;

		criteria.setProjection(null);//  
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid
																		// duplicates.

		if (pageNumber > qtdPaginas) {
			pageNumber = qtdPaginas;
		}
		pageNumber = pageNumber - 1;

		criteria.setFirstResult(pageNumber * pageSize);
		criteria.setMaxResults(pageSize);

		List<Main> users = (List<Main>) criteria.list();

		return users;
	}

	
	@Override
	public CustomEntity<Main> getEntity(String term, String col,String order, int pageSize, int pageNumber) {
		CustomEntity<Main> entity=new CustomEntity<Main>();
		 
		Session session = sessionFactoryMSSql.getCurrentSession();

		Criteria criteria = session.createCriteria(Main.class);

		if (col != null && !col.isEmpty())
			criteria.add(Restrictions.like(col, "%" + term + "%"));

		criteria.setProjection(Projections.rowCount());

		Long resultCount = (Long) criteria.uniqueResult();
		int qtdPaginas = (resultCount.intValue() / pageSize) + 1;

		criteria.setProjection(null);//  
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid
																		// duplicates.

		if (pageNumber > qtdPaginas) {
			pageNumber = qtdPaginas;
		}
		pageNumber = pageNumber - 1;

		criteria.setFirstResult(pageNumber * pageSize);
		criteria.setMaxResults(pageSize);

		List<Main> list = (List<Main>) criteria.list();
		entity.setList(list);
		entity.setTotal(resultCount);
		 
	//	entity.s
		return entity;
	}

	@Override
	public CustomEntity<Main> getEntity(List<HashMap> filters,String col, String order, int pageSize, int pageNumber) {
		CustomEntity<Main> entity=new CustomEntity<Main>();
		 
		Session session = sessionFactoryMSSql.getCurrentSession();

		Criteria criteria = session.createCriteria(Main.class);

	 for(HashMap filter:filters)
	 {
		   System.out.println(filter.get("col").toString()+"%" + filter.get("term").toString() + "%");
		 	 criteria.add(Restrictions.like(filter.get("col").toString(), "%" + filter.get("term").toString() + "%")); 	 
	 }
 
	
	
		criteria.setProjection(Projections.rowCount());

		Long resultCount = (Long) criteria.uniqueResult();
		int qtdPaginas = (resultCount.intValue() / pageSize) + 1;

		criteria.setProjection(null);//  
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid
																		// duplicates.

		if (pageNumber > qtdPaginas) {
			pageNumber = qtdPaginas;
		}
		pageNumber = pageNumber - 1;

		criteria.setFirstResult(pageNumber * pageSize);
		criteria.setMaxResults(pageSize);

		 if(col !=null && order!=null )
		 {
			 if(order.endsWith("asc")){
			 criteria.addOrder(Order.asc(col));
			 }else
			 {
				 criteria.addOrder(Order.desc(col));
			 }
		 }
		 
		List<Main> list = (List<Main>) criteria.list();
 
		entity.setList(list);
		entity.setTotal(resultCount);
		 
	 
		return entity;
	}
 
}
