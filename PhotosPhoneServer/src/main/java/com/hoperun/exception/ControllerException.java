/**   
* @Title ControllerException.java 
* @Package com.fande.cmserver.exception 
* @Description TODO
* @author XAFD)ZhouJiao
* @date 2015年10月28日 下午4:04:08 
* @version V1.0
*/
package com.hoperun.exception;

/**
 * 
 * @package com.fande.cmserver.exception
 * @ClassName ControllerException
 * @author XAFD)ZhouJiao
 * @date 2015年10月28日 下午4:04:08
 */
class ControllerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5308914889992188113L;

	public ControllerException() {
		super();
	}

	public ControllerException(String msg) {
		super(msg);
	}

	public ControllerException(Throwable t) {
		super(t);
	}
	
	public ControllerException(String msg, Throwable t) {
		super(msg, t);
	}
}
