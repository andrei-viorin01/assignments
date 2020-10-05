package com.fis.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fis.assignment.model.Offer;

/**
* OfferRepository implements JpaRepository to take advantage of its methods.
*
* @author Andrei Almasanu
*/

public interface OfferRepository extends JpaRepository<Offer, Long> {
}