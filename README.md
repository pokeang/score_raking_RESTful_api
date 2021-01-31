1. Instructions on how to build and run your app
   - Java 8 or above (required)
   - Import project as gradle project and build gradle (download dependencies)
   - Right click on project_name choose run as Spring Boot App (run project)
   - DB using jdbc:h2:mem (store in membery) when run project we can see database throws (http://localhost:8080/h2-console/)

2. Instruction on how to run unit and integration tests.
   - ScoreControllerTest.java is for testing api
   - ScoreServiceTest.java is for testing the DAO(Data Accesses Object) and service
   + Run TestExecution
    - Go to file above run as JUnit Test

3. Quick documentation of your API
   - URL of api is http://localhost:8080/api/v1/score/..

   + For Postman: 
    - Please import Score-Ranking-API.json to the Postman Tools

   + For using cURL:
    - Create Post:
    
    curl --header "Content-Type: application/json" \
    --request POST \
    --data '{"player":"edo","score":"100","time": "2021-01-31"}' \
    http://localhost:8080/api/v1/score/

    - Get Score by id
    
      curl -v http://localhost:8080/api/v1/score/1
  
    - Delete Score by id
    
      curl -X DELETE http://localhost:8080/api/v1/score/1
    
    - Get list of score by player:
    
       Create Post:
       curl --header "Content-Type: application/json" \
       --request POST \
       --data '{"player":"edo"}' \
       http://localhost:8080/api/v1/score/search

    - Get list of score by after date:
    
       Create Post:
       curl --header "Content-Type: application/json" \
       --request POST \
       --data '{"afterDate":"2021-01-30"}' \
       http://localhost:8080/api/v1/score/search

    - Get list of score by many players
    
       Create Post:
       curl --header "Content-Type: application/json" \
       --request POST \
       --data '{"players":["edo", "player"]}' \
       http://localhost:8080/api/v1/score/search

    - Get list of score by after date and before date
    
      Create Post:
       curl --header "Content-Type: application/json" \
       --request POST \
       --data '{"afterDate":"2021-01-30", "beforeDate": "2021-02-29"}' \
       http://localhost:8080/api/v1/score/search

    - Get players' history by player name
    
      curl -v http://localhost:8080/api/v1/score/history/eDo
