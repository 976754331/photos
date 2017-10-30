//package com.hoperun.service;
//
//import java.util.Map;
//
//import com.hoperun.exception.ServiceException;
//import com.hoperun.model.po.User;
//
//public interface IUserService extends IBaseService<User>{
//	
//	public User getUserInfo(String userId);
//	
//	public boolean updateUserInfo(Map<String, String> params);
//	
//	/**
//     * 增加意见建议记录
//     */
//    public void insertSuggest(Map<String, String> params) throws ServiceException;
//    /**
//     * 用户登录
//     * @param searchMap
//     * @return
//     */
//	public Map<String, Object> login(Map<String, String> searchMap) throws ServiceException;
//
//	public Map<String, Object> isExist(Map<String, Object> res)throws ServiceException;
//
//	public void insert(User user) throws ServiceException;
//	
//}
//
