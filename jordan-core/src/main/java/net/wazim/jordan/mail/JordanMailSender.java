package net.wazim.jordan.mail;

import net.wazim.jordan.domain.BluRay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static javax.mail.Message.RecipientType.TO;

public class JordanMailSender {

    private final Session session;
    private final String username = "no.reply.project.jordan@gmail.com";
    private static final Logger log = LoggerFactory.getLogger(JordanMailSender.class);

    public JordanMailSender(String hostname) throws MessagingException {
        this(hostname, "25");
    }

    public JordanMailSender(String hostname, String port) throws MessagingException {
        if ("smtp.gmail.com".equals(hostname)) {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, System.getenv("EMAIL_PASSWORD"));
                        }
                    });
            log.info("Using Gmail mail sender");
        } else {
            Properties properties = System.getProperties();
            properties.setProperty("mail.smtp.host", hostname);
            properties.setProperty("mail.smtp.port", port);
            session = Session.getDefaultInstance(properties);
            log.info("Using localhost mail sender");
        }
    }

    public void send(String recipientAddress, BluRay bluRay) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(TO, InternetAddress.parse(recipientAddress));
            message.setSubject(String.format("[Project Jordan] %s has become available", bluRay.getName()));
            message.setText(String.format("Get it now: %s " +
                    "\nKind regards, " +
                    "\nProject Jordan"
                    , bluRay.getUrl()));

            Transport.send(message);
            log.info(String.format("Emailed %s informing them that %s has become available", recipientAddress, bluRay.getName()));
        } catch (MessagingException e) {
            log.error(String.format("Failed to email %s", recipientAddress));
            throw new RuntimeException(e);
        }
    }
}
