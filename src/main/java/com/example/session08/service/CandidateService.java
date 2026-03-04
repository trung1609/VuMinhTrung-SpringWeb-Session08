package com.example.session08.service;

import com.example.session08.exception.NotFoundCandidateException;
import com.example.session08.exception.ResourceConflictException;
import com.example.session08.model.dto.CandidateCreateDTO;
import com.example.session08.model.dto.CandidateUpdateDTO;
import com.example.session08.model.entity.Candidate;

public interface CandidateService {
    public Candidate createCandidate(CandidateCreateDTO request);

    Candidate updateCandidate(Long id, CandidateUpdateDTO request) throws NotFoundCandidateException;

}
