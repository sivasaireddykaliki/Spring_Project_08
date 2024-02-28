package com.example.spring_project_08.controller;

import com.example.spring_project_08.service.ReportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportRestController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/excel")
    public void generateExcelReport(HttpServletResponse response) throws Exception{

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=books.xls";

        response.setHeader(headerKey,headerValue);

        reportService.generateExcel(response);


    }
}
