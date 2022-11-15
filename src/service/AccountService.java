package service;

import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import model.Account;
import repository.AccountRepository;

public class AccountService {

	private final AccountRepository accountRepo;

	public AccountService() {
		accountRepo = new AccountRepository();
	}

	public List<Account> getAllProduct() {
		return accountRepo.getAll();
	}

	public Account getByUserName(String userName) {
		return accountRepo.getByUsername(userName);
	}

	public Boolean create(Account acc) {
		try {
			accountRepo.create(acc);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean update(Account acc) {
		try {
			accountRepo.update(acc);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean delete(Account acc) {
		try {
			accountRepo.delete(acc);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String sendEmailRegister(String toEmail) {

		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		String verifyCode = String.format("%06d", number);

		final String fromEmail = "minhlhtph18449@fpt.edu.vn";
		final String password = "240101minh";

		try {
			// Thông số kết nối Smtp Server
			Properties pr = new Properties();
			pr.put("mail.smtp.host", "smtp.gmail.com");
			pr.put("mail.smtp.port", "587");
			pr.put("mail.smtp.auth", "true");
			pr.put("mail.smtp.starttls.enable", "true");

			// Kết nối Smtp Sever
			Session session = Session.getInstance(pr, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			});

			Message mess = new MimeMessage(session);
			mess.setFrom(new InternetAddress(fromEmail));
			mess.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			mess.setSubject("Xác thực tài khoản");
			mess.setContent(
					"<h2 style=\"color: black;\">Xác thực tài khoản tại\r\n"
					+ "    <a style=\"color: blue;\" href=\"http://localhost:8080/PH18449_ASM/register-verify\" role=\"button\">ĐÂY</a>\r\n"
					+ "</h2>"
					, "text/html; charset=utf-8");

			Transport.send(mess);
			System.out.println("Gửi email thành công!");
			return verifyCode;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Lỗi gửi mail");
		}

		return null;

	}

	public String sendEmailForgotPassword(String toEmail) {

		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		String verifyCode = String.format("%06d", number);

		final String fromEmail = "minhlhtph18449@fpt.edu.vn";
		final String password = "240101minh";

		try {
			// Thông số kết nối Smtp Server
			Properties pr = new Properties();
			pr.put("mail.smtp.host", "smtp.gmail.com");
			pr.put("mail.smtp.port", "587");
			pr.put("mail.smtp.auth", "true");
			pr.put("mail.smtp.starttls.enable", "true");

			// Kết nối Smtp Sever
			Session session = Session.getInstance(pr, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			});

			Message mess = new MimeMessage(session);
			mess.setFrom(new InternetAddress(fromEmail));
			mess.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			mess.setSubject("Xác thực tài khoản");
			mess.setText("Mã xác thực của bạn là: " + verifyCode);

			Transport.send(mess);
			System.out.println("Gửi email thành công!");
			return verifyCode;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Lỗi gửi mail");
		}

		return null;

	}

}
