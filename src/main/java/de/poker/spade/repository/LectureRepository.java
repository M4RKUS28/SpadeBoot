package de.poker.spade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.poker.spade.entity.Lecture;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Integer> {

}
