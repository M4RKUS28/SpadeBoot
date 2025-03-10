package tum.seba.persistence.repository;

import java.util.List;

import tum.seba.persistence.entity.Student;

public interface StudentRepositoryCustom {

	public List<Student> findAllStudentsWithCriteriaAPI();

	public Student findStudentByUsernameWithCriteriaAPI(String username);

	public List<Student> findStudentsByAgeRangeWithCriteriaAPI(int age1, int age2);

	public List<Student> findAllStudentsWithQuerydsl();

	public Student findStudentByUsernameWithQuerydsl(String username);

	public List<Student> findStudentsByAgeRangeWithQuerydsl(int age1, int age2);

}
