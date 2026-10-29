
package ca.hccis.squash.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Utility class to save vehicle search reports to a text file.
 *
 * @author Brian Lawt
 * @since 2026-10-29
 */
public class VehicleReportFileWriter {

    /**
     * Saves vehicle search results to a timestamped text file.
     *
     * @param searchText Search entered by the user
     * @param vehicles List of matching vehicles
     * @throws IOException If the file cannot be written
     */
    public static void saveSearchReport(String searchText,
                                        List<String[]> vehicles) throws IOException {

        Path folder = Path.of("C:\\cis2232");
        Files.createDirectories(folder);

        String timestamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));

        Path file = folder.resolve("VehicleSearchReport_" + timestamp + ".txt");

        StringBuilder report = new StringBuilder();

        report.append("AUTO TRACK - VEHICLE SEARCH REPORT\n");
        report.append("Search: ").append(searchText).append("\n");
        report.append("Generated: ").append(LocalDateTime.now()).append("\n\n");

        report.append("ID | Make | Model | Year | Mileage | Asking Price | Repairs | Other Costs | Total Cost | Market Value | Difference\n");

        for (String[] vehicle : vehicles) {
            report.append(String.join(" | ", vehicle)).append("\n");
        }

        report.append("\nTotal Vehicles Found: ").append(vehicles.size());

        Files.writeString(file, report.toString(),
                StandardOpenOption.CREATE_NEW);
    }


    /**
     * Saves vehicle cost comparison results to a timestamped text file.
     *
     * @param searchText Search entered by the user
     * @param vehicles List of matching vehicles
     * @throws IOException If the file cannot be written
     */
    public static void saveComparisonReport(String searchText,
                                            List<String[]> vehicles) throws IOException {

        Path folder = Path.of("C:\\cis2232");
        Files.createDirectories(folder);

        String timestamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));

        Path file = folder.resolve("VehicleCostComparison_" + timestamp + ".txt");

        StringBuilder report = new StringBuilder();

        report.append("AUTO TRACK - VEHICLE COST COMPARISON REPORT\n");
        report.append("Search: ").append(searchText).append("\n");
        report.append("Generated: ").append(LocalDateTime.now()).append("\n\n");

        report.append("Vehicle | Total Estimated Cost | Market Value | Potential Difference\n");

        for (String[] vehicle : vehicles) {
            String vehicleName = vehicle[1] + " " + vehicle[2] + " " + vehicle[3];

            report.append(vehicleName).append(" | ")
                    .append(vehicle[8]).append(" | ")
                    .append(vehicle[9]).append(" | ")
                    .append(vehicle[10]).append("\n");
        }

        report.append("\nTotal Vehicles Compared: ").append(vehicles.size());

        Files.writeString(file, report.toString(),
                StandardOpenOption.CREATE_NEW);
    }


}
