package com.example.spring_project_08.service;

import com.example.spring_project_08.entity.Book;
import com.example.spring_project_08.repository.BookRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    private BookRepository bookRepository;

    public void generateExcel(HttpServletResponse response) throws IOException {

        List<Book> bookList=bookRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Books Info");
        HSSFRow row= sheet.createRow(0);

        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("Author");
        row.createCell(2).setCellValue("Category");
        row.createCell(3).setCellValue("Title");

        int dataRowIndex = 1;

        for(Book book:bookList){
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(book.getId());
            dataRow.createCell(1).setCellValue(book.getAuthor());
            dataRow.createCell(2).setCellValue(book.getCategory());
            dataRow.createCell(3).setCellValue(book.getTitle());
            dataRowIndex++;
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
    }
}
