package com.nk.learningplatformreactiveapi.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICRUD<T, K> {

    Mono<T> save(T t);

    Mono<T> update(T t, K k);

    Flux<T> readAll();

    Mono<T> readById(K k);

    Mono<Void> delete(K k);

}
