package com.test.dbhappy.service;

import com.test.dbhappy.kevin.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticRepository extends ElasticsearchRepository<User, String> {
}
