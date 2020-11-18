package poly.controller;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import poly.dto.OcrDTO;
import poly.service.IOcrService;
import poly.util.CmmUtil;
import poly.util.DateUtil;
import poly.util.FileUtil;

@Controller
public class OcrController {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "OcrService")
	private IOcrService ocrService;

	final private String FILE_UPLOAD_SAVE_PATH = "c:/upload"; // C://upload 폴더에 저장
	
	@RequestMapping(value="ocr/imageFileUpload")
	public String Index() {
		log.info(this.getClass().getName() + ".imageFileUpload!");
		
		return "/ocr/ImageFileUpload";
	}
	
	
	/*
	 * @requestParam(value="fileUpload")는 파일이 커지면 파일의 전송이 100 완료 되기 전까지 돌아가지 않기때문에
	 * 속도가 느려짐 (작은파일만 쓸때만 사용)*/
	@RequestMapping(value="ocr/getReadforImageText")
	public String getReadForImageText(HttpServletRequest request, HttpServletResponse response, ModelMap model, 
			@RequestParam(value = "fileUpload") MultipartFile mf) throws Exception{
		
		log.info(this.getClass().getName() + ".imageeadForImageText Start!");
		
		
		// OCR 실행 결과
		String res = "";
		
		//업로드하는 실제 파일명
		//다운로드 기능 구현시, 임의로 정의된 파이명을 원래대로 만들어주기 위한 목적
		String originalFileName = mf.getOriginalFilename();
		
		//파일 확장자 가져오기
		String ext = originalFileName.substring(originalFileName.lastIndexOf(".")+1, originalFileName.length()).toLowerCase();
		
		//이미지 파일만 실행되도록 함
		if(ext.equals("jpeg") || ext.equals("jpg") || ext.equals("gif") || ext.equals("png")) {
			
			//웹서버에 저장되는 파일 이름
			//업로드하는 파일 이름에 한글, 특수 문자들이 저장될수 있기 때문에 강제로 영와 숫자로 구성된 파일명으로 변경해서 저장한다.
			//리눅스나 유닉스 등 운영체제는 다국어 지원에 취약하기 때문이다.
			String saveFileName = DateUtil.getDateTime("24hhmmss") + "." + ext;
			
			//웹서버에 업로드한 파일 저장ㅎ나느 물리적 경로
			String saveFilePath = FileUtil.mkdirForDate(FILE_UPLOAD_SAVE_PATH);
			
			String fullFileInfo = saveFilePath + "/" + saveFileName;
			
			//정상적으로 값이 생성되었는지 로그로 확인
			log.info("ext : " + ext);
			log.info("saveFileName : " + saveFileName);
			log.info("saveFilePath : " + saveFilePath);
			log.info("fullFileInfo : "  + fullFileInfo);
			
			//업로드 되는 파일을 서버에 저장
			mf.transferTo(new File(fullFileInfo));
			
			OcrDTO pDTO = new OcrDTO();
			
			pDTO.setSave_file_name(saveFileName);
			pDTO.setSave_file_path(saveFilePath);
			pDTO.setOrg_file_name(originalFileName);
			pDTO.setExt(ext);
			pDTO.setReg_id("Kim");
			
			OcrDTO rDTO = ocrService.getReadforImageText(pDTO);
			
			if(rDTO == null) {
				rDTO = new OcrDTO();
			}
			
			res = CmmUtil.nvl(rDTO.getOcr_text());
			
			rDTO = null;
			pDTO = null;
			
		}else {
			res = "이미지 파일이 아니라서 인식이 불가능 합니다.";
		}
		
		//크롤링 결과를 넣어주기
		model.addAttribute("res", res);
		
		log.info(this.getClass().getName() + "getReadForImageText end!");
		
		return "/ocr/TextFromImage";
	}
}
