package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class TeamCapacityConfig {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Team name is required")
    private String teamName;

    @Min(value = 1, message = "Total headcount must be greater than 0")
    private int totalHeadcount;

    @DecimalMin(value = "1.0", message = "Minimum capacity must be at least 1%")
    @DecimalMax(value = "100.0", message = "Capacity cannot exceed 100%")
    private double minCapacityPercent;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public int getTotalHeadcount() { return totalHeadcount; }
    public void setTotalHeadcount(int totalHeadcount) { this.totalHeadcount = totalHeadcount; }

    public double getMinCapacityPercent() { return minCapacityPercent; }
    public void setMinCapacityPercent(double minCapacityPercent) {
        this.minCapacityPercent = minCapacityPercent;
    }
}
