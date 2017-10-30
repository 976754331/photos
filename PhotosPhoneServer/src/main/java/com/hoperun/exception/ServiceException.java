/**   
* @Title ServiceException.java 
* @Package com.fande.cmserver.exception 
* @Description TODO
* @author XAFD)ZhouJiao
* @date 2015年10月28日 下午4:03:33 
* @version V1.0
*/
package com.hoperun.exception;

/**
 * 
 * @package com.fande.cmserver.exception
 * @ClassName ServiceException
 * @author XAFD)ZhouJiao
 * @date 2015年10月28日 下午4:03:33
 */
public class ServiceException extends Exception {
	/** serialVersionUID */
	private static final long serialVersionUID = -1709046797280927082L;
	
	public ServiceException() {
		super();
	}

	public ServiceException(String msg) {
		super(msg);
	}
	
	public ServiceException(Throwable t) {
		super(t);
	}
	
	public ServiceException(String msg, Throwable t) {
		super(msg, t);
	}
}
