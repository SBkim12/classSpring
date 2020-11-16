package poly.service;

import java.util.List;
import java.util.Map;

import poly.dto.BoardDTO;

public interface IWordAnalysisService {
	
	/*
	 * 자연어 처리- 행태소 분석(명사만 추출하기)
	 * 
	 * @param 분석학 문장
	 * 
	 * @return 분서결과
	 */
	List<String> doWordNouns(String text) throws Exception;
	
	/*
	 * 빈도수 분석(단어별 출현 빈도수)
	 * 
	 * @param 명사만 추출된 단어 모음(리스트)
	 * 
	 * @return 분석 결과
	 */
	Map<String, Integer> doWordCount(List<String> pList)throws Exception;
	
	/*
	 * 분석할 문장의 자연어 처리 및 빈도수 분석 수행
	 * 
	 * @param 분석할 문장
	 * 
	 * @return 분석 결과
	 */
	Map<String, Integer> doWordAnalysis(String text)throws Exception;
	
	
}
