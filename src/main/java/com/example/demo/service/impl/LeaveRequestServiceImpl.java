package com.example.demo.service.impl;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.exception.BadRequestException;
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
    private final EmployeeProfileRepository empRepo;

    public LeaveRequestServiceImpl(LeaveRequestRepository leaveRepo,
                                   EmployeeProfileRepository empRepo) {
        this.leaveRepo = leaveRepo;
        this.empRepo = empRepo;
    }

    @Override
    public LeaveRequestDto create(LeaveRequestDto dto) {
        if (dto.getStartDate().isAfter(dto.getEndDate())) {
            throw new BadRequestException("Invalid Date Range: start date");
        }

        EmployeeProfile emp = empRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        LeaveRequest lr = new LeaveRequest();
        lr.setEmployee(emp);
        lr.setStartDate(dto.getStartDate());
        lr.setEndDate(dto.getEndDate());
        lr.setType(dto.getType());
        lr.setReason(dto.getReason());
        lr.setStatus("PENDING");

        return toDto(leaveRepo.save(lr));
    }

    @Override
    public LeaveRequestDto approve(Long id) {
        LeaveRequest lr = getLeave(id);
        lr.setStatus("APPROVED");
        return toDto(leaveRepo.save(lr));
    }

    @Override
    public LeaveRequestDto reject(Long id) {
        LeaveRequest lr = getLeave(id);
        lr.setStatus("REJECTED");
        return toDto(leaveRepo.save(lr));
    }

    @Override
    public List<LeaveRequestDto> getByEmployee(Long employeeId) {
        EmployeeProfile emp = empRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        return leaveRepo.findByEmployee(emp)
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<LeaveRequestDto> getOverlappingForTeam(
            String teamName, LocalDate start, LocalDate end) {

        return leaveRepo.findApprovedOverlappingForTeam(teamName, start, end)
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    private LeaveRequest getLeave(Long id) {
        return leaveRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave not found"));
    }

    private LeaveRequestDto toDto(LeaveRequest lr) {
        LeaveRequestDto dto = new LeaveRequestDto();
        dto.setId(lr.getId());
        dto.setEmployeeId(lr.getEmployee().getId());
        dto.setStartDate(lr.getStartDate());
        dto.setEndDate(lr.getEndDate());
        dto.setType(lr.getType());
        dto.setStatus(lr.getStatus());
        dto.setReason(lr.getReason());
        return dto;
    }
}
