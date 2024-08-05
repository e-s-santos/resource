package com.resource.resource.mongo.svc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resource.resource.mongo.repo.ResourceInclude;
import com.resource.resource.mongo.repo.iResourceInclude;

import org.springframework.stereotype.Service;

@Service
public class ResourceServicesMongo {
    private final iResourceInclude ri;

	
    public ResourceServicesMongo(iResourceInclude ri) {
        this.ri = ri;
    }
    
    public ResourceInclude saveResource(ResourceInclude ri) {
        return this.ri.save(ri);
    }
    public void remove(ResourceInclude ri) {
    	this.ri.delete(ri);
    }
}
