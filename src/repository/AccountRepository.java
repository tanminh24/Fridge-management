package repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.Account;
import model.AccountRole;
import model.TuLanh;
import utility.HibernateUtil;

public class AccountRepository {

	public List<Account> getAll() {
		List<Account> accounts = null;
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Query<Account> query = session.createQuery("FROM Account acc", Account.class);
		accounts = query.getResultList();
		session.close();
		return accounts;
	}

	public Account getByUsername(String username) {
		List<Account> accounts = getAll();
		for (Account account : accounts) {
			if (account.getUsername().equals(username)) {
				return account;
			}
		}
		return null;
	}

	public void create(Account acc) {
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(acc);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new RuntimeException(e);
		}
		session.close();
	}

	public void update(Account acc) {
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(acc);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new RuntimeException(e);
		}
		session.close();
	}

	public void delete(Account acc) {
		SessionFactory factory = HibernateUtil.getFACTORY();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(acc);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new RuntimeException(e);
		}
		session.close();
	}

}
