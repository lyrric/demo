package com.demo.rabbit.mq.producer;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created on 2018/6/12.
 * 生产者-生产任务
 * @author wangxiaodong
 */
public class ProducerFrame extends JFrame{

    private JButton sendBtn;

    public ProducerFrame() throws HeadlessException {
        setTitle("生产者:" + new Random().nextInt());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(400,250);
        //居中显示, 需要在setSize后面
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        JPanel panel = (JPanel) this.getContentPane();
        panel.setLayout(null);


        sendBtn = new JButton("发它100次");
        sendBtn.setBounds(130,100,120,50);
        panel.add(sendBtn);


        setVisible(true);
    }
}
