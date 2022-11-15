package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class Account {

	@Id // PRIMARY KEY
	private String username;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private AccountRole role;

	@Column(nullable = false)
	private Boolean verify;
	
	public Account() {

	}

	public Account(String username, String name, String email, String password, AccountRole role, Boolean verify) {
		super();
		this.username = username;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.verify = verify;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AccountRole getRole() {
		return role;
	}

	public void setRole(AccountRole role) {
		this.role = role;
	}

	public Boolean getVerify() {
		return verify;
	}

	public void setVerify(Boolean verify) {
		this.verify = verify;
	}

	@Override
	public String toString() {
		return "Account [username=" + username + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", role=" + role + ", verify=" + verify + "]";
	}
	
}
