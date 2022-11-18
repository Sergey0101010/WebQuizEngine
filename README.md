# WebQuizEngine
Engine for conducting surveys and tests.
This service allows you to make quizzes and let your friends to solve them.

**Stack:** Java 17, Spring boot, Spring Security, Hibernate, H2, Maven.
## Description.
To access this service, registration is required, which is available to everyone on request `POST /api/register`. 
After registration, the following functions are available to the user:
- Getting specific quiz from db. `GET /api/quizzes/{id}`
- Get all available quizzes divided into pages. `GET /api/quizzes`
- Get all completed quizzes of authenticated user. `GET /api/quizzes/completed`
- Add new quiz. `GET /api/quizzes`
- Answer some quiz. `POST /api/quizzes/{id}/solve`
- Delete your quiz. `DELETE /api/quizzes`
