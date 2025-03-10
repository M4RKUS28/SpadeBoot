package de.poker.spade.service;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import de.poker.spade.entity.Student;
import de.poker.spade.exception.EntityNotFoundException;
import de.poker.spade.exception.UniqueConstraintViolationException;
import de.poker.spade.repository.StudentRepository;

@Service
public class StudentService implements UserDetailsService {


	@Autowired
	private StudentRepository studentRepository;
	

	@Autowired
	private Argon2PasswordEncoder argon2PasswordEncoder;

	
	public Student saveStudent(Student newStudent) {
		
		newStudent.setPassword(argon2PasswordEncoder.encode(newStudent.getPassword()));
				
		try {
			return studentRepository.save(newStudent);
		} catch (DataIntegrityViolationException e) {
			throw new UniqueConstraintViolationException("Username '" + newStudent.getUsername() + "' is already taken!");
		}
	}

	public Iterable<Student> findAllStudents() {
		return studentRepository.findAll();
	}

	public Student findStudentById(int studentId) {
		return studentRepository.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Student with ID '" + studentId + "' does not exist!"));
	}

	public void deleteStudentById(int studentId) {
		try {
			studentRepository.deleteById(studentId);
		} catch (Exception e) {
			System.err.println("Unable to delete Student with ID: " + studentId);
		}
	}
	

	// JPQL Example
	public Iterable<Student> findAllStudentsWithJPQL() {
		return studentRepository.findAllStudentsWithJPQL();
	}

	// Criteria API Example
	public Iterable<Student> findAllStudentsWithCriteriaAPI() {
		return studentRepository.findAllStudentsWithCriteriaAPI();
	}

	// Querydsl Example
	public Iterable<Student> findAllStudentsWithQuerydsl() {
		return studentRepository.findAllStudentsWithQuerydsl();
	}

	// Query 1 JPQL
	public Student findStudentByUsernameWithJPQL(String username) {
		return studentRepository.findStudentByUsernameWithJPQL(username);
	}

	// Query 1 CriteriaAPI
	public Student findStudentByUsernameWithCriteriaAPI(String username) {
		return studentRepository.findStudentByUsernameWithCriteriaAPI(username);
	}

	// Query 1 Querydsl
	public Student findStudentByUsernameWithQuerydsl(String username) {
		return studentRepository.findStudentByUsernameWithQuerydsl(username);
	}

	// Query 2 JPQL
	public Iterable<Student> findStudentsbyAgeRangeWithJPQL(int age1, int age2) {
		return studentRepository.findStudentsByAgeRangeWithJPQL(age1, age2);
	}

	// Query 2 CriteriaAPI
	public Iterable<Student> findStudentsByAgeRangeWithCriteriaAPI(int age1, int age2) {
		return studentRepository.findStudentsByAgeRangeWithCriteriaAPI(age1, age2);
	}

	// Query 2 Querydsl
	public Iterable<Student> findStudentsByAgeRangeWithQuerydsl(int age1, int age2) {
		return studentRepository.findStudentsByAgeRangeWithQuerydsl(age1, age2);
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return studentRepository.findByUsername(username).orElseThrow(() ->
		new UsernameNotFoundException(MessageFormat.format("User with username {0} cannot be found.", username)));
	}

}
