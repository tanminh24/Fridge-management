package service;

import model.Account;
import repository.AccountRepository;

public class AuthenticationService {

	private final AccountRepository accountRepo;

	public AuthenticationService() {
		accountRepo = new AccountRepository();
	}

	public Account authenticate(String username, String password) {
		Account account = accountRepo.getByUsername(username);
		if (account == null) {
			return null;
		}
		if (!account.getPassword().equals(password)) {
			return null;
		}
		return account;
	}

}
