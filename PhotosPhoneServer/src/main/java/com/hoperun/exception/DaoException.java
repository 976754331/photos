/**   
* @Title DaoException.java 
* @Package com.fande.cmserver.exception 
* @Description TODO
* @author XAFD)ZhouJiao
* @date 2015年10月28日 下午3:59:55 
* @version V1.0
*/
package com.hoperun.exception;

/**
 * 
 * @package com.fande.cmserver.exception
 * @ClassName DaoException
 * @author XAFD)ZhouJiao
 * @date 2015年10月28日 下午3:59:55
 */
public class DaoException extends Exception {
	/** serialVersionUID */
	private static final long serialVersionUID = -5528666873574690885L;
	
	public DaoException() {
		super();
	}

	public DaoException(String msg) {
		super(msg);
	}

	public DaoException(Throwable t) {
		super(t);
	}
	
	public DaoException(String msg, Throwable t) {
		super(msg, t);
	}
}
