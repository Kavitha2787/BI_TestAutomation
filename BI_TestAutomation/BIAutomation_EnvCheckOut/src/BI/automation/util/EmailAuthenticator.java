package BI.automation.util;
import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
public class EmailAuthenticator extends Authenticator {

    String user;
    String pw;
    public EmailAuthenticator (String username, String password)
    {
        super();
        this.user = username;
        this.pw = password;
    }
    public PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication(user, pw);
    }
  }
