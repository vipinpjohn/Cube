package com.cubes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

public class LoggableDispatcherServlet extends DispatcherServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Log logger = LogFactory.getLog(getClass());

	@Override
	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!(request instanceof ContentCachingRequestWrapper)) {
			request = new ContentCachingRequestWrapper(request);
		}
		if (!(response instanceof ContentCachingResponseWrapper)) {
			response = new ContentCachingResponseWrapper(response);
		}
		HandlerExecutionChain handler = getHandler(request);

		try {
			super.doDispatch(request, response);
		} finally {
			log(request, response, handler);
			// updateResponse(response);
		}
	}

	private void log(HttpServletRequest requestToCache, HttpServletResponse responseToCache,
			HandlerExecutionChain handler) {

		System.out.println("vipin");
	}

}