/**
 * Hibernate 数据访问对象接口
 */
package com.gmail.xcjava.base.spring;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.type.Type;

public interface CommonHibernateDao {

	/**
	 * 删除
	 * @param obj
	 */
	public void delete(Object obj);

	/**
	 * 批量删除
	 * @param objs
	 */
	public void deleteAll(Collection objs);

	/**
	 * 插入
	 * @param obj
	 * @return
	 */
	public Serializable save(Object obj);

	/**
	 * 批量插入
	 * @param objs
	 */
	public void saveAll(Collection objs);

	/**
	 * 读取
	 * 当对象不存在时返回null
	 * @param c
	 * @param id
	 * @return
	 */
	public Object get(Class c, Serializable id);

	/**
	 * 读取
	 * 当对象不存在时抛出异常
	 * 使用内部缓存和二级缓存中的现有数据
	 * @param c
	 * @param id
	 * @return
	 */
	public Object load(Class c, Serializable id);

	/**
	 * id对应的对象是否存在
	 * @param c
	 * @param id
	 * @return
	 */
	public boolean exist(Class c, Serializable id);
	
	/**
	 * 统计记录数
	 * @param hql 例:select count(*) from Master
	 * @return
	 */
	public Integer count(String hql);

	/**
	 * 按单条件统计记录数
	 * @param hql select count(*) from Master where username = ?
	 * @param obj
	 * @return
	 */
	public Integer countBy(String hql, Object obj);

	/**
	 * 按多条件统计记录数
	 * @param hql 例:select count(*) from Master where username = ? and sex = ?
	 * @param objs
	 * @return
	 */
	public Integer countBy(String hql, Object[] objs);

	/**
	 * 查询全部
	 * @param hql 例:from Master
	 * @return
	 */
	public List find(String hql);

	/**
	 * 按单条件查询
	 * @param hql 例:from Master where username = ?
	 * @param obj
	 * @return
	 */
	public List findBy(String hql, Object obj);

	/**
	 * 按多条件查询
	 * @param hql 例:from Master where username = ? and sex = ?
	 * @param objs
	 * @return
	 */
	public List findBy(String hql, Object[] objs);

	/**
	 * 查询全部（按需查询）
	 * @param hql 例:from Master
	 * @param startRow 起始行数
	 * @param pageSize 每页行数
	 * @return
	 */
	public List find(String hql, Integer startRow, Integer pageSize);

	/**
	 * 按单条件查询（按需查询）
	 * @param hql 例:from Master where username = ?
	 * @param obj
	 * @param startRow 起始行数
	 * @param pageSize 每页行数
	 * @return
	 */
	public List findBy(String hql, Object obj, Integer startRow,
			Integer pageSize);

	/**
	 * 按多条件查询（按需查询）
	 * @param hql 例:from Master where username = ? and sex = ?
	 * @param objs
	 * @param types 对象数组数据类型
	 * @param startRow 起始行数
	 * @param pageSize 每页行数
	 * @return
	 */
	public List findBy(String hql, Object[] objs, Type[] types,
			Integer startRow, Integer pageSize);

	/**
	 * 更新
	 * @param obj
	 */
	public void update(Object obj);

	/**
	 * 批量更新
	 * @param objs
	 */
	public void updateAll(Collection objs);

	/**
	 * 插入／更新
	 * @param obj
	 */
	public void saveOrUpdate(Object obj);

	/**
	 * 批量插入／更新
	 * @param objs
	 */
	public void saveOrUpdateAll(Collection objs);

	/**
	 * 批量更新／删除，绕过缓存，可能产生脏数据
	 * @param hql 例:delete from Master
	 * @return
	 */
	public Integer bulkUpdateDelete(String hql);

	/**
	 * 批量更新／删除，绕过缓存，可能产生脏数据
	 * @param hql 例:update Master set sex = ?
	 * @param obj
	 * @return
	 */
	public Integer bulkUpdateDelete(String hql, Object obj);

	/**
	 * 批量更新／删除，绕过缓存，可能产生脏数据
	 * @param hql 例:delete from Master where username = ? and sex = ?
	 * @param objs
	 * @return
	 */
	public Integer bulkUpdateDelete(String hql, Object[] objs);

	/**
	 * persist把一个瞬态的实例持久化，但是并"不保证"标识符被立刻填入到持久化实例中，标识符的填入可能被推迟到flush的时间
	 * @param obj
	 */
	public void persist(Object obj);
	
	/**
	 * 更新
	 * session存在多个持久实例时,用用户给出的对象的状态覆盖旧有的持久实例 
	 * 如果session没有相应的持久实例，则尝试从数据库中加载，或创建新的持久化实例,最后返回该持久实例
	 * 用户给出的这个对象没有被关联到session上，它依旧是脱管的
	 * @param obj
	 * @return
	 */
	public Object merge(Object obj);
	
	/**
	 * 刷新持久状态的对象
	 */
	public void flush();
	
	/**
	 * 是把一个没有更改过的脱管状态的对象变成持久状态
	 * 采用LockMode.READ,从数据库读取
	 * @param obj
	 * @param lockMode 
	 * 	HIBERNATE级别的锁，也就是缓存级别的锁。 
	 * 	LockMode.NONE ：有缓存用缓存，没缓存则从数据库读 
	 * 	LockMode.READ ：直接从数据库读，不使用缓存数据 
	 * 	LockMode.WRITE ：在insert update数据的时候，HIBERNATE内部使用的。 
	 * 	数据库级别的锁： 
	 * 	LockMode.UPGRADE：相当于SQL语句select for update，被select的数据都被数据库锁住了，不能被其他事务修改。 
	 * 	LockMode. UPGRADE_NOWAIT ：是ORACLE数据库特有的select for update nowait
	 */
	public void lock(Object obj,LockMode lockMode);
	
	/**
	 * 清空内部(一级)缓存
	 */
	public void clean();
}
