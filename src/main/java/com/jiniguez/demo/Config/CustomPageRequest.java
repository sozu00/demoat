package com.jiniguez.demo.Config;

import org.springframework.data.domain.PageRequest;

public class CustomPageRequest{

	public static PageRequest newPageRequest(Integer page, Integer size) {
		int pageNumber = (page == null) ? 0 : page;
		int pageSize = (size == null) ? 10 : size;
		return new PageRequest(pageNumber, pageSize);
	}
}
