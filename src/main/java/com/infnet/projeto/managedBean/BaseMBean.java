/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.infnet.projeto.managedBean;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 *
 * @author Kehlt
 */
public class BaseMBean {

	public void addErrorMessage(String summary){
		addMessage(FacesMessage.SEVERITY_ERROR, summary, "");
	}
	
	public void addWarningMessage(String summary){
		addMessage(FacesMessage.SEVERITY_WARN, summary, "");
	}
	
	public void addInfoMessage(String summary){
		addInfoMessage(summary, "");
	}

	public void addInfoMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_INFO, summary, detail);
	}

	private void addMessage(Severity severity, String summary, String detail) {
		FacesMessage message = new FacesMessage(severity, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
