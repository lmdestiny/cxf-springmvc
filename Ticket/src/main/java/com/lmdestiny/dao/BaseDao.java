package com.lmdestiny.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDao<T>{

	@Autowired
	private SessionFactory sessionFactory;
	private Class<T> clazz;
	/**
	 * 使用反射获取子类声明的具体泛型类型，使子类无需传入泛型类型参数
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BaseDao() {
		Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        clazz = (Class) pt.getActualTypeArguments()[0];
	}
	@SuppressWarnings("unchecked")
	public T merge(T entity) {
		// TODO Auto-generated method stub
		return (T) getCurrentSession().merge(entity);
	}
	public void flush() {
		getCurrentSession().flush();
	}
	public void refresh(T entity) {
		getCurrentSession().refresh(entity);
	}
	public void persist(T entity) {
		// TODO Auto-generated method stub
		getCurrentSession().persist(entity);
	}
	public void remove(T entity) {
		// TODO Auto-generated method stub
		getCurrentSession().remove(entity);
	}
	public void clear() {
		getCurrentSession().clear();
	}

	public T find(int id) {
		// TODO Auto-generated method stub
		return (T) getCurrentSession().find(clazz, id);
	}
	
	public T find(String o) {
		// TODO Auto-generated method stub
		return (T) getCurrentSession().find(clazz, o);
	}
	
	public List<T> find() {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(clazz);
		Root<T> root = criteriaQuery.from(clazz);
		criteriaQuery.orderBy(builder.asc(root.get("plate")));
		TypedQuery<T> typedQuery = getCurrentSession().createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}
	
	public List<T> findUsers() {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(clazz);
		Root<T> root = criteriaQuery.from(clazz);
		criteriaQuery.orderBy(builder.asc(root.get("plate")));
		TypedQuery<T> typedQuery = getCurrentSession().createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}
	
	public List<T> find(int firstResult, int maxResults) {
		CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(clazz);
		Root<T> root = criteriaQuery.from(clazz);
		criteriaQuery.orderBy(builder.asc(root.get("id")));
		TypedQuery<T> typedQuery = getCurrentSession().createQuery(criteriaQuery);
		typedQuery.setMaxResults(maxResults).setFirstResult(firstResult);
		return typedQuery.getResultList();
	}
	
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

}
