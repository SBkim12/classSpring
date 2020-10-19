package poly.service;

import poly.dto.OcrDTO;

public interface IOcrService {

	//아마지 파일로부터 문자 일어 오기
	OcrDTO getReadforImageText(OcrDTO pDTO)throws Exception;
	
}
