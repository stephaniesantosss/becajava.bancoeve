package br.bancoeveris.app.model;

import javax.persistence.Transient;

public class BaseResponse {
	
	@Transient
	public int StatusCode;
	@Transient
	public String Message;
	
	

}