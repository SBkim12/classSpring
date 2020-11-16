package poly.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.service.IWordAnalysisService;

@Controller
public class WordController {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "WordAnalysisService")
	private IWordAnalysisService wordAnalysisServcie;

	
	@RequestMapping(value = "word/analysis")
	@ResponseBody
	public Map<String, Integer> analysis() throws Exception {
		log.info(this.getClass() + ".inputForm!");
		
		//분석할 문장
		String text = "한국폴리텍대학 서울강서캠퍼스 데이터분석과는 최고야!";
		
		//신조어 및 새롭게 생겨난 가수 및 그룹명은 제대로된 분석이 불가능함
		//새로운 명사 단어들은 어떻게 데이터를 처리해야 할까? => 정답은 데이터 사전의 주기적인 업데이트
		//String text ="난 아이오아이가 최고의 가수라고 생각해. 올해의 가수는 당연히 아이오아이지";
		
		Map<String, Integer> rMap = wordAnalysisServcie.doWordAnalysis(text);
		
		if(rMap == null) {
			rMap = new HashMap<String, Integer>();
		}
		
		return rMap;
	}

}
