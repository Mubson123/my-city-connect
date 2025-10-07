package com.mc.citizen.repository;

import com.mc.citizen.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, UUID> {
}
