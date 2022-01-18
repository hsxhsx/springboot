package com.test.dbhappy.es;

import com.google.common.collect.Lists;
import com.test.dbhappy.kevin.entity.User;
import com.test.dbhappy.service.ElasticRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("es")
public class EsController {


    @Autowired
    private ElasticRepository elasticRepository;

    @PostMapping("save")
    public User save(@RequestBody User user) {
        return elasticRepository.save(user);
    }

    @GetMapping("list")
    public List<User> getList(User user) {
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("username", "hsx");
        return Lists.newLinkedList(elasticRepository.search(queryBuilder));
    }

}
