package cn.ecourses.search.service;

import cn.ecourses.common.pojo.SearchResult;

public interface SearchService {
	SearchResult search(String keyword, int page, int rows)  throws Exception;
}
