//package com.hoperun.mapper;
//
//import java.util.List;
//import java.util.Map;
//
//import com.hoperun.model.po.PaperBook;
//
//public interface BookShelfMapper extends IBaseMapper<PaperBook>{
//	
//	/**
//	 * 
//	 * @Description: 根据personId查询借阅规则
//	 */
//	public Map<String, Object> selectBorrowRule(Map<String, String> searchMap);
//	
//	/**
//	 * 
//	 * @Description: 查询借阅列表
//	 */
//	public List<Map<String, Object>> selectBorrowList(Map<String, String> searchMap);
//	
//	/**
//	 * 
//	 * @Description: 根据personId查询预约列表并且结束时间大于当前时间
//	 */
//	public List<Map<String, Object>> querySubscribe(Map<String, String> searchMap);
//	
//	/**
//	 * 我要续借
//	 * @param userId
//	 * @param request
//	 * @return
//	 */
//	public void renewBook(Map<String, String> searchMap);
//	
//	/**
//	 * 查询续借次数
//	 * @param userId
//	 * @param request
//	 * @return
//	 */
//	public Map<String, Object> selectReborrowTimes(String borrowId);
//	
//	/**
//	 * 
//	 * @Description: 获取近期阅读列表
//	 */
//	public List<Map<String, Object>> selectRecentRead(String personId);
//	
//	/**
//	 * 
//	 * @Description: 通过用户id和书籍id查询近期阅读记录
//	 */
//	public Map<String, Object> selectRecentBook(Map<String, Object> searchMap);
//	
//	/**
//	 * 
//	 * @Description: 添加近期阅读记录
//	 */
//	public void insertRecentBook(Map<String, Object> searchMap);
//	
//	/**
//	 * 
//	 * @Description: 更新近期阅读记录
//	 */
//	public void updateRecentBook(Map<String, Object> searchMap);
//
//}
