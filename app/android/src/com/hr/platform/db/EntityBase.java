/** 
 * @ProjectName:  ResidentialUserInteractionApp 
 * @FileName:  	  BaseBean.java 
 * @PackageName:  com.nari.misp.mobile.bean 
 * @Date:         2015-5-20上午9:08:29 
 * @Copyright:    Copyright (c) 2015, 国电南瑞科技股份有限公司 All Rights Reserved. 
 * 
 */
package com.hr.platform.db;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;

/**
 * @ClassName: BaseBean <br/>
 * 
 * @Description 数据库表基表
 * 
 * @date: 2015-5-20 上午9:08:29 <br/>
 * @author Loong
 * @version
 * @since JDK 1.6
 */
public abstract class EntityBase implements Serializable {

	/** 
	 * 
	 * @Description  TODO
	 *
	 * @author Loong 
	 * @since JDK 1.6 
	 */  
	private static final long serialVersionUID = 6615287046544633717L;
	//@Id // 如果主键没有命名名为id或_id的时，需要为主键添加此注解
    //@NoAutoIncrement // int,long类型的id默认自增，不想使用自增时添加此注解
	@Id
	private int Tid=1;
	/** 
	 * tid. 
	 * 
	 * @return  the tid 
	 * @author  马二川 
	 * @since JDK 1.6 
	 */
	public int getTid() {
		return Tid;
	}
	/** 
	 * tid. 
	 * 
	 * @param   tid    the tid to set 
	 * @return  the tid 
	 * @author  马二川 
	 * @since JDK 1.6 
	 */
	public void setTid(int tid) {
		Tid = tid;
	}


}
