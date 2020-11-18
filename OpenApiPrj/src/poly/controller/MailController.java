package poly.controller;

import static poly.util.CmmUtil.nvl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poly.dto.MailDTO;
import poly.service.IMailService;

@Controller
public class MailController {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "MailService")
	private IMailService mailService;
	
	@RequestMapping(value ="/mail/mailForm")
	public String mailForm() {
		log.info("mailForm start");

		log.info("mailForm end");
		return "/mail/mailForm";
	}
	
	@RequestMapping(value ="/mail/sendMail", method = RequestMethod.POST)
	public String sendMail(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
		log.info(this.getClass().getName() + "mail.sendMail start!");
		
		//웹 URL로부터 전달받는 값
		String toMail = nvl(request.getParameter("toMail"));
		String title = nvl(request.getParameter("title"));
		String contents = nvl(request.getParameter("contents"));
		
		log.info("받는 사람 :" +toMail);
		log.info("제목 :" +title);
		log.info("내용 :" +contents);
		
		//메일 발송할 정보 넣기 위한 DTO 객체 생성
		MailDTO pDTO = new MailDTO();
		
		//웹에서 받은 값을 DTO에 넣기
		pDTO.setContents(contents);
		pDTO.setTitle(title);
		pDTO.setToMail(toMail);
		
		//메일발송하기
		int res = mailService.doSendMail(pDTO);
		
		if(res==1) {
			log.info(this.getClass().getName() + "mail.sendMail success");
		}else {
			log.info(this.getClass().getName() + "mail.sendMail fail!!");
		}
		
		//메일 발송 겨로가를 JSP에 전달하기(데이터 전달시, 숫자보단 문자열이 컨트롤하기 편하기 때문에 강제로 숫자를 문자로 변환함)
		model.addAttribute("res", String.valueOf(res));
		
		log.info(this.getClass().getName() + "mail.sendMail end!");
		
		return "/mail/sendMailResult";
	}
}
