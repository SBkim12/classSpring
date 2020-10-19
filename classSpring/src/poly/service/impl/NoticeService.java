package poly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.NoticeDTO;
import poly.persistance.mapper.INoticeMapper;
import poly.service.INoticeService;

@Service("NoticeService")
public class NoticeService implements INoticeService{

	@Resource(name="NoticeMapper")
	INoticeMapper noticeMapper;

	@Override
	public List<NoticeDTO> getNoticeList() throws Exception{
		// TODO Auto-generated method stub
		return noticeMapper.getNoticeList();
	}

	

	@Override
	public void updateNoticeReadCnt(NoticeDTO pDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public NoticeDTO getNoticeInfo(NoticeDTO pDTO) {
		// TODO Auto-generated method stub
		return noticeMapper.getNoticeInfo(pDTO);
	}

	@Override
	public void updateNoticeInfo(NoticeDTO pDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteNoticeInfo(NoticeDTO pDTO) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public int InsertNoticeInfo(NoticeDTO pDTO) {
		// TODO Auto-generated method stub
		return noticeMapper.InsertNoticeInfo(pDTO);
	}

	
	

}
