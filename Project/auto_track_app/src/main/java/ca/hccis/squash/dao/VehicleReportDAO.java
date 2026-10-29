/**
 * Author: Brian Lawt
 * Date: October 24, 2026
 * Class: CIS2232
 * Description: Handles JDBC database
 * queries for Auto Track vehicle reports.
 */
package ca.hccis.squash.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VehicleReportDAO {

    /**
     * Searches for vehicles matching a make or model.
     *
     * @param searchText Make or model entered by the user
     * @return Matching vehicle records
     */
    public List<String[]> searchVehicles(String searchText)
            throws SQLException {

        List<String[]> vehicles = new ArrayList<>();

        ResourceBundle rb = ResourceBundle.getBundle("application");

        String url = rb.getString("spring.datasource.url");
        String username = rb.getString("spring.datasource.username");
        String password = rb.getString("spring.datasource.password");

        String sql = """
                SELECT vehicleId, make, model, year, mileage,
                       askingPrice, estimatedRepairs, otherCosts,
                       marketValue
                FROM vehicle
                WHERE LOWER(make) LIKE LOWER(?)
                   OR LOWER(model) LIKE LOWER(?)
                ORDER BY make, model
                """;

        try (Connection conn = DriverManager.getConnection(
                url, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String searchValue = "%" + searchText.trim() + "%";

            stmt.setString(1, searchValue);
            stmt.setString(2, searchValue);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    double totalCost =
                            rs.getDouble("askingPrice")
                                    + rs.getDouble("estimatedRepairs")
                                    + rs.getDouble("otherCosts");

                    double difference =
                            rs.getDouble("marketValue") - totalCost;

                    vehicles.add(new String[]{
                            String.valueOf(rs.getInt("vehicleId")),
                            rs.getString("make"),
                            rs.getString("model"),
                            String.valueOf(rs.getInt("year")),
                            String.valueOf(rs.getDouble("mileage")),
                            String.valueOf(rs.getDouble("askingPrice")),
                            String.valueOf(rs.getDouble("estimatedRepairs")),
                            String.valueOf(rs.getDouble("otherCosts")),
                            String.valueOf(totalCost),
                            String.valueOf(rs.getDouble("marketValue")),
                            String.valueOf(difference)
                    });
                }
            }
        }

        return vehicles;
    }
}

