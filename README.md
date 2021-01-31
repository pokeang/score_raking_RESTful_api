1. Instructions on how to build and run your app
   - Java 8 or above (required)
   - import project as gradle project and build gradle (download dependencies)
   - right click on project_name choose run as Spring Boot App (run project)
   - DB using jdbc:h2:mem (store in membery) when run project we can see database throws (http://localhost:8080/h2-console/)

2. Instruction on how to run unit and integration tests.
   - ScoreControllerTest.java is for testing api
   - ScoreServiceTest.java is for testing the DAO(Data Accesses Object) and service
   + Run TestExecution
    - Go to file above run as JUnit Test

3. Quick documentation of your API
  Please import Score-Ranking-API.json to the Postman Tools (all api at there)
   - URL of api is http://localhost:8080/api/v1/score/..
