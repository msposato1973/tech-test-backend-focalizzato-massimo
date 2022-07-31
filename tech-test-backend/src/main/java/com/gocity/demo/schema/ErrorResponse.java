package com.gocity.demo.schema;


/***
 * 
 * @author maxp7
 *
 */
public class ErrorResponse {
	 
	private String errorCode;
	private String errorMessage;
	
	/***
	 * 
	 * @return String for errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}
	
	/***
	 * 
	 * @param String for errorCode
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	/***
	 * 
	 * @return  String for errorMessage
	 */
	public String getErrorMessage() {
		return this.errorMessage;
	}
	/***
	 * 
	 * @param String for errorMessage
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	/***
	 * Def. Constructor
	 */
	public ErrorResponse() {
		super();
	}
	
	/***
	 * Constructor
	 * @param String for errorCode
	 * @param String for errorMessage
	 */
	public ErrorResponse(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	
	@Override
	public String toString() {
		return "ErrorResponse [errorCode=" + errorCode + ", errorMessage=" + errorMessage + "]";
	}
	
	 
}
