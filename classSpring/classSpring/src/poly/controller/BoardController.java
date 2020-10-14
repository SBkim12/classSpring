package poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.BoardDTO;
import poly.service.IBoardService;

@Controller
public class BoardController {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "BoardService")
	IBoardService boardService;

	@RequestMapping(value = "/board/boardList")
	public String boardList(ModelMap model) {
		log.info("boardList start!!");

		List<BoardDTO> rList = boardService.getBoardList();

		if (rList == null) {
			rList = new ArrayList<>();
		}

		for (BoardDTO bDTO : rList) {
			log.info("no : " + bDTO.getPost_no());
			log.info("reg_id : " + bDTO.getReg_id());
		}

		model.addAttribute("rList", rList);

		return "/board/boardList";
	}

	@RequestMapping(value = "/board/newPost")
	public String newPost() {
		log.info("newPost start!!");

		return "/board/newPost";
	}

	@RequestMapping(value = "/board/doPost")
	public String doPost(HttpServletRequest request, ModelMap model) {
		log.info("doPost start!!");

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("user_id");
		if(id == null) {
			id = "알수 없는 이용자";
		}
		
		log.info(id);

		String post_title = request.getParameter("post_title");
		String post_content = request.getParameter("post_content");

		log.info(post_title);
		log.info(post_content);

		BoardDTO pDTO = new BoardDTO();

		pDTO.setReg_id(id);
		pDTO.setPost_title(post_title);
		pDTO.setPost_content(post_content);

		int res = boardService.insertPost(pDTO);

		String msg;
		String url = "/board/boardList.do";

		if (res > 0) {
			msg = "등록에 성공했습니다.";
			log.info("등록성공");
		} else {
			msg = "등록에 실패했습니다.";
			log.info("등록 실패");
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "/redirect";
	}

	@RequestMapping(value = "/board/boardDetail")
	public String boardDetail(HttpServletRequest request, ModelMap model) {
		log.info("boardDetail start!!");

		String post_no = request.getParameter("no");

		BoardDTO pDTO = new BoardDTO();
		pDTO.setPost_no(post_no);

		BoardDTO rDTO = boardService.getBoardDetail(pDTO);

		if (rDTO == null) {
			model.addAttribute("msg", "존재하지 않는 게시물입니다.");
			model.addAttribute("url", "/board/boardList.do");
			return "/redirect";
		}

		model.addAttribute("rDTO", rDTO);

		return "/board/boardDetail";
	}

	@RequestMapping(value = "board/editPost")
	public String editPost(HttpServletRequest request, ModelMap model) {
		log.info("boardEdit start!!");

		// 寃뚯떆湲 踰덊샇 諛쏆븘?ㅺ린
		String post_no = request.getParameter("no");

		BoardDTO pDTO = new BoardDTO();
		pDTO.setPost_no(post_no);

		BoardDTO rDTO = boardService.getBoardDetail(pDTO);

		// 寃곌낵媛 ?놁쓣 寃쎌슦 硫붿떆吏? ?④퍡 寃뚯떆湲 紐⑸줉?쇰줈 由щ떎?대젆??
		if (rDTO == null) {
			model.addAttribute("msg", "議댁옱?섏? ?딅뒗 寃뚯떆臾쇱엯?덈떎.");
			model.addAttribute("url", "/board/boardList.do");
			return "/redirect";
		}

		model.addAttribute("rDTO", rDTO);

		return "/board/editPost";
	}

	@RequestMapping(value = "board/doEditPost.do")
	public String doEditPost(HttpServletRequest request, ModelMap model) {

		String post_title = request.getParameter("post_title");
		String post_content = request.getParameter("post_content");
		String post_no = request.getParameter("post_no");

		BoardDTO pDTO = new BoardDTO();

		pDTO.setPost_title(post_title);
		pDTO.setPost_content(post_content);
		pDTO.setPost_no(post_no);

		int res = boardService.updatePost(pDTO);

		String msg;
		String url = "/board/boardList.do";

		if (res > 0) {
			msg = "수정 완료.";

		} else {
			msg = "수정 실패.";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "/redirect";

	}

	@RequestMapping(value = "board/deletePost")
	public String deletePost(HttpServletRequest request, ModelMap model) {
		log.info("boardDelete start!!");

		String post_no = request.getParameter("no");

		BoardDTO pDTO = new BoardDTO();
		pDTO.setPost_no(post_no);

		int res = boardService.deletePost(pDTO);

		String msg;
		String url = "/board/boardList.do";

		if (res > 0) {
			msg = "수정 완료.";

		} else {
			msg = "수정 실패.";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "/redirect";

	}

}
