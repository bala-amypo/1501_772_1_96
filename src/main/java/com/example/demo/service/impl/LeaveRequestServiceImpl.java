package com.example.demo.service.impl;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.model.LeaveRequest;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.service.LeaveRequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRepo;
    private final EmployeeProfileRepository employeeRepo;

    public LeaveRequestServiceImpl(
            LeaveRequestRepository leaveRepo,
            EmployeeProfileRepository employeeRepo) {
        this.leaveRepo = leaveRepo;
        this.employeeRepo = employeeRepo;
    }

    @Override
    public LeaveRequestDto create(LeaveRequestDto dto) {

        EmployeeProfile emp = employeeRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        LeaveRequest leave = new LeaveRequest();
        leave.setEmployee(emp);
        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setType(dto.getType());
        leave.setReason(dto.getReason());

        LeaveRequest saved = leaveRepo.save(leave);
        return toDto(saved);
    }

    @Override
    public LeaveRequestDto approve(Long id) {
        LeaveRequest leave = leaveRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave not found"));
        leave.setStatus("APPROVED");
        return toDto(leaveRepo.save(leave));
    }

    @Override
    public LeaveRequestDto reject(Long id) {
        LeaveRequest leave = leaveRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave not found"));
        leave.setStatus("REJECTED");
        return toDto(leaveRepo.save(leave));
    }

    @Override
    public List<LeaveRequestDto> getByEmployee(Long employeeId) {
        EmployeeProfile emp = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        return leaveRepo.findByEmployee(emp)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LeaveRequestDto> getOverlappingForTeam(
            String team, LocalDate start, LocalDate end) {

        return leaveRepo.findApprovedOverlappingForTeam(team, start, end)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private LeaveRequestDto toDto(LeaveRequest leave) {
        LeaveRequestDto dto = new LeaveRequestDto();
        dto.setEmployeeId(leave.getEmployee().getId());
        dto.setStartDate(leave.getStartDate());
        dto.setEndDate(leave.getEndDate());
        dto.setType(leave.getType());
        dto.setReason(leave.getReason());
        return dto;
    }
}
