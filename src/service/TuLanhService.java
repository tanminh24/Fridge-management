package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import model.TuLanh;
import repository.TuLanhRepository;
import utility.HibernateUtil;

public class TuLanhService {

	private final TuLanhRepository tuLanhRepo;

	public TuLanhService() {
		tuLanhRepo = new TuLanhRepository();
	}

	public List<TuLanh> getAll() {
		return tuLanhRepo.getAll();
	}

	public TuLanh getById(int id) {
		return tuLanhRepo.getById(id);
	}

	public Boolean create(TuLanh tl) {
		try {
			tuLanhRepo.create(tl);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean update(TuLanh tl) {
		try {
			tuLanhRepo.update(tl);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean delete(TuLanh tl) {
		try {
			tuLanhRepo.delete(tl);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<TuLanh> getByFilter(String name, String quantity, BigDecimal priceMin, BigDecimal priceMax,
			Boolean status,int startPosition, int endPosition) {
		return tuLanhRepo.getByFilter(name, quantity, priceMin, priceMax, status, startPosition,endPosition);
	}
	
	public int countTL(Boolean status) {
		return tuLanhRepo.countTL(status);
	}

}
