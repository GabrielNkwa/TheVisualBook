package com.example.thevisualbook;

import android.content.Context;
import android.os.AsyncTask;
import android.text.PrecomputedText;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.xml.transform.Result;

public class JavaMailAPI extends AsyncTask<void, void, void> {

    private Context context;
    private Session session;
    private String email, subject, message;

    public JavaMailAPI(Context context, Session session, String email, String subject, String message) {
        this.context = context;
        this.session = session;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    @Override
    protected Result doInBackground(PrecomputedText.Params... params) {
        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(properties, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(Utils.EMAIL, Utils.PASSWORD);

            }
        });

        MimeMessage mimeMessage = new MimeMessage(session);

        try {
            mimeMessage.setForm(new InternetAddress(Utils.Email));
            mimeMessage.addRecipients(Message.RecipientType.TO , String.valueOf( new InternetAddress(email)));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);
            Transport.send(mimeMessage);
        }   catch (MessagingException e){
            e.printStackTrace();
        }

        return null;


    }
}
