# openapi-java-client

## FakeStoreAPI OpenAPI Client

This project demonstrates API client generation and API automation using the **OpenAPI Specification**, **OpenAPI Generator**, **Java**, and **TestNG**.

The API client is generated automatically from the FakeStoreAPI OpenAPI specification. The test layer then uses the generated client to execute API operations and validate successful and failure scenarios.

> **Note:** The API client code is generated using OpenAPI Generator and should not be manually modified.

---

## Project Overview

The overall implementation follows this flow:

```text
OpenAPI Specification
        ↓
OpenAPI Generator
        ↓
Generated Java API Client
        ↓
Custom TestNG Test Layer
        ↓
FakeStoreAPI
        ↓
Assertions / Test Results
```

The objective is to demonstrate how an OpenAPI specification can be used to generate a Java API client and how that generated client can subsequently be consumed by an automation/test layer.

---

## Assignment Objectives

This project covers the following requirements:

1. Generate API client code using an OpenAPI specification.
2. Select a programming language and an OpenAPI-compatible code generation tool.
3. Generate the API client instead of manually implementing HTTP client code.
4. Create a small test/application layer using the generated client.
5. Demonstrate both successful and failure scenarios.
6. Keep generated code separate from custom test/application code.
7. Provide documentation explaining the setup, generation, build, and test execution process.

---

## Technology Stack

| Technology        | Version / Details     |
| ----------------- | --------------------- |
| Java              | JDK 21                |
| Maven             | 3.9.x                 |
| OpenAPI Generator | 7.25.0                |
| Test Framework    | TestNG 7.12.0         |
| API Client        | Generated Java client |
| API               | FakeStoreAPI          |
| Build Tool        | Maven                 |

The generated client identifies FakeStoreAPI as version `v2.1.11` and was generated using OpenAPI Generator `7.25.0`.

---

# Prerequisites

Before setting up the project, make sure the following tools are installed.

### 1. Java JDK

Java 21 is recommended for this project.

Verify the installation:

```bash
java -version
```

### 2. Maven

Maven is required to build the generated Java client and execute the tests.

Verify:

```bash
mvn -version
```

### 3. OpenAPI Generator

Install OpenAPI Generator CLI.

Verify:

```bash
openapi-generator version
```

Expected version:

```text
7.25.0
```

### 4. Git

Git is required if the project is being cloned from a repository.

Verify:

```bash
git --version
```

### 5. IDE

Any Java IDE can be used.

Recommended:

* IntelliJ IDEA
* Visual Studio Code

---

# Project Structure

A recommended project structure is:

```text
openAPIDemo/
│
├── fakeStoreAPI.yml
├── README.md
│
└── generated-client/
    │
    ├── pom.xml
    │
    ├── src/
    │   ├── main/
    │   │   └── java/
    │   │       └── org/openapitools/client/
    │   │           ├── ApiClient.java
    │   │           ├── ApiException.java
    │   │           ├── Configuration.java
    │   │           │
    │   │           ├── api/
    │   │           │   ├── AuthApi.java
    │   │           │   ├── CartsApi.java
    │   │           │   ├── ProductsApi.java
    │   │           │   └── UsersApi.java
    │   │           │
    │   │           └── model/
    │   │               ├── Cart.java
    │   │               ├── Login.java
    │   │               ├── LoginResponse.java
    │   │               ├── Product.java
    │   │               └── User.java
    │   │
    │   └── test/
    │       └── java/
    │           └── org/openapitools/client/
    │               └── FakeStoreProductsTest.java
    │
    └── target/
```

The generated client contains API classes such as `ProductsApi`, `CartsApi`, `UsersApi`, and `AuthApi`. The generated README also documents these endpoints.

> **Recommendation:** For a production project, generated code and custom test code should ideally be maintained in separate source modules/directories so that regenerating the client does not overwrite custom code.

---

# OpenAPI Specification

The API client is generated from:

```text
fakeStoreAPI.yml
```

The OpenAPI specification acts as the contract describing:

* API endpoints
* HTTP methods
* Request parameters
* Request bodies
* Response schemas
* Data models
* Authentication requirements

The generated client is therefore based on the API contract rather than manually written HTTP calls.

---

# OpenAPI Generator

## Why OpenAPI Generator?

OpenAPI Generator generates client libraries, server stubs, and other artifacts from an OpenAPI specification.

For this project:

```text
Input:
fakeStoreAPI.yml

Generator:
OpenAPI Generator

Language:
Java

Output:
Java API Client
```

---

# Validate the OpenAPI Specification

Before generating the client, validate the OpenAPI specification.

From the project root:

```bash
openapi-generator validate -i fakeStoreAPI.yml
```

A successful validation should indicate that no validation issues were detected.

---

# Generate the Java Client

Run:

```bash
openapi-generator generate \
  -i fakeStoreAPI.yml \
  -g java \
  -o generated-client
```

### Explanation

```text
-i fakeStoreAPI.yml
```

Specifies the OpenAPI specification.

```text
-g java
```

Specifies Java as the target programming language.

```text
-o generated-client
```

Specifies the output directory.

---

# Important: Generated Code vs Custom Code

The API client implementation is generated automatically.

Examples of generated classes include:

```text
ApiClient.java
ProductsApi.java
UsersApi.java
CartsApi.java
AuthApi.java
Product.java
User.java
Cart.java
Login.java
```

These classes should not be manually modified.

The custom automation layer should consume these generated classes.

For example:

```java
ProductsApi productsApi = new ProductsApi(apiClient);

List<Product> products = productsApi.getAllProducts();
```

The test code is responsible for:

* Creating the client
* Configuring the base URL
* Calling generated API methods
* Validating responses
* Performing assertions
* Handling positive and negative scenarios

---

# Generated API Operations

The generated client exposes the following major API operations.

## Authentication

```text
POST /auth/login
```

Generated method:

```java
loginUser(Login login)
```

## Carts

```text
POST   /carts
GET    /carts
GET    /carts/{id}
PUT    /carts/{id}
DELETE /carts/{id}
```

## Products

```text
POST   /products
GET    /products
GET    /products/{id}
PUT    /products/{id}
DELETE /products/{id}
```

## Users

```text
POST   /users
GET    /users
GET    /users/{id}
PUT    /users/{id}
DELETE /users/{id}
```

These operations correspond to the generated API documentation.

---

# Generated Product Model

The generated `Product` model is used by the test layer rather than creating a separate request/response model manually.

Example:

```java
Product product = new Product();

product.setTitle("Test Product");
product.setPrice(199.99f);
product.setDescription("Product created for API automation testing");
product.setCategory("electronics");
```

The generated model is then passed directly to the generated API method.

---

# Test Layer

The custom TestNG test class is:

```text
FakeStoreProductsTest.java
```

The test layer demonstrates several API operations using the generated `ProductsApi`.

### Positive scenarios

```text
getAllProductsTest()
getProductByIdTest()
addProductTest()
updateProductTest()
deleteProductTest()
```

### Negative scenario

```text
getProductByInvalidIdTest()
```

The important point is that the tests do not manually construct HTTP requests using RestAssured or OkHttp.

Instead, they use the generated client:

```java
productsApi.getAllProducts();
productsApi.getProductById(productId);
productsApi.addProduct(product);
productsApi.updateProduct(productId, product);
productsApi.deleteProduct(productId);
```

This demonstrates the intended architecture:

```text
TestNG
  ↓
ProductsApi
  ↓
Generated ApiClient
  ↓
FakeStoreAPI
```

---

# Configure the API Client

The generated client can be configured with the FakeStoreAPI base URL:

```java
ApiClient apiClient = new ApiClient();

apiClient.setBasePath("https://fakestoreapi.com");

ProductsApi productsApi = new ProductsApi(apiClient);
```

The generated documentation also shows the base URL being configured as:

```text
https://fakestoreapi.com
```

## and notes that the API endpoints are relative to this URL.

# Example Test

Example of using the generated client:

```java
@Test(description = "Verify GET all products API")
public void getAllProductsTest() throws Exception {

    List<Product> products = productsApi.getAllProducts();

    Assert.assertNotNull(
        products,
        "Products response should not be null"
    );

    Assert.assertFalse(
        products.isEmpty(),
        "Products list should not be empty"
    );

    System.out.println("Total products: " + products.size());
}
```

The important aspect of this test is that the API interaction is performed through the generated `ProductsApi` class.

---

# Successful Scenarios

The test layer demonstrates successful operations such as:

### GET all products

```text
GET /products
```

Validation:

* Response should not be null
* Product list should not be empty

### GET product by ID

```text
GET /products/{id}
```

Validation:

* Response should not be null
* Returned product ID should match the requested ID

### Add product

```text
POST /products
```

Validation:

* Response should not be null
* Created product information can be inspected

### Update product

```text
PUT /products/{id}
```

Validation:

* Response should not be null
* Updated product information can be inspected

### Delete product

```text
DELETE /products/{id}
```

Validation can be performed using the generated `deleteProductWithHttpInfo()` method when response status verification is required.

For example:

```java
ApiResponse<Void> response =
        productsApi.deleteProductWithHttpInfo(productId);

Assert.assertEquals(response.getStatusCode(), expectedStatus);
```

The exact expected status should be based on the API specification/actual API contract rather than being assumed.

---

# Failure Scenarios

Negative scenarios should also be included to demonstrate how the generated client handles API failures.

Example:

```java
@Test(description = "Verify GET product API with invalid product ID",
      expectedExceptions = Exception.class)
public void getProductByInvalidIdTest() throws Exception {

    int invalidProductId = -1;

    productsApi.getProductById(invalidProductId);
}
```

Possible failure scenarios include:

* Invalid product ID
* Non-existing product ID
* Invalid request payload
* Missing required request data
* Invalid HTTP operation
* Unexpected server response

The exact expected exception/status should be aligned with the behavior defined in the OpenAPI specification and the actual API.

---

# Build the Project

Navigate to the generated client:

```bash
cd generated-client
```

Build the project:

```bash
mvn clean install
```

The generated project also supports:

```bash
mvn clean package
```

The generated documentation describes `mvn clean install` as the standard way to install the generated API client into the local Maven repository.

---

# Run All Tests

Execute:

```bash
mvn clean test
```

This will:

1. Compile the generated client.
2. Compile the custom test classes.
3. Execute the TestNG tests.
4. Generate the test results.

---

# Run a Specific Test Class

To execute only the product tests:

```bash
mvn -Dtest=FakeStoreProductsTest test
```

---

# Run a Specific Test Method

For example:

```bash
mvn -Dtest=FakeStoreProductsTest#getAllProductsTest test
```

---

# Installation of Generated Client

The generated client can be installed into the local Maven repository using:

```bash
mvn clean install
```

After installation, another Maven project can reference the generated client as a dependency.

The generated README provides the standard Maven dependency structure:

```xml
<dependency>
    <groupId>org.openapitools</groupId>
    <artifactId>openapi-java-client</artifactId>
    <version>v2.1.11</version>
    <scope>compile</scope>
</dependency>
```

---

# Setup from Scratch

Follow these steps when setting up the project on a new machine.

## Step 1: Clone/Open the Project

Clone the repository or open the project directory.

```bash
cd ~/Documents/workspace/openAPIDemo
```

---

## Step 2: Verify the OpenAPI Specification

Make sure the following file exists:

```text
fakeStoreAPI.yml
```

---

## Step 3: Validate the Specification

```bash
openapi-generator validate -i fakeStoreAPI.yml
```

---

## Step 4: Generate the Java Client

```bash
openapi-generator generate \
  -i fakeStoreAPI.yml \
  -g java \
  -o generated-client
```

---

## Step 5: Open the Generated Maven Project

Open:

```text
generated-client/
```

in IntelliJ IDEA or VS Code.

---

## Step 6: Resolve Maven Dependencies

Run:

```bash
mvn dependency:resolve
```

---

## Step 7: Build the Project

```bash
mvn clean install
```

---

## Step 8: Execute Tests

```bash
mvn clean test
```

---

# Complete Workflow

The complete workflow can be summarized as:

```text
                fakeStoreAPI.yml
                       │
                       ▼
             OpenAPI Specification
                       │
                       ▼
              Validate Specification
                       │
                       ▼
              OpenAPI Generator
                       │
                       ▼
              Generated Java Client
                       │
          ┌────────────┴────────────┐
          │                         │
          ▼                         ▼
     API Classes              Model Classes
   ProductsApi.java             Product.java
   UsersApi.java                User.java
   CartsApi.java                Cart.java
   AuthApi.java                 Login.java
          │
          └────────────┬────────────┘
                       ▼
               Custom TestNG Layer
                       │
                       ▼
                 FakeStoreAPI
                       │
                       ▼
                 Assertions
                       │
                       ▼
                  Test Results
```

---

# Why This Approach?

This implementation demonstrates an API automation approach where the API client is generated from a formal API contract.

Instead of manually writing:

```java
given()
    .when()
    .get("/products");
```

the test uses the generated client:

```java
productsApi.getAllProducts();
```

This provides a clear separation between:

```text
API Contract
      ↓
Generated Client
      ↓
Test/Application Logic
```

The generated client handles the API communication while the custom test layer focuses on validation and business/test logic.

---

# Generated Documentation

OpenAPI Generator also creates documentation for the generated APIs and models.

Examples include:

```text
docs/AuthApi.md
docs/CartsApi.md
docs/ProductsApi.md
docs/UsersApi.md
docs/Product.md
docs/User.md
docs/Cart.md
docs/Login.md
```

The generated README contains links to the API endpoint and model documentation.

---

# Authorization

According to the generated client documentation, the endpoints do not require authorization.

Therefore, the current test implementation does not need to configure an authentication token.

---

# Multithreading Recommendation

The generated documentation recommends creating an `ApiClient` instance per thread when using the client in a multithreaded environment to avoid potential issues.

For example:

```java
ApiClient apiClient = new ApiClient();
ProductsApi productsApi = new ProductsApi(apiClient);
```

For parallel TestNG execution, this consideration should be taken into account when designing the test framework.

---

# Useful Commands

### Validate OpenAPI specification

```bash
openapi-generator validate -i fakeStoreAPI.yml
```

### Generate Java client

```bash
openapi-generator generate \
  -i fakeStoreAPI.yml \
  -g java \
  -o generated-client
```

### Build

```bash
mvn clean install
```

### Run all tests

```bash
mvn clean test
```

### Run product tests

```bash
mvn -Dtest=FakeStoreProductsTest test
```

### Check OpenAPI Generator version

```bash
openapi-generator version
```

---

# Key Learning

This project demonstrates the following concepts:

* OpenAPI Specification
* YAML-based API definition
* OpenAPI specification validation
* OpenAPI Generator
* Java client generation
* Generated API classes
* Generated model classes
* TestNG integration
* Positive API testing
* Negative API testing
* Response validation
* HTTP status-code validation
* Separation of generated and custom code
* Maven build and test execution

---

# Conclusion

The project demonstrates an end-to-end implementation of:

```text
OpenAPI Specification
        ↓
OpenAPI Generator
        ↓
Generated Java API Client
        ↓
Custom TestNG Test Layer
        ↓
FakeStoreAPI
        ↓
Assertions and Test Results
```

The API client is generated from the OpenAPI specification rather than manually implemented, while the custom TestNG layer demonstrates how the generated client can be consumed for API automation.

This approach satisfies the core objective of demonstrating **OpenAPI-driven API client generation and test automation**.

## OpenAPI Client Generation & Git Workflow

```text
                    ┌──────────────────────────────┐
                    │ Analyze Actual API Response  │
                    │                              │
                    │ Identify missing/incorrect   │
                    │ fields in the API response   │
                    └──────────────┬───────────────┘
                                   │
                                   ▼
                    ┌──────────────────────────────┐
                    │ Update YAML Schema            │
                    │                              │
                    │ Modify fakeStoreAPI.yml      │
                    │ to match the actual API     │
                    │ request/response structure   │
                    └──────────────┬───────────────┘
                                   │
                                   ▼
                    ┌──────────────────────────────┐
                    │ Generate Java Client          │
                    │                              │
                    │ openapi-generator generate   │
                    └──────────────┬───────────────┘
                                   │
                                   ▼
                    ┌──────────────────────────────┐
                    │ Review Generated Changes     │
                    │                              │
                    │ git status                  │
                    │ git diff                    │
                    └──────────────┬───────────────┘
                                   │
                                   ▼
                    ┌──────────────────────────────┐
                    │ Remove Unrelated Changes    │
                    │                              │
                    │ git restore <files>         │
                    └──────────────┬───────────────┘
                                   │
                                   ▼
                    ┌──────────────────────────────┐
                    │ Run API Tests                │
                    │                              │
                    │ mvn -Dtest=... test         │
                    └──────────────┬───────────────┘
                                   │
                                   ▼
                           ┌───────────────┐
                           │ Tests Pass?   │
                           └───────┬───────┘
                              Yes │ No
                                  │
                     ┌────────────┘
                     │
                     │ No → Fix YAML / Code
                     │          │
                     │          └──→ Run Tests Again
                     │
                     ▼
             ┌─────────────────────────┐
             │ Stage Changes           │
             │                         │
             │ git add <files>         │
             └────────────┬────────────┘
                          │
                          ▼
             ┌─────────────────────────┐
             │ Review Staged Changes   │
             │                         │
             │ git diff --cached       │
             └────────────┬────────────┘
                          │
                          ▼
             ┌─────────────────────────┐
             │ Commit Changes          │
             │                         │
             │ git commit -m "..."     │
             └────────────┬────────────┘
                          │
                          ▼
             ┌─────────────────────────┐
             │ Push to GitHub          │
             │                         │
             │ git push                │
             └─────────────────────────┘
```

### Step 1: Update the YAML Schema

Before generating the Java client, **compare the actual API response with the OpenAPI YAML schema**.

If the actual API response contains fields that are missing from the YAML schema, update:

```text
fakeStoreAPI.yml
```

For example, if the API returns:

```json
{
  "id": 1,
  "username": "johnd",
  "email": "john@example.com",
  "phone": "1-570-236-7033",
  "name": {
    "firstname": "John",
    "lastname": "Doe"
  },
  "address": {
    "city": "kilcoole",
    "street": "new road",
    "number": 7682,
    "zipcode": "12926-3874",
    "geolocation": {
      "lat": "-37.3159",
      "long": "81.1496"
    }
  },
  "__v": 0
}
```

The corresponding schemas should be defined in `fakeStoreAPI.yml`.

### Step 2: Generate the Java Client

After updating the YAML schema:

```bash
openapi-generator generate \
  -i fakeStoreAPI.yml \
  -g java \
  -o . \
  --skip-validate-spec
```

### Step 3: Review Generated Changes

```bash
git status
```

```bash
git --no-pager diff --stat
```

Review important generated files:

```bash
git --no-pager diff -- src/main/java/org/openapitools/client/model/User.java
```

### Step 4: Run Tests

Run the specific test:

```bash
mvn -Dtest=FakeStoreUsersTest test
```

Then run the complete suite:

```bash
mvn test
```

### Step 5: Stage and Review

```bash
git add <files>
```

Review staged changes:

```bash
git --no-pager diff --cached --stat
```

```bash
git --no-pager diff --cached
```

### Step 6: Commit

```bash
git commit -m "Update User model for FakeStore API response"
```

### Step 7: Push

```bash
git push
```

> **Important:** The YAML schema should be updated first. The generated Java client should be treated as a result of the OpenAPI specification, not manually modified as the source of truth.
