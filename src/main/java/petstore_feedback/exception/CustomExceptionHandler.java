/*******************************************************************************
 * Licensed materials - Property of IBM
 * 6949-63D
 * Developed by Digital Integration Practice
 * (C) Copyright IBM Corp. 2016 All Rights Reserved.
 * 
 * US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp. 
 * This asset is provided ``as is'' and any expressed or implied warranties, including, but not limited to, the implied warranties and fitness 
 * for a particular purpose are disclaimed. in no event shall IBM be liable for any direct, indirect, incidental, special, 
 * exemplary, or consequential damages.
 * 
 *******************************************************************************/
package petstore_feedback.exception;

import java.io.IOException;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import petstore_feedback.services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.dip.microservice.framework.exception.CustomException;
import com.ibm.dip.microservice.framework.exception.Error.SeverityEnum;
import com.ibm.dip.microservice.framework.exception.ErrorResponse;
/**
 * 
 * CustomExceptionHandler is a controller advice which captures any of the exceptions in the process of api execution.
 *
 */
@ControllerAdvice
public class CustomExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

	private static final String FAILED = "FAILED";

	private static final String INTERNAL_SERVER_ERROR_CODE ="MV0012";
	private static final String BAD_REQUEST_CODE = "MV0020";

	//#AutowireServices
	@Autowired
	 private CreateFeedbackI createFeedbackI;
	@Autowired
	 private GetFeedbackI getFeedbackI;



	private HttpHeaders getResponseHeaders(WebRequest request) {
		// get header values
		// response headers created first
		final MultiValueMap<String, String> map = buildResponseMap();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.putAll(map);
		//TODO return headers for responses
		return httpHeaders;
	}

	private MultiValueMap<String, String> buildResponseMap() {
		final MultiValueMap<String, String> map;
		map = new LinkedMultiValueMap<>();		
		return map;
	}

	@ExceptionHandler(value = { MissingServletRequestParameterException.class,
			MethodArgumentTypeMismatchException.class, 
			MissingPathVariableException.class,
			HttpRequestMethodNotSupportedException.class,
			UnsatisfiedServletRequestParameterException.class,
			ServletRequestBindingException.class,
			ConstraintViolationException.class,
			IllegalArgumentException.class })
	protected ResponseEntity<Object> handleRequestException(Exception exception, WebRequest request){
		// build header responses
		final HttpHeaders httpHeaders = getResponseHeaders(request);
		//Building API specific Exception Response Body
		Object object = getApiSpecificErrorResponse(request,httpHeaders,ErrorStatusCode.BAD_REQUEST,exception);
		if(null != object){			
			return ResponseEntity.status(ErrorStatusCode.BAD_REQUEST.getCode()).headers(httpHeaders).contentType(MediaType.APPLICATION_JSON).body(object);
		}

		// building default error response
		ErrorResponse errorResponse = ErrorResponse.instance(SeverityEnum.ERROR, exception, exception.getMessage(),ErrorStatusCode.BAD_REQUEST.getCode()+"", FAILED, true, BAD_REQUEST_CODE);		
		// global log
		log.error("global error occured in controller, cause: " + exception.getMessage(), exception);
		// send error response
		return ResponseEntity.status(ErrorStatusCode.BAD_REQUEST.getCode()).headers(httpHeaders).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
	}

	/**
	 * CustomException handler
	 * @param customException
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = { CustomException.class })
	protected ResponseEntity<Object> handleRequestException(CustomException customException, WebRequest request) {
		// build header responses
		final HttpHeaders httpHeaders = getResponseHeaders(request);

		// send error response
		return ResponseEntity.status(customException.getHttpCode()).headers(httpHeaders).contentType(MediaType.APPLICATION_JSON)
				.body(customException.getErrorResponse());
	}

	/**
	 * Internal server Error -500
	 * @param exception
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<Object> handleGeneralException(Exception exception, WebRequest request) {
		// build error response
		final ErrorResponse errorResponse;
		// build headers
		final HttpHeaders httpHeaders = getResponseHeaders(request);
		//Building API specific Exception Response Body
		Object object = getApiSpecificErrorResponse(request,httpHeaders,ErrorStatusCode.INTERNAL_SERVER_ERROR,exception);
		if(null != object){			
			return ResponseEntity.status(ErrorStatusCode.INTERNAL_SERVER_ERROR.getCode()).headers(httpHeaders).contentType(MediaType.APPLICATION_JSON).body(object);
		}
		// global log
		log.error("global error occured in controller, cause: " + exception.getMessage(), exception);		
		// building default error response
		errorResponse = ErrorResponse.instance(SeverityEnum.ERROR, exception, exception.getMessage(),""+ErrorStatusCode.INTERNAL_SERVER_ERROR.getCode(), FAILED, true,INTERNAL_SERVER_ERROR_CODE);

		// send error response
		return ResponseEntity.status(ErrorStatusCode.INTERNAL_SERVER_ERROR.getCode()).headers(httpHeaders).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
	}

	/**
	 * Http Server Error
	 * @param HttpStatusCodeException httpStatusCodeException
	 * @param WebRequest request
	 * @return ResponseEntity<ErrorResponse>
	 */
	@ExceptionHandler(value = { HttpClientErrorException.class, HttpServerErrorException.class })
	protected ResponseEntity<Object> handleHTTPException(HttpStatusCodeException httpStatusCodeException, WebRequest request) {
		// build error response
		log.info("Entered CustomExceptionHandler::handleHTTPException::");
		final String errorResponseText = httpStatusCodeException.getResponseBodyAsString();
		final ObjectMapper mapper = new ObjectMapper();
		// building default error response
		ErrorResponse errorResponse = new ErrorResponse();

		try {
			errorResponse = mapper.readValue(errorResponseText, ErrorResponse.class);
		} catch (IOException ioException) {
			log.error("cannot read error response [" + errorResponseText
					+ "] to convert to Object --> ErrorResponse, cause: " + ioException.getMessage(), ioException);
		}

		// build headers
		final HttpHeaders httpHeaders = getResponseHeaders(request);

		ErrorStatusCode statusCode = ErrorStatusCode.getStatusCode(httpStatusCodeException.getStatusCode().value());
		Object object = getApiSpecificErrorResponse(request,httpHeaders,statusCode,httpStatusCodeException);
		if(null != object){			
			return ResponseEntity.status(httpStatusCodeException.getStatusCode().value()).headers(httpHeaders).contentType(MediaType.APPLICATION_JSON).body(object);
		}
		// global log
		log.error("HTTP Server error occured in controller, cause: " + httpStatusCodeException.getMessage(), httpStatusCodeException);

		// send error response
		return ResponseEntity.status(httpStatusCodeException.getStatusCode().value()).headers(httpHeaders).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
	}


	private Object getApiSpecificErrorResponse(WebRequest request,HttpHeaders httpHeaders,ErrorStatusCode code,Exception exception){
		log.info("Entered CustomExceptionHandler::getApiSpecificErrorResponse::");
		Object object=null;
		String operationId = (String) request.getAttribute("operationId",0);
		log.info("operationId to getApiSpecificErrorResponse :: {} ",operationId);
		if(null != operationId && !"".equals(operationId)) {
			object = getErrorObject(operationId,code,exception);
		}
		return object;
	}

	@SuppressWarnings("unchecked")
	private Object getErrorObject(String operationId,ErrorStatusCode code,Exception exception){
		log.info("Entered CustomExceptionHandler::getErrorObject:: ");
		Object object= null;
		Map<ErrorStatusCode, ApiExceptionResponse> errorRes = MetaData.apiExceptions.get(operationId);
		ApiExceptionResponse apiExceptionResponse = errorRes.get(code);	
		if(apiExceptionResponse != null) {
			try {
				switch(operationId){
			case "getFeedback":
			object = getFeedbackI.error(code.getCode(),apiExceptionResponse.getDataType(),exception);
			break;

			case "createFeedback":
			object = createFeedbackI.error(code.getCode(),apiExceptionResponse.getDataType(),exception);
			break;

				/*case "ChangePassword": 
				object = changePasswordI.error(code.getCode(),apiExceptionResponse.getDataType());
				break;*/
				default: 
					object = null;
				}
			} catch (InstantiationException | IllegalAccessException e){
				e.printStackTrace();
			}
		}
		return object;
	}

}
