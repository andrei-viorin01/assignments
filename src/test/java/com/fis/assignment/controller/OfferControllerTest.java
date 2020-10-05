package com.fis.assignment.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fis.assignment.model.Offer;
import com.fis.assignment.repository.OfferRepository;

@RunWith(SpringRunner.class) 
@WebMvcTest(OfferController.class)
public class OfferControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OfferRepository offerRepository;

	Offer expectedOffer = new Offer(1L, "test name", "test description", "Active", LocalDateTime.now(), 5,
			LocalDateTime.now().plusDays(5));

	@Test
	public void list() throws Exception {
		// setup data
		when(offerRepository.findAll()).thenReturn(Arrays.asList(expectedOffer));

		// test
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/offers").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		// verify
		// check if response code is 200 - OK
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		assertTrue(result.getResponse().getContentAsString().length() > 0);
	}

	@Test
	public void getById() throws Exception {
		// setup data
		when(offerRepository.getOne(anyLong())).thenReturn(expectedOffer);

		// test
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/offers/1")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		// verify
		// check if response code is 200 - OK
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		// check if the returned string contains the offer name
		assertTrue(result.getResponse().getContentAsString().contains(expectedOffer.getOfferName()));
	}

	@Test
	public void create() throws Exception {
		// setup data
		String expectedOfferAsJson = "{\"offerId\":1,\"offerName\":\"test2\",\"offerDescription\""
				+ ":\"test description1\",\"status\":\"ACTIVE\",\"createdDt\":\"\",\"activeDays\""
				+ ":6,\"expiryDt\":\"\"}";
		when(offerRepository.saveAndFlush(any(Offer.class))).thenReturn(expectedOffer);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/offers").accept(MediaType.APPLICATION_JSON)
				.content(expectedOfferAsJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		// verify
		// check the response code
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		// check if the returned string contains the offer name
		assertTrue(result.getResponse().getContentAsString().contains(expectedOffer.getOfferName()));
	}
}
