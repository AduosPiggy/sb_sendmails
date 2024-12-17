package com.example.demo_sendmultmails.beans;

/**
 * @author kfflso
 * @data 2024-12-17 11:57
 * @plus: 发送的消息
 */
public class MsgBean {
    private String subject; // 主题
    private String content; // 文本内容
    private String img_path[]; // 将发送的图片路径
    private String attachment_path[]; // 将发送的附件路径

    public MsgBean(String subject, String content, String[] img_path, String[] attachment_path) {
        this.subject = subject;
        this.content = content;
        this.img_path = img_path;
        this.attachment_path = attachment_path;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String[] getImg_path() {
        return img_path;
    }

    public String[] getAttachment_path() {
        return attachment_path;
    }
}
