package petstore_feedback.services.impl;

import java.util.*;

import petstore_feedback.model.*;

import petstore_feedback.dao.*;
import petstore_feedback.services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class GetFeedbackImpl implements GetFeedbackI {

	@Override
	public GetFeedbackResponseWrapper execute(GetFeedbackP pojo){
		//TODO
		return new GetFeedbackResponseWrapper();
	}

	@Override
	public <T> T error(int statusCode, Class<T> type,Exception exception)
			throws InstantiationException, IllegalAccessException {
		//TODO to write error response
		return type.newInstance();
	}}