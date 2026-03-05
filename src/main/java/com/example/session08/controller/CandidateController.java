package com.example.session08.controller;

import com.example.session08.exception.FileStorageException;
import com.example.session08.exception.NotFoundCandidateException;
import com.example.session08.model.dto.ApiResponse;
import com.example.session08.model.dto.CandidateCreateDTO;
import com.example.session08.model.dto.CandidateUpdateDTO;
import com.example.session08.model.entity.Candidate;
import com.example.session08.service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<Candidate>> createCandidate(@ModelAttribute CandidateCreateDTO request) throws FileStorageException {
        Candidate candidate = candidateService.createCandidate(request);
        return ResponseEntity.ok(new ApiResponse<>("SUCCESS", "Candidate created successfully", candidate));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Candidate>> updateCandidate(@PathVariable Long id, @Valid @ModelAttribute CandidateUpdateDTO request) throws NotFoundCandidateException {
        Candidate candidate = candidateService.updateCandidate(id, request);
        return ResponseEntity.ok(new ApiResponse<>("SUCCESS", "Candidate updated successfully", candidate));
    }
}
