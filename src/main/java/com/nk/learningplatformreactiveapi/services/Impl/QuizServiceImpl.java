package com.nk.learningplatformreactiveapi.services.Impl;

import com.nk.learningplatformreactiveapi.entities.QuizEntity;
import com.nk.learningplatformreactiveapi.repositories.IGenericRepository;
import com.nk.learningplatformreactiveapi.repositories.IQuizRepository;
import com.nk.learningplatformreactiveapi.services.IQuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl extends CRUDImpl<QuizEntity, Integer> implements IQuizService {

    private final IQuizRepository repository;

    @Override
    protected IGenericRepository<QuizEntity, Integer> getRepo() {
        return repository;
    }
}
