package com.sapient.starwars.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.sapient.starwars.beans.StarwarsResponse;

public class SwapAPIServiceTest {

	SwapAPIService swapapiService = new SwapAPIService();

	@Test
	public void getStarwarDataTest() {
		StarwarsResponse response = swapapiService.getStarwarData("Vehicles", "Sand Crawler");
		assert(response != null);
	}

	@Test
	public void getStarwarDataInvalidTest() {
		try {
			StarwarsResponse response = swapapiService.getStarwarData("xxx", "Sand Crawler");
			assert (response.getCount() == 0);
		} catch (Exception e) {
			assert (true);
		}
	}
	
	@Test
	public void getStarwarData1Test() {
		StarwarsResponse response = swapapiService.getStarwarData("Vehicles", "Sand Crawler");
		assertEquals("URI is not absolute", response.getErrorMessage());
	}
}
