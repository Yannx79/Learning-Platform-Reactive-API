package com.nk.learningplatformreactiveapi.services.Impl;

import com.nk.learningplatformreactiveapi.entities.LessonEntity;
import com.nk.learningplatformreactiveapi.repositories.IGenericRepository;
import com.nk.learningplatformreactiveapi.repositories.ILessonRepository;
import com.nk.learningplatformreactiveapi.services.ILessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl extends CRUDImpl<LessonEntity, Integer> implements ILessonService {

    private final ILessonRepository repository;

    @Override
    protected IGenericRepository<LessonEntity, Integer> getRepo() {
        return repository;
    }
}
