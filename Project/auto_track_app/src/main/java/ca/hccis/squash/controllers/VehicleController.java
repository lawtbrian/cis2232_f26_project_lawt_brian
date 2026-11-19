
/*
 * Project: Auto Track - Vehicle Cost & Maintenance Manager
 * Course: CIS-2232
 * Sprint: 3 - MVC, CRUD, JPA, and Validation
 * File: VehicleController.java
 * Author: Brian Lawt
 * Date: November 18, 2026
 *
 * Purpose:
 * Handles vehicle CRUD operations using Spring MVC
 * and Spring Data JPA. Connects the vehicle database
 * to the web application.
 */

package ca.hccis.squash.controllers;

import ca.hccis.squash.jpa.entity.Vehicle;
import ca.hccis.squash.repositories.VehicleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;

@Controller
public class VehicleController {

    private final VehicleRepository vehicleRepository;

    public VehicleController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * Retrieves all vehicles from the database
     * and displays them on the vehicle list page.
     *
     * @param model Model used to pass data to the view
     * @return Name of the vehicle list template
     */
    @GetMapping("/vehicles")
    public String listVehicles(Model model) {

        model.addAttribute("vehicles", vehicleRepository.findAll());

        return "vehicles/list";
    }

    /**
     * Displays the form for adding a new vehicle.
     *
     * @param model Model used to pass a new vehicle
     * @return Name of the vehicle form template
     */
    @GetMapping("/vehicles/add")
    public String showAddForm(Model model) {

        model.addAttribute("vehicle", new Vehicle());

        return "vehicles/form";
    }


    /**
     * Validates and saves a new vehicle using Spring Data JPA.
     * Returns to the form if validation errors are found.
     *
     * @param vehicle Vehicle submitted from the form
     * @param bindingResult Contains validation errors
     * @return Vehicle form or redirect to vehicle list
     */
    @PostMapping("/vehicles/add")
    public String addVehicle(@Valid @ModelAttribute("vehicle") Vehicle vehicle,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "vehicles/form";
        }
        if (vehicle.getAccidentClaimAmount() == null){
            vehicle.setAccidentClaimAmount(0.0);
        }

        vehicleRepository.save(vehicle);

        return "redirect:/vehicles";
    }


    /**
     * Displays the edit form for an existing vehicle.
     *
     * @param id ID of the vehicle to edit
     * @param model Model used to pass vehicle information
     * @return Name of the vehicle form template
     */
    @GetMapping("/vehicles/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Invalid vehicle ID: " + id));

        model.addAttribute("vehicle", vehicle);

        return "vehicles/form";
    }


    /**
     * Validates and updates an existing vehicle using
     * Spring Data JPA. Returns to the form if invalid.
     *
     * @param id ID of the vehicle being updated
     * @param vehicle Updated vehicle information
     * @param bindingResult Contains validation errors
     * @return Vehicle form or redirect to vehicle list
     */
    @PostMapping("/vehicles/edit/{id}")
    public String updateVehicle(@PathVariable Integer id,
                                @Valid @ModelAttribute("vehicle") Vehicle vehicle,
                                BindingResult bindingResult) {

        if (!vehicleRepository.existsById(id)) {
            throw new IllegalArgumentException(
                    "Invalid vehicle ID: " + id);
        }

        vehicle.setVehicleId(id);

        if (bindingResult.hasErrors()) {
            return "vehicles/form";
        }
        if (vehicle.getAccidentClaimAmount() == null) {
            vehicle.setAccidentClaimAmount(0.0);
        }

        vehicleRepository.save(vehicle);

        return "redirect:/vehicles";
    }


    /**
     * Deletes an existing vehicle using Spring Data JPA.
     *
     * @param id ID of the vehicle to delete
     * @return Redirect to the vehicle list page
     * Using POST instead of GET because deleting a
     * vehicle changes the database
     */
    @PostMapping("/vehicles/delete/{id}")
    public String deleteVehicle(@PathVariable Integer id) {

        if (!vehicleRepository.existsById(id)) {
            throw new IllegalArgumentException(
                    "Invalid vehicle ID: " + id);
        }

        vehicleRepository.deleteById(id);

        return "redirect:/vehicles";
    }



}

