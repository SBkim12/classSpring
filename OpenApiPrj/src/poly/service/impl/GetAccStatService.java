package poly.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.dto.AccStatDTO;
import poly.service.IGetAccStatService;

@Service("GetAccStatService")
public class GetAccStatService implements IGetAccStatService{

	private Logger log = Logger.getLogger(this.getClass());

	/*
	 * JSON 결과를 받아오기 위한 함수
	 * 
	 * URL을 파라미터로 전달하면, 자동으로 JSON 결과를 String 변수에 저장하고 결과값 반환
	 * 
	 * @param 호출URL
	 * 
	 * @return JSON 결과
	 */
	private String getUrlForJSON(String callUrl) {
		
		log.info(this.getClass().getName() + ".getUrlForJSON 시작!");
		
		log.info("Request URL: " + callUrl);
		
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		
		// JSON 결과 값이 저장되는 변수
		String json = "";
		
		// SSL  적용된 사이트일 경우, 데이터 증명을 위해 사용
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			@Override
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		
		try {
			
			// 웹사이트 접속을 위한 URL파싱
			URL url = new URL(callUrl);
			
			//접속
			urlConn = url.openConnection();
			
			//접속하면, 응답을 60초(60 * 1000ms)동안 기다림
			if(urlConn != null) {
				urlConn.setReadTimeout(60*1000);
			}
			
			if(urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(), Charset.forName("UTF-8"));
				
				BufferedReader bufferedReader = new BufferedReader(in);
				
				//주어진 문자 입력 스트림 inputStream에 대해 기본 크기의 버퍼르 갖는 객체를 생성
				if(bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
			in.close();
		}catch (Exception e) {
			throw new RuntimeException("Exception URL: " + callUrl, e);
		}
		
		json = sb.toString(); //json 결과 저장
		log.info("JSON result : " + json);
		
		log.info(this.getClass().getName() + ".getUrlForJSON End!");
		
		return json;
		
		
	}

	@Override
	public Map<String, Object> getAccStatforJSON(AccStatDTO pDTO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
