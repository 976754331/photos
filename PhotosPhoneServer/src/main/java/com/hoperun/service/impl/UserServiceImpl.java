//package com.hoperun.service.impl;
//
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.hoperun.exception.ServiceException;
//import com.hoperun.mapper.IBaseMapper;
//import com.hoperun.mapper.UserMapper;
//import com.hoperun.model.po.User;
//import com.hoperun.service.IUserService;
//@Transactional
//@Service
//public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService{
//
//	@Autowired
//	private UserMapper userMapper;
//	
//	/**
//	 * 获取用户信息
//	 */
//	public User getUserInfo(String userId) {
//		return userMapper.getUserInfo(userId);
//	}
//	
//	/**
//     * 增加意见建议记录
//     */
//    public void insertSuggest(Map<String, String> params) throws ServiceException{
//    	userMapper.insertSuggest(params);
//    }
//
//    /**
//	 * 修改用户信息
//	 */
//	public boolean updateUserInfo(Map<String, String> params) {
//		User use = new User();
//		use.setId(params.get("userId"));
//		use.setName(params.get("name"));
//		use.setSex(params.get("sex"));
//		use.setEmail(params.get("email"));
//		use.setUrl(params.get("url"));
//		return userMapper.updateUserInfo(use);	
//	}
//	/**
//	 * 用户登录
//	 */
//	@Override
//	public Map<String, Object> login(Map<String, String> searchMap) {
//		return userMapper.login(searchMap);
//	}
//
//	@Override
//	public Map<String, Object> isExist(Map<String, Object> res) {
//		 return userMapper.isExist(res);
//	}
//
//	@Override
//	public IBaseMapper<User> getBaseMapper() {
//		return userMapper;
//	}
//
//}
