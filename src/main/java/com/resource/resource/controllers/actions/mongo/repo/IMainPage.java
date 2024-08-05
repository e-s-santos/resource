package com.resource.resource.controllers.actions.mongo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.resource.resource.controllers.actions.mongo.op.MainPageOp;

public interface IMainPage extends MongoRepository<MainPageOp, String>{
}
