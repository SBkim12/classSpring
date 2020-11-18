package poly.persistance.mapper;

import java.util.List;

import config.Mapper;
import poly.dto.NoticeDTO;

@Mapper("NoticeMapper")
public interface INoticeMapper {

	List<NoticeDTO> getNoticeList() throws Exception;

	NoticeDTO getNoticeInfo(NoticeDTO pDTO);

	int InsertNoticeInfo(NoticeDTO pDTO);

}
