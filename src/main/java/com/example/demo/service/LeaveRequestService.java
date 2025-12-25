package com.example.demo.service;

import com.example.demo.dto.LeaveRequestDto;

public interface LeaveRequestService {

    LeaveRequestDto create(LeaveRequestDto dto);

    LeaveRequestDto approve(Long id);

    LeaveRequestDto reject(Long id);
}
