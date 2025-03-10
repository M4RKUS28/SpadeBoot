package de.poker.spade.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import de.poker.spade.entity.Student;
import de.poker.spade.service.RequestValidationService;
import de.poker.spade.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private RequestValidationService requestValidationService;

	// CRUD

	@GetMapping("/students")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<Iterable<Student>>(studentService.findAllStudents(), HttpStatus.OK);
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<?> findOne(@PathVariable int id) {
		return new ResponseEntity<Student>(studentService.findStudentById(id), HttpStatus.OK);
	}

	@PostMapping("/students")
	public ResponseEntity<?> newStudent(@Valid @RequestBody Student newStudent, BindingResult result) {
		Map<String, String> errorMap = requestValidationService.handleValidationErrors(result);
		if (errorMap != null) return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Student>(studentService.saveStudent(newStudent), HttpStatus.CREATED);
	}

	@PutMapping("/students/{id}")
	public ResponseEntity<?> replaceStudent(@PathVariable int id, @Valid @RequestBody Student newStudent, BindingResult result) {
		Map<String, String> errorMap = requestValidationService.handleValidationErrors(result);
		if (errorMap != null) return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		newStudent.setId(id);
		studentService.saveStudent(newStudent);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/students/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable int id) {
		studentService.deleteStudentById(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	// Queries

	@GetMapping("/students/jpql")
	public ResponseEntity<?> findStudentByUsername(@RequestParam(value="username") String username) {
		return new ResponseEntity<Student>(studentService.findStudentByUsernameWithJPQL(username), HttpStatus.OK);
	}

	@GetMapping("/students/criteriaapi")
	public ResponseEntity<?> findStudentByAgeRange(@RequestParam(value="age1") int age1, @RequestParam(value="age2") int age2) {
		return new ResponseEntity<Iterable<Student>>(studentService.findStudentsByAgeRangeWithCriteriaAPI(age1, age2), HttpStatus.OK);
	}

}
