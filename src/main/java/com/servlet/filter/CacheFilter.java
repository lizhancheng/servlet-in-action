package com.servlet.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.WriteListener;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

@WebFilter("/slowpage")
public class CacheFilter implements Filter {
	private Map<String, byte[]> cache = new ConcurrentHashMap<String, byte[]>();
	
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		// 获取路径
		String url = request.getRequestURI();
		// 获取缓存内容
		byte[] data = this.cache.get(url);
		response.setHeader("X-Cache-Hit", data == null ? "No" : "Yes");
		
		if (data == null) {
			// 缓存未找到，构造一个伪造的Response
			CachedHttpServletResponse wrapper = new CachedHttpServletResponse(response);
			// 让下游组件写入数据到伪造的Response
			chain.doFilter(request, wrapper);
			// 从伪造的Response中读取写入的内容并放入缓存
			data = wrapper.getContent();
			cache.put(url, data);
		}
		// 写入到原始Response
		ServletOutputStream output = response.getOutputStream();
		output.write(data);
		output.flush();
	}
}

class CachedHttpServletResponse extends HttpServletResponseWrapper {
	private boolean open = false;
	private ByteArrayOutputStream output = new ByteArrayOutputStream();
	
	public CachedHttpServletResponse(HttpServletResponse response) {
		super(response);
	}
	
	public PrintWriter getWriter() throws IOException {
		if (open) {
			throw new IllegalStateException("Can not re-open writer");
		}
		open = true;
		return new PrintWriter(output, false, StandardCharsets.UTF_8);
	}
	
	public ServletOutputStream getOutputStream() throws IOException {
		if (open) {
			throw new IllegalStateException("Can not re-open output stream!");
		}
		open = true;
		
		return new ServletOutputStream() {
			
			@Override
			public void write(int b) throws IOException {
				output.write(b);
			}
			
			@Override
			public void setWriteListener(WriteListener writeListener) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isReady() {
				return true;
			}
		};
	}
	
	public byte[] getContent() {
		return output.toByteArray();
	}
}
