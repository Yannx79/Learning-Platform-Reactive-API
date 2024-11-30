package com.nk.learningplatformreactiveapi.services.Impl;

import com.nk.learningplatformreactiveapi.entities.CourseEntity;
import com.nk.learningplatformreactiveapi.repositories.ICourseRepository;
import com.nk.learningplatformreactiveapi.repositories.IGenericRepository;
import com.nk.learningplatformreactiveapi.services.ICourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl extends CRUDImpl<CourseEntity, Integer> implements ICourseService {

    private final ICourseRepository repository;

    @Override
    protected IGenericRepository<CourseEntity, Integer> getRepo() {
        return repository;
    }
}
