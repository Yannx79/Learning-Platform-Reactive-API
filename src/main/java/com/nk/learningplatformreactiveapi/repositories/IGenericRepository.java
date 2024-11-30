package com.nk.learningplatformreactiveapi.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

@NoRepositoryBean
public interface IGenericRepository<T, K> extends ReactiveCrudRepository<T, K> {
}
