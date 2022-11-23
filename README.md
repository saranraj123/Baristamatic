# Baristamtic
Coffee ordering App

# PROBLEM DESCRIPTION
Your task is to create the backend API for a coffee ordering app called the Baristamatic. The
server maintains an inventory of drink ingredients and is able to prepare a fixed set of possible
drinks by combining these ingredients in different amounts. The cost of a drink is determined by
its component ingredients.

The Baristamatic should be capable of offering the following drinks:
![Available Drinks](https://user-images.githubusercontent.com/35673164/203593647-4f5bad45-69d4-42d3-a742-ea272b80d4f9.JPG)

Per-unit ingredient costs are as follows:
![Per_Ingredient_Cost](https://user-images.githubusercontent.com/35673164/203594090-00316bc8-795c-4631-a735-943a498a584a.JPG)

Initially the Baristamatic should contain 10 units of each ingredient and issuing a restock request
should restore each ingredient to its maximum of 10 units. As drink requests are made,
component ingredients should decrease accordingly. Should a drink not be able to be made
due to one or more of the ingredients not having the required quantity, Baristamatic should
respond appropriately. The user can request the current inventory which should list each
ingredient and the current quantity on-hand.
At any time a menu request should be available that lists the id, name, cost, and availability
(based on the ability of Baristamatic to make the drink given its current inventory) for each drink.

# APPLICATION FRAMEWORK
The application should be a RESTful web application using the Spring Boot framework version
2.5.5 (or most recent stable release). Build tooling may be done via gradle or maven. If you
choose to use a database for data management, use an in-memory, in-process database such
as H2 or similar. The target JDK is Java 11. It is you decision on how best to structure and
implement your solution within these restrictions.

# RESTFUL INTERFACE
The design of the RESTful API is up to you, but should be able to handle all the capabilities
discussed in the “PROBLEM DESCRIPTION” section. Securing the endpoints is not necessary.
Invalid endpoints may be gracefully handled or allowed to give the default whitelabel error. Valid
endpoints should always respond with a JSON formatted DTO.

# TEST COVERAGE
You may use any testing framework you wish, but there should be a reasonable set of tests to
cover the application’s functionality.

# STRETCH GOALS
While not necessary for completion, the stretch goals below will demonstrate competencies in
areas commonly used by our applications:
- Postman collection to document your API
- Dockerization of the web application
