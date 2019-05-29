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
 * Feedback
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-05-28T13:26:34.286Z")

public class Feedback   {
  @JsonProperty("created_ts")
  private String createdTs = null;

  @JsonProperty("feedback")
  private String feedback = null;

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("rating")
  private Integer rating = null;

  @JsonProperty("rev")
  private String rev = null;

  public Feedback createdTs(String createdTs) {
    this.createdTs = createdTs;
    return this;
  }

  /**
   * utc time feedback was submitted
   * @return createdTs
  **/
  @ApiModelProperty(value = "utc time feedback was submitted")


  public String getCreatedTs() {
    return createdTs;
  }

  public void setCreatedTs(String createdTs) {
    this.createdTs = createdTs;
  }

  public Feedback feedback(String feedback) {
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

  public Feedback id(String id) {
    this.id = id;
    return this;
  }

  /**
   * id of feedback
   * @return id
  **/
  @ApiModelProperty(value = "id of feedback")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Feedback name(String name) {
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

  public Feedback rating(Integer rating) {
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

  public Feedback rev(String rev) {
    this.rev = rev;
    return this;
  }

  /**
   * revision number of feedback
   * @return rev
  **/
  @ApiModelProperty(value = "revision number of feedback")


  public String getRev() {
    return rev;
  }

  public void setRev(String rev) {
    this.rev = rev;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Feedback feedback = (Feedback) o;
    return Objects.equals(this.createdTs, feedback.createdTs) &&
        Objects.equals(this.feedback, feedback.feedback) &&
        Objects.equals(this.id, feedback.id) &&
        Objects.equals(this.name, feedback.name) &&
        Objects.equals(this.rating, feedback.rating) &&
        Objects.equals(this.rev, feedback.rev);
  }

  @Override
  public int hashCode() {
    return Objects.hash(createdTs, feedback, id, name, rating, rev);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Feedback {\n");
    
    sb.append("    createdTs: ").append(toIndentedString(createdTs)).append("\n");
    sb.append("    feedback: ").append(toIndentedString(feedback)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    rating: ").append(toIndentedString(rating)).append("\n");
    sb.append("    rev: ").append(toIndentedString(rev)).append("\n");
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

