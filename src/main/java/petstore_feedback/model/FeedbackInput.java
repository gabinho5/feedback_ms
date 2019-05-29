package petstore_feedback.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * FeedbackInput
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-05-28T13:26:34.286Z")

public class FeedbackInput   {
  @JsonProperty("feedback")
  private String feedback = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("rating")
  private Integer rating = null;

  public FeedbackInput feedback(String feedback) {
    this.feedback = feedback;
    return this;
  }

  /**
   * feedback of user
   * @return feedback
  **/
  @ApiModelProperty(value = "feedback of user")


  public String getFeedback() {
    return feedback;
  }

  public void setFeedback(String feedback) {
    this.feedback = feedback;
  }

  public FeedbackInput name(String name) {
    this.name = name;
    return this;
  }

  /**
   * name of user submitting feedback
   * @return name
  **/
  @ApiModelProperty(value = "name of user submitting feedback")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public FeedbackInput rating(Integer rating) {
    this.rating = rating;
    return this;
  }

  /**
   * rating of feedback (0 - 5)
   * @return rating
  **/
  @ApiModelProperty(value = "rating of feedback (0 - 5)")


  public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FeedbackInput feedbackInput = (FeedbackInput) o;
    return Objects.equals(this.feedback, feedbackInput.feedback) &&
        Objects.equals(this.name, feedbackInput.name) &&
        Objects.equals(this.rating, feedbackInput.rating);
  }

  @Override
  public int hashCode() {
    return Objects.hash(feedback, name, rating);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FeedbackInput {\n");
    
    sb.append("    feedback: ").append(toIndentedString(feedback)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    rating: ").append(toIndentedString(rating)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

