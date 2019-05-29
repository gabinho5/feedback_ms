package petstore_feedback.services;

import java.util.*;

import petstore_feedback.model.*;

public interface GetFeedbackI {

	public GetFeedbackResponseWrapper execute(GetFeedbackP pojo);
	public <T> T error(int statusCode,Class<T> type,Exception exception) throws InstantiationException, IllegalAccessException;

}