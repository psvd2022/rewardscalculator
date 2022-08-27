package com.demo.rewardscalculator.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class RewardscalculatorApplicationTests {
	
	@Autowired
	TestRestTemplate restTemplate;
	
	
	static Stream<File> parametersForIntegrationTest() {
		String testcaseFolder = "src/test/resources/testcases";
		return FileUtils.listFiles(new File(testcaseFolder), new String[] {"json"}, true).stream();
	}
	
	@ParameterizedTest
	@MethodSource("parametersForIntegrationTest")
	void runApiTests(File scenarios) throws IOException, JSONException{
		
		var content = FileUtils.readFileToString(scenarios, Charset.defaultCharset());
		var json = new JSONObject(content);
		var operation = json.getString("method");
		var url = json.getString("url");
		var expectedResponse = json.getString("expectedResponse");
		var expectedStatusCode = json.getString("expectedStatusCode");
				
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.valueOf(operation.toUpperCase()),  null, String.class);
		String actualResponse = responseEntity.getBody();
		String actualStatusCode = responseEntity.getStatusCode().toString();
	
		assertEquals(actualStatusCode, expectedStatusCode);
		assertEquals(actualResponse, expectedResponse);
	}
}
