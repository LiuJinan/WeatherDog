package cn.liujinnan.weather.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

@Component("EmailSender")
public class EmailSender implements Sender {

    private static final Logger logger = LoggerFactory.getLogger(EmailSender.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String senderEmailName;

    @Override
    public void send(Message message) {
        EmailMesssage emailMesssage = (EmailMesssage)message;
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            String nickname = MimeUtility.encodeText(emailMesssage.getNickname());
            mimeMessage.setFrom(new InternetAddress(nickname + "<"+senderEmailName+">"));
            mimeMessage.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(emailMesssage.getReceiver()));
            mimeMessage.setSubject(emailMesssage.getTitle());
            mimeMessage.setText(emailMesssage.getContext());
            javaMailSender.send(mimeMessage);
            logger.info("发送邮件成功, emailMesssage={}", emailMesssage);
        } catch (Exception e) {
            logger.error("邮件发送错误", e);
        }
    }
}
