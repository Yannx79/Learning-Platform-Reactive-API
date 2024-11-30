package com.nk.learningplatformreactiveapi.services.Impl;

import com.nk.learningplatformreactiveapi.exceptions.DatabaseException;
import com.nk.learningplatformreactiveapi.exceptions.EntityNotFoundException;
import com.nk.learningplatformreactiveapi.repositories.IGenericRepository;
import com.nk.learningplatformreactiveapi.services.ICRUD;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class CRUDImpl<T, K> implements ICRUD<T, K> {

    protected abstract IGenericRepository<T, K> getRepo();

    @Override
    public Mono<T> save(T t) {
        return getRepo().save(t)
                .onErrorMap(ex -> new DatabaseException("Error saving entity", ex));
    }

    @Override
    public Mono<T> update(T t, K k) {
        return getRepo().findById(k)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("ID NOT FOUND: " + k)))
                .flatMap(existing -> getRepo().save(t))
                .onErrorMap(ex -> new DatabaseException("Error updating entity", ex));
    }

    @Override
    public Flux<T> readAll() {
        return getRepo().findAll()
                .onErrorMap(ex -> new DatabaseException("Error fetching all entities", ex));
    }

    @Override
    public Mono<T> readById(K k) {
        return getRepo().findById(k)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("ID NOT FOUND: " + k)))
                .onErrorMap(ex -> new DatabaseException("Error fetching entity by ID", ex));
    }

    @Override
    public Mono<Void> delete(K k) {
        return getRepo().findById(k)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("ID NOT FOUND: " + k)))
                .flatMap(existing -> getRepo().deleteById(k))
                .onErrorMap(ex -> new DatabaseException("Error deleting entity", ex));
    }

}
