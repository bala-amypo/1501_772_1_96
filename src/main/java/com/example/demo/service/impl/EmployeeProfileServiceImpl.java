package com.example.demo.service.impl;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeProfileServiceImpl implements EmployeeProfileService {

    private final EmployeeProfileRepository repository;

    public EmployeeProfileServiceImpl(EmployeeProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmployeeProfileDto create(EmployeeProfileDto dto) {
        EmployeeProfile emp = new EmployeeProfile();
        emp.setEmployeeId(dto.getEmployeeId());
        emp.setFullName(dto.getFullName());
        emp.setEmail(dto.getEmail());
        emp.setTeamName(dto.getTeamName());
        emp.setRole(dto.getRole());
        emp.setActive(true);

        return toDto(repository.save(emp));
    }

    @Override
    public EmployeeProfileDto update(Long id, EmployeeProfileDto dto) {
        EmployeeProfile emp = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        emp.setFullName(dto.getFullName());
        emp.setTeamName(dto.getTeamName());
        emp.setRole(dto.getRole());

        return toDto(repository.save(emp));
    }

    @Override
    public void deactivate(Long id) {
        EmployeeProfile emp = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        emp.setActive(false);
        repository.save(emp);
    }

    @Override
    public EmployeeProfileDto getById(Long id) {
        return repository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    @Override
    public List<EmployeeProfileDto> getByTeam(String teamName) {
        return repository.findByTeamNameAndActiveTrue(teamName)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeProfileDto> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private EmployeeProfileDto toDto(EmployeeProfile emp) {
        EmployeeProfileDto dto = new EmployeeProfileDto();
        dto.setId(emp.getId());
        dto.setEmployeeId(emp.getEmployeeId());
        dto.setFullName(emp.getFullName());
        dto.setEmail(emp.getEmail());
        dto.setTeamName(emp.getTeamName());
        dto.setRole(emp.getRole());
        return dto;
    }
}
