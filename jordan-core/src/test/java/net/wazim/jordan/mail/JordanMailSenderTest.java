package net.wazim.jordan.mail;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;
import net.wazim.jordan.domain.BluRay;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.mail.MessagingException;
import java.util.Iterator;

import static net.wazim.jordan.fixtures.BluRayDataFixtures.someUnownedBluRay;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class JordanMailSenderTest {

    private SimpleSmtpServer server;

    @Before
    public void startUpEmailServer() {
        server = SimpleSmtpServer.start(1500);
    }

    @After
    public void shutDownEmailServer() {
        server.stop();
    }

    @Test
    public void canSendAnEmail() throws MessagingException {
        BluRay bluRay = someUnownedBluRay();
        JordanMailSender mailSender = new JordanMailSender("localhost", "1500");
        mailSender.send("test@test.com", bluRay);

        assertThat(server.getReceivedEmailSize(), is(1));
        Iterator receivedEmail = server.getReceivedEmail();
        SmtpMessage receivedMessage = (SmtpMessage) receivedEmail.next();
        assertThat(receivedMessage.getHeaderValue("Subject"), is(String.format("[Project Jordan] %s has become available", bluRay.getName())));
        assertThat(receivedMessage.getBody(), containsString(bluRay.getUrl()));
    }

    @Ignore("Uses the Gmail SMTP server to send an email. Won't work unless password is set on environment")
    @Test
    public void sendActualEmail() throws MessagingException {
        BluRay bluRay = someUnownedBluRay();
        JordanMailSender mailSender = new JordanMailSender("smtp.gmail.com");
        mailSender.send("jon@wazim.net", bluRay);
    }
}
