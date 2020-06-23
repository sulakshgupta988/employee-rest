package com.increff.employee;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	public static String writeJson(EmployeePojo p) throws ServletException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(p);
		} catch (JsonProcessingException e) {

			throw new ServletException("Error converting object into JSON");
		}

	}

	public static String writeJson(List<EmployeePojo> p) throws ServletException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(p);
		} catch (JsonProcessingException e) {

			throw new ServletException("Error converting object into JSON");
		}

	}

	public static EmployeePojo readJson(HttpServletRequest request) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(request.getInputStream(), EmployeePojo.class);
		} catch (JsonProcessingException e) {

			throw new ServletException("Error converting JSON into object");
		}

	}
}
