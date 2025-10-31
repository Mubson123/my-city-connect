package com.mc.extend.repository;

import com.mc.extend.model.Visa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VisaRepository extends JpaRepository<Visa, UUID> {
}
