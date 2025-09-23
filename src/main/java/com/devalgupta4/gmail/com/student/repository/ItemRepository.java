package com.devalgupta4.gmail.com.student.repository;

import com.devalgupta4.gmail.com.student.entity.Item;
import com.devalgupta4.gmail.com.student.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByOwner(User owner);
    List<Item> findByOwner_CollegeDomain(String collegeDomain);
    List<Item> findByCategory(String category);
    List<Item> findByOwner_CollegeDomainAndCategory(String collegeDomain, String category);
}
