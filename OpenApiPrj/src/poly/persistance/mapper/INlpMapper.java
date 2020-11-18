package poly.persistance.mapper;

import java.util.List;

import config.Mapper;
import poly.dto.NlpDTO;

@Mapper("NlpMapper")
public interface INlpMapper {

	List<NlpDTO> getWord(NlpDTO pDTO)throws Exception;



}
