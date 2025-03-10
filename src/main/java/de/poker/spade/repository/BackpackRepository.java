package de.poker.spade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.poker.spade.entity.Backpack;

@Repository
public interface BackpackRepository extends JpaRepository<Backpack, Integer> {

}
