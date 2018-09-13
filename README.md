==========
How to RUN
==========
1. ./mvnw spring-boot:run
2. browse -> https://localhost:8080
3. it will redirect to https://localhost:8443
4. enable un-secure access if prompted because of self generated SSL Certificate -> "Proceed to localhost (unsafe)"

=======
Details
=======
1. Java Spring Boot App using JSP, JPA Hibernate and H2 Database
2. Challenging Thing was to write a customized DB Query for Payroll Listing
3. no external DB Server is required. Use https://localhost:8443/h2 for DB console, use default username 'sa' and blank password for login