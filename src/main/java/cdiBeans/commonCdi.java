package cdiBeans;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

import Authentication.KeepRecord;
import composite.Alerts;
import entities.Projectusers;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Properties;
import java.util.Random;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;
import org.primefaces.PrimeFaces;

import serverBeans.commonBeansLocal;


@ManagedBean
@Named(value = "commoncdi")
@SessionScoped
public class commonCdi implements Serializable {
    
    @EJB commonBeansLocal bean;
    String Errorstatus ;
    Alerts alert;
    public commonCdi() {
        Errorstatus = KeepRecord.getErrorStatus();
        user = new Projectusers();
        alert =  new Alerts();
      
    }
       
       


    public String getErrorstatus() {
        return Errorstatus;
    }

    public void setErrorstatus(String Errorstatus) {
        this.Errorstatus = Errorstatus;
    }
    
    private String verificationOTP;
    private String email;
    private String password;
    private String confirmpassword;
    private String userID;
    private boolean msg=false;
    private boolean renderd=false;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
   

    public boolean isRenderd() {
        return renderd;
    }

    public void setRenderd(boolean renderd) {
        this.renderd = renderd;
    }
    
    Projectusers user;

    public boolean isMsg() {
        return msg;
    }

    public void setMsg(boolean msg) {
        this.msg = msg;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String UserID) {
        this.userID = UserID;
    }

    public String getGeneratedOTP() {
        return generatedOTP;
    }

    public void setGeneratedOTP(String generatedOTP) {
        this.generatedOTP = generatedOTP;
    }
    String generatedOTP;
    

    public String getVerificationOTP() {
        return verificationOTP;
    }

    public void setVerificationOTP(String verificationOTP) {
        this.verificationOTP = verificationOTP;
    }

    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String generateOtp(int length) {
        String digits = "0123456789";
        Random random = new Random();
        StringBuilder otpBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(digits.length());
            otpBuilder.append(digits.charAt(index));
        }
        this.generatedOTP = otpBuilder.toString();
        return otpBuilder.toString();
    }
  
    public boolean verifyOTP(){
        
        if(verificationOTP.equals(getGeneratedOTP())){
            return true;
        }
        return false;
    }
   
    public void resetPageData(){
     verificationOTP=null;
     email=null;
     password=null;
     confirmpassword=null;
     userID=null;
     msg=false;
     renderd=false;
    }  
  

    public String resetPassword() throws InterruptedException{
       if(verifyOTP()){
            Pbkdf2PasswordHashImpl pbk = new Pbkdf2PasswordHashImpl();
         String hash = pbk.generate(password.toCharArray());
           user.setPassword(hash);
           bean.resetPassword(user);
            alert.showInfo("Password Changed Successfully!");
            PrimeFaces.current().executeScript("setTimeout(function(){ window.location.href = 'login.jsf'; }, 3000);");
            user = new Projectusers();
            resetPageData();// empty previously stored values  
          return null;
       }
       resetPageData();
        alert.showError("Wrong OTP Or Some Technical Error!!!");
       return "ForgotPassword.jsf";
        
    }
    public boolean userIdNotAvailiable(){
        user = bean.getUserByID(userID);
        msg = user==null;
        if(msg==false){
            renderd = true;
        }else{
            renderd = false;
        }
        return msg;
    }
    
    
    
    public void sendEmail(){
        boolean flag=false;
        if(msg!=true){
            String to = "bhattjaimin2001@gmail.com";
        String from = "bhattjaimin1812@gmail.com";
        String subject = "Your OTP For Password Reset";
        String text = generateOtp(6);
        
        Properties properties =  new Properties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String username = "bhattjaimin1812@gmail.com";
        String mailpassword = "wgsqtaihenmumsaz";
        Session session = Session.getInstance(properties,new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, mailpassword);
            }
        });
        
        try{
            MimeMessage message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, InternetAddress.parse(to)[0]);
            message.setFrom(new InternetAddress(from));
            message.setSubject(subject);
            message.setContent(text, "text/html");
            
            Transport.send(message);
            flag = true;
            alert.showInfo("OTP Sent Successfully!");
        }catch(Exception ex){
            ex.printStackTrace();
        }
        }
        
        
        
    }
}
