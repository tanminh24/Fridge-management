package repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.TuLanh;
import utility.HibernateUtil;

public class TuLanhRepository {

	public List<TuLanh> getAll() {
		List<TuLanh> listTuLanh = null;
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Query<TuLanh> query = session.createQuery("FROM TuLanh tl", TuLanh.class);
		listTuLanh = query.getResultList();
		session.close();
		return listTuLanh;
	}

	public List<TuLanh> getByFilter(String name, String quantity, BigDecimal priceMin, BigDecimal priceMax,
			Boolean status, int startPosition, int endPosition) {
		String hql = "FROM TuLanh tl WHERE tl.ten LIKE :name";
		if (priceMin != null && priceMax != null) {
			hql += " AND tl.gia BETWEEN :priceMin AND :priceMax";
		}
		if (status != null) {
			hql += " AND tl.trangThai= :status";
		}
		if (quantity != null) {
			if (quantity.equalsIgnoreCase("DESC")) {
				hql += " ORDER BY tl.soLuong DESC";
			}
			if (quantity.equalsIgnoreCase("ASC")) {
				hql += " ORDER BY tl.soLuong ASC";
			}
		}
		List<TuLanh> tls = new ArrayList<>();
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Query<TuLanh> query = session.createQuery(hql, TuLanh.class);

		query.setParameter("name", "%" + name + "%");
		if (priceMin != null && priceMax != null) {
			query.setParameter("priceMin", priceMin);
			query.setParameter("priceMax", priceMax);
		}
		if (status != null) {
			query.setParameter("status", status);
		}

		query.setFirstResult(startPosition);
		query.setMaxResults(endPosition);

		tls = query.getResultList();
		session.close();
		return tls;
	}

	public int countTL(Boolean status) {
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		int countTL = 0;
		if (status == null) {
			Query query = session.createQuery("SELECT COUNT(*) FROM TuLanh tl");
			countTL = ((Long) query.uniqueResult()).intValue();
		} else {
			Query query = session.createQuery("SELECT COUNT(*) FROM TuLanh tl WHERE tl.trangThai = :status");
			query.setParameter("status", status);
			countTL = ((Long) query.uniqueResult()).intValue();
		}
		session.close();
		return countTL;
	}

	public TuLanh getById(int id) {
		TuLanh tl = null;
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		tl = session.find(TuLanh.class, id);

		session.close();
		return tl;
	}

	public void create(TuLanh tl) {
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(tl);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new RuntimeException(e);
		}
		session.close();
	}

	public void update(TuLanh tl) {
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(tl);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new RuntimeException(e);
		}
		session.close();
	}

	public void delete(TuLanh tl) {
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(tl);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new RuntimeException(e);
		}
		session.close();
	}

}
