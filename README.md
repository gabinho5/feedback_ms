# Feedback Microservice Instructions

### System Requirements
* Git Command Line Tools [Github CLI](https://git-scm.com/downloads)
* Maven Commane Line Tools [Maven CLI](https://maven.apache.org/download.cgi)
* Code Editor [Visual Studio Code](https://code.visualstudio.com/download) (Or similar)
* Web Browser [Google Chrome](https://www.google.com/chrome/)
* IBM Network Access (Via VPN or physically residing on IBM internal network)

### Instructions
1. Use Provided .yaml file to generate spring microservice
    * I want to create a new `Spring Boot` Microservice using `[Provided .yaml file]` with name: `petstorefeedback`
    * Packaged as `petstorefeedback` check in at `git_url`
    * Service Capabilities
      * Leave all defaults
    * Enable CI/CD
      * check this box
      * Leave all defaults
      * Environment ts: `dev-docker`
      * Port: `32000`
    * Validate
      * Git
        * User: `Your Github Username`
        * Password: `Your Github Personal Access Token` Don't have one? [Generate Personal Access Token Here](https://github.ibm.com/settings/tokens)
      * Jenkins:
        * User: `demo_user`
        * Password: `jenkins123`
        * Token: `token12345678`
      * Click `Okay`
        * ensure you see: `git repository exist`, and `Valid application name!`
    * Generate Microservice
        * Utilize credentials from `Validate` step
2. Update microservice with logic
    1. Clone Microservice to local machine
        * `git clone git_url_here`
    2. Change into directory
    3. Open directory in VS Code (Or similar code editor)
    4. Update Dependancies (`~/pom.xml` After line `#261` - creating another entry within `<dependencies></dependencies>` tag)
          ```xml
          <dependency>
              <groupId>com.cloudant</groupId>
              <artifactId>cloudant-spring-boot-starter</artifactId>
              <version>0.0.4</version>
          </dependency>
          ```
    5. Edit `~/src/main/resources/application.properties` with the following information:
          ```properties
          # Server Port
          server.port=3000

          # Cloudant NoSQL DB Configuration
          cloudant.url=http://cloudant.com
          cloudant.username=myUsername
          cloudant.password=myPassword
          cloudant.db=feedback
          ```
    6. Update Imports (`~/src/main/java/petstore_feedback/controller/FeedbackApiController.java` after line `#30`)
          ```java
          import com.cloudant.client.api.Database;
          import org.springframework.beans.factory.annotation.Autowired;
          import com.cloudant.client.api.model.Response;
          import java.util.ArrayList;
          import java.util.Date;
          ```
    7. Update Variables (`~/src/main/java/petstore_feedback/controller/FeedbackApiController.java` after line `#44`)
          ```java
          @Autowired
          private Database feedbackDB;
          ```
    8. Update Logic for Get Feedback endpoint (`~/src/main/java/petstore_feedback/controller/FeedbackApiController.java` Replace function at line `#53`)
          ```java
          public ResponseEntity<List<Feedback>> feedbackGet() {
            List<Feedback> allDocs = new ArrayList<Feedback>();
            try {
              allDocs = feedbackDB.getAllDocsRequestBuilder()
                                  .includeDocs(true)
                                  .build()
                                  .getResponse()
                                  .getDocsAs(Feedback.class);
            } catch (Exception e) {
                log.error(“error occurred: {}“, e.getLocalizedMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<List<Feedback>>(allDocs, HttpStatus.OK);
          }
          ```
    9. Update Logic for Post Feedback endpoint (`~/src/main/java/petstore_feedback/controller/FeedbackApiController.java` Replace function at line `#69`)
          ```java
          public ResponseEntity<SuccessfulResponse> feedbackPost(@ApiParam(value = “”  )  @Valid @RequestBody FeedbackInput body) {
              Response response;
              SuccessfulResponse successfulResponse;
              Feedback newFeedback = new Feedback();
              Date d = new Date();
              newFeedback.setCreatedTs(d.toString());
              newFeedback.setFeedback(body.getFeedback());
              newFeedback.setName(body.getName());
              newFeedback.setRating(body.getRating());
              try {
                  response = feedbackDB.save(newFeedback);
                  successfulResponse = new SuccessfulResponse();
                  successfulResponse.setId(response.getId());
                  successfulResponse.setRev(response.getRev());
              } catch (Exception e) {
                  log.error(“error occurred: {}“, e.getLocalizedMessage());
                  return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
              }
              ResponseEntity<SuccessfulResponse> responseEntity = new ResponseEntity<SuccessfulResponse>(successfulResponse, HttpStatus.OK);
              return responseEntity;
          }
          ```
    <!-- 10. Update Tests (`~/src/test/java/petstore_feedback/...`)
          ```java
          @test
          public void feedbackGetTest() throws Exception {
              ResponseEntity<List<Feedback>> responseEntity = api.feedbackGet();
              assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
          }
          @test
          public void feedbackPostTest() throws Exception {
              FeedbackInput body = new FeedbackInput();
              ResponseEntity<SuccessfulResponse> responseEntity = api.feedbackPost(body);
              assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
          }
          ``` -->
    10. `TODO:` Build and allow java spring application to serve front end react application
        * **Implement this step**
    11. Build Updated Code
        * `mvn clean package`
        * Ensure something similar to:
            ```properties
            [INFO] ------------------------------------------------------------------------
            [INFO] BUILD SUCCESS
            [INFO] ------------------------------------------------------------------------
            [INFO] Total time: 8.044 s
            [INFO] Finished at: 2019-05-28T11:10:29-05:00
            [INFO] ------------------------------------------------------------------------
            ```
3. Commit updated logic to github
    * In Terminal, `cd` into directory which contains the current project
    * `git add .`
    * `git commit -m 'implement logic`
    * `git push`
4. Ensure commit to github triggers build/deployment process
    * Check Jenkins Pipeline: [Jenkins Pipeline URL]()
5. Ensure completed build / deployment
    * Check Jenkins Pipeline: [Jenkins Pipeline URL]()
6. See working microservice in action!
    * Visit [Feedback Microservice URL]()