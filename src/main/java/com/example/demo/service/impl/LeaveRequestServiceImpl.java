package com.example.demo.service.impl;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.model.LeaveRequest;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.service.LeaveRequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository repository;

    public LeaveRequestServiceImpl(LeaveRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public LeaveRequestDto create(LeaveRequestDto dto) {
        LeaveRequest entity = new LeaveRequest();
        entity.setReason(dto.getReason());
        entity.setStatus("PENDING");
        repository.save(entity);
        return dto;
    }

    @Override
    public LeaveRequestDto approve(Long id) {
        LeaveRequest lr = repository.findById(id).orElseThrow();
        lr.setStatus("APPROVED");
        repository.save(lr);
        return new LeaveRequestDto();
    }

    @Override
    public LeaveRequestDto reject(Long id) {
        LeaveRequest lr = repository.findById(id).orElseThrow();
        lr.setStatus("REJECTED");
        repository.save(lr);
        return new LeaveRequestDto();
    }

    @Override
    public List<LeaveRequestDto> getOverlappingForTeam(
            String teamName,
            LocalDate start,
            LocalDate end
    ) {
        // SIMPLE RETURN (no logic needed for now)
        return Collections.emptyList();
    }
}
