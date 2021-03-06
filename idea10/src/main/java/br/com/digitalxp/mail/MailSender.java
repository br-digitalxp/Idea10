package br.com.digitalxp.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

	public void enviarEmail(final String subject, final String text) throws AddressException, MessagingException {

		final String username = "ordemservicoidea10@gmail.com";
		final String password = "OrdemIde@10";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("idea10"));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(username));
		message.setSubject(subject);
		message.setText(text);

		Transport.send(message);

	}
}