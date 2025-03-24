package email;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;

public class EmailUtiility {
	
	
	
	 public static void sendEmail(String[] to, String subject, String body, String reportPath, String excelPath) {
	        final String from = "dipesh.singh@lyxelandflamingo.com"; // Sender email
	        final String username = "887f81001@smtp-brevo.com";      // SMTP user
	        final String password = "dpHxUJOMsEv8rw4T";              // SMTP password

	        // SMTP configuration
	        Properties props = new Properties();
	        props.put("mail.smtp.host", "smtp-relay.brevo.com");
	        props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");

	        // Create session
	        Session session = Session.getInstance(props, new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(username, password);
	            }
	        });

	        try {
	            // Validate recipients
	            if (to == null || to.length == 0) {
	                throw new IllegalArgumentException("Recipient list cannot be empty.");
	            }

	            // Convert String[] to a comma-separated String for InternetAddress
	            String recipientAddresses = String.join(",", to);

	            // Create the email message
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(from));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientAddresses));
	            message.setSubject(subject);

	            // Email body
	            MimeBodyPart textPart = new MimeBodyPart();
	            textPart.setText(body);

	            // Attachments
	            Multipart multipart = new MimeMultipart();

	            // Add the email body
	            multipart.addBodyPart(textPart);

	            // Attach the TestNG report if path is valid
	            if (reportPath != null && !reportPath.isEmpty() && new File(reportPath).exists()) {
	                MimeBodyPart reportAttachment = new MimeBodyPart();
	                reportAttachment.attachFile(new File(reportPath));
	                multipart.addBodyPart(reportAttachment);
	            } else {
	                System.err.println("Report file not found or invalid: " + reportPath);
	            }

	            // Attach the Excel file if path is valid
	            if (excelPath != null && !excelPath.isEmpty() && new File(excelPath).exists()) {
	                MimeBodyPart excelAttachment = new MimeBodyPart();
	                excelAttachment.attachFile(new File(excelPath));
	                multipart.addBodyPart(excelAttachment);
	            } else {
	                System.err.println("Excel file not found or invalid: " + excelPath);
	            }

	            // Set the content of the message
	            message.setContent(multipart);

	            // Send the email
	            Transport.send(message);
	            System.out.println("Email sent successfully.");
	        } catch (Exception e) {
	            System.err.println("Failed to send email: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
	}