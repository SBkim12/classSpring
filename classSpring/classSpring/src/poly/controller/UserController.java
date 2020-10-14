package poly.controller;

import static poly.util.CmmUtil.nvl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.UserInfoDTO;
import poly.service.IUserService;
import poly.util.EncryptUtil;

@Controller
public class UserController {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "UserService")
	private IUserService userService;

	@RequestMapping(value = "/user/userLogin")
	public String userLogin() {
		log.info(this.getClass() + "user/userLogin start!!");

		log.info(this.getClass() + "user/userLogin end!!");
		return "/user/userLogin";
	}

	@RequestMapping(value = "/user/userLoginProc")
	public String userLoginProc(HttpServletRequest request, Model model, HttpSession session) throws Exception {
		log.info(this.getClass() + "user?userLoginProc start!!");
		
		int res =0; 
		
		UserInfoDTO uDTO = null;
		
		try {
			String id = nvl(request.getParameter("id"));
			String pwd = nvl(EncryptUtil.encHashSHA256(request.getParameter("pwd")));
			
			log.info("id : " + id);
			log.info("pwd : " + pwd);
			
			uDTO = new UserInfoDTO();
			
			uDTO.setUser_id(id);
			uDTO.setUser_pwd(pwd);
			
			res = userService.getUserInfo(uDTO);
			if(res==1) {
				session.setAttribute("SS_USER_ID", uDTO.getUser_id());
			}
		}catch(Exception e) {
			res =2;
			log.info(e.toString());
			e.printStackTrace();
		}finally {
			log.info(this.getClass().getName() + ".insertUserInfo end");
			
			model.addAttribute("res", String.valueOf(res));
			
			uDTO = null;
		}
		

		return "/user/LoginResult";
	}

	@RequestMapping(value = "/user/logOut")
	public String userLoginProc(Model model, HttpSession session) throws Exception {
		log.info(this.getClass() + "user/logOut start!!");

		String msg = "";
		String url = "";
		msg = "로그아웃 성공";

		session.invalidate();

		url = "/";

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		log.info("로그아웃 끝");
		return "/redirect";
	}

	/* ajax /user/usearchList */
	@RequestMapping(value = "/user/userSearchList")
	public @ResponseBody List<UserInfoDTO> userSearchList(HttpServletRequest request) throws Exception {
		log.info(this.getClass() + ".user/userSearchList start!!");
		String name = nvl(request.getParameter("name"));
		log.info("name : " + name);

		UserInfoDTO uDTO = new UserInfoDTO();
		uDTO.setUser_name(name);

		List<UserInfoDTO> uList = userService.getUserList(uDTO);
		log.info("uList size : " + uList.size());

		log.info(this.getClass() + ".user/userSearchList end!!");
		return uList;
	}

	// 회원가입 화면 이동
	@RequestMapping(value = "user/userRegForm")
	public String userRegForm() {
		log.info(this.getClass().getName() + ".user/userRegForm ok!");

		return "/user/UserRegForm";
	}

	// 회원가입 로직 처리
	@RequestMapping(value = "user/insertUserInfo")
	public String insertUserInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		log.info(this.getClass().getName() + ".insertUserInfo start!");

		// 회원가입 겨과에 대한 메시지 전달할 변수
		String msg = "";

		// 웹에서 받은 정보 저장할 변수
		UserInfoDTO pDTO = null;

		try {

			String user_id = nvl(request.getParameter("user_id")); // 아이디
			String user_name = nvl(request.getParameter("user_name")); // 이름
			String user_pwd = nvl(request.getParameter("user_pwd")); // 비밀번호
			String email = nvl(request.getParameter("email")); // 이메일
			String addr1 = nvl(request.getParameter("addr1")); // 주소
			String addr2 = nvl(request.getParameter("addr2")); // 상세주소

			log.info("user_id : " + user_id);
			log.info("user_name : " + user_name);
			log.info("user_pwd : " + user_pwd);
			log.info("email : " + email);
			log.info("addr1 : " + addr1);
			log.info("addr2 : " + addr2);

			// 웹에서 받은 정보를 저장할 변수를 메모리에 올리기
			pDTO = new UserInfoDTO();

			pDTO.setUser_id(user_id);
			pDTO.setUser_name(user_name);
			pDTO.setUser_pwd(EncryptUtil.encHashSHA256(user_pwd));
			pDTO.setEmail(EncryptUtil.encAES128CBC(email));
			pDTO.setAddr1(addr1);
			pDTO.setAddr2(addr2);

			// 회원가입
			int res = userService.insertUserInfo(pDTO);

			if (res == 1) {
				msg = "회원가입되었습니다.";

			} else if (res == 2) {

				msg = "이미 가입된 이메일 주소입니다.";

			} else {
				msg = "오류로 인해 회원가입이 실패하였습니다.";

			}
		} catch (Exception e) {
			// 저장 실패시 사용자에게 보여줄 메시지
			msg = "실패하였습니다. : " + e.toString();
			log.info(e.toString());
			e.printStackTrace();

		} finally {
			log.info(this.getClass().getName() + ".insertUserInfo end!");

			// 회원가입 여부 결과 메시지 전달하기
			model.addAttribute("msg", msg);
			log.info(msg);
			
			// 회원가입 여부 결과 메시지 전달하기
			model.addAttribute("pDTO", pDTO);

			// 변수 초기화(메모리 효율화 시키기 위해)
			pDTO = null;

		}

		return "/user/Msg";
	}
}
