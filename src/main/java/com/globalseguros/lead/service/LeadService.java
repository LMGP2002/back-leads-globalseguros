package com.globalseguros.lead.service;

import com.globalseguros.lead.entity.Lead;
import com.globalseguros.lead.repository.LeadRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

@Service
public class LeadService {
    @Autowired
    private LeadRepository leadRepository;

    public List<Lead> getLeads() {
        return leadRepository.findAll();
    }

    public void saveOrUpdate(Lead lead) {
        leadRepository.save(lead);
    }


    public List<Lead> getLeadsByDateRange(LocalDate startDate, LocalDate endDate) {
        return leadRepository.findByFechaCreacionBetween(startDate, endDate);
    }

    public void exportLeadsToCSV(LocalDate startDate, LocalDate endDate, PrintWriter writer) throws IOException {
        List<Lead> leads = leadRepository.findByFechaCreacionBetween(startDate, endDate);

        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID", "Nombre Cliente" ,"NIT", "Nombre Punto", "Nombre Equipo","Ciudad", "RTC", "Promotor", "Usuario", "Fecha Creación"));

        for (Lead lead : leads) {
            csvPrinter.printRecord(
                    lead.getId(),
                    lead.getNomCliente(),
                    lead.getNit(),
                    lead.getNomPunto(),
                    lead.getNomEquipo(),
                    lead.getCiudad(),
                    lead.getRtc(),
                    lead.getPromotor(),
                    lead.getUsuario(),
                    lead.getFechaCreacion()
            );
        }
        csvPrinter.flush();
    }


}
