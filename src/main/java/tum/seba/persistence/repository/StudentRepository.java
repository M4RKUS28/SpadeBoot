package tum.seba.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tum.seba.persistence.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>, StudentRepositoryCustom {

	@Query("SELECT s FROM Student s")
	public List<Student> findAllStudentsWithJPQL();
	
	@Query("SELECT s FROM Student s WHERE s.username = ?1")
	public Student findStudentByUsernameWithJPQL(String username);
	
	@Query("SELECT s FROM Student s WHERE s.age between ?1 and ?2")
	public List<Student> findStudentsByAgeRangeWithJPQL(int age1, int age2);
	
	Optional<Student> findByUsername(String username);

}
