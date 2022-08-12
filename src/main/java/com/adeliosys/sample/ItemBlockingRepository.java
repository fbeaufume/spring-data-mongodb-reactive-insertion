package com.adeliosys.sample;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemBlockingRepository extends MongoRepository<Item, String> {
}
