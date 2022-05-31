package com.oredoo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.util.Random;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/mail")
public class MailController {

    private final Logger LOGGER = LogManager.getLogger(MailController.class);

    @Autowired
    private JavaMailSender sender;

    @GetMapping(value = "/send")
    public void sendMail() {
        Random random = new Random();
        String password = String.valueOf(10000000 + random.nextInt(90000000));
        try {
            String content = buildEmail(password);
            MimeMessage mimeMessage = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setText(content, true);
            helper.setTo("thanhphuong151001@gmail.com");
            helper.setSubject("Forgot your password?");
            helper.setFrom("tom1810.2001@gmail.com");
            sender.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.error("Failed to send email for: " + "\n" + e);
            throw new IllegalArgumentException("Failed to send email for: " + "");
        }

    }

    private String buildEmail(String password) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
            "\n" +
            "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
            "\n" +
            "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;" +
            "width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
            "    <tbody><tr>\n" +
            "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
            "        \n" +
            "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" " +
            "cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
            "          <tbody><tr>\n" +
            "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
            "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" " +
            "style=\"border-collapse:collapse\">\n" +
            "                  <tbody><tr>\n" +
            "                    <td style=\"padding-left:10px\">\n" +
            "                  \n" +
            "                    </td>\n" +
            "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;" +
            "padding-left:10px\">\n" +
            "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;" +
            "color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Verify your " +
            "account</span>\n" +
            "                    </td>\n" +
            "                  </tr>\n" +
            "                </tbody></table>\n" +
            "              </a>\n" +
            "            </td>\n" +
            "          </tr>\n" +
            "        </tbody></table>\n" +
            "        \n" +
            "      </td>\n" +
            "    </tr>\n" +
            "  </tbody></table>\n" +
            "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" " +
            "cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;" +
            "width:100%!important\" width=\"100%\">\n" +
            "    <tbody><tr>\n" +
            "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
            "      <td>\n" +
            "        \n" +
            "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" " +
            "border=\"0\" style=\"border-collapse:collapse\">\n" +
            "                  <tbody><tr>\n" +
            "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
            "                  </tr>\n" +
            "                </tbody></table>\n" +
            "        \n" +
            "      </td>\n" +
            "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
            "    </tr>\n" +
            "  </tbody></table>\n" +
            "\n" +
            "\n" +
            "\n" +
            "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" " +
            "cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;" +
            "width:100%!important\" width=\"100%\">\n" +
            "    <tbody><tr>\n" +
            "      <td height=\"30\"><br></td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
            "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;" +
            "max-width:560px\">\n" +
            "        \n" +
            "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " +
            ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> This is your " +
            "new password. Do not share it to anyone: </p><blockquote style=\"Margin:0 0" +
            " 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p " +
            "style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"javascript:void(0)" +
            "\">" + password + "</a> </p></blockquote>\n <p>Thank you for using our blog</p>" +
            "        \n" +
            "      </td>\n" +
            "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "      <td height=\"30\"><br></td>\n" +
            "    </tr>\n" +
            "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
            "\n" +
            "</div></div>";
    }
}
