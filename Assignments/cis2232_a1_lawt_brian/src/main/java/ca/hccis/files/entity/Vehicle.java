package ca.hccis.files.entity;

import java.util.Scanner;

public class Vehicle {

    private int vehicleId;
    private String make;
    private String model;
    private int year;
    private double mileage;
    private double askingPrice;
    private double marketValue;
    private double accidentClaimAmount;
    private double estimatedRepairs;
    private int ownershipCount;
    private boolean inspectionStatus;
    private double otherCosts;

    public Vehicle() {
    }
    public Vehicle(int vehicleId){
        this.vehicleId = vehicleId;
    }

    public void getInformation(){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter make: ");
        make = input.nextLine();

        System.out.print("Enter model: ");
        model = input.nextLine();

        System.out.print("Enter year: ");
        year = input.nextInt();

        System.out.print("Enter mileage (km): ");
        mileage = input.nextDouble();

        System.out.print("Enter asking price: ");
        askingPrice = input.nextDouble();

        System.out.print("Enter market value: ");
        marketValue = input.nextDouble();

        System.out.print("Enter accident claim amount: ");
        accidentClaimAmount = input.nextDouble();

        System.out.print("Enter estimated repair costs: ");
        estimatedRepairs = input.nextDouble();

        System.out.print("Enter ownership count: ");
        ownershipCount = input.nextInt();

        System.out.print("Has the vehicle passed inspection? (true/false): ");
        inspectionStatus = input.nextBoolean();

        System.out.print("Enter other costs: ");
        otherCosts = input.nextDouble();
    }
    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
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
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public double getAskingPrice() {
        return askingPrice;
    }

    public void setAskingPrice(double askingPrice) {
        this.askingPrice = askingPrice;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }

    public double getAccidentClaimAmount() {
        return accidentClaimAmount;
    }

    public void setAccidentClaimAmount(double accidentClaimAmount) {
        this.accidentClaimAmount = accidentClaimAmount;
    }

    public double getEstimatedRepairs() {
        return estimatedRepairs;
    }

    public void setEstimatedRepairs(double estimatedRepairs) {
        this.estimatedRepairs = estimatedRepairs;
    }

    public int getOwnershipCount() {
        return ownershipCount;
    }

    public void setOwnershipCount(int ownershipCount) {
        this.ownershipCount = ownershipCount;
    }

    public boolean isInspectionStatus() {
        return inspectionStatus;
    }

    public void setInspectionStatus(boolean inspectionStatus) {
        this.inspectionStatus = inspectionStatus;
    }

    public double getOtherCosts() {
        return otherCosts;
    }

    public void setOtherCosts(double otherCosts) {
        this.otherCosts = otherCosts;
    }
    @Override
    public String toString() {

        return "Vehicle ID: " + vehicleId
                + System.lineSeparator()
                + "Make: " + make
                + System.lineSeparator()
                + "Model: " + model
                + System.lineSeparator()
                + "Year: " + year
                + System.lineSeparator()
                + "Mileage: " + mileage + " km"
                + System.lineSeparator()
                + "Asking Price: $" + askingPrice
                + System.lineSeparator()
                + "Market Value: $" + marketValue
                + System.lineSeparator()
                + "Accident Claim Amount: $" + accidentClaimAmount
                + System.lineSeparator()
                + "Estimated Repairs: $" + estimatedRepairs
                + System.lineSeparator()
                + "Ownership Count: " + ownershipCount
                + System.lineSeparator()
                + "Inspection Status: " + inspectionStatus
                + System.lineSeparator()
                + "Other Costs: $" + otherCosts;
    }



}
