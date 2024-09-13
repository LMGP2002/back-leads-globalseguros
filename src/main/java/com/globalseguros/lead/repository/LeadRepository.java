package com.globalseguros.lead.repository;

import com.globalseguros.lead.entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {
    List<Lead> findByFechaCreacionBetween(LocalDate startDate, LocalDate endDate);
}
