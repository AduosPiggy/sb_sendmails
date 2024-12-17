package com.example.demo_sendmultmails.beans;

/**
 * @author kfflso
 * @data 2024-12-17 11:33
 * @plus:
 */
public class InfosBean {
    /*************************** 发件人邮箱信息 *****************************/
    public static final String SMTP_HOST = "smtp.example.com"; // SMTP服务器地址
    public static final String SMTP_PORT = "587"; // SMTP端口
    public static final String USERNAME = "your_email@example.com"; // 发件人邮箱
    public static final String PASSWORD = "your_password"; // 发件人邮箱密码


    /*************************** 收件人邮箱信息 *****************************/
    // UserName 随便填, mail 需要正确
    public static final String[][] RECEIVERS = {
            {"UserName1", "user1@example.com"},
            {"UserName2", "user2@example.com"},
            {"UserName3", "user3@example.com"}
    };

}
