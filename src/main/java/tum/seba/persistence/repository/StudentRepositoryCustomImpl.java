package tum.seba.persistence.repository;

import java.util.List;

import com.querydsl.jpa.impl.JPAQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import tum.seba.persistence.entity.QStudent;
import tum.seba.persistence.entity.Student;

public class StudentRepositoryCustomImpl implements StudentRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Student> findAllStudentsWithCriteriaAPI() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Student> cquery = cb.createQuery(Student.class);
		Root<Student> root = cquery.from(Student.class);
		cquery.select(root);
		
		TypedQuery<Student> query = em.createQuery(cquery);
		List<Student> results = query.getResultList();
		return results;
	}

	@Override
	public Student findStudentByUsernameWithCriteriaAPI(String username) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Student> cquery = cb.createQuery(Student.class);
		Root<Student> root = cquery.from(Student.class);
		cquery.select(root);
		Predicate eqUsername = cb.equal(root.get("username"), username);
		cquery.where(eqUsername);
		
		Query query = em.createQuery(cquery);
		Student result = (Student) query.getSingleResult();
		return result;
	}

	@Override
	public List<Student> findStudentsByAgeRangeWithCriteriaAPI(int age1, int age2) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Student> cquery = cb.createQuery(Student.class);
		Root<Student> root = cquery.from(Student.class);
		cquery.select(root);
		Predicate ageRange = cb.between(root.get("age"), age1, age2);
		cquery.where(ageRange);
		
		TypedQuery<Student> query = em.createQuery(cquery);
		List<Student> results = query.getResultList();
		return results;
	}

	@Override
	public List<Student> findAllStudentsWithQuerydsl() {
		JPAQuery<Student> query = new JPAQuery<Student>(em);
		QStudent student = QStudent.student;
		return query.from(student).fetch();
	}

	@Override
	public Student findStudentByUsernameWithQuerydsl(String username) {
		JPAQuery<Student> query = new JPAQuery<Student>(em);
		QStudent student = QStudent.student;
		return query.from(student).where(student.username.eq(username)).fetchFirst();
	}

	@Override
	public List<Student> findStudentsByAgeRangeWithQuerydsl(int age1, int age2) {
		JPAQuery<Student> query = new JPAQuery<Student>(em);
		QStudent student = QStudent.student;
		return query.from(student).where(student.age.between(age1, age2)).fetch();
	}

}
