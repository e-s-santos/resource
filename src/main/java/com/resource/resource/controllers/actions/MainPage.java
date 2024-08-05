package com.resource.resource.controllers.actions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resource.resource.controllers.actions.dto.ListShowTradePageForUser;
import com.resource.resource.controllers.actions.mongo.svc.MainPageSvc;

@RestController
@RequestMapping("/page")
public class MainPage {
	/**
	 * Pega os valores da página principal para demonstração ao cliente final 
	 */
	private final MainPageSvc mmpsvc;
	MainPage(MainPageSvc mmpsvc){
		this.mmpsvc = mmpsvc;
	}
	@GetMapping
	public ResponseEntity<Object> getMainPage() {
		ListShowTradePageForUser listPage =  mmpsvc.getMainPage();
		return ResponseEntity.status(HttpStatus.OK).body(listPage);
	}
}
