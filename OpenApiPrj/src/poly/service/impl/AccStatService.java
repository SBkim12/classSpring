package poly.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.dto.AccStatDTO;
import poly.persistance.mapper.IAccStatMapper;
import poly.service.IAccStatService;

@Service("AccStatService")
public class AccStatService implements IAccStatService{

	@Resource(name="AccStatMapper")
	private IAccStatMapper accStatMapper;
	
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public List<AccStatDTO> getAccStatInfo(AccStatDTO pDTO) throws Exception {
		
		log.info(this.getClass().getName() + ".getAccStatInfo start!");
		
		List<AccStatDTO> rList = accStatMapper.getAccStatInfo(pDTO);
		
		if(rList == null) {
			rList = new ArrayList<AccStatDTO>();
		}
		
		log.info(this.getClass().getName() + ".getAccStatInfo end!");
		
		return rList;
		
	}


}
