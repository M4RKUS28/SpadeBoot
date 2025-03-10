package tum.seba.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tum.seba.persistence.entity.Backpack;

@Repository
public interface BackpackRepository extends JpaRepository<Backpack, Integer> {

}
