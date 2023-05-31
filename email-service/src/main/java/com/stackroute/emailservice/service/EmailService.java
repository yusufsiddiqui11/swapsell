package com.stackroute.emailservice.service;

import com.stackroute.emailservice.dto.MailRequest;
import com.stackroute.emailservice.dto.MailResponse;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class EmailService {
    @Autowired
    private Configuration configuration;
    @Autowired
    private JavaMailSender sender;

    public MailResponse sendEmail(MailRequest request, Map<String, Object> model){
        System.out.println("In Service layer ");
        MailResponse response = new MailResponse();
        MimeMessage message  = sender.createMimeMessage();
        try {
            System.out.println("make response");
            System.out.println(response);
            MimeMessageHelper helper = new MimeMessageHelper(message,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,StandardCharsets.UTF_8.name());
            Template t = configuration.getTemplate("successfulPayment.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
            helper.setTo(request.getTo());
            helper.setText(html,true);
            helper.setSubject(request.getSubject());
            helper.setFrom(request.getFrom());
            sender.send(message);

//            response.setMessage("mail send to : "+request.getTo());
            response.setStatus(Boolean.TRUE);
//            System.out.println("Email send");

        } catch (MessagingException | IOException | TemplateException e){
            response.setMessage("Mail sending failure : "+e.getMessage());
        }
        return response;
    }
}
