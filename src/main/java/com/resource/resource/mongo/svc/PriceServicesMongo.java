package com.resource.resource.mongo.svc;
import java.util.List;

import org.springframework.stereotype.Service;

import com.resource.resource.mongo.repo.IPostPrice;
import com.resource.resource.mongo.repo.PriceInclude;

@Service
public class PriceServicesMongo {
    private  final IPostPrice ri;
	
    public PriceServicesMongo(IPostPrice ri) {
        this.ri = ri;
    }

    public PriceInclude saveResource(PriceInclude pi) {
        return this.ri.save(pi); 
    }
    public void delete(PriceInclude pd) {
    	this.ri.delete(pd);
    }
    public void deleteId(String id) {
    	this.ri.deleteById(id);
    }
    public PriceInclude update(PriceInclude pd ){
        return this.ri.save(pd); 
    }
    public List<PriceInclude> search() {
    	return this.ri.findAll();
    }
}
