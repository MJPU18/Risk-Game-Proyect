package co.edu.unbosque.util;

import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

/**
 * Clase encargada de las pruebas unitarias del MailSender
 * 
 * @param emailFrom    String que almacena el correo desde donde se envia el
 *                     correo
 * @param passwordFrom String que almacena la contrase�a del correo desde donde
 *                     se envia el correo
 * @param prop         Objeto de la clase {@link Properties}
 * @param session      Objeto de la clase {@link Session}
 * @param mimeMessage  Objeto de la clase {@link MimeMessage}
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Juan Pablo
 *         Urrego Cortez, Jeisson Nicolas Uyaban Prieto
 */
public class MailSender {

	private static String emailFrom = "automatonJavaSender@gmail.com";
	private static String passwordFrom = "lmnxukpiduqypaur";

	private static Properties prop = new Properties();
	private static Session session;
	private static MimeMessage mimeMessage;

	/**
	 * Metodo encargado de el envio de el correo electronico
	 * 
	 * @return booleano que representa si se realiza o no correctamente el envio del
	 *         correo
	 * @throws MessagingException excepcion que salta en caso de que no se haya
	 *                            podido enviar el correo
	 */
	public static boolean sendEmail() throws MessagingException {
		Transport transport = session.getTransport("smtp");
		transport.connect(emailFrom, passwordFrom);
		transport.sendMessage(mimeMessage, mimeMessage.getRecipients(Message.RecipientType.TO));
		transport.close();

		return true;
	}

	// CREACION DE CODIGO, ASUNTO Y EL DESTINATARIO
	/**
	 * Metodo encargado de crear el mensaje que ira en el correo y establecer
	 * destinatarios del correo
	 * 
	 * @param emailTo String que almacena el correo electronico del destinatario
	 * @return String que almacena el uuid
	 * @throws AddressException   excepcion que salta en caso de que el correo sea
	 *                            invalido
	 * @throws MessagingException excepcion que salta en caso de que no se haya
	 *                            podido enviar el correo
	 */
	public static void createAuthEmail(String emailTo, String emailContent)
			throws AddressException, MessagingException {
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		prop.setProperty("mail.smtp.starttls.enable", "true");
		prop.setProperty("mail.smtp.port", "587");
		prop.setProperty("mail.smtp.user", emailFrom);
		prop.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
		prop.setProperty("mail.smtp.auth", "true");
		session = Session.getDefaultInstance(prop);

		String content = emailContent;

		String subject = "Risk Game | Game Summary";

		mimeMessage = new MimeMessage(session);
		mimeMessage.setFrom(new InternetAddress(emailFrom));
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
		mimeMessage.setSubject(subject);

		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(content, "text/html; charset=utf-8");
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);

		mimeMessage.setContent(multipart);

	}

	public static String getEmailFrom() {
		return emailFrom;
	}

	public static void setEmailFrom(String emailFrom) {
		MailSender.emailFrom = emailFrom;
	}

	public static String getPasswordFrom() {
		return passwordFrom;
	}

	public static void setPasswordFrom(String passwordFrom) {
		MailSender.passwordFrom = passwordFrom;
	}

	public static Properties getProp() {
		return prop;
	}

	public static void setProp(Properties prop) {
		MailSender.prop = prop;
	}

	public static Session getSession() {
		return session;
	}

	public static void setSession(Session session) {
		MailSender.session = session;
	}

	public static MimeMessage getMimeMessage() {
		return mimeMessage;
	}

	public static void setMimeMessage(MimeMessage mimeMessage) {
		MailSender.mimeMessage = mimeMessage;
	}

}
