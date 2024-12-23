package com.firebase;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class firebaseCrud {
	
	private String documentID;
	private String response;
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getTrigger() {
		return trigger;
	}
	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}
	private String trigger;
	public String getDocumentID() {
		return documentID;
	}
	public void setDocumentID(String documentID) {
		this.documentID = documentID;
	}
	 
	 
	 

}
