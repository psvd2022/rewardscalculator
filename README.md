# rewardscalculator

This is a maven project.

To Run the application -> Run com.demo.rewardscalculator.RewardsCalculatorApplication.java as JAVA Application

Swagger -> http://localhost:8083/swagger-ui/index.html

H2 Console Login Details:

  Console URL: http://localhost:8083/h2-console/login.do
  
  JDBC URL: jdbc:h2:mem:demo
  
  username: demo
  
  password: demo 


GET -> http://localhost:8083/api/v1/customers/{customerId}/rewards

SUCCESS Scenarios:

  http://localhost:8083/api/v1/customers/1/rewards

  http://localhost:8083/api/v1/customers/2/rewards

  http://localhost:8083/api/v1/customers/3/rewards

CUSTOMER_NOT_FOUND Scenario:

  http://localhost:8083/api/v1/customers/5/rewards

TRANSACTIONS_NOT_FOUND_FOR_REQUESTED_CUSTOMER Scenario:

  http://localhost:8083/api/v1/customers/4/rewards
  
To Run the Code Coverage -> mvn test jacoco:report

After running the above maven command, Jacoco Report can be found at target/site/jacoco/index.html

![image](https://user-images.githubusercontent.com/112277186/187046867-cd232aec-1c79-4776-9e71-9d838b1e012e.png)







