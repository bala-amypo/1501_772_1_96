package com.example.demo.controller;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.service.LeaveRequestService;
import jakarta.validation.Valid;  
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leaves")
public class LeaveRequestController {

    private final LeaveRequestService service;

    public LeaveRequestController(LeaveRequestService service) {
        this.service = service;
    }

    @PostMapping("/apply")
    public LeaveRequestDto applyLeave(@RequestBody LeaveRequestDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}/approve")
    public LeaveRequestDto approve(@PathVariable Long id) {
        return service.approve(id);
    }

    @PutMapping("/{id}/reject")
    public LeaveRequestDto reject(@PathVariable Long id) {
        return service.reject(id);
    }
}
