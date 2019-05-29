package petstore_feedback.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import petstore_feedback.model.CreateFeedbackP;
public interface CreateFeedbackPMRepo extends MongoRepository<CreateFeedbackP,String>{

}
