1. Instructions on how to build and run your app
   - Java 8 or above
   - import project as gradle project and build gradle
   - right click on project_name choose run as Spring Boot App
   - DB using jdbc:h2:mem (store in membery) when run project link (http://localhost:8080/h2-console/) to see database

2. Instruction on how to run unit and integration tests.
   - ScoreControllerTest.java is for testing api
   - ScoreServiceTest.java is for testing the DAO(Data Accesses Object) and service
   + Run TestExecution
    - Go to file above run as JUnit Test

3. Quick documentation of your API
  Please import Score-Ranking-API.json to the post man Tools
   - URL of api is http://localhost:8080/api/v1/score/..
