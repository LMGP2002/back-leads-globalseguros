package com.globalseguros.lead.controller;

import com.globalseguros.lead.dto.DateRangeRequest;
import com.globalseguros.lead.entity.Lead;
import com.globalseguros.lead.service.LeadService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:4200")  // Agrega esto
@RestController
@RequestMapping(path="api/v1/leads")
public class LeadController {
    @Autowired
    private LeadService leadService;

    @GetMapping
    public List<Lead> getAll() {
        return leadService.getLeads();
    }

    @PostMapping("/filter")
    public List<Lead> getLeadsByDateRange(@RequestBody DateRangeRequest dateRangeRequest) {
        return leadService.getLeadsByDateRange(dateRangeRequest.getStartDate(), dateRangeRequest.getEndDate());
    }

    @PostMapping("/export")
    public void exportLeadsToCSV(@RequestBody DateRangeRequest dateRangeRequest, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=leads.csv");

        PrintWriter writer = response.getWriter();
        leadService.exportLeadsToCSV(dateRangeRequest.getStartDate(), dateRangeRequest.getEndDate(), writer);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> saveOrUpdate(@Valid @RequestBody Lead lead) {
        leadService.saveOrUpdate(lead);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Lead almacenado correctamente.");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
