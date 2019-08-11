package com.lcg.example.repository;
import com.lcg.example.model.User;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;

public interface ElasticUserRepository extends ReactiveElasticsearchRepository<User,Long> {
}
