package cn.cloud.email.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;

import com.sun.mail.util.MailConnectException;

import cn.cloud.email.config.EmailConfig;
import cn.cloud.email.config.EmailSeeting;
import cn.cloud.email.message.MailMessage;

public class CloudEmail implements Runnable {
	
	private EmailSeeting es;
	
	private boolean Restart = false;
	
	public CloudEmail(String htmlContent,String recipientAddress) {
		EmailConfig ec = new EmailConfig();
		es = ec.loadConfig();
		es.setHtmlContent(htmlContent);
		es.setRecipientAddress(recipientAddress);
	}
	
	
	@Override
	public void run() {

        /**
         *  创建参数配置, 用于连接邮件服务器的参数配置
         *  Create parameter configuration, parameter configuration for connecting to the mail server
         */
		
		// 参数配置对象   -> Parameter configuration Object
        Properties props = new Properties();
        // 使用的协议（JavaMail规范要求）-> Protocol used (JavaMail specification required)
        props.setProperty("mail.transport.protocol", es.getProtocol());   
        // 发件人的邮箱的 SMTP 服务器地址 -> The SMTP server address of the sender's mailbox
        props.setProperty("mail.smtp.host", es.getHost()); 
        // 请求认证 -> Request for certification
        props.setProperty("mail.smtp.auth", "true");           
        
        // 开启 SSL 连接 -> Turn on SSL connection
        props.setProperty("mail.smtp.port", es.getSmtpPort());
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", es.getSmtpPort());
		
        // 根据配置创建会话对象, 用于和邮件服务器交互 -> Create a session object based on the configuration to interact with the mail server
        Session session = Session.getInstance(props);
        // 设置为debug模式, 可以查看详细的发送 log -> Set to debug mode, you can view the detailed send log
        session.setDebug(es.isDebug());
        Message msg;
        try {
        	msg = MailMessage.getOneTextMail(session, es);
        	
        	//根据session对象获取邮件传输对象Transport -> Get the mail transfer object Transport according to the session object
	        Transport transport = session.getTransport();
	   
	        //设置发件人的账户名和密码 -> Set the sender's account name and password
	        transport.connect(es.getSenderAddress(),es.getSenderPassword());
	        
	        /*
	         * 发送邮件，并发送到所有收件人地址，message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
	         * Send the message and send it to all recipient addresses. 
	         * message.getAllRecipients() gets all the recipients added when creating the mail object, Cc, Bcc
	         */
	        transport.sendMessage(msg,msg.getAllRecipients());
	        
	        //关闭邮件连接 -> Close mail connection
	        transport.close();
	        System.out.println("邮件发送成功  -> CloudMail（JavaMail 1.2）");
	        System.out.println("Email sent successfully -> CloudMail (JavaMail 1.2)");
        } catch (MailConnectException e) {
        	if ( ! Restart ) {
        		Restart = true;
        		System.err.println("发生了网络连接错误(链接超时)，轻云邮箱正在重新发送...");
        		System.err.println("A network connection error has occurred (link timeout), the light cloud mailbox is being resent...");
        		//重启此线程  -> Restart this thread
        		this.run();
        		System.err.println("【如若显示发送成功，请忽略此信息】似乎仍然无法正常发送，请检测网络状态。");
        		System.err.println("[If the display is successful, please ignore this message] It seems that it still cannot be sent normally. Please check the network status.");
        	}
        }catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		Thread t = new Thread(this);
		t.start();
	}
	
}
