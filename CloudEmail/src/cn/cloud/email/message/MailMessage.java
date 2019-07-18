package cn.cloud.email.message;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import cn.cloud.email.config.EmailSeeting;

public class MailMessage {
	
	/**
     * 获得创建一封邮件的实例对象，仅发送给一个人，适合验证码
     * Get an instance object that creates a message, only sent to one person, suitable for verification code
     * @param session
     * @return
     * @throws MessagingException
     * @throws AddressException
     */
	public static MimeMessage getOneTextMail(Session session,EmailSeeting es) throws Exception{
		//创建一封邮件的实例对象 -> Create an instance object for a message
        MimeMessage msg = new MimeMessage(session);
        //设置发件人地址 -> Set the sender address
        msg.setFrom(new InternetAddress(es.getSenderAddress()));
        try {
            //设置发件人地址及昵称 -> Set sender address and nickname
            msg.setFrom(new InternetAddress(MimeUtility.encodeText(es.getSenderName())+" <"+es.getSenderAddress()+">"));
        } catch (UnsupportedEncodingException e) {
        	System.out.println("尝试创建一封文本邮件时发生了错误。");
            e.printStackTrace();  
        }
        /*
         * 设置收件人地址（可以增加多个收件人、抄送、密送），即下面这一行代码书写多行
         * 但是需要写另外的方法以配置多人发送
         * Set the recipient address (you can add multiple recipients, CC, Bcc), that is, the following line of code to write multiple lines
         * But you need to write another way to configure multiple people to send
         * MimeMessage.RecipientType.TO:发送
         * MimeMessage.RecipientType.CC：抄送
         * MimeMessage.RecipientType.BCC：密送
         */
        msg.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(es.getRecipientAddress()));
        //设置邮件主题 -> Set up mail subject
        msg.setSubject(es.getSubject(),"UTF-8");
        //设置邮件正文 -> Set the message body
        msg.setContent(es.getHtmlContent(), "text/html;charset=UTF-8");
        //设置邮件的发送时间,默认立即发送 -> Set the sending time of the mail, send it by default
        msg.setSentDate(new Date());
        //保存上面的所有设置 -> Save all the settings above
        msg.saveChanges();
        return msg;
        
	}
	
}
