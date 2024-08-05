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
import com.resource.resource.controllers.actions.mongo.repo.ITrade;

import org.springframework.data.mongodb.core.aggregation.AggregationResults;

@Service
public class TradeSvc {
    private final ITrade td; 
    TradeSvc(ITrade td){
    	this.td = td;
    }
	public void trade(com.resource.resource.controllers.actions.mongo.op.Trade trade) {
		td.insert(trade);
		//td.insert(trade);
	}
}
