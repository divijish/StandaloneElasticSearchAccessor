package com.divij.elasticsearch.controller;

import java.io.IOException;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.divij.elasticsearch.dto.SearchDetail;
import com.divij.elasticsearch.service.SearchService;

@RestController
public class SearchController {

	@Autowired
	SearchService searchService;

	@PostMapping("/setDocument/{indexName}/{documentName}/{documentId}")
	public IndexResponse indexData(@RequestBody final SearchDetail searchDetail, @RequestParam String indexName,
			@RequestParam String documentName, @RequestParam String documentId) throws IOException {
		IndexResponse indexResponse = searchService.indexRequest(indexName, documentName, documentId);

		return indexResponse;
	}
	
	@PostMapping("/get")
	public GetResponse getData(String indexName, String docType, String docId) {
	GetResponse getResponse = null;
	
	
	return getResponse;
	}
	
	@PostMapping("/search")
	public SearchResponse searchData() throws IOException {
		SearchResponse searchResponse = null;
		SearchRequest searchRequest = null;
		searchResponse = searchService.searchData();
		return searchResponse;
	}

}
