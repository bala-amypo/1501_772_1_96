package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class EmployeeProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Employee ID required")
    private String employeeId;

    @NotBlank(message = "Full name required")
    private String fullName;

    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Role required")
    private String role;

    @NotBlank(message = "Team name required")
    private String teamName;

    private boolean active = true;

    public Long getId() {
        return id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getTeamName() {
        return teamName;
    }

    public boolean isActive() {
        return active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
