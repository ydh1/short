package com.leederedu.educhat.model.api;

import java.io.Serializable;
import java.util.HashMap;

public class ApiResult implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final public static int CODE_NO_LOGIN = 801;

    final public static String CODE_COMMON_SUCCESS = "2000";
    final public static String CODE_COMMON_FAILED = "4000";
	
	private int result = -1;
	private String errordesc = "";
    private String errorcode = "0000000000";		
	private HashMap<String, Object> data = new HashMap<String, Object>();

	public ApiResult(){
	}
	
	public ApiResult(int result,String errorcode, String errordesc){
		this.result = result;
		this.errordesc = errordesc;
	}
	
	public static ApiResult error(String errordesc){
		return new ApiResult(-1,CODE_COMMON_FAILED,errordesc);
	}
	
	public static ApiResult success(){
		return new ApiResult(1,CODE_COMMON_SUCCESS,"");
	}
	
	public ApiResult put(String key,Object value){
		data.put(key, value);
		return this;
	}
	
	

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getErrordesc() {
		return errordesc;
	}
	

	public ApiResult setErrordesc(String errordesc) {
		this.errordesc = errordesc;
        return this;
	}

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }


	public HashMap<String, Object> getData() {
		return data;
	}

	public void setData(HashMap<String, Object> data) {
		this.data = data;
	}
}
