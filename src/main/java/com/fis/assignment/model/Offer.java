package com.fis.assignment.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
* Offer representation.
*
* @author Andrei Almasanu
*/

@Entity(name="offer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Offer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long offerId;

	private String offerName;
	private String offerDescription;
	private String status;
	private LocalDateTime createdDt;
	private long activeDays;
	private LocalDateTime expiryDt;

	public Offer() {
	}

	public Offer(long offerId, String offerName, String offerDescription, String status, LocalDateTime createdDt,
			long activeDays, LocalDateTime expiryDt) {
		super();
		this.offerId = offerId;
		this.offerName = offerName;
		this.offerDescription = offerDescription;
		this.status = status;
		this.createdDt = createdDt;
		this.activeDays = activeDays;
		this.expiryDt = expiryDt;
	}

	public long getOfferId() {
		return offerId;
	}
	public void setOfferId(long offerId) {
		this.offerId = offerId;
	}
	public String getOfferName() {
		return offerName;
	}
	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}
	public String getOfferDescription() {
		return offerDescription;
	}
	public void setDescription(String offerDescription) {
		this.offerDescription = offerDescription;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getCreatedDt() {
		return createdDt;
	}
	public void setCreatedDt(LocalDateTime createdDt) {
		this.createdDt = createdDt;
	}
	public long getActiveDays() {
		return activeDays;
	}
	public void setActiveDays(long activeDays) {
		this.activeDays = activeDays;
	}
	public LocalDateTime getExpireDt() {
		return expiryDt;
	}
	public void setExpireDt(LocalDateTime expiryDt) {
		this.expiryDt = expiryDt;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Offer))
			return false;
		Offer offer = (Offer) o;
		// field comparison
		return offerId == offer.offerId;
	}

	 @Override
	  public int hashCode() {
	    return Objects.hash(offerId);
	  }

	@Override
	public String toString() {
		return "Offer = {" + offerId + ", " + offerName + ", " + offerDescription + ", " + status + ", " 
				+ createdDt.toString() + ", " + activeDays + ", " + expiryDt.toString() +'}';
	}
}
