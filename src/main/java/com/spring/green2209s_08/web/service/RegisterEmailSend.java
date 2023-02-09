package com.spring.green2209s_08.web.service;

import com.spring.green2209s_08.constants.ControllerConst;
import com.spring.green2209s_08.web.exception.EmailSendException;
import com.spring.green2209s_08.web.exception.EmailErrorResult;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegisterEmailSend {

    private final JavaMailSender mailSender;

    private String certificationCode;

    public MimeMessage createMessage(String target) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
        message.addRecipients(MimeMessage.RecipientType.TO, target);
        message.setSubject("[올 블루] 회원가입 이메일 인증");

        String sendMessage = "";
        sendMessage += "<div style='margin:100px;'>";
        sendMessage += "<br>";
        sendMessage += "<p><span style='font-size:16px; font-weight:bolder; text-align:center'>안녕하세요, 대한민국 NO.1 수족관</span><p>";
        sendMessage += "<p><span style='font-size:36px; font-weight:bolder; text-align:center'>올 블루 입니다.</span><p>";
        sendMessage += "<p>- 회원가입을 원하시면 아래의 링크를 클릭해주세요.<p>";
        sendMessage += "<p>- 인증 유효시간까지 인증이 완료되지 않으면 다시 인증을 받으셔야 합니다.<p>";
        sendMessage += "<p>- 인증 유효시간은 1시간 입니다.<p>";
        sendMessage += "<br>";
        sendMessage += "<br>";
        sendMessage += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        sendMessage += "<h3 style='color:#3f37c9; font-weight:bolder'>회원가입 인증 코드입니다.</h3>";
        sendMessage += "<div style='font-size:130%'>";
        sendMessage += "<a href='http://localhost:9090"+ ControllerConst.URL +"/member/email/verify?token="+certificationCode+"'>올 블루 회원가입하기</a>";
        sendMessage += "</div>";
        message.setText(sendMessage, "utf-8", "html");

        return message;
    }

    private String createKey() {
        UUID uuid = UUID.randomUUID();
        String key = uuid.toString().replace("-", "");
        return key.toString();
    }

    public String sendMail(String target) {
        certificationCode = createKey();

        try {
            MimeMessage message = createMessage(target);
            mailSender.send(message);

        } catch (MailException e) {
            e.printStackTrace();
            throw new EmailSendException(EmailErrorResult.FAIL_SEND_EMAIL);
        } catch (MessagingException e) {
            throw new EmailSendException(EmailErrorResult.FAIL_SEND_EMAIL);
        } catch (UnsupportedEncodingException e) {
            throw new EmailSendException(EmailErrorResult.FAIL_SEND_EMAIL);
        }

        return certificationCode;
    }
}
