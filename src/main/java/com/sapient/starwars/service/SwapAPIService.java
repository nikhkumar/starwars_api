package com.sapient.starwars.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sapient.starwars.beans.StarwarsResponse;

@Service
public class SwapAPIService {

	@Autowired
	RestTemplate restTemplate;

	@Value("${swapapi.url}")
	String swapapiURL;

	public StarwarsResponse getStarwarData(String type, String name) {

		try {
			String url = null;
			if (null != type)
				url = swapapiURL + type.toLowerCase(); 
			else 
				return prepareFailureResponse(type, name, "Invalid Type or Name");
				
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			ResponseEntity<String> swapapiResponse = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			
			return prepareStarwarResponse(type, name, swapapiResponse);
		} 
		catch (Exception ex) {
			
			return prepareFailureResponse(type, name, ex.getMessage());
		}
	}

	private StarwarsResponse prepareFailureResponse(String type, String name, String string) {
		StarwarsResponse response = new StarwarsResponse();
		response.setType(type);
		response.setName(name);
		response.setErrorMessage(string);
		return response;
	}

	private StarwarsResponse prepareStarwarResponse(String type, String name, ResponseEntity<String> swapapiResponse) {
		StarwarsResponse response = new StarwarsResponse();
		JSONObject data = new JSONObject(swapapiResponse.getBody());
		System.out.println(swapapiResponse.getBody());
		JSONArray results = data.getJSONArray("results");

		response.setType(type);
		response.setName(name);
		response.setCount(data.getInt("count"));
		response.setResults(toList(results));
		return response;
	}

	public List<Object> toList(JSONArray array) throws JSONException {
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < array.length(); i++) {
			Object value = array.get(i);
			if (value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if (value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			list.add(value);
		}
		return list;
	}

	public Map<String, Object> toMap(JSONObject object) throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();

		Iterator<String> keysItr = object.keys();
		while (keysItr.hasNext()) {
			String key = keysItr.next();
			Object value = object.get(key);

			if (value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if (value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			map.put(key, value);
		}
		return map;
	}
}
