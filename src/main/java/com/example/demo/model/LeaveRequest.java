package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Start date required")
    private LocalDate startDate;

    @NotNull(message = "End date required")
    private LocalDate endDate;

    @NotBlank(message = "Reason required")
    private String reason;

    @NotBlank(message = "Type required")
    private String type;

    private String status = "PENDING";

    @ManyToOne
    private EmployeeProfile employee;

    public Long getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getReason() {
        return reason;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public EmployeeProfile getEmployee() {
        return employee;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEmployee(EmployeeProfile employee) {
        this.employee = employee;
    }
}
