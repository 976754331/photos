//package com.hoperun.controller;
//
///**
// * 
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.github.pagehelper.Page;
//import com.hoperun.exception.ServiceException;
//import com.hoperun.model.po.PaperBook;
//import com.hoperun.model.vo.ReturnInfo;
//import com.hoperun.service.IEBookService;
//import com.hoperun.service.IPaperBookService;
//
//@Controller
//@RequestMapping("/pbook")
//public class PaperBookController extends AbstractController {
//
//	@Autowired
//	private IPaperBookService papreBookService;
//	
//	@Autowired
//	private IEBookService eBookService;
//
//	/**
//	 * 获取纸书详情
//	 * 
//	 * @param bookId  书籍isbn
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "getBookDeal")
//	@ResponseBody
//	public ReturnInfo getBookDeal(String bookId, String userId, HttpServletRequest request) {
//		ReturnInfo rtn = new ReturnInfo();
//		PaperBook inputBook = new PaperBook();
//		inputBook.setIsbn(bookId);
//		Map<String,String> param = new HashMap<String, String>();
//		param.put("isbn", bookId);		
//		try {
//			Map<String, Object> stockMap = papreBookService.selectStock(param);
//			//先查出库存数；
//			int count = Integer.parseInt(stockMap.get("count").toString());
//			Map<String, Object> map = new HashMap<String, Object>();
//			if(count <=0){  //库存为0显示书籍详情
//			   map = papreBookService.getDealWithoutStock(param);
//			   String bookIdNew = map.get("book_isbn").toString();
//			   eBookService.addHits(bookIdNew, userId, "3");
//			   map.put("stock", count);
//			}else{  //库存不为0按可预借与可借阅过滤显示书籍详情
//			   map = papreBookService.get(inputBook);
//			   String bookIdNew = map.get("book_isbn").toString();
//			   eBookService.addHits(bookIdNew, userId, "3");
//			   map.put("stock", count);
//			}
//			rtn.setRtnCode(0);
//			rtn.setMsg("请求成功");
//			rtn.setData(map);
//		} catch (Exception e) {
//			ReturnInfo errRtn = buildReturnValue(50101, "获取书籍详情异常");
//			errRtn.setData("[]");
//			return errRtn;
//		}
//		return rtn;
//	}
//
//	/**
//	 * 获取预约状态
//	 * 
//	 * @param bookId  书籍id
//	 * @param userId   用户id
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "getAppoint")
//	@ResponseBody
//	public ReturnInfo getAppoint(String bookId, String userId, HttpServletRequest request) {
//		ReturnInfo rtn = papreBookService.getAppoint(bookId, userId);		
//		return rtn;
//	}
//
//	/**
//	 * 根据书名、作者、出版社搜索书籍
//	 * 
//	 * @param searchType   搜索类型
//	 * @param keyWords   模糊查询字
//	 * @param pageNum    
//	 * @param pageSize
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "searchBookByCondition")
//	@ResponseBody
//	public ReturnInfo searchBookByCondition(String searchType, String keyWords,
//			Integer pageNum, Integer pageSize, HttpServletRequest request) {
//		ReturnInfo rtn = new ReturnInfo();
//		// "searchType":"搜索类型",0书名，1作者，2出版
//		Map<String, String> searchMap = new HashMap<String, String>();
//		searchMap.put("searchType", searchType);
//		searchMap.put("keyWords", keyWords);
//		Page<PaperBook> page = new Page<PaperBook>();
//		try {
//			page.setPageNum(pageNum);
//			page.setPageSize(pageSize);
//		} catch (Exception e) {
//			page.setPageNum(1);
//			page.setPageSize(10);
//		}
//		try {
//			List<Map<String, Object>> list = papreBookService.selectListByPage(
//					page, searchMap);
//			rtn.setRtnCode(0);
//			rtn.setMsg("请求成功");
//			rtn.setData(list);
//		} catch (ServiceException e) {
//			ReturnInfo errRtn = buildReturnValue(50201, "搜索书籍异常");
//			errRtn.setData("[]");
//			return errRtn;
//		}
//		return rtn;
//	}
//
//	/**
//	 * 获取书籍列表
//	 * 
//	 * @param secondClassifyId  二级分类号
//	 * @param orderWords  排序字段
//	 * @param pageNum
//	 * @param pageSize
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "getBookLists")
//	@ResponseBody
//	public ReturnInfo getBookLists(String secondClassifyId, String orderWords,
//			Integer pageNum, Integer pageSize, HttpServletRequest request) {
//		ReturnInfo rtn = new ReturnInfo();
//		// "orderWords":0：上架时间，1：点击量，2：出版时间，3：收藏量，4：推荐量
//		//    add_date   book_hits  book_publication_date  book_collect_hits  book_recommend_hits
//		Map<String, String> searchMap = new HashMap<String, String>();
//		if(null == orderWords){
//			orderWords = "0";
//		}
//		searchMap.put("orderWords", orderWords);
//		searchMap.put("secondClassifyId", secondClassifyId);
//		Page<PaperBook> page = new Page<PaperBook>();
//		try {
//			page.setPageNum(pageNum);
//			page.setPageSize(pageSize);
//		} catch (Exception e) {
//			page.setPageNum(1);
//			page.setPageSize(10);
//		}
//
//		try {
//			List<Map<String, Object>> list = papreBookService.selectBooksByType(page, searchMap);
//			rtn.setRtnCode(0);
//			rtn.setMsg("请求成功");
//			rtn.setData(list);
//		} catch (ServiceException e) {
//			ReturnInfo errRtn = buildReturnValue(50701, "查询书籍列表失败");
//			errRtn.setData("[]");
//			return errRtn;
//		}
//
//		return rtn;
//	}
//
//	/**
//	 * 获取推荐书籍
//	 * 
//	 * @param secondClassifyId  二级分类号
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "getRecommendBooks")
//	@ResponseBody
//	public ReturnInfo getRecommendBooks(String secondClassifyId,
//			HttpServletRequest request) {
//		ReturnInfo rtn = new ReturnInfo();
//		Map<String, String> searchMap = new HashMap<String, String>();
//		searchMap.put("secondClassifyId", secondClassifyId);
//		try {
//			List<Map<String,Object>> list = papreBookService.selectBooksForRecommend(searchMap);
//			rtn.setRtnCode(0);
//			rtn.setMsg("请求成功");
//			rtn.setData(list);
//		} catch (ServiceException e) {
//			ReturnInfo errRtn = buildReturnValue(50301, "相关书籍推荐失败");
//			errRtn.setData("[]");
//			return errRtn;
//		}
//		
//		return rtn;
//
//	}
//
//	/**
//	 * 获取一级分类
//	 * 
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "getFirstClassify")
//	@ResponseBody
//	public ReturnInfo getFirstClassify(HttpServletRequest request) {
//		ReturnInfo rtn = new ReturnInfo();
//		try {
//			List<Map<String, Object>> list = papreBookService.getFirstClassify();
//			rtn.setRtnCode(0);
//			rtn.setMsg("请求成功");
//			rtn.setData(list);
//		} catch (ServiceException e) {
//			ReturnInfo errRtn = buildReturnValue(50401, "查询一级分类失败");
//			errRtn.setData("[]");
//			return errRtn;
//		}
//		return rtn;
//	}
//
//	/**
//	 * 获取二级分类
//	 * 
//	 * @param firstClassifyId  一级分类id
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "getSecondClassify")
//	@ResponseBody
//	public ReturnInfo getSecondClassify(String firstClassifyId,
//			HttpServletRequest request) {
//		ReturnInfo rtn = new ReturnInfo();
//		try {
//			List list = papreBookService.getSecondClassify(firstClassifyId);
//			rtn.setRtnCode(0);
//			rtn.setMsg("请求成功");
//			rtn.setData(list);
//		} catch (ServiceException e) {
//			ReturnInfo errRtn = buildReturnValue(50501, "查询二级分类失败");
//			errRtn.setData("[]");
//			return errRtn;
//		}
//		return rtn;
//	}
//
//	/**
//	 * 预约/取消预约
//	 * 
//	 * @param bookId  书籍id
//	 * @param subscribeId  预约id
//	 * @param personId  用户id
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "appointBook")
//	@ResponseBody
//	public ReturnInfo appointBook(String bookId, String subscribeId, String userId,
//			HttpServletRequest request) {
//		ReturnInfo rtn = papreBookService.appointBook(bookId,subscribeId,userId);	
//		return rtn;
//	}
//
//}

