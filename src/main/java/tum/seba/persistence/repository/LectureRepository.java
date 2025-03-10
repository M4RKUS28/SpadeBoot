package tum.seba.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tum.seba.persistence.entity.Lecture;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Integer> {

}
