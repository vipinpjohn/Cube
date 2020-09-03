package com.cubes.util;

import org.springframework.http.HttpHeaders;

public class Header {

	public static HttpHeaders getHeader() {
		HttpHeaders headers = new HttpHeaders();

		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		headers.add("Access-Control-Allow-Headers",
				"Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With,observe");
		headers.add("Access-Control-Max-Age", "3600");
		headers.add("Access-Control-Allow-Credentials", "true");
		headers.add("Access-Control-Expose-Headers", "Authorization");
		return headers;
	}

}
