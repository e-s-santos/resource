package com.resource.resource.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resource.resource.dto.PricePost;
import com.resource.resource.mongo.repo.PriceInclude;
import com.resource.resource.mongo.svc.PriceServicesMongo;

import io.micrometer.core.instrument.MeterRegistry;
import io.opentelemetry.api.trace.Tracer;

@RestController
@RequestMapping("/price")
public class Price {
	private final PriceServicesMongo psm;
	private  Tracer tc; 
	private static Logger log = LoggerFactory.getLogger(Price.class);
	private final MeterRegistry mreg;
	
    //@Value("${otel.endpointSpan}")
	private String otelEndpoint;
	

    Price(PriceServicesMongo psm,MeterRegistry rs){
		this.psm = psm;
		this.mreg = rs;
	}
	@PostMapping
	public ResponseEntity<Object> include(@RequestBody PricePost pp ){
		
		
		PriceInclude pi = new PriceInclude(pp);
		this.psm.saveResource(pi);
		log.info("Preço incluído : "+pp.id()+" Prince Value: "+pp.price()+"");
		
		/** Sendo demonstrada no prometheus
		 * https://www.baeldung.com/spring-boot-opentelemetry-setup
		 * **/
		/** Etapa 2, exporter GRPC **/
		
		mreg.counter("price.count.include", "include", "price").increment();
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	//Remoção do preço 
	@DeleteMapping
	public ResponseEntity<Object> remove(@RequestBody com.resource.resource.dto.PricePost pd ){
		com.resource.resource.mongo.repo.PriceInclude pds = new com.resource.resource.mongo.repo.PriceInclude(pd);
		this.psm.delete(pds);
		log.info("O registro foi excluido "+pd.id());
		mreg.counter("price.count.remove", "remove", "price").increment();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(pd);
	}
	
	//Ajuste do preço
	@PutMapping
	public ResponseEntity<Object> changePrice(){
		log.info("Atualização executada com sucesso");
		mreg.counter("price.count.update", "update", "price").increment();
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	@GetMapping
	public ResponseEntity<Object> getPrice(){
		this.psm.search();
		mreg.counter("price.count.get", "get", "price").increment();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
	}
}
