
/*
 * Project: Auto Track - Vehicle Cost & Maintenance Manager
 * Course: CIS-2232
 * Sprint: 3 - MVC, CRUD, JPA, and Validation
 * File: VehicleRepository.java
 * Author: Brian Lawt
 * Date: November 18, 2026
 *
 * Purpose:
 * Provides database access for vehicle records using
 * Spring Data JPA. Supports listing, adding, updating,
 * and deleting vehicles without manually writing SQL.
 */

package ca.hccis.squash.repositories;

import ca.hccis.squash.jpa.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

}

