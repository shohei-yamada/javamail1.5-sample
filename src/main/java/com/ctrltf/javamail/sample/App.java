package com.ctrltf.javamail.sample;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class App {
    public static void main( String[] args ) {
        // x-windows-iso2022jpをISO-2022-JPのエイリアスに
        System.setProperty("sun.nio.cs.map", "x-windows-iso2022jp/ISO-2022-JP");

        Properties properties = new Properties();

        // SMTPサーバのホスト名 or IPアドレス
        properties.put("mail.smtp.host", "localhost");
        // SMTPサーバのポート
        properties.put("mail.smtp.port", "25");

        // デバッグ情報を出力する場合
        properties.put("mail.debug", "true");

        try {
            Session session = Session.getDefaultInstance(properties);

            // 送信メッセージ
            MimeMessage message = new MimeMessage(session);

            // From
            message.setFrom(new InternetAddress("test@example.com"));
            // 宛先（TO）
            message.setRecipients(MimeMessage.RecipientType.TO,
                                  new InternetAddress[] { new InternetAddress("user@example.com") });

            // 件名および本文
            message.setSubject("件名です", "ISO-2022-JP");
            message.setText("こんにちは、JavaMail\r\n①②③", "ISO-2022-JP");

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        
    }
}
