package petstore_feedback.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import petstore_feedback.model.GetFeedbackP;
public interface GetFeedbackPMRepo extends MongoRepository<GetFeedbackP,String>{

}
