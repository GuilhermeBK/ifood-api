package com.br.core.email;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Validated
@Component
@ConfigurationProperties("food.email")
public class EmailProperties {

	private Implementacao impl = Implementacao.FAKE;
	
	@NotNull
	private String remetente;
	
	
	
	public Implementacao getImpl() {
		return impl;
	}

	public void setImpl(Implementacao impl) {
		this.impl = impl;
	}

	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	public Sandbox getSandbox() {
		return sandbox;
	}

	public void setSandbox(Sandbox sandbox) {
		this.sandbox = sandbox;
	}

	private Sandbox sandbox = new Sandbox();
	
	public enum Implementacao {
		SMTP, FAKE, SANDBOX
	}
	
	
	public class Sandbox {
		
		private String destinatario;

		public String getDestinatario() {
			return destinatario;
		}

		public void setDestinatario(String destinatario) {
			this.destinatario = destinatario;
		}
		
		
	}
	
}
