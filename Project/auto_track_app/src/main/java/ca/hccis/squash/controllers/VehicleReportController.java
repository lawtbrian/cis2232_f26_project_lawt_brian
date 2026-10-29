/**
 * Author: Brian Lawt
 * Date: October 27, 2026
 * Class: CIS2232
 * Description: Handles requests for Auto Track vehicle reports.
 */

package ca.hccis.squash.controllers;

import ca.hccis.squash.util.VehicleReportFileWriter;
import java.io.IOException;
import ca.hccis.squash.dao.VehicleReportDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;


@Controller
public class VehicleReportController {

    private final VehicleReportDAO vehicleReportDAO =
            new VehicleReportDAO();

    /**
     * Displays the vehicle search input form.
     *
     * @return Vehicle search template
     */
    @GetMapping("/reports/search")
    public String showVehicleSearch() {
        return "report/vehicleSearch";
    }

    /**
     * Processes the vehicle search and displays matching records.
     *
     * @param searchText Vehicle make or model entered by the user
     * @param model      Spring MVC model
     * @return Vehicle search results template
     */
    @GetMapping("/reports/search/results")
    public String searchVehicles(
            @RequestParam("searchText") String searchText,
            Model model) {

        try {
            List<String[]> vehicles = vehicleReportDAO.searchVehicles(searchText);

            model.addAttribute("vehicles", vehicles);
            model.addAttribute("searchText", searchText);

// Save the vehicle search report to a text file
            try {
                VehicleReportFileWriter.saveSearchReport(searchText, vehicles);
                model.addAttribute("message", "Report saved successfully.");
            } catch (IOException e) {
                model.addAttribute("message", "Report displayed, but could not be saved.");
            }


        } catch (SQLException e) {
            model.addAttribute("error",
                    "Unable to retrieve vehicle information.");
        }

        return "report/vehicleSearch";
    }

    /**
     * Displays the vehicle cost comparison report page.
     *
     * @return Comparison report view
     */
    @GetMapping("/reports/comparison")
    public String showVehicleComparison() {
        return "report/vehicleCostComparison";
    }

    /**
     * Retrieves vehicles and compares total estimated cost
     * against market value.
     *
     * @param searchText Vehicle make or model
     * @param model      Spring MVC model
     * @return Comparison report view
     */
    @GetMapping("/reports/comparison/results")
    public String compareVehicles(
            @RequestParam(value = "searchText", defaultValue = "") String searchText,
            Model model) {

        try {
            // Retrieve vehicles using JDBC
            List<String[]> vehicles = vehicleReportDAO.searchVehicles(searchText);

            // Find the highest value for scaling the chart
            double chartMax = 1;

            for (String[] vehicle : vehicles) {
                double totalCost = Double.parseDouble(vehicle[8]);
                double marketValue = Double.parseDouble(vehicle[9]);

                chartMax = Math.max(chartMax, Math.max(totalCost, marketValue));
            }

            model.addAttribute("vehicles", vehicles);
            model.addAttribute("searchText", searchText);
            model.addAttribute("chartMax", chartMax);


            // Save the vehicle cost comparison report to a text file
            try {
                VehicleReportFileWriter.saveComparisonReport(searchText, vehicles);
                model.addAttribute("message", "Comparison report saved successfully.");
            } catch (IOException e) {
                model.addAttribute("message", "Comparison report displayed, but could not be saved.");
            }


        } catch (SQLException e) {
            model.addAttribute("error", "Unable to retrieve vehicle information.");
        }

        return "report/vehicleCostComparison";
    }
}




