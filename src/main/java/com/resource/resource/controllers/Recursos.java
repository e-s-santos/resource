 package com.resource.resource.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resource.resource.dto.Resource;
import com.resource.resource.mongo.repo.ResourceInclude;
import com.resource.resource.mongo.repo.iResourceInclude;
import com.resource.resource.mongo.svc.NewsLetterServiceMongo;
import com.resource.resource.mongo.svc.PriceServicesMongo;
import com.resource.resource.mongo.svc.ResourceServicesMongo;

import io.micrometer.core.instrument.MeterRegistry;

@RestController
@RequestMapping("/api/resources")
public class Recursos {
	/** 
	 * Avaliar o para cadastro de novos recursos a serem apresentados na loja
	 */
	private ResourceServicesMongo rs; 
	private final MeterRegistry mreg;
	private final PriceServicesMongo psm;
	private final NewsLetterServiceMongo nlsm;
	private static Logger log = LoggerFactory.getLogger(Recursos.class);

	@Autowired
	public Recursos(ResourceServicesMongo rs,PriceServicesMongo psm,NewsLetterServiceMongo nlsm, MeterRegistry mreg) {
	        this.mreg = mreg;
			this.rs = rs;
			this.psm = psm;
			this.nlsm = nlsm;
	}
	
	@PostMapping
	public  ResponseEntity<Object> insertResource(@RequestBody Resource r){
		ResourceInclude n = new ResourceInclude(r);
		ResourceInclude sv = rs.saveResource(n);
		log.info("Cadastros de recursos concluído com sucesso: "+r.resourceType());
		mreg.counter("rec.count.include", "include", "resource").increment();
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@DeleteMapping
	public ResponseEntity<Object> remove(@RequestBody Resource r){
		ResourceInclude n = new ResourceInclude(r);
		/**
		 * Remove o preco 
		 * Remove o texto
		 */
		psm.deleteId(r.id());
		nlsm.removeId(r.id());
		rs.remove(n);
		mreg.counter("rec.count.delete", "delete", "resource").increment();
		log.info("Recurso excluido"+r.id()+" preço e news deletado");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("O registro foi excluido");
	}
	
	
	@PutMapping
	public ResponseEntity<Object>  updateResource(@RequestBody Resource r){
		mreg.counter("rec.count.put", "put", "resource").increment();
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
}
