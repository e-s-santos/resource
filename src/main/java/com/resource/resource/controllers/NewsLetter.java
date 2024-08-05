package com.resource.resource.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resource.resource.dto.NewsPost;
import com.resource.resource.mongo.repo.NewsPostMongo;
import com.resource.resource.mongo.svc.NewsLetterServiceMongo;

import io.micrometer.core.instrument.MeterRegistry;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
//import io.opentelemetry.instrumentation.annotations.WithSpan;

@RestController
@RequestMapping("api/news")
public class NewsLetter {
	/** 
	 * Publicação de descrição dos recursos da loja virtual 
	 * Publicação de descrição dos perfis das entidades
	 * Avaliar não usar para comentários no produto
	 */
	private NewsLetterServiceMongo nlsm;
	private static Logger log = LoggerFactory.getLogger(NewsLetter.class);
	private final MeterRegistry mreg;
	
	@Autowired
	public NewsLetter(NewsLetterServiceMongo nlsm,MeterRegistry mreg) {
	        this.nlsm = nlsm;
	        this.mreg = mreg;
	}
	@PostMapping
	public ResponseEntity<Object> postNews(@RequestBody NewsPost np) {
		NewsPostMongo npm = new NewsPostMongo(np);
		nlsm.saveResource(npm);
		log.info("Cadastros de novas informações foram executadas com sucesso: "+np.collection());
		
		mreg.counter("news.count.include", "include", "news").increment();
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	@DeleteMapping
	public ResponseEntity<Object> remove(@RequestBody NewsPost np){
		NewsPostMongo npm = new NewsPostMongo(np);
		nlsm.remove(npm);
		log.info("Recurso: O registro foi excluido ");
		mreg.counter("news.count.delete", "delete", "news").increment();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Recurso: O registro foi excluido");
	}
}
