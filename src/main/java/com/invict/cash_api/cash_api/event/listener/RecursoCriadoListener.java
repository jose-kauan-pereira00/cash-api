package com.invict.cash_api.cash_api.event.listener;

import java.net.URI;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.invict.cash_api.cash_api.event.RecursoCriado;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriado>{

	@Override
	public void onApplicationEvent(RecursoCriado event) {
		HttpServletResponse response = event.getResponse();
		Long codigo = event.getCodigo();

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
		.buildAndExpand(codigo).toUri();

		response.setHeader("Location", uri.toASCIIString());
	}

	
}
