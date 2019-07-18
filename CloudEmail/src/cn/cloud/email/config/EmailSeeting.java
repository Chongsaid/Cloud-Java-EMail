package cn.cloud.email.config;

public class EmailSeeting {
	
	/**
	 * 发件人地址
	 * Sender address
	 */
    private String senderAddress;
    
    /**
     * 收件人地址
     * receiver's address
     */
    private String recipientAddress;
    
    /**
     * 发件人账户密码
     * Sender account password
     */
    private String senderPassword;
    
    /**
     * 邮件传输协议
     * Mail transfer protocol
     */
    private String protocol;
    
    /**
     * 发件人的邮箱的 SMTP 服务器地址
     * The SMTP server address of the sender's mailbox
     */
    private String host;
    
    /**
     * 是否使用控制台 DEBUG
     * Whether to use the console DEBUG
     */
    private boolean Debug;
    
    /**
     * 发件人昵称
     * Sender nickname
     */
    private String senderName;
    
    /**
     * 邮件主题 - 即标题
     * Mail subject - the title
     */
    private String subject;
    
    /**
     * HTML 文本内容 -> 邮件内容
     * HTML text content -> mail content
     */
    private String htmlContent;
    
    /**
     * SMTP 端口 -> 465
     * SMTP port -> 465
     */
    private String smtpPort;
    
	public String getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public String getRecipientAddress() {
		return recipientAddress;
	}

	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}

	public String getSenderPassword() {
		return senderPassword;
	}

	public void setSenderPassword(String senderPassword) {
		this.senderPassword = senderPassword;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public boolean isDebug() {
		return Debug;
	}

	public void setDebug(boolean debug) {
		Debug = debug;
	}
   
}
