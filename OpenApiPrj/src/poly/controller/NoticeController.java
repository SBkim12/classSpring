package poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poly.dto.NoticeDTO;
import poly.service.INoticeService;
import poly.util.CmmUtil;

@Controller
public class NoticeController {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "NoticeService")
	private INoticeService noticeService;

	@RequestMapping(value = "/notice/noticeList", method = RequestMethod.GET)
	public String NoticList(HttpServletRequest request, HttpServletResponse response, 
			ModelMap model) throws Exception {
		
		log.info(this.getClass().getName() + ".NoticeList start!");
		
		List<NoticeDTO> rList = noticeService.getNoticeList();
		
		if(rList==null) {
			log.info("데이터를 못가져옴");
			rList = new ArrayList<NoticeDTO>();
		}
		
		model.addAttribute("rList",rList);

		rList = null;
		
		log.info(this.getClass().getName() + ".NoticeList end!");
	
		return "/notice/noticeList";
	}
	
	@RequestMapping(value="notice/NoticeReg", method=RequestMethod.GET)
	public String NoticeReg(HttpServletRequest request, HttpServletResponse response, 
			ModelMap model) throws Exception {
		
		log.info(this.getClass().getName() + ".NoticeReg start!");
		
		log.info(this.getClass().getName() + ".NoticeReg end!");
		
		return "/notice/NoticeReg";
	}
	
	@RequestMapping(value="notice/NoticeInsert", method=RequestMethod.POST)
	public String NoticeInsert(HttpSession session, HttpServletRequest request, HttpServletResponse response, 
			ModelMap model) throws Exception {
		
		log.info(this.getClass().getName() + ".NoticeInsert start!");
		
		String msg = "";
		
		try{
			String user_id = CmmUtil.nvl((String)session.getAttribute("SESSION_USER_ID")); //?꾩씠??
			String title = CmmUtil.nvl(request.getParameter("title")); //?쒕ぉ
			String noticeYn = CmmUtil.nvl(request.getParameter("noticeYn")); //怨듭?湲 ?щ?
			String contents = CmmUtil.nvl(request.getParameter("contents")); //?댁슜
	
			log.info("user_id : "+ user_id);
			log.info("title : "+ title);
			log.info("noticeYn : "+ noticeYn);
			log.info("contents : "+ contents);		
			
			NoticeDTO pDTO = new NoticeDTO();
			
			pDTO.setUser_id(user_id);
			pDTO.setTitle(title);
			pDTO.setNotice_yn(noticeYn);;
			pDTO.setContents(contents);
	
			
			int res = noticeService.InsertNoticeInfo(pDTO);

			
			msg = "성공";
			
			
			pDTO = null;
			
		}catch(Exception e){
			
			msg = "실패 이유 : "+ e.toString();
			log.info(e.toString());
			e.printStackTrace();
			
		}finally{
			log.info(this.getClass().getName() + ".NoticeInsert end!");
			
			model.addAttribute("msg", msg);
			
		}
		
		return "/notice/MsgToList";
	}
	
	@RequestMapping(value="notice/NoticeInfo", method=RequestMethod.GET)
	public String NoticeInfo(HttpServletRequest request, HttpServletResponse response, 
			ModelMap model) throws Exception {
		
		log.info(this.getClass().getName() + ".NoticeInfo start!");
		

		String nSeq = CmmUtil.nvl(request.getParameter("nSeq")); //怨듭?湲踰덊샇(PK)

		log.info("nSeq : "+ nSeq);
		
		NoticeDTO pDTO = new NoticeDTO();

		
		pDTO.setNotice_seq(nSeq);;		
		
		noticeService.updateNoticeReadCnt(pDTO);
		
		log.info("read_cnt update success!!!");
		
		NoticeDTO rDTO = noticeService.getNoticeInfo(pDTO);
		
		if (rDTO==null){
			rDTO = new NoticeDTO();
			
		}
		
		log.info("getNoticeInfo success!!!");
		
		//議고쉶??由ъ뒪??寃곌낵媛??ｌ뼱二쇨린
		model.addAttribute("rDTO", rDTO);
		
		//蹂??珥덇린??硫붾え由??⑥쑉???쒗궎湲??꾪빐 ?ъ슜??
		rDTO = null;
		pDTO = null;
		
		log.info(this.getClass().getName() + ".NoticeInfo end!");
		
		return "/notice/NoticeInfo";
	}

	@RequestMapping(value="notice/NoticeEditInfo", method=RequestMethod.GET)
	public String NoticeEditInfo(HttpServletRequest request, HttpServletResponse response, 
			ModelMap model) throws Exception {
		
		log.info(this.getClass().getName() + ".NoticeEditInfo start!");
		
		
		String nSeq = CmmUtil.nvl(request.getParameter("nSeq")); //怨듭?湲踰덊샇(PK)
		
		log.info("nSeq : "+ nSeq);
		
		
		NoticeDTO pDTO = new NoticeDTO();
		
		pDTO.setNotice_seq(nSeq);;		
		
		NoticeDTO rDTO = noticeService.getNoticeInfo(pDTO);
		
		if (rDTO==null){
			rDTO = new NoticeDTO();
			
		}
		
		model.addAttribute("rDTO", rDTO);
		
		
		rDTO = null;
		pDTO = null;
		
		log.info(this.getClass().getName() + ".NoticeEditInfo end!");
		
		return "/notice/NoticeEditInfo";
	}
	
	
	@RequestMapping(value="notice/NoticeUpdate", method=RequestMethod.POST)
	public String NoticeUpdate(HttpSession session, HttpServletRequest request, HttpServletResponse response, 
			ModelMap model) throws Exception {
		
		log.info(this.getClass().getName() + ".NoticeUpdate start!");
		
		String msg = "";
		
		try{
			
			String user_id = CmmUtil.nvl((String)session.getAttribute("SESSION_USER_ID")); //?꾩씠??
			String nSeq = CmmUtil.nvl(request.getParameter("nSeq")); //湲踰덊샇(PK)
			String title = CmmUtil.nvl(request.getParameter("title")); //?쒕ぉ
			String noticeYn = CmmUtil.nvl(request.getParameter("noticeYn")); //怨듭?湲 ?щ?
			String contents = CmmUtil.nvl(request.getParameter("contents")); //?댁슜
	
			log.info("user_id : "+ user_id);
			log.info("nSeq : "+ nSeq);
			log.info("title : "+ title);
			log.info("noticeYn : "+ noticeYn);
			log.info("contents : "+ contents);		
			
			NoticeDTO pDTO = new NoticeDTO();
			
			pDTO.setUser_id(user_id);
			pDTO.setNotice_seq(nSeq);;
			pDTO.setTitle(title);
			pDTO.setNotice_yn(noticeYn);;
			pDTO.setContents(contents);
	
			noticeService.updateNoticeInfo(pDTO);
			
			msg = "?섏젙?섏뿀?듬땲??";
			
			pDTO = null;
			
		}catch(Exception e){
			msg = "?ㅽ뙣?섏??듬땲?? : "+ e.toString();
			log.info(e.toString());
			e.printStackTrace();
			
		}finally{
			log.info(this.getClass().getName() + ".NoticeUpdate end!");
			
			model.addAttribute("msg", msg);
			
		}
		
		return "/notice/MsgToList";
	}	
	
	
	@RequestMapping(value="notice/NoticeDelete", method=RequestMethod.POST)
	public String NoticeDelete(HttpSession session, HttpServletRequest request, HttpServletResponse response, 
			ModelMap model) throws Exception {
		
		log.info(this.getClass().getName() + ".NoticeDelete start!");
		
		String msg = "";
		
		try{
			
			String nSeq = CmmUtil.nvl(request.getParameter("nSeq")); //湲踰덊샇(PK)
			
			log.info("nSeq : "+ nSeq);
			
			NoticeDTO pDTO = new NoticeDTO();
			
			pDTO.setNotice_seq(nSeq);;
			
			noticeService.deleteNoticeInfo(pDTO);;
			
			msg = "??젣?섏뿀?듬땲??";
			
			pDTO = null;
			
		}catch(Exception e){
			msg = "?ㅽ뙣?섏??듬땲?? : "+ e.toString();
			log.info(e.toString());
			e.printStackTrace();
			
		}finally{
			log.info(this.getClass().getName() + ".NoticeDelete end!");
			
			//寃곌낵 硫붿떆吏 ?꾨떖?섍린
			model.addAttribute("msg", msg);
			
		}
		
		return "/notice/MsgToList";
	}
}
