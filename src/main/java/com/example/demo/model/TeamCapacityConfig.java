package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "team_capacity_config")
public class TeamCapacityConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Team name is required")
    @Column(nullable = false)
    private String teamName;

    @Min(value = 1, message = "Total headcount must be at least 1")
    @Column(nullable = false)
    private int totalHeadcount;

    @DecimalMin(value = "0.0", message = "Minimum capacity must be >= 0")
    @DecimalMax(value = "100.0", message = "Minimum capacity must be <= 100")
    @Column(nullable = false)
    private double minCapacityPercent;

    public TeamCapacityConfig() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTotalHeadcount() {
        return totalHeadcount;
    }

    public void setTotalHeadcount(int totalHeadcount) {
        this.totalHeadcount = totalHeadcount;
    }

    public double getMinCapacityPercent() {
        return minCapacityPercent;
    }

    public void setMinCapacityPercent(double minCapacityPercent) {
        this.minCapacityPercent = minCapacityPercent;
    }
}
