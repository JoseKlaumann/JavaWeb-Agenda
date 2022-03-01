package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import db.DbException;

public class Dao {
	
	EntityManagerFactory emf;
	EntityManager em;
	
	public Dao() { 
		emf = Persistence.createEntityManagerFactory("agenda");
		em = emf.createEntityManager();
	}
	
	public void insert (JavaBeans obj) {
		try {
//			em = null;
			em.getTransaction().begin();
			em.persist(obj);
			em.getTransaction().commit();
		} 
		catch (RuntimeException e) {
			em.getTransaction().rollback();
			throw new DbException(e.getMessage());
		}
	}
	
	public void update (JavaBeans obj) {
		try {
//			em = null;
			em.getTransaction().begin();
			em.merge(obj);
			em.getTransaction().commit();
		}
		catch(RuntimeException e) {
			em.getTransaction().rollback();
			throw new DbException(e.getMessage());
		}
	}
	
	public void delete (String id) {
		try {
//			em = null;
			em.getTransaction().begin();
			JavaBeans aluno = findById(id);
			em.remove(aluno);
			em.getTransaction().commit();
		}
		catch (RuntimeException e) {
			em.getTransaction().rollback();
			throw new DbException(e.getMessage());
		}
	}
	
	public JavaBeans findById (String id) {
		try {
			return em.find(JavaBeans.class, id);
		} 
		catch(RuntimeException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<JavaBeans> findAll() {
		try {
			return em.createQuery("FROM " +
					JavaBeans.class.getName()).getResultList();
		} 
		catch(RuntimeException e) {
			throw new DbException(e.getMessage());
		}
	}	
}
