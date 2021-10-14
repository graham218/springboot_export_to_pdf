package com.graham.pdf.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.graham.pdf.export.ExportPdf;
import com.graham.pdf.model.Employee;
import com.graham.pdf.repository.EmployeeRepository;


@Controller
public class HomeController {
	@Autowired
	EmployeeRepository employeeRepository;

	@GetMapping("/welcome")
	public String list() {

		return "welcome";
	}

	@RequestMapping(value = { "/allemployees" }, method = RequestMethod.GET)
	public String viewPersonList(Model model) {

		List<Employee> allEmployees = employeeRepository.findAll();
		model.addAttribute("employees", allEmployees);

		return "employeelist";
	}

	@GetMapping(value = "/exportpdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> employeeReports(HttpServletResponse response) throws IOException {

		List<Employee> allEmployees = employeeRepository.findAll();

		ByteArrayInputStream bis = ExportPdf.employeesReport(allEmployees);

		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Disposition", "attachment;filename=employees.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
}
