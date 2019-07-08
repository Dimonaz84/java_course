package pl.stqa.pft.mantis.appmanager;

import org.apache.commons.net.telnet.TelnetClient;
import pl.stqa.pft.mantis.model.MailMessage;

import javax.mail.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JamesHelper {

    private ApplicationManager app;

    private TelnetClient telnet;
    private InputStream in;
    private PrintStream out;

    private Session mailSession;
    private Store store;
    private String mailServer;

    public JamesHelper(ApplicationManager app) {
        this.app = app;
        telnet = new TelnetClient();
        mailSession = Session.getDefaultInstance(System.getProperties());
    }

    public boolean doesUserExist(String username) {
        initTelnetSession();
        write("verify " + username);
        String result = readUntil("exists");
        closeTelnetSession();
        return result.trim().equals("User " + username + " exists");
    }

    public void createUser(String username, String password) {
        initTelnetSession();
        write("adduser " + username + " " + password);
        String result = readUntil("User " + username + " added");
        closeTelnetSession();
    }

    public void deleteUser(String username) {
        initTelnetSession();
        write("deluser " + username);
        String result = readUntil("User " + username + " deleted");
        closeTelnetSession();
    }

    private String readUntil(String pattern) {
        try{
            char lastChar = pattern.charAt(pattern.length() - 1);
            StringBuffer sb = new StringBuffer();
            char ch = (char) in.read();
            while (true) {
                System.out.print(ch);
                sb.append(ch);
                if (ch == lastChar) {
                    if (sb.toString().endsWith(pattern)) {
                        return sb.toString();
                    }
                }
                ch = (char) in.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void write(String value) {
        try {
            out.println(value);
            out.flush();
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initTelnetSession() {
        mailServer = app.getProperty("mailserver.host");
        int port = Integer.parseInt(app.getProperty("mailserver.port"));
        String login = app.getProperty("mailserver.adminLogin");
        String password = app.getProperty("mailserver.adminPassword");

        try {
            telnet.connect(mailServer, port);
            in = telnet.getInputStream();
            out = new PrintStream(telnet.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        readUntil("Login id:");
        write("");
        readUntil("Password:");
        write("");

        readUntil("Login id:");
        write(login);
        readUntil("Password:");
        write(password);

        readUntil("Welcome " + login + ". HELP for a list of commands");
    }

    private void closeTelnetSession() {
        write("quit");
    }

    public List<MailMessage> waitForMail(String username, String password, long timeout) throws MessagingException {
        long now = System.currentTimeMillis();
        while (System.currentTimeMillis() < now + timeout) {
            List<MailMessage> allMail = getAllMail(username, password);
            if (allMail.size() > 0){
                return allMail;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        throw new Error("No mail!");
    }

    private Folder openInbox(String username, String password) throws MessagingException {
        store = mailSession.getStore("pop3");
        store.connect(mailServer, username, password);
        Folder folder = store.getDefaultFolder().getFolder("INBOX");
        folder.open(Folder.READ_WRITE);
        return folder;
    }

    private void closeFolder(Folder folder) throws MessagingException {
        folder.close(true);
        store.close();
    }

    public List<MailMessage> getAllMail(String username, String password) throws MessagingException {
        Folder inbox = openInbox(username, password);
        List<MailMessage> messages = Arrays.asList(inbox.getMessages()).stream().map((m) -> toModelMail(m)).collect(Collectors.toList());
        closeFolder(inbox);
        return messages;
    }

    public static MailMessage toModelMail(Message m) {
        try {
            return new MailMessage(m.getAllRecipients()[0].toString(), (String)m.getContent());
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
