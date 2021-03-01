#Requirements:

	Spring Boot
	Maven
	Java 8
	WireMock

**1. Build and run the app using maven**

```bash
mvn clean install
java -jar target/app-integration-service-1.0.0.jar```

```

BaseURL : http://localhost:9000


1. http://localhost:9000/customer/{cust_id}/order/summary

	i.e http://localhost:8000/customer/customer1/order/summary

This above API Provide Order Summary based on customer Id.

2. http://localhost:9000/order/{order_id}/detail

	i.e http://localhost:8000/order/1/detail

This API Provide Order Details based on order Id.

