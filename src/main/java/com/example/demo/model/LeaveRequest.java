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

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeProfile employee;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotBlank
    private String type;

    private String reason;

    private String status = "PENDING";



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
