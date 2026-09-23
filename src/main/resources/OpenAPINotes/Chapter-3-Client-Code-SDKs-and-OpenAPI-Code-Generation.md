
# Chapter 3: Client Code, SDKs, and OpenAPI Code Generation

## 3.1 What is Client Code?

### Definition

Client code is a software component that allows an application or test framework to communicate with an API.

It acts as a bridge between your application code and the API server.

Instead of manually creating HTTP requests and processing responses, developers can invoke methods exposed by the generated client.

### Example

#### Without Client Code

```java
given()
    .baseUri("https://fakestoreapi.com")
.when()
    .get("/products/1");
```

The developer must manually handle:

* URL construction
* HTTP methods
* Request headers
* Request payloads
* Response parsing

#### With Generated Client Code

```java
ProductsApi productsApi = new ProductsApi();

Product product = productsApi.getProductById(1);
```

The generated client automatically handles:

* URL creation
* HTTP request execution
* Serialization
* Deserialization
* Error handling

### Behind the Scenes

When the following method is executed:

```java
Product product = productsApi.getProductById(1);
```

The generated client:

1. Creates the API URL
2. Sends the HTTP request
3. Receives the response
4. Converts JSON into a Java object
5. Returns the object to the caller

---

## 3.2 Is RestAssured Also a Client?

### Answer

Yes, but at a different level.

RestAssured is a generic HTTP testing library that allows users to create and execute HTTP requests.

Example:

```java
given()
    .baseUri("https://fakestoreapi.com")
.when()
    .get("/products/1");
```

With RestAssured, the tester is responsible for:

* Defining endpoints
* Creating requests
* Managing payloads
* Parsing responses

Generated OpenAPI clients provide higher-level abstractions:

```java
productsApi.getProductById(1);
```

The generated client hides the HTTP implementation details and exposes business-friendly methods.

---

## 3.3 Why Do We Need Client Code?

### Productivity

Without generated clients, every API interaction requires:

* Request construction
* Endpoint management
* Response parsing
* Error handling

Generated clients simplify API interactions:

```java
productsApi.getProductById(1);
```

This significantly reduces development effort.

### Type Safety

Generated clients return strongly typed objects.

```java
Product product = productsApi.getProductById(1);

String title = product.getTitle();
```

Advantages:

* Compile-time validation
* IDE auto-completion
* Reduced runtime errors

### Maintainability

If an API schema changes:

**Old Response**

```json
{
  "productName": "TV"
}
```

**New Response**

```json
{
  "title": "TV"
}
```

The process becomes:

```text
Update OpenAPI Specification
            ↓
Regenerate Client
            ↓
Recompile Project
```

This is easier than updating numerous manually written API calls.

### Reusability

The same generated client can be shared across:

* Backend teams
* Frontend teams
* QA teams
* Automation engineers

This ensures consistency across projects.

---

## 3.4 How Does Client Generation Work?

Consider the following OpenAPI definition:

```yaml
/products/{id}:
  get:
    summary: Get product by ID
```

OpenAPI Generator converts it into a Java method:

```java
public Product getProductById(Integer id)
```

Thus:

```text
OpenAPI Endpoint
        ↓
Code Generation
        ↓
Java Method
```

The API contract becomes executable code.

---

## 3.5 Generated Components in the FakeStore API Project

### Project Structure

```text
generated-client/
│
├── ApiClient.java
├── Configuration.java
├── ApiException.java
│
├── api/
│   ├── ProductsApi.java
│   ├── UsersApi.java
│   ├── CartsApi.java
│   └── AuthApi.java
│
└── model/
    ├── Product.java
    ├── User.java
    └── Cart.java
```

### ApiClient.java

Responsible for:

* HTTP communication
* Authentication
* Request execution
* Serialization and deserialization

### ProductsApi.java

Contains API operations such as:

```java
getAllProducts()
getProductById()
addProduct()
updateProduct()
deleteProduct()
```

### Product.java

Represents the API response as a Java object.

Example:

```json
{
  "id": 1,
  "title": "Laptop"
}
```

becomes:

```java
Product product
```

---

## 3.6 Why Keep Generated Code Separate?

Generated code can be regenerated at any time.

Recommended structure:

```text
generated-client/
    Generated Code

src/test/java/
    Custom Test Code
```

Benefits:

* Easier maintenance
* Prevents accidental modifications
* Safe regeneration of clients

### Risk of Mixing Code

```text
Generated Code
      +
Custom Logic
      ↓
Regenerate Client
      ↓
Custom Changes Lost
```

Keeping them separate avoids this issue.

---

## 3.7 What is an SDK?

### Definition

SDK stands for **Software Development Kit**.

An SDK is a collection of tools, libraries, documentation, and utilities that help developers interact with a platform or service.

### API SDK Example

An OpenAPI-generated Java client is considered an SDK because it provides:

* API methods
* Data models
* Configuration classes
* Authentication support
* Error handling

Example:

```java
ProductsApi api = new ProductsApi();

Product product = api.getProductById(1);
```

The SDK hides all HTTP communication details.

---

## 3.8 What Can Be Generated from OpenAPI?

OpenAPI specifications can generate multiple artifacts.

### Documentation

Tools:

* Swagger UI
* ReDoc

### Client SDKs

Languages:

* Java
* Python
* JavaScript
* TypeScript
* C#
* Go

### Server Stubs

Frameworks:

* Spring Boot
* Node.js
* ASP.NET

### Mock Servers

Tools:

* Prism
* WireMock
* SwaggerHub

### Validation Schemas

Used for:

* Request validation
* Response validation

---

## 3.9 Contract-First (Design-First) Development

### Traditional Approach

```text
Implement API
      ↓
Write Documentation
```

Problems:

* Documentation drift
* Miscommunication
* Integration issues

### Contract-First Approach

```text
Design API
      ↓
Create OpenAPI Specification
      ↓
Review and Approve Contract
      ↓
Generate Code
      ↓
Implement API
```

Advantages:

* Clear expectations
* Reduced misunderstandings
* Faster parallel development
* Better API governance

**Note:** Contract-First Development and Design-First Development are generally considered the same concept.

---

## 3.10 Benefits for QA Engineers

### Faster API Understanding

QA engineers can understand APIs directly from the specification.

Key sections:

```yaml
paths:
components:
schemas:
```

### Faster Test Automation

```text
OpenAPI Specification
          ↓
Generated Client
          ↓
Automation Framework
          ↓
Test Execution
```

### Contract Validation

Verify that actual responses match the API contract.

```text
Actual Response
       ==
OpenAPI Specification
```

### Better Test Design

OpenAPI specifications provide:

* Required fields
* Optional fields
* Data types
* Constraints
* Status codes

These details help create:

* Positive test scenarios
* Negative test scenarios
* Boundary test scenarios

---

## Chapter Summary

Client code is generated code that simplifies communication with APIs by exposing strongly typed methods instead of requiring manual HTTP requests. OpenAPI Generator uses an OpenAPI Specification to create client SDKs, models, and supporting classes automatically. Keeping generated code separate from custom automation code improves maintainability and allows safe regeneration. OpenAPI also supports documentation generation, mock servers, validation, and server stub generation. Through Contract-First Development, teams can define and agree on API behavior before implementation, improving collaboration and reducing integration issues. For QA engineers, OpenAPI accelerates API understanding, test design, automation development, and contract validation.
