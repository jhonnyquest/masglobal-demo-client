/******************************************************************
 *
 * MAS Global - Hiring process application test.
 *
 *
 * © 2019, Jhonny Munoz All rights reserved.
 *
 ******************************************************************/

package com.masglobal.demo.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * Note that spring framework features are using to support web interactions (requests/responses)
 *      easily, other way it should be none manually: to build HTTP requests and parse HTTP response
 */
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility for abstract complexity in the interactions with restful services.
 *    Note that spring framework features are using to support web interactions (requests/responses)
 *    easily, other way it should be none manually: to build HTTP requests and parse HTTP response
 *
 * @author jmunoz
 * @since 09/03/2019
 * @version 1.0
 */
public class RestTemplateHelper {

	private static final Logger LOGGER =
			LoggerFactory.getLogger(RestTemplateHelper.class.getCanonicalName());


	@Autowired
	private RestTemplate rt;

	private ObjectMapper om;

	/**
	 * empty constructor that configure the component responsible of the objects transformations
	 * processed by this class.
	 *
	 */

	public RestTemplateHelper(RestTemplate rt) {
		om = new ObjectMapper();
		om.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
		this.rt = rt;
	}


	/**
	 * <b>processRequestBase</b>: Operation that send a http request by using Spring RestTemplate
	 * component
	 *
	 * @author jmunoz
	 * @since 09/03/2019
	 * @param url Remote service endpoint URL
	 * @param obj List of service endpoint params
	 * @param response Class type response
	 * @param method Service endpoint HTTP method
	 * @return {@link ResponseEntity}
	 */
	private <T> ResponseEntity<T> processRequestBase(String url, Map<String, String> obj,
																									 Class<T> response, HttpMethod method) {

		LOGGER.info("request received for {}", url);

		HttpHeaders headers = getBasicHeaders();
		HttpEntity<String> request;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
		try {
			request = new HttpEntity<>(om.writeValueAsString(obj), headers);
			String uri = (obj == null) ? url : builder.buildAndExpand(obj).toUri().toString();
			LOGGER.info("rt status {}", rt);

			return rt.exchange(uri, method, request, response);

		} catch (HttpClientErrorException httpError) {
			return processApiError(httpError);
		} catch (Exception e) {
			LOGGER.error(
					"processRequest(String url,Map<String,String> obj,Class<T> response,HttpMethod method)",
					e);
			return null;
		}
	}

	/**
	 * <b>processApiError</b>: Process all http errors that could generate HTTP requests
	 *
	 * @author jmunoz
	 * @since 09/03/2019
	 * @param httpError HTTP error definition
	 * @return {@link ResponseEntity}
	 */
	@SuppressWarnings("unchecked")
	private <T> ResponseEntity<T> processApiError(HttpClientErrorException httpError) {
		try {
			return new ResponseEntity<>(
					(T) om.readValue(httpError.getResponseBodyAsString(), Error.class),
					httpError.getStatusCode());
		} catch (Exception e) {
			return new ResponseEntity<>((T)httpError.getResponseBodyAsString(),httpError.getStatusCode());
		}
	}


	/**
	 * <b>getBasicHeaders</b>: Retrieve configured HTTP headers that will be sent to remote server
	 *
	 * @author jmunoz
	 * @since 09/03/2019
	 * @return {@link HttpHeaders}
	 */
	private HttpHeaders getBasicHeaders() {

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

		return headers;
	}

	/**
	 * <b>processRequestGet</b>: Route http request to proper operation with proper params
	 *
	 * @author jmunoz
	 * @since 09/03/2019
	 * @param url Remote service endpoint URL
	 * @param response Class type response
	 * @return {@link ResponseEntity}
	 */
	public <T> ResponseEntity<T> processRequestGet(String url, Class<T> response) {
		return processRequestBase(url, new HashMap<>(), response, HttpMethod.GET);
	}
}
