

## How to Run the Application

### Using Maven

1. **Navigate to the project directory:**
   ```bash
   cd \Assesment
   ```

2. **Build the project:**
   ```bash
   mvn clean package
   ```

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```


4. **The application will start on port 8080**

### Testing the Endpoint

Once the application is running, you can test it using curl, Postman, or your web browser:

**Valid name examples (A-M):**
```bash
curl http://localhost:8080/hello-world?name=alice
# Response: {"message":"Hello Alice"} (HTTP 200)

curl http://localhost:8080/hello-world?name=Bob
# Response: {"message":"Hello Bob"} (HTTP 200)

curl http://localhost:8080/hello-world?name=MIKE
# Response: {"message":"Hello Mike"} (HTTP 200)
```

**Invalid name examples (N-Z):**
```bash
curl http://localhost:8080/hello-world?name=nancy
# Response: {"error":"Invalid Input"} (HTTP 400)

curl http://localhost:8080/hello-world?name=Zoe
# Response: {"error":"Invalid Input"} (HTTP 400)
```

**Missing or empty parameter:**
```bash
curl http://localhost:8080/hello-world
# Response: {"error":"Invalid Input"} (HTTP 400)

curl http://localhost:8080/hello-world?name=
# Response: {"error":"Invalid Input"} (HTTP 400)
```

## How to Run the Tests

### Run all tests:
```bash
mvn test
```

### Run tests with detailed output:
```bash
mvn test -X
```

### Run a specific test class:
```bash
mvn test -Dtest=HelloWorldControllerTest
```

### Run tests and generate coverage report:
```bash
mvn clean test
```

## API Specification

### Endpoint: `GET /hello-world`

**Query Parameters:**
- `name` (required): The name to validate

**Responses:**

| Condition | HTTP Status | Response Body | Description |
|-----------|-------------|---------------|-------------|
| Name starts with A-M (case-insensitive) | 200 OK | `{"message":"Hello <Name>"}` | Name is capitalized in response |
| Name starts with N-Z (case-insensitive) | 400 Bad Request | `{"error":"Invalid Input"}` | First letter is in second half of alphabet |
| Name parameter missing or empty | 400 Bad Request | `{"error":"Invalid Input"}` | Required parameter not provided |
| Name is only whitespace | 400 Bad Request | `{"error":"Invalid Input"}` | Name contains no valid characters |



### Assumptions I made
1. **Name Formatting**: Names are capitalized (first letter uppercase, rest lowercase) in the response
2. **Whitespace Handling**: Leading and trailing whitespace is trimmed before validation
3. **Single Name**: The API expects a single name, not multiple words
4. **Alphabet**: Only English alphabet characters (A-Z, a-z) are considered
5. **Case Insensitivity**: Validation is case-insensitive (both 'A' and 'a' are valid)
6. **Character Range**: First half = A-M, Second half = N-Z (M is valid, N is invalid)

### Edge Cases Handled
- Null or missing name parameter
- Empty string
- Whitespace-only strings
- Leading/trailing whitespace
- Mixed case input (e.g., "aLiCe", "BOB")
- Single character names
- Boundary cases (M/N, A/Z)


## Testing

I wrote tests to cover the main scenarios:

1. **Service Tests** (`NameValidationServiceTest`) - 5 tests
   - Valid and invalid names
   - Null/empty handling
   - The M/N boundary case
   - Name formatting

2. **Controller Tests** (`HelloWorldControllerTest`) - 6 tests
   - Valid names return 200 with greeting
   - Invalid names return 400 with error
   - Missing/empty parameters
   - Case insensitivity
   - The important M vs N boundary

3. **Integration Test** (`HelloWorldApplicationTest`) - 1 test
   - Spring boots up correctly

Total: 12 tests covering the essential scenarios
