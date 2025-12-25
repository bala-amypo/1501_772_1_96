package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
public class EmployeeProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String employeeId;

    @NotBlank
    private String fullName;

    @Email
    private String email;

    @NotBlank
    private String role;

    @NotBlank
    private String teamName;

    private boolean active = true;

    @ManyToMany
    @JoinTable(
            name = "employee_colleagues",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "colleague_id")
    )
    private Set<EmployeeProfile> colleagues = new HashSet<>();

    // getters & setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public Set<EmployeeProfile> getColleagues() {
        return colleagues;
    }
}
