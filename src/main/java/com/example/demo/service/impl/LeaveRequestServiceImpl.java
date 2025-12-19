package com.example.demo.service.impl;

import com.example.demo.model.LeaveRequest;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.service.LeaveRequestService;
import org.springframework.stereotype.Service;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository repository;

    public LeaveRequestServiceImpl(LeaveRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public LeaveRequest create(LeaveRequest request) {
        return repository.save(request);
    }

    @Override
    public LeaveRequest approve(Long id) {
        LeaveRequest lr = repository.findById(id).orElseThrow();
        lr.setStatus("APPROVED");
        return repository.save(lr);
    }

    @Override
    public LeaveRequest reject(Long id) {
        LeaveRequest lr = repository.findById(id).orElseThrow();
        lr.setStatus("REJECTED");
        return repository.save(lr);
    }
}
