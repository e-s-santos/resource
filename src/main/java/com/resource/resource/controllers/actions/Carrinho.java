package com.resource.resource.controllers.actions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resource.resource.controllers.actions.mongo.svc.TradeSvc;
@RestController
@RequestMapping("/buy")
public class Carrinho {
	private final TradeSvc tdsvc;
	Carrinho(TradeSvc svc ){
		this.tdsvc = svc;
	}
	/**
	 * 1. Escolha dos produtos 
	 * 2. Finalização da aquisição
	 */
	
	@PostMapping
	public  ResponseEntity<Object> buy(@RequestBody com.resource.resource.controllers.actions.dto.Trade tra) {
		com.resource.resource.controllers.actions.mongo.op.Trade tre = new com.resource.resource.controllers.actions.mongo.op.Trade(
				tra.idrec(),
				tra.datetime(),
				tra.Success()
				);
		tdsvc.trade(tre);
		return  ResponseEntity.status(HttpStatus.OK).body("Compra concluída");
	}
}
