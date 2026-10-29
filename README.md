# Auto Track — Vehicle Cost & Maintenance Manager

CIS2232 Project

## Development Team

- Business Client: Prabin
- Lead Developer: Brian 
- Quality Control: Harry  

## Description

Auto Track is a vehicle management web application designed to help people evaluate used vehicles and understand the potential cost of purchasing and owning them. Buying a used vehicle can involve more than simply comparing the seller's asking price. Mileage, previous accident claims, estimated repair costs, vehicle market value, and inspection status can all affect the overall financial decision. Auto Track will provide a simple way for users to enter important vehicle information and calculate the estimated cost and potential savings of a vehicle.
The primary purpose of the application is to give users a centralized place to record information about used vehicles they are considering. Users can enter the vehicle's make, model, year, mileage, asking price, estimated market value, accident claim amount, estimated repair costs, ownership count, and inspection status.
The system will use this information to calculate the total estimated purchase cost and compare it with the estimated market value. This allows users to quickly see how much they may potentially save or spend compared with the estimated value of the vehicle. The application is intended to assist users with organizing information and making informed comparisons rather than replacing a professional vehicle inspection or appraisal.
The initial web application will focus on entering, viewing, updating, and comparing vehicle information. In the future, Auto Track could be expanded into a mobile application for vehicle owners. Additional features could include maintenance records, fuel expenses, insurance costs, tire tracking, service reminders, vehicle photos, and total ownership cost tracking. This would allow Auto Track to develop from a used-car evaluation tool into a complete vehicle management application.


## Color

Main Color: Deep Navy Blue

## Required Fields

| Field | Type | Description |
| --- | --- | --- |
| Vehicle Id | Int | A unique identifier automatically assigned to each vehicle record. |
| Make | String | The manufacturer of the vehicle. |
| Model | String | The specific vehicle model. |
| Year | Integer | The model year of the vehicle. |
| Mileage | Double | The current mileage in kilometers. |
| Asking Price | Double | The seller's asking price. |
| Market Value | Double | The estimated current market value. |
| Accident Claim Amount | Double | The total reported accident claim amount, if known. |
| Estimated Repairs | Double | The estimated cost of repairs required. |
| Ownership Count | Integer | The number of previous owners. |
| Inspection Status | Boolean | Indicates whether the vehicle has passed inspection. |
| Other Costs | Double | Any additional costs associated with the vehicle. |

## Calculation

Auto Track will calculate the Total Estimated Cost of a vehicle by combining the asking price, estimated repair costs, and other costs associated with purchasing the vehicle.

**Total Estimated Cost = Asking Price + Estimated Repairs + Other Costs**

For example, if a vehicle has:
- Asking Price: $12,500
- Estimated Repairs: $800
- Other Costs: $500

**Total Estimated Cost = $12,500 + $800 + $500 = $13,800**

The system will then compare the Total Estimated Cost with the vehicle's estimated market value to calculate the Potential Difference.

**Potential Difference = Market Value - Total Estimated Cost**

If the estimated market value is $15,000:

**Potential Difference = $15,000 - $13,800 = $1,200**

Estimated Repairs and Other Costs can be entered as $0 when there are no additional costs.

This calculation helps users understand the estimated overall purchase cost of a vehicle compared with its market value.



## Report Details

### Vehicle Search Report

**Use Case:** Allow users to search for vehicles by make or model and view their information and estimated costs.

**Input:** Users enter a vehicle make or model to search for matching vehicles.

**Output:** Vehicle information displayed in a table format.

**Description:** Users can enter a vehicle make or model to search for matching vehicles stored in the database. The report will display all matching vehicles along with their details and estimated costs.

**Report Data Includes:**

- Vehicle ID
- Make
- Model
- Year
- Mileage
- Asking Price
- Estimated Repairs
- Other Costs
- Total Estimated Cost
- Market Value
- Potential Difference

**File Output:** The report results are automatically saved to a timestamped text file in `C:\cis2232`.

---


### Vehicle Cost Comparison Report

**Use Case:** Allow users to compare the estimated total costs and market values of vehicles stored in the database.

**Input:** Users can enter a vehicle make or model to compare matching vehicles. Leaving the search field blank displays all vehicles.

**Output:** Vehicle cost information displayed in a comparison table and horizontal bar chart.

**Description:** Users can search for vehicles by make or model to compare their estimated total costs against their market values. The report displays a table and horizontal bar chart showing the financial differences between vehicles.

**Report Data Includes:**

- Vehicle Make and Model
- Vehicle Year
- Total Estimated Cost
- Market Value
- Potential Difference

**Chart Details:**

| Component | Description |
| --- | --- |
| Chart Type | Horizontal Bar Chart |
| Categories | Vehicle Make, Model, and Year |
| Values | Dollar Amount ($) |
| First Bar | Total Estimated Cost |
| Second Bar | Market Value |

**File Output:** The report results are automatically saved to a timestamped text file in `C:\cis2232`.

This report helps users identify which vehicles offer the best potential financial value by comparing their estimated expenses against their market values.

---

## Sprint 2 — JDBC and File I/O

Both Auto Track reports retrieve vehicle information from the MySQL database using JDBC through the `VehicleReportDAO` class.

The DAO uses `PreparedStatement` to execute SQL queries and `ResultSet` to retrieve matching vehicle records.

When a report is generated, the application displays the results on the webpage and automatically saves them to a timestamped text file in the `C:\cis2232` folder.

### Report Files

| Report | Filename Format |
| --- | --- |
| Vehicle Search Report | `VehicleSearchReport_yyyyMMdd_HHmmss_SSS.txt` |
| Vehicle Cost Comparison Report | `VehicleCostComparison_yyyyMMdd_HHmmss_SSS.txt` |

### Report URLs

The following URLs are available when the Spring Boot application is running locally.

| Report | URL |
| --- | --- |
| Vehicle Search Report | [Open Vehicle Search](http://localhost:8080/auto-track/reports/search) |
| Vehicle Cost Comparison Report | [Open Cost Comparison](http://localhost:8080/auto-track/reports/comparison) |


