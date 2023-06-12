package com.javarush.ivannikov.spring.project1.dao;

import com.javarush.ivannikov.spring.project1.domain.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class TaskDAOImpl implements TaskDAO {

    private final SessionFactory sessionFactory;


    public TaskDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<Task> getAllTask(int offset, int limit) {
        Query<Task> query = getSession().createQuery("select t from Task t", Task.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Integer getAllCount() {
        Query<Long> query = getSession().createQuery("select count (t) from Task t", Long.class);
        return Math.toIntExact(query.uniqueResult());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Task getById(Long id) {
        Query<Task> query = getSession().createQuery("select t from Task t where t.id= :id", Task.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdate(Task task) {
        getSession().persist(task);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Task task) {
        getSession().remove(task);
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
