# JWT Token Validation - Spring Boot

A small Maven Spring Boot project for demonstrating JWT signature/expiration validation and claims extraction.

## Requirements
- JDK 17 or later
- Eclipse IDE for Enterprise Java and Web Developers (or Eclipse with Spring Tools)
- Internet connection for Maven to download dependencies

## Open in Eclipse
1. Extract this ZIP file.
2. Open Eclipse.
3. Select **File -> Import -> Maven -> Existing Maven Projects**.
4. Browse to the extracted `jwt-token-validation` folder.
5. Select the project and click **Finish**.
6. Wait for Maven dependencies to finish downloading.
7. Right-click the project -> **Maven -> Update Project** if needed.
8. Run `JwtTokenValidationApplication.java` using **Run As -> Java Application** or **Spring Boot App**.

## Test with Swagger UI
Open:
http://localhost:8080/swagger-ui/index.html

### A. Generate a demo token
Run this endpoint first:
`GET http://localhost:8080/api/demo/token`

Copy the `token` value from the response.

### B. Validate the token
In Swagger, open:
`POST /api/security/validate`

Use this request body:
```json
{
  "token": "PASTE_THE_TOKEN_HERE"
}
```

A valid token should return HTTP 200 and the username/claims.

### C. Test an invalid token
Change one or more characters in the token and submit again. It should return HTTP 401.

### D. Test an expired token
The demo token expires after one hour. To test expiry, wait until it expires and submit it again.

## Important
- The validator and demo token generator use the same configured secret key.
- `parseSignedClaims()` verifies the signature and checks the expiration claim.
- The demo token endpoint is only a testing helper. In a real application, issue tokens only after authenticating a user.
- This standalone demo does not include Spring Security login or database integration.
