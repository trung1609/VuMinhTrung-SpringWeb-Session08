package com.example.session08.service.impl;

import com.example.session08.exception.NotFoundCandidateException;
import com.example.session08.exception.ResourceConflictException;
import com.example.session08.model.dto.CandidateCreateDTO;
import com.example.session08.model.dto.CandidateUpdateDTO;
import com.example.session08.model.entity.Candidate;
import com.example.session08.repository.CandidateRepository;
import com.example.session08.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public Candidate createCandidate(CandidateCreateDTO request) {
        Candidate candidate = new Candidate();
        candidate.setFullName(request.getFullName());
        candidate.setEmail(request.getEmail());
        candidate.setAge(request.getAge());
        candidate.setYearsOfExperience(request.getYearsOfExperience());
        candidate.setAddress(request.getAddress());
        candidate.setBio(request.getBio());
        candidate.setPhone(request.getPhone());

        return candidateRepository.save(candidate);
    }

    @Override
    public Candidate updateCandidate(Long id, CandidateUpdateDTO request) throws NotFoundCandidateException {
        Candidate candidate = candidateRepository.findById(id).orElseThrow(() -> new NotFoundCandidateException("Candidate not found with id: " + id));
        candidate.setAddress(request.getAddress());
        candidate.setBio(request.getBio());
        return candidateRepository.save(candidate);
    }



}
