package poly.service;

import java.util.List;

import poly.dto.BoardDTO;
import poly.dto.NoticeDTO;

public interface INoticeService {

	List<NoticeDTO> getNoticeList() throws Exception;


	void updateNoticeReadCnt(NoticeDTO pDTO);

	NoticeDTO getNoticeInfo(NoticeDTO pDTO);

	void updateNoticeInfo(NoticeDTO pDTO);

	void deleteNoticeInfo(NoticeDTO pDTO);


	int InsertNoticeInfo(NoticeDTO pDTO);

	
	
}
