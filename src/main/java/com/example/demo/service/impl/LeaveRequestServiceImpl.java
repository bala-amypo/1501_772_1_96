package com.example.demo.service.impl;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.model.LeaveRequest;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.service.LeaveRequestService;
import org.springframework.stereotype.Service;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRepo;
    private final EmployeeProfileRepository employeeRepo;

    public LeaveRequestServiceImpl(
            LeaveRequestRepository leaveRepo,
            EmployeeProfileRepository employeeRepo
    ) {
        this.leaveRepo = leaveRepo;
        this.employeeRepo = employeeRepo;
    }

    @Override
    public LeaveRequestDto create(LeaveRequestDto dto) {

        EmployeeProfile employee = employeeRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        LeaveRequest leave = new LeaveRequest();
        leave.setEmployee(employee);
        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setType(dto.getType());
        leave.setReason(dto.getReason());

        LeaveRequest saved = leaveRepo.save(leave);

        LeaveRequestDto response = new LeaveRequestDto();
        response.setEmployeeId(employee.getId());
        response.setStartDate(saved.getStartDate());
        response.setEndDate(saved.getEndDate());
        response.setType(saved.getType());
        response.setReason(saved.getReason());

        return response;
    }

    @Override
    public LeaveRequestDto approve(Long id) {
        LeaveRequest leave = leaveRepo.findById(id).orElseThrow();
        leave.setStatus("APPROVED");
        leaveRepo.save(leave);
        return createResponse(leave);
    }

    @Override
    public LeaveRequestDto reject(Long id) {
        LeaveRequest leave = leaveRepo.findById(id).orElseThrow();
        leave.setStatus("REJECTED");
        leaveRepo.save(leave);
        return createResponse(leave);
    }

    private LeaveRequestDto createResponse(LeaveRequest leave) {
        LeaveRequestDto dto = new LeaveRequestDto();
        dto.setEmployeeId(leave.getEmployee().getId());
        dto.setStartDate(leave.getStartDate());
        dto.setEndDate(leave.getEndDate());
        dto.setType(leave.getType());
        dto.setReason(leave.getReason());
        return dto;
    }
}
