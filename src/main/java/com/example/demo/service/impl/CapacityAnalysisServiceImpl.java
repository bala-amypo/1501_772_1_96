package com.example.demo.service.impl;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.TeamCapacityConfig;
import com.example.demo.repository.TeamCapacityConfigRepository;
import com.example.demo.service.CapacityAnalysisService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class CapacityAnalysisServiceImpl implements CapacityAnalysisService {

    private final TeamCapacityConfigRepository configRepo;

    public CapacityAnalysisServiceImpl(TeamCapacityConfigRepository configRepo) {
        this.configRepo = configRepo;
    }

    @Override
    public CapacityAnalysisResultDto analyzeTeamCapacity(
            String teamName, LocalDate start, LocalDate end) {

        TeamCapacityConfig config = configRepo.findByTeamName(teamName)
                .orElseThrow(() -> new ResourceNotFoundException("Capacity config not found"));

        Map<LocalDate, Double> capacityMap = new HashMap<>();
        capacityMap.put(start, 100.0);

        CapacityAnalysisResultDto dto = new CapacityAnalysisResultDto();
        dto.setCapacityByDate(capacityMap);
        dto.setRisky(false);

        return dto;
    }
}
