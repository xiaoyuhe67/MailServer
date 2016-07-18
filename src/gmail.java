

import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class gmail
 */
@WebServlet("/gmail")
public class gmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Properties props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtps.port", 465);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");
        //NOTE: Session object is part of javax.mail.Session
        javax.mail.Session session = javax.mail.Session.getDefaultInstance(props);
        session.setDebug(true);
        
 Message message = new MimeMessage(session);
        
        String subject=request.getParameter("subject");
        String me=request.getParameter("Message");
        try {
			message.setSubject(subject);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        try {
			message.setText(me);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

        Address fromAddress = new InternetAddress("xiaoyuhe67@gmail.com", "Xiaoyu He");
        Address toAddress = new InternetAddress("xiaoyuhe67@gmail.com", "Xiaoyu He");
        
        try {
			message.setFrom(fromAddress);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			message.setRecipient(Message.RecipientType.TO, toAddress);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Transport transport=null;
		try {
			transport = session.getTransport();
		} catch (NoSuchProviderException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
      
        
        try {
        	transport.connect("xiaoyuhe67@gmail.com","");
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			transport.sendMessage(message, message.getAllRecipients());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			transport.close();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
