package com.springboot.filtter.logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.io.output.TeeOutputStream;
import org.springframework.stereotype.Component;

@Component
public class CustomResponseWrapper extends HttpServletResponseWrapper {

	private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	private PrintStream printStream = new PrintStream(byteArrayOutputStream);
	public CustomResponseWrapper(HttpServletResponse response) {
		super(response);

	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return new  MyDelegatingServletInputStream(new TeeOutputStream(super.getOutputStream(), printStream));
		

	}
	
	@Override
	public PrintWriter getWriter() throws IOException {
		return new PrintWriter(new TeeOutputStream(getOutputStream(), printStream));
		
	}

	public ByteArrayOutputStream getByteArrayOutputStream() {
		return byteArrayOutputStream;
	}

	public void setByteArrayOutputStream(ByteArrayOutputStream byteArrayOutputStream) {
		this.byteArrayOutputStream = byteArrayOutputStream;
	}
	
	

	
	
	

}
