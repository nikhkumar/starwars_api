package com.sapient.starwars.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.starwars.beans.StarwarsResponse;
import com.sapient.starwars.service.SwapAPIService;

@RestController
public class StarwarsResource {

	@Autowired
	SwapAPIService swapApiService;
	
	@GetMapping(path = "/starwars", produces = "application/json")
	public StarwarsResponse getStarwarsData(@RequestParam(name = "type") String type, @RequestParam String name) {

		System.out.println(type + " " + name);
		return swapApiService.getStarwarData(type, name);
	}

}
