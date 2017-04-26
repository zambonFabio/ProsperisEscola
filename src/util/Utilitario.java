package util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Utilitario implements Serializable {

	private static final long serialVersionUID = 1L;

	public static void message(String tipo, String cabecalho, String msg) {

		FacesMessage message = null;

		if (tipo.equals("info")) {
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, cabecalho, msg);
		}

		if (tipo.equals("warn")) {
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, cabecalho, msg);
		}

		if (tipo.equals("error")) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, cabecalho, msg);
		}

		FacesContext.getCurrentInstance().addMessage(null, message);

	}

}
