# Chapter 1: OpenAPI Specification Fundamentals

## 1. What is OpenAPI Specification (OAS)?

OpenAPI Specification (OAS) is a standard, language-agnostic format used to describe REST APIs. It acts as a contract between API providers and API consumers by clearly defining:

* Available endpoints
* HTTP methods
* Request parameters
* Request body schemas
* Response schemas
* Authentication mechanisms
* Error responses

OpenAPI specifications are commonly written in **YAML** or **JSON**.

### Example

```yaml
openapi: 3.1.0

paths:
  /products:
    get:
      summary: Get all products
```

OpenAPI can be thought of as:

* An API Blueprint
* An API Contract
* A Documentation Source

---

## 2. Why Do We Need OpenAPI Specification?

Without OpenAPI:

```text
Backend Team
      ↓
Creates API
      ↓
Shares documentation manually
      ↓
Consumers implement clients manually
      ↓
Documentation becomes outdated
```

Common challenges include:

* Documentation mismatch
* Client implementation errors
* Slow onboarding
* Difficult maintenance

With OpenAPI:

```text
OpenAPI Specification
          ↓
Documentation
          ↓
Code Generation
          ↓
Mocking
          ↓
Validation
```

OpenAPI serves as a **single source of truth** for API development and testing.

---

## 3. Problems Solved by OpenAPI

### Documentation Drift

When an API changes but documentation is not updated, consumers can break unexpectedly.

Example:

Old Response:

```json
{
  "productName": "TV"
}
```

Updated Response:

```json
{
  "title": "TV"
}
```

OpenAPI helps keep implementation and documentation synchronized.

### Manual SDK Development

Without OpenAPI, developers must manually write:

* HTTP requests
* JSON serialization
* JSON deserialization
* Error handling

OpenAPI Generator can automate this process.

### Lack of Standardization

Different teams may document APIs differently. OpenAPI provides:

* Common structure
* Common schema definitions
* Standard tooling

---

## 4. Structure of an OpenAPI Document

### Example

```yaml
openapi: 3.1.0

info:
  title: FakeStore API
  version: 1.0.0

servers:
  - url: https://fakestoreapi.com

paths:
  /products:
    get:
      summary: Get products

components:
  schemas:
    Product:
      type: object
```

### Main Sections

| Section    | Purpose                    |
| ---------- | -------------------------- |
| openapi    | OpenAPI version            |
| info       | API metadata               |
| servers    | API base URLs              |
| paths      | API endpoints              |
| components | Reusable schemas           |
| security   | Authentication definitions |

---

## 5. What is an API Contract?

An API contract defines the agreement between API consumers and providers.

### Example

**Request**

```http
POST /products
```

```json
{
  "title": "Laptop"
}
```

**Response**

```json
{
  "id": 21,
  "title": "Laptop"
}
```

Both provider and consumer agree on this structure.

---

## 6. OpenAPI Generator

OpenAPI Generator is an open-source tool used to generate code from an OpenAPI specification.

### Example Command

```bash
openapi-generator generate \
  -i fakeStoreAPI.yml \
  -g java \
  -o generated-client
```

### Generated Artifacts

* ApiClient.java
* ProductsApi.java
* UsersApi.java
* CartsApi.java
* AuthApi.java
* Model classes

---

## 7. Why Generate Code Instead of Writing It Manually?

### Manual Approach

Developers must implement:

* HTTP communication
* Serialization
* Deserialization
* Header management
* Error handling

### Generated Client Approach

```java
ProductsApi productsApi = new ProductsApi();

Product product = productsApi.getProductById(1);
```

Benefits:

* Less coding effort
* Faster development
* Improved consistency
* Reduced maintenance

---

## 8. Importance of Separating Generated Code

Recommended Structure:

```text
openAPIDemo/
│
├── fakeStoreAPI.yml
├── README.md
│
├── generated-client/
│   └── Generated OpenAPI code
│
└── src/test/java/
    └── Custom automation tests
```

Generated code can be regenerated at any time.

Keeping custom code separate prevents accidental loss of manual changes.

---

## 9. What is Client Code?

Client code is generated code used to consume APIs.

### Example

```java
ProductsApi productsApi = new ProductsApi();

Product product = productsApi.getProductById(1);
```

Instead of manually creating:

* HTTP requests
* URLs
* JSON parsing logic

the generated client handles these responsibilities automatically.

---

## 10. Server Code Generation

OpenAPI can also generate server-side code.

Supported frameworks include:

* Spring Boot
* Node.js
* Python
* .NET

Example:

```bash
openapi-generator generate \
  -i api.yml \
  -g spring \
  -o generated-server
```

Generated code typically includes:

* Controllers
* Models
* Endpoint interfaces

---

## 11. What is a Mock Server?

A mock server simulates API behavior based on an OpenAPI specification.

```text
Frontend Team
      ↓
Mock Server
      ↓
Example Responses
```

This allows development to continue before backend implementation is completed.

---

## 12. Benefits of Mock Servers

Without Mock Server:

```text
Frontend waits for Backend
```

With Mock Server:

```text
Frontend Development
           ↓
       Mock API
           ↑
Backend Development
```

Teams can work independently and in parallel.

---

## 13. Schema Validation

Schemas define expected request and response structures.

Example:

```json
{
  "id": 1,
  "title": "TV",
  "price": 100
}
```

Validation checks:

* Required fields
* Data types
* Formats
* Constraints

---

## 14. Benefits of OpenAPI for QA Engineers

### API Understanding

Quickly understand API functionality from the specification.

### Test Design

Create:

* Positive test cases
* Negative test cases
* Boundary test cases

### Automation

Generate client SDKs instead of manually creating API wrappers.

### Contract Validation

Verify that implementation matches the documented specification.

---

## 15. FakeStoreAPI Assignment Overview

### Positive Test Scenarios

* Get All Products
* Get Product By ID
* Add Product
* Update Product
* Delete Product
* Get All Users
* Get User By ID
* Get All Carts
* Get Cart By ID

Expected Results:

* HTTP 200 Success
* Valid Response Body
* Correct Data Structure

### Negative Test Scenarios

* Invalid Product ID
* Negative Product ID
* Null Request Body
* Missing Mandatory Fields
* Invalid User ID
* Invalid Cart ID

Expected Results:

* Error Responses
* Validation Failures
* Appropriate Status Codes

---

## 16. Why Use TestNG?

TestNG provides:

* Assertions
* Data Providers
* Test Grouping
* Reporting
* Parallel Execution

The generated client performs API operations, while TestNG validates the results.

---

## 17. Jenkins Integration

Pipeline Flow:

```text
GitHub
   ↓
Jenkins
   ↓
Checkout Source Code
   ↓
mvn clean test
   ↓
Execute API Tests
   ↓
Publish Results
```

Benefits:

* Automated execution
* Continuous validation
* Centralized reporting

---

## 18. Jenkins Implementation

Implemented using:

* Jenkins Pipeline
* Jenkinsfile
* Maven Build
* TestNG Execution
* JUnit Report Publishing

Pipeline Stages:

```text
Checkout
   ↓
Build & Test
   ↓
Publish Test Results
```

---

## 19. Importance of OpenAPI in Modern QA Automation

```text
OpenAPI Specification
          ↓
Documentation
          ↓
Mock Servers
          ↓
Code Generation
          ↓
Validation
          ↓
Automation
```

Benefits include:

* Reduced manual coding
* Better documentation accuracy
* Faster onboarding
* Easier maintenance
* Improved collaboration between teams

---

## Chapter Summary

OpenAPI Specification provides a standardized way to describe REST APIs. It serves as a contract between API providers and consumers and enables documentation generation, code generation, mock server creation, validation, and automation. In this project, the FakeStoreAPI OpenAPI specification was used to generate a Java client using OpenAPI Generator, create automated TestNG tests for positive and negative scenarios, and integrate execution with Jenkins. This approach improves maintainability, consistency, and overall automation efficiency.
