package com.smallbell.server.model;

import java.io.Serializable;

public class SecureModel  implements Serializable{

	private static final long serialVersionUID = -2108336644101910071L;
	/**
	 * ??֤ token 
	 */
	private String token ;
	
	private boolean autoSuccess;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public boolean isAutoSuccess() {
		return autoSuccess;
	}
	public void setAutoSuccess(boolean autoSuccess) {
		this.autoSuccess = autoSuccess;
	}
	
	
}
