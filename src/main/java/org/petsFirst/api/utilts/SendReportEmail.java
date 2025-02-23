package org.petsFirst.api.utilts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendReportEmail {

	public static void sendReportViaEmail() {
		final String username = "muhammadfiazcs@gmail.com";
		final String password = "hpmeajgdfskigqol";
		String from = "muhammadfiazcs@gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		//587
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		try {
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			String[] toParticipants = { "aqsaaruj13@gmail.com" };
			for (String to : toParticipants) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			}

			message.setSubject("TestSuite Report");
			String htmlContent = "<html><body>" + "<p>Hi,</p>" + "<p>Please find the Report attached.</p>"
					+ "</body></html>";
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(htmlContent, "text/html");

			String reportsPath = System.getProperty("user.dir") + "/reports/testCasesResult";
			
			File zipFile = new File(reportsPath + ".zip");
			zipFolder(reportsPath, zipFile.getPath());
			MimeBodyPart attachmentPart = new MimeBodyPart();
			DataSource source = new FileDataSource(zipFile);
			attachmentPart.setDataHandler(new DataHandler(source));
			attachmentPart.setFileName(zipFile.getName());
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			multipart.addBodyPart(attachmentPart);

			message.setContent(multipart);

			Transport.send(message);
			System.out.println("Email sent successfully!");
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
	}

	private static void zipFolder(String srcFolder, String destZipFile) throws IOException {
		try (FileOutputStream fos = new FileOutputStream(destZipFile); ZipOutputStream zos = new ZipOutputStream(fos)) {

			File folder = new File(srcFolder);
			if (folder.isDirectory()) {
				for (File file : folder.listFiles()) {
					addToZip("", file, zos);
				}
			}
		}
	}

	private static void addToZip(String path, File file, ZipOutputStream zipOut) throws IOException {
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				addToZip(path + file.getName() + "/", f, zipOut);
			}
			System.out.println("Zip file created successfully!");
			return;
		}

		try (FileInputStream fis = new FileInputStream(file)) {
			ZipEntry zipEntry = new ZipEntry(path + file.getName());
			zipOut.putNextEntry(zipEntry);
			byte[] bytes = new byte[1024];
			int length;
			while ((length = fis.read(bytes)) >= 0) {
				zipOut.write(bytes, 0, length);
			}
			zipOut.closeEntry();
		}
	}

}