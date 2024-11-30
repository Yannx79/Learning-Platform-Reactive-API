package com.nk.learningplatformreactiveapi.services.Impl;

import com.nk.learningplatformreactiveapi.entities.EnrollmentEntity;
import com.nk.learningplatformreactiveapi.repositories.IEnrollmentRepository;
import com.nk.learningplatformreactiveapi.repositories.IGenericRepository;
import com.nk.learningplatformreactiveapi.services.IEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl extends CRUDImpl<EnrollmentEntity, Integer> implements IEnrollmentService {

    private final IEnrollmentRepository repository;

    @Override
    protected IGenericRepository<EnrollmentEntity, Integer> getRepo() {
        return repository;
    }
}
