package com.fis.assignment.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fis.assignment.model.Offer;
import com.fis.assignment.model.Status;
import com.fis.assignment.repository.OfferRepository;

/**
* Offer REST services.
*
* @author Andrei Almasanu
*/
@RestController
@RequestMapping("/api/v1/offers")
public class OfferController {

    @Autowired
    private OfferRepository offerRepository;

    private static final Logger log =
			LoggerFactory.getLogger(OfferController.class);

    /**
     * GET request to return all ACTIVE offers (Status must be ACTIVE).
     * @param  
     * @return List of Offer objects with Status of ACTIVE
     */
	@GetMapping()
	public List<Offer> list() {
		log.info("list()");
		List<Offer> result = offerRepository.findAll();
		if (null != result) {
			result.forEach(o -> checkAndSetExpired(o));
			result.stream().filter(o -> o.getStatus().equals(Status.ACTIVE.name())).collect(Collectors.toList());
		}
		return result;
	}

    /**
     * GET request that returns an Offer object by its id.
     * @param  offer id
     * @return Offer object with the given id
     */
	@GetMapping("{id}")
	public Offer getById(@PathVariable long id) throws Exception {
		log.info("getById({})", id);
		Offer offer = offerRepository.getOne(id);
		checkAndSetExpired(offer);
		return offer;
	}

    /**
     * POST request to create an Offer.
     * @param Offer object 
     * @return The created Offer object
     */
	@PostMapping()
	public Offer create(@RequestBody Offer offer) {
		log.info("create()");
		// set createdDt
		offer.setCreatedDt(LocalDateTime.now());
		// set expireDt
		offer.setExpireDt(LocalDateTime.now().plusDays(offer.getActiveDays()));
		return offerRepository.saveAndFlush(offer);
	}

    /**
     * PUT request to create an Offer.
     * @param Offer object 
     * @return The created Offer object
     */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public Offer update(@RequestBody Offer offer, @PathVariable int id) {
		log.info("update({})", offer.toString());
		return offerRepository.saveAndFlush(offer);
	}

    /**
     * PUT request to update the status of the Offer with given id to CANCELLED.
     * @param offerId 
     * @return
     */
	@RequestMapping(value = "{id}/cancel", method = RequestMethod.PUT)
	public void cancel(@PathVariable long id) throws Exception {
		log.info("cancel({})", id);
		Offer offer = offerRepository.getOne(id);
		if (offer.getStatus().equals(Status.ACTIVE.name()) ) {
			offer.setStatus(Status.CANCELLED.name());
			offerRepository.saveAndFlush(offer);
		} else {
			throw new IllegalArgumentException(
					"Offer [" + offer.toString() + "] is not ACTIVE and cannot be cancelled");
		}
	}

	private void checkAndSetExpired(Offer offer) {
		if(null != offer && offer.getExpireDt().isBefore(LocalDateTime.now())) {
			offer.setStatus(Status.EXPIRED.name());
		}
	}
}
