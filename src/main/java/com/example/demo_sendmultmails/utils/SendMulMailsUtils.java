package com.example.demo_sendmultmails.utils;

/**
 * @author kfflso
 * @data 2024-12-17 11:16
 * @plus:
 */

import com.example.demo_sendmultmails.beans.InfosBean;
import com.example.demo_sendmultmails.beans.MsgBean;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.io.File;
import java.util.Properties;

public class SendMulMailsUtils {

    public static void sendEmail(String userName, String userEmail, MsgBean msgBean) {
        Properties props = new Properties();
        props.put("mail.smtp.host", InfosBean.SMTP_HOST);
        props.put("mail.smtp.port", InfosBean.SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // 使用TLS

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(InfosBean.USERNAME, InfosBean.PASSWORD);
            }
        });

        try {
            // 创建邮件消息
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(InfosBean.USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject(msgBean.getSubject());

            // 创建多部分内容
            MimeMultipart multipart = new MimeMultipart();

            // 添加文本内容
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(msgBean.getContent());
            multipart.addBodyPart(textPart);

            // 添加图片
            // 添加图片
            for (String imgPath : msgBean.getImg_path()) {
                if (imgPath != null && !imgPath.isEmpty()) {
                    File imgFile = new File(imgPath);
                    if (imgFile.exists() && imgFile.canRead()) { // 检查文件是否存在且可读
                        MimeBodyPart imagePart = new MimeBodyPart();
                        DataSource fds = new FileDataSource(imgFile);
                        imagePart.setDataHandler(new DataHandler(fds));
                        imagePart.setFileName(imgFile.getName());
                        imagePart.setHeader("Content-ID", "<image>");
                        multipart.addBodyPart(imagePart);
                    } else {
                        System.out.println("文件不可用: " + imgPath);
                    }
                }
            }


//            // 添加图片到文本
//            MimeBodyPart imageTextPart = new MimeBodyPart();
//            imageTextPart.setContent("<h1>带图片的邮件</h1><img src='cid:image'>", "text/html");
//            multipart.addBodyPart(imageTextPart);

            // 添加附件
            for (String attachmentPath : msgBean.getAttachment_path()) {
                if (attachmentPath != null && !attachmentPath.isEmpty()) {
                    File attachmentFile = new File(attachmentPath);
                    if (attachmentFile.exists() && attachmentFile.canRead()) { // 检查文件是否存在且可读
                        MimeBodyPart attachmentPart = new MimeBodyPart();
                        DataSource source = new FileDataSource(attachmentFile);
                        attachmentPart.setDataHandler(new DataHandler(source));
                        attachmentPart.setFileName(attachmentFile.getName());
                        multipart.addBodyPart(attachmentPart);
                    } else {
                        System.out.println("附件不可用: " + attachmentPath);
                    }
                }
            }


            // 设置邮件内容
            message.setContent(multipart);

            // 发送邮件
            Transport.send(message);
            System.out.println("邮件已发送给: " + userName + " (" + userEmail + ")");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}


