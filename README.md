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

Deep Navy Blue

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

Auto Track will calculate the Total Estimated Cost of a vehicle by combining the asking price, estimated repair costs, and other estimated costs associated with purchasing the vehicle.
Total Estimated Cost = Asking Price + Estimated Repairs + Other Costs
For example, if a vehicle has an asking price of $12,500, estimated repairs of $800, and other costs of $500, the Total Estimated Cost would be:
$12,500 + $800 + $500 = $13,800
The system will then compare the Total Estimated Cost with the vehicle's estimated market value to calculate the Potential Difference.
Potential Difference = Market Value − Total Estimated Cost
If the estimated market value is $15,000, the Potential Difference would be:
$15,000 − $13,800 = $1,200
Estimated Repairs and Other Costs can be entered as $0 when there are no additional costs. This allows the calculation to work for vehicles that do not require repairs or have additional expenses.
The calculation uses several fields entered by the user and provides a simple way to understand the vehicle's estimated overall purchase cost compared with its estimated market value.

## Report Details

To be determined in future sprint
