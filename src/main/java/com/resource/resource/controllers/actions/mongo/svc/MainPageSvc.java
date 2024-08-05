package com.resource.resource.controllers.actions.mongo.svc;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.stereotype.Service;

import com.resource.resource.controllers.actions.dto.ListShowTradePageForUser;
import com.resource.resource.controllers.actions.dto.ShowTradePageForUser;
import com.resource.resource.controllers.actions.mongo.op.ListText;
import com.resource.resource.controllers.actions.mongo.op.MainPageOp;
import com.resource.resource.controllers.actions.mongo.repo.IMainPage;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

@Service
public class MainPageSvc {
    private final IMainPage imp;
    private ShowTradePageForUser stpgu;
    private ListShowTradePageForUser liststpgu;
    private List<ShowTradePageForUser> lst;
    @Autowired
    private MongoTemplate mt;
	
    public MainPageSvc(IMainPage imp) {
        this.imp = imp;
    }
	
    
    public ListShowTradePageForUser getMainPage() {
    	/**
    	 * db.resource.aggregate([{
            $lookup: {
                from: "news",localField: "_id",foreignField: "Id",as: "userRole"
            }
        }])
    	 */
        LookupOperation newlk = LookupOperation.newLookup()
                .from("news")
                .localField("_id")
                .foreignField("Id")
                .as("news");  
        LookupOperation pricelk = LookupOperation.newLookup()
        		.from("price")
        		.localField("_id")
        		.foreignField("_id")
        		.as("price");
        //UnwindOperation un = Aggregation.unwind("news");
        Aggregation agregar = Aggregation.newAggregation(
        		newlk,pricelk,
                 Aggregation.project("_id",
                		 "operationType",
                		 "resourceName",
                		 "resourceNick",
                		 "country",
                		 "state",
                		 "city")
        		         .and("news").as("news")
        		         .and("price").as("price"));
        AggregationResults<MainPageOp> ar = mt.aggregate(agregar, "resource", MainPageOp.class);
        lst  = new ArrayList<ShowTradePageForUser>();
        for (MainPageOp m : ar ){
        	String newsResource = "";
        	for (ListText lt : m.news()) {
        		newsResource += lt.text();
        	}
        	stpgu = new ShowTradePageForUser(m._id(),m.resourceType(),
        		    m.resourceName(),
        		    m.resourceNick(),
        		    m.eat(),
        		    m.country(),
        		    m.state(),
        		    m.city(),
        		    newsResource);
        	lst.add(stpgu);
        }
        liststpgu = new ListShowTradePageForUser(lst);
    	return liststpgu;
    }
}
