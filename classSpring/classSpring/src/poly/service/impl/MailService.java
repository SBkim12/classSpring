package poly.service.impl;

import static poly.util.CmmUtil.nvl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.dto.MailDTO;
import poly.service.IMailService;

@Service("MailService")
public class MailService implements IMailService{

	private Logger log = Logger.getLogger(this.getClass());
	
	final String host = "smtp.naver.com";
	final String user = "mar5924@naver.com";
	final String password = "wkdqja9112!@";
	
	@Override
	public int doSendMail(MailDTO pDTO) {

		log.info(this.getClass().getName() + ".doSendMail start!");
		
		// 메일 발송 성공 여부(발송성공 : 1 / 발송실패 : 0);
		int res = 1;
		
		//전달받은 DTO로부터 데이터 가져오기(DTO 객체가 메모리에 올라가지 않아 NUll이 발생할수 있기 때문에 에러 방지)
		if(pDTO ==null) {
			pDTO = new MailDTO();
		}
		
		//받는사람
		String toMail = nvl(pDTO.getToMail());
		
		Properties props = new Properties();
		props.put("mail.smtp.host",  host); //javax 외부 라이브러리에 메일 보내느 사람의 정보 설정
		props.put("mail.smtp.auth",  "true"); //javax 외부 라이브러리에 메일 보내느 사람 인증 여부 설정
		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
			
			//메일제목
			message.setSubject(nvl(pDTO.getTitle()));
			
			//메일 내용
			message.setText(nvl(pDTO.getContents()));
			
			//메일 발송
			Transport.send(message);
		
		}catch(MessagingException e) {
			res =0;
			log.info("[ERROR] "+ this.getClass().getName() + ".doSendMail : " + e);
			
		}catch (Exception e) {
			res =0;
			log.info("[ERORR] "+ this.getClass().getName() + ".doSendMail : " + e);
			
		}
		
		log.info(this.getClass().getName() + ".doSendMail end!");
		return res;
	}

}
