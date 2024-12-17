package com.example.demo_sendmultmails.service;

import com.example.demo_sendmultmails.utils.SendMulMailsUtils;
import com.example.demo_sendmultmails.beans.InfosBean;
import com.example.demo_sendmultmails.beans.MsgBean;
import org.springframework.stereotype.Service;

/**
 * @author kfflso
 * @data 2024-12-17 11:22
 * @plus:
 */
@Service
public class EmailService {
    public void run() {
        // init msg
        // img or attachment should not add too many to avoid oom;
        String subject = "带附件和图片的邮件示例"; //主题
        String content = "这是带有图片和附件的邮件示例"; // 内容
        String img_paths[] = {"path/to/image1.jpg", "path/to/image2.jpg"}; // 图片路径
        String attachment_path[] = {"path/to/attachment1.txt", "path/to/attachment2.txt"};
        MsgBean msgBean = new MsgBean(subject, content, img_paths, attachment_path);

        //send msg
        for (String[] user : InfosBean.RECEIVERS) {
            String userName = user[0];
            String userEmail = user[1];
            if(userName == null || userName.isEmpty()){
                userName = userEmail;
            }
            SendMulMailsUtils.sendEmail(userName, userEmail, msgBean);
        }
    }

}
