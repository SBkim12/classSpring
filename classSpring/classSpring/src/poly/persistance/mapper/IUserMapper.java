package poly.persistance.mapper;

import java.util.List;

import config.Mapper;
import poly.dto.UserInfoDTO;

@Mapper("UserMapper")
public interface IUserMapper {

	UserInfoDTO getUserInfo(UserInfoDTO uDTO);

	List<UserInfoDTO> getUserList(UserInfoDTO uDTO)throws Exception;


	UserInfoDTO getUserExists(UserInfoDTO pDTO)throws Exception;

	int insertUserInfo(UserInfoDTO pDTO)throws Exception;

}
