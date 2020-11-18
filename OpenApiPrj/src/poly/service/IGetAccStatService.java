package poly.service;

import java.util.Map;

import poly.dto.AccStatDTO;

public interface IGetAccStatService {

	/*
	 * OPENAPI서버로부터 전달받은 JSON 데이터
	 * 
	 * 교통사고 건수 가져오기
	 */
	
	Map<String, Object> getAccStatforJSON(AccStatDTO pDTO) throws Exception;
}
