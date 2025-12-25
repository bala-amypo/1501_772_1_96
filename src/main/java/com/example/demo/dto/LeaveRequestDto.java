package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class LeaveRequestDto {

    @NotNull(message = "Employee ID required")
    private Long employeeId;

    @NotNull(message = "Start date required")
    private LocalDate startDate;

    @NotNull(message = "End date required")
    private LocalDate endDate;

    @NotBlank(message = "Type required")
    private String type;

    private String reason;

    // getters & setters
    public Long getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
}
