# Final Project | Netflix Pathways Bootcamp

![name](https://www.morgan.edu/Images/News/Netflix_PathwaysProgram.png)

### Application Overview
This group final project involves the creation of a full-stack application that includes the following:
- A simple back-end API (via REST and GraphQL) inventory management web service for a video game store, developed using agile techniques in a group setting of 3-4 learners. You are responsible for designing and documenting the API and implementing the controllers, service, layering, repository, Java data objects, and unit tests for the application based on the provided database structure.
- The use of Agile methodology to plan and track your project, an important skill upon which you will likely be evaluated by hiring managers.


### User Stories
- As a user, I would like to be able to create, read, update, and delete game information.
- As a user, I would like to be able to search for games by studio, ESRB rating, and title.
- As a user, I would like to be able to create, read, update, and delete console information.
- As a user, I would like to be able to search for consoles by manufacturer.
- As a user, I would like to be able to create, read, update, and delete T-shirt information.
- As a user, I would like to be able to search for games by color and size.
- As a user, I would like to be able to purchase a specified quantity of products (games, consoles, T-shirts) and an invoice will be created that includes any taxes and processing fees.

### API Features
This system must manage the inventory of video games, game consoles, and T-shirts.
- Your REST API must allow the end user to do the following in each category:
    - Games:
        - Perform standard CRUD operations for games.
        - Search for games by studio.
        - Search for games by ESRB rating.
        - Search for games by title.
    - Consoles:
        - Perform standard CRUD operations for consoles.
        - Search for consoles by manufacturer.
    - T-shirts:
        - Perform standard CRUD operations for T-shirts.
        - Search for T-shirts by color.
        - Search for T-shirts by size.
    - Invoices:
        - Create an invoice by supplying the following information to the endpoint:
        - Name
        - Street
        - City
        - State
        - Zip
        - Item type
        - Item ID
        - Quantity
    - Your project must support GraphQL queries to retrieve the following information:
        - Get all Games
        - Get a Game by ID
        - Get a Game by Title
        - Get a Game by ESRB rating
        - Get a Game by Studio
        - Get all Consoles
        - Get a Console by ID
        - Get a Console by Manufacturer

### Test Requirements
- You must test all REST API routes using MockMVC.
    - This includes testing for both expected return values and expected controller failures (4xx and 5xx status codes).
- Test all service layer methods.
    - You should have 100% code coverage of the service layer.
    - These should be unit tests—in other words, they should employ mocking.
- You must have integration tests for all repositories.
    - These should test the basic CRUD operations.
    - These should also test any custom methods you've defined (such as findByCategory).

### Business Rules
- Sales tax applies only to the cost of the items.
- Sales tax does not apply to any processing fees for an invoice.
- The processing fee is applied only once per order, regardless of the number of items in the order, unless the number of items in the order is greater than 10, in which case an additional processing fee of $15.49 is applied to the order.
- The order-processing logic must properly update the quantity available for the item in the order.
- Order quantity must be greater than zero.
- Order quantity must be less than or equal to the number of items available in the inventory.
- The order must contain a valid state code.
- The REST API must properly handle and report all violations of business rules.

### Deployment
- Project code should be synchronized to a GitHub repository.
- Application should be deployed to AWS and should receive requests and respond accordingly.
- Application database should be deployed to Amazon RDS and should receive requests and respond accordingly.
- Project code should be deployed to a CircleCI CI/CD pipeline that includes a "build and test" job.

### Technologies
- Languages
    - Java
    - SQL
    - GraphQL
- Software
    - MySQL - Database
    - Spring Boot - Application Service
    - OpenAPI - Documentation
    - Circleci - CI/DI
    - AWS Relational Database Service (RDS) - Database Deployment (PaaS)
    - AWS Elastic Beanstalk - API Deployment (PaaS)

### Video Submission
- [Final Project Group Presentation video](https://youtu.be/ZIdMpHaZvUg)
    - Created by [James Bosch](https://www.linkedin.com/in/james-bosch/), [Efaz Ahmed](https://www.linkedin.com/in/eahmed2024/), [Reuben Agogoe](https://www.linkedin.com/in/reubenagogoe/) and [Ashley Acevado](https://www.linkedin.com/in/ashleyrennee/)

### Deployment
- [Gamestore Service API Endpoint](http://gamestore-service-prod-env.eba-mnkhtfsg.us-east-1.elasticbeanstalk.com/)
    - NOTE: This service may be out of service depending on when you see this GitHub repository
- [Gamestore GraphQL Endpoint](http://gamestore-service-prod-env.eba-mnkhtfsg.us-east-1.elasticbeanstalk.com/graphiql?path=/graphql)
    - NOTE: This service may be out of service depending on when you see this GitHub repository
- [OpenAPI Documentation](http://gamestore-service-prod-env.eba-mnkhtfsg.us-east-1.elasticbeanstalk.com/swagger-ui/index.html)
