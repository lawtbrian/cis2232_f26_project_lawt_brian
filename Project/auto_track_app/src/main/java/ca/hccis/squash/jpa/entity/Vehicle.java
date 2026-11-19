
/*
 * Project: Auto Track - Vehicle Cost & Maintenance Manager
 * Course: CIS-2232
 * Sprint: 3 - MVC, CRUD, JPA, and Validation
 * File: Vehicle.java
 * Author: Brian Lawt
 * Date: November 18, 2026
 *
 * Purpose:
 * Represents a vehicle stored in the MySQL database.
 * Uses Spring Data JPA for database mapping and
 * Spring Validation for input validation.
 * Calculates total estimated cost and potential difference.
 */

package ca.hccis.squash.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "vehicle")
public class Vehicle {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicleId")
    private Integer vehicleId;

    @NotBlank(message = "Make is required")
    @Size(max = 50, message = "Make must be 50 characters or less")
    private String make;

    @NotBlank(message = "Model is required")
    @Size(max = 50, message = "Model must be 50 characters or less")
    private String model;

    @NotNull(message = "Year is required")
    @Min(value = 1886, message = "Year must be 1886 or later")
    @Max(value = 2100, message = "Year must be 2100 or earlier")
    private Integer year;

    @NotNull(message = "Mileage is required")
    @PositiveOrZero(message = "Mileage cannot be negative")
    private Double mileage;

    @NotNull(message = "Asking price is required")
    @PositiveOrZero(message = "Asking price cannot be negative")
    private Double askingPrice;

    @NotNull(message = "Market value is required")
    @PositiveOrZero(message = "Market value cannot be negative")
    private Double marketValue;

    @PositiveOrZero(message = "Accident claim amount cannot be negative")
    private Double accidentClaimAmount;

    @NotNull(message = "Estimated repairs is required")
    @PositiveOrZero(message = "Estimated repairs cannot be negative")
    private Double estimatedRepairs;

    @NotNull(message = "Ownership count is required")
    @PositiveOrZero(message = "Ownership count cannot be negative")
    private Integer ownershipCount;

    @NotNull(message = "Inspection status is required")
    private Boolean inspectionStatus;

    @NotNull(message = "Other costs are required")
    @PositiveOrZero(message = "Other costs cannot be negative")
    private Double otherCosts;


    public Vehicle() {
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public Double getAskingPrice() {
        return askingPrice;
    }

    public void setAskingPrice(Double askingPrice) {
        this.askingPrice = askingPrice;
    }

    public Double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(Double marketValue) {
        this.marketValue = marketValue;
    }

    public Double getAccidentClaimAmount() {
        return accidentClaimAmount;
    }

    public void setAccidentClaimAmount(Double accidentClaimAmount) {
        this.accidentClaimAmount = accidentClaimAmount;
    }

    public Double getEstimatedRepairs() {
        return estimatedRepairs;
    }

    public void setEstimatedRepairs(Double estimatedRepairs) {
        this.estimatedRepairs = estimatedRepairs;
    }

    public Integer getOwnershipCount() {
        return ownershipCount;
    }

    public void setOwnershipCount(Integer ownershipCount) {
        this.ownershipCount = ownershipCount;
    }

    public Boolean getInspectionStatus() {
        return inspectionStatus;
    }

    public void setInspectionStatus(Boolean inspectionStatus) {
        this.inspectionStatus = inspectionStatus;
    }

    public Double getOtherCosts() {
        return otherCosts;
    }

    public void setOtherCosts(Double otherCosts) {
        this.otherCosts = otherCosts;
    }

    @Transient
    public double getTotalEstimatedCost() {
        return (askingPrice == null ? 0 : askingPrice)
                + (estimatedRepairs == null ? 0 : estimatedRepairs)
                + (otherCosts == null ? 0 : otherCosts);
    }

    @Transient
    public double getPotentialDifference() {
        return (marketValue == null ? 0 : marketValue)
                - getTotalEstimatedCost();
    }
}
