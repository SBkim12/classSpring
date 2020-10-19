package poly.service;

import java.util.List;

import poly.dto.UserInfoDTO;

public interface IUserService {

	int getUserInfo(UserInfoDTO uDTO)throws Exception;

	List<UserInfoDTO> getUserList(UserInfoDTO uDTO)throws Exception;

	int insertUserInfo(UserInfoDTO pDTO)throws Exception;

}
