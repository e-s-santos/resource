package com.resource.resource.mongo.svc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resource.resource.mongo.repo.IPostNewsLetter;
import com.resource.resource.mongo.repo.NewsPostMongo;

import org.springframework.stereotype.Service;

@Service
public class NewsLetterServiceMongo {
    private final IPostNewsLetter np;

    public NewsLetterServiceMongo(IPostNewsLetter np) {
        this.np = np;
    }
    
    public NewsPostMongo saveResource(NewsPostMongo npm) {
        return this.np.save(npm);
    }
    public void remove(NewsPostMongo npm) {
        this.np.delete(npm);
    }
    public void removeId(String id) {
    	this.np.deleteById(id);
    }
    public String searctQuery(String query) {
    	return null;
    }
}
