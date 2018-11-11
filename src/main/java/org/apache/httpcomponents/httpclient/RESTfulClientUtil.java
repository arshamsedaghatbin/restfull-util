package org.apache.httpcomponents.httpclient;

import java.io.IOException;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class RESTfulClientUtil 
{

	    public static String restFullServicePost(String url, String serviceName, String jsonString) {
	        try {
	        	RequestConfig.Builder requestBuilder = RequestConfig.custom();
	        	requestBuilder = requestBuilder.setConnectTimeout(500000);
	        	requestBuilder = requestBuilder.setConnectionRequestTimeout(500000);

	        	HttpClientBuilder builder = HttpClientBuilder.create();     
	        	builder.setDefaultRequestConfig(requestBuilder.build());
	        	HttpClient client = builder.build();
//	            HttpClient client = new DefaultHttpClient();
	            HttpPost postRequest = new HttpPost(url + serviceName);
	            postRequest.setHeader("Content-type", "application/json");
	            postRequest.setEntity(new StringEntity(jsonString, "UTF-8"));
	            HttpResponse response = client.execute(postRequest);
	            if (response.getStatusLine().getStatusCode() != 200 && response.getStatusLine().getStatusCode() != 202) {
	                throw new RuntimeException("Failed : HTTP error code : "
	                    + response.getStatusLine().getStatusCode());
	            }

	            return IOUtils.toString(response.getEntity().getContent(), "UTF-8");

	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }



	    public static String restFullServicePost(String url, String serviceName) {
	        try {
	        	RequestConfig.Builder requestBuilder = RequestConfig.custom();
	        	requestBuilder = requestBuilder.setConnectTimeout(100000);
	        	requestBuilder = requestBuilder.setConnectionRequestTimeout(100000);

	        	HttpClientBuilder builder = HttpClientBuilder.create();     
	        	builder.setDefaultRequestConfig(requestBuilder.build());
	        	HttpClient client = builder.build();
//	            HttpClient client = new DefaultHttpClient();
	            HttpGet postRequest = new HttpGet(url + serviceName);
	            postRequest.setHeader("Content-type", "application/json; charset=UTF-8");
				postRequest.setHeader("Accept-Charset", "UTF-8");
	            HttpResponse response = client.execute(postRequest);
	            if (response.getStatusLine().getStatusCode() != 200 && response.getStatusLine().getStatusCode() != 400) {
	                throw new RuntimeException("Failed : HTTP error code : "
	                    + response.getStatusLine().getStatusCode());
	            }
	            String asd = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
//				byte[] utf8Bytes = asd.getBytes("UTF8");
//				System.out.println(new String(utf8Bytes, "UTF8"));
				return asd;
//	            return IOUtils.toString(response.getEntity().getContent(), "UTF-8");
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	    public static void main(String[] args) throws IOException {
			String s = restFullServicePost("http://booking.romaparvaz.com",
					"/web/ajax/transports/ac?language_code=fa&market=1&include_trains=1&q=thr");
			Example example = new ObjectMapper().readValue(s, Example.class);
			System.out.println();

		}
}