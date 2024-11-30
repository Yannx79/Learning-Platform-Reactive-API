package com.nk.learningplatformreactiveapi.services.Impl;

import com.nk.learningplatformreactiveapi.entities.UserEntity;
import com.nk.learningplatformreactiveapi.repositories.IGenericRepository;
import com.nk.learningplatformreactiveapi.repositories.IUserRepository;
import com.nk.learningplatformreactiveapi.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends CRUDImpl<UserEntity, Integer> implements IUserService {

    private final IUserRepository repository;

    @Override
    protected IGenericRepository<UserEntity, Integer> getRepo() {
        return repository;
    }
}
