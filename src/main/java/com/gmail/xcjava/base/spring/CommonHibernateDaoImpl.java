package com.gmail.xcjava.base.spring;

import java.io.Serializable;
import java.util.List;
import java.util.Collection;
import java.util.Iterator;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CommonHibernateDaoImpl extends HibernateDaoSupport implements CommonHibernateDao {

	public CommonHibernateDaoImpl() {
	}

	public void delete(Object obj) {
		getHibernateTemplate().delete(obj);
	}

	public void deleteAll(Collection objs) {
		getHibernateTemplate().deleteAll(objs);
	}

	public Serializable save(Object obj) {
		return getHibernateTemplate().save(obj);
	}

	public void saveAll(Collection objs) {
		Integer i = 0; // 计数器

		for (Object obj : objs) {
			if (i % 30 == 0) { // 清空内部（一级）缓存
				getHibernateTemplate().flush();
				getHibernateTemplate().clear();
			}

			getHibernateTemplate().save(obj);
			i++;
		}
	}

	public Object get(Class c, Serializable id) {
		return getHibernateTemplate().get(c, id);
	}

	public Object load(Class c, Serializable id) {
		return getHibernateTemplate().load(c, id);
	}
	
	public boolean exist(Class c, Serializable id){
		if(getHibernateTemplate().get(c, id)!=null)
			return true;
		return false;
	}
	
	public Integer count(String hql) {
		Iterator iterator = getHibernateTemplate().iterate(hql);
		if (iterator.hasNext()) {
			return ((Long) iterator.next()).intValue();
		}

		return 0;
	}

	public Integer countBy(String hql, Object obj) {
		Iterator iterator = getHibernateTemplate().iterate(hql, obj);
		if (iterator.hasNext()) {
			return ((Long) iterator.next()).intValue();
		}

		return 0;
	}

	public Integer countBy(String hql, Object[] objs) {
		Iterator iterator = getHibernateTemplate().iterate(hql, objs);
		if (iterator.hasNext()) {
			return ((Long) iterator.next()).intValue();
		}

		return 0;
	}

	public List find(String hql) {
		return getHibernateTemplate().find(hql);
	}

	public List findBy(String hql, Object obj) {
		return getHibernateTemplate().find(hql, obj);
	}

	public List findBy(String hql, Object[] objs) {
		return getHibernateTemplate().find(hql, objs);
	}

	public List find(String hql, Integer startRow, Integer pageSize) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);

		return query.list();
	}

	public List findBy(String hql, Object obj, Integer startRow,
			Integer pageSize) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter(0, obj);
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);

		return query.list();
	}

	public List findBy(String hql, Object[] objs, Type[] types,
			Integer startRow, Integer pageSize) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameters(objs, types);
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);

		return query.list();
	}

	public void update(Object obj) {
		getHibernateTemplate().update(obj);
	}

	public void updateAll(Collection objs) {
		Integer i = 0; // 计数器

		for (Object obj : objs) {
			if (i % 30 == 0) { // 清空内部（一级）缓存
				getHibernateTemplate().flush();
				getHibernateTemplate().clear();
			}

			getHibernateTemplate().update(obj);
			i++;
		}
	}

	public void saveOrUpdate(Object obj) {
		getHibernateTemplate().saveOrUpdate(obj);
	}

	public void saveOrUpdateAll(Collection objs) {
		Integer i = 0; // 计数器

		for (Object obj : objs) {
			if (i % 30 == 0) { // 清空内部（一级）缓存
				getHibernateTemplate().flush();
				getHibernateTemplate().clear();
			}

			getHibernateTemplate().saveOrUpdate(obj);
			i++;
		}
	}

	public Integer bulkUpdateDelete(String hql) {
		return getHibernateTemplate().bulkUpdate(hql);
	}

	public Integer bulkUpdateDelete(String hql, Object obj) {
		return getHibernateTemplate().bulkUpdate(hql, obj);
	}

	public Integer bulkUpdateDelete(String hql, Object[] objs) {
		return getHibernateTemplate().bulkUpdate(hql, objs);
	}

	public void persist(Object obj){
		getHibernateTemplate().persist(obj);
	}
	
	public Object merge(Object obj){
		return getHibernateTemplate().merge(obj);
	}
	
	public void flush(){
		getHibernateTemplate().flush();
	}
	
	public void lock(Object obj,LockMode lockMode){
		getHibernateTemplate().lock(obj,lockMode);
	}
	
	public void clean(){
		getHibernateTemplate().clear();
	}
}
