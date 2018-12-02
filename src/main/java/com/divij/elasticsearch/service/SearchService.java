package com.divij.elasticsearch.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import com.divij.elasticsearch.vo.SearchDetailVO;

@Service
public class SearchService {

	RestHighLevelClient client = new RestHighLevelClient(
			RestClient.builder(new HttpHost("localhost", 9200, "http"), new HttpHost("localhost", 9201, "http")));

	public SearchDetailVO getData() throws IOException {

		SearchDetailVO searchDetailVO = new SearchDetailVO();

		GetRequest getRequest = new GetRequest("customer", "doc", "1");
		GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);

		return searchDetailVO;
	}

	public IndexResponse indexRequest(String indexName, String documentName, String documentId) throws IOException {
		IndexResponse indexResponse = null;

		IndexRequest request = new IndexRequest(indexName, documentName, documentId);
		String jsonString = "{" + "\"user\":\"kimchy\"," + "\"postDate\":\"2013-01-30\","
				+ "\"message\":\"trying out Elasticsearch\"" + "}";
		request.source(jsonString, XContentType.JSON);

		indexResponse = client.index(request, RequestOptions.DEFAULT);
		return indexResponse;

	}

	public SearchResponse searchData() throws IOException {
		SearchResponse searchResponse = null;
		BoolQueryBuilder queryBuilder = new BoolQueryBuilder();

		/*
		 * QueryBuilder matchQueryBuilder = QueryBuilders .matchQuery("user", "kimchy");
		 */
		Map<String, String> myMap = new HashMap<String, String>();
		myMap.put("name", "divij");
		List<QueryBuilder> queries = new ArrayList<QueryBuilder>();

		for (Map.Entry<String, String> en : myMap.entrySet()) {
			queryBuilder = queryBuilder.should(QueryBuilders.matchQuery(en.getKey(), en.getValue()));
		}

		// queryBuilder.should(QueryBuilders.matchQuery("",
		// "")).should(QueryBuilders.matchQuery("", ""));

		SearchRequest searchRequest = new SearchRequest();
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(queryBuilder);
		searchRequest.source(searchSourceBuilder);

		searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		SearchHits hits = searchResponse.getHits();

		SearchHit[] searchHits = hits.getHits();
		Map<String, Object> innerObject = new HashMap<String, Object>();
		
		for (SearchHit hit : searchHits) {

			String sourceAsString = hit.getSourceAsString();
			Map<String, Object> sourceAsMap = hit.getSourceAsMap();
			String documentTitle = (String) sourceAsMap.get("title");
			List<Object> users = (List<Object>) sourceAsMap.get("user");
			innerObject.putAll(sourceAsMap);//(Map<String, Object>) sourceAsMap.get("innerObject");

		}
		
		for(Map.Entry<String, Object> responseMap: innerObject.entrySet()) {
			System.out.println("Key: "+responseMap.getKey()+" Value :"+ responseMap.getValue());
		}
		return searchResponse;
	}

}
