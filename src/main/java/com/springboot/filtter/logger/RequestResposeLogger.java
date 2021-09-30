package com.springboot.filtter.logger;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)

public class RequestResposeLogger implements Filter {

	private Logger logger = LoggerFactory.getLogger(RequestResposeLogger.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		CustomHttpRequestWapper servletRequest = new CustomHttpRequestWapper((HttpServletRequest) request);
		// HttpServletRequest servletRequest = (HttpServletRequest) request;
		logger.info("Request Method: {}", servletRequest.getMethod());
		logger.info("Request URL: {}", servletRequest.getRequestURI());

		// byte[] byteArray =
		// org.apache.commons.io.IOUtils.toByteArray(servletRequest.getByteArray());
		logger.info("Request Body: {}", new String(servletRequest.getByteArray()));

		CustomResponseWrapper responseWrapper = new CustomResponseWrapper((HttpServletResponse) response);
		chain.doFilter(servletRequest, responseWrapper);
		//HttpServletResponse servletResponse = (HttpServletResponse) response;

		
		logger.info("Response Status -{}",responseWrapper.getStatus());

		logger.info("Response Body -{}",new String(responseWrapper.getByteArrayOutputStream().toByteArray()));
		}

	private class CustomHttpRequestWapper extends HttpServletRequestWrapper {
		private byte[] byteArray;

		public CustomHttpRequestWapper(HttpServletRequest request) {

			super(request);
			try {

				byteArray = org.apache.commons.io.IOUtils.toByteArray(request.getInputStream());
			} catch (Exception e) {
				// TODO: handle exception
				// e.printStackTrace();
				throw new RuntimeException("Issue while reading request stream" + e);
			}

		}

		@Override
		public ServletInputStream getInputStream() throws IOException {

			CustomeDelegatingServelte customeDelegatingServelte = new CustomeDelegatingServelte(
					new ByteArrayInputStream(byteArray));
			return customeDelegatingServelte;

		}

		public byte[] getByteArray() {
			return byteArray;
		}

		

	}

}
