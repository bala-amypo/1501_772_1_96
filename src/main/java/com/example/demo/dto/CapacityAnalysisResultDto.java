package com.example.demo.dto;

import java.time.LocalDate;
import java.util.Map;

public class CapacityAnalysisResultDto {

    private Map<LocalDate, Double> capacityByDate;
    private boolean risky;

    public Map<LocalDate, Double> getCapacityByDate() {
        return capacityByDate;
    }

    public void setCapacityByDate(Map<LocalDate, Double> capacityByDate) {
        this.capacityByDate = capacityByDate;
    }

    public boolean isRisky() {
        return risky;
    }

    public void setRisky(boolean risky) {
        this.risky = risky;
    }
}
