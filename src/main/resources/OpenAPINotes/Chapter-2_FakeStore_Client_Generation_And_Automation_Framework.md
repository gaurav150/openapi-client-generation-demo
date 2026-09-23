
# Chapter 2: FakeStore API Client Generation and Automation Framework Implementation

## 2.1 Task Overview

### Objective

The objective of this task was to work with the OpenAPI Specification of the FakeStore API and generate a Java API client using an OpenAPI-compatible code generation tool.

### Requirements

* Generate API client code from OpenAPI Specification.
* Do not manually implement API clients.
* Keep generated code separate from custom automation code.
* Implement positive and negative test scenarios.
* Create a reusable automation framework.
* Document generation and execution steps.

The implementation was completed using:

* Java
* OpenAPI Generator
* Maven
* TestNG
* OpenAPI 3.1 YAML Specification

## 2.2 Technology Stack

| Component            | Technology        |
| -------------------- | ----------------- |
| API Specification    | OpenAPI 3.1       |
| Specification Format | YAML              |
| Code Generator       | OpenAPI Generator |
| Programming Language | Java              |
| Build Tool           | Maven             |
| Test Framework       | TestNG            |
| CI Tool              | Jenkins           |

## 2.3 End-to-End Workflow

```text
OpenAPI Specification
          ↓
     YAML File
          ↓
 OpenAPI Generator
          ↓
 Generated Java Client
          ↓
 TestNG Automation Layer
          ↓
      Maven Build
          ↓
    Jenkins Pipeline
```

## 2.4 Generating the Client Code

### Generation Command

```bash
openapi-generator generate \
-i fakeStoreAPI.yml \
-g java \
-o generated-client
```

### Command Explanation

| Option           | Description                 |
| ---------------- | --------------------------- |
| generate         | Generates source code       |
| -i               | Input OpenAPI specification |
| fakeStoreAPI.yml | OpenAPI YAML file           |
| -g java          | Generates Java client       |
| -o               | Output directory            |
| generated-client | Location of generated code  |

## 2.5 Generated Components

The OpenAPI Generator automatically created:

### API Classes

```text
ProductsApi
UsersApi
CartsApi
AuthApi
```

### Model Classes

```text
Product
User
Cart
CartProduct
Rating
```

### Supporting Classes

```text
ApiClient
ApiResponse
ApiException
Configuration
```

These classes handle:

* HTTP communication
* Serialization
* Deserialization
* Request creation
* Response processing

## 2.6 API Classes vs Model Classes

### API Classes

API classes contain methods corresponding to API operations.

Example:

```java
ProductsApi productsApi = new ProductsApi();

Product product = productsApi.getProductById(1);
```

### Model Classes

Model classes represent request and response data structures.

Example:

```java
Product product = new Product();

product.setTitle("Test Product");
```

## 2.7 Project Structure

```text
project-root
│
├── fakeStoreAPI.yml
│
├── generated-client
│   ├── api
│   ├── model
│   └── supporting classes
│
├── src
│   └── test
│       └── java
│           └── custom tests
│
└── pom.xml
```

## 2.8 Why Generated Code Was Kept Separate

Generated code can be regenerated whenever the OpenAPI specification changes.

Keeping custom code inside generated folders may cause:

```text
Regenerate Client
        ↓
Generated Files Replaced
        ↓
Custom Changes Lost
```

Therefore:

* Generated code remains untouched.
* Custom automation code remains safe.
* Maintenance becomes easier.

## 2.9 Testing the Generated Client

A separate TestNG layer was created to consume the generated API client.

Example:

```java
ProductsApi productsApi = new ProductsApi();

List<Product> products =
        productsApi.getAllProducts();

Assert.assertNotNull(products);
Assert.assertFalse(products.isEmpty());
```

The automation layer validates:

* Status codes
* Response objects
* Response fields
* Error handling
* API behaviour

## 2.10 Positive Test Scenarios

Implemented positive scenarios include:

### Products API

* Get All Products
* Get Product By Id
* Add Product
* Update Product
* Delete Product

### Users API

* Get All Users
* Get User By Id
* Create User

### Carts API

* Get All Carts
* Get Cart By Id
* Add Cart
* Update Cart
* Delete Cart

## 2.11 Negative Test Scenarios

Implemented negative scenarios include:

* Invalid Product ID
* Negative Product ID
* Invalid User ID
* Invalid Cart ID
* Missing Required Request Body
* Missing Mandatory Fields
* Null Inputs

These tests verify that APIs handle invalid input gracefully.

## 2.12 Cart Schema Modeling Challenge

### Initial Issue

Initially, the Cart schema was incorrectly modeled using the Product schema.

```text
Cart
 └── Products
      └── Product
```

However, the actual API response returned:

```json
{
  "productId": 1,
  "quantity": 2
}
```

instead of a complete Product object.

### Resulting Problem

```java
Product cannot be converted to CartProduct
```

### Solution

A dedicated schema was created:

```yaml
CartProduct:
  type: object
  properties:
    productId:
      type: integer
    quantity:
      type: integer
```

The client was regenerated and the issue was resolved.

## 2.13 Build and Execution

### Build

```bash
mvn clean install
```

### Execute Tests

```bash
mvn clean test
```

Maven Surefire Plugin executes the TestNG test suite.

## 2.14 Jenkins Integration

The project was integrated with Jenkins using Pipeline as Code.

### Pipeline Stages

```text
GitHub
   ↓
Checkout
   ↓
Build
   ↓
Execute Tests
   ↓
Publish Reports
```

### Jenkinsfile

The pipeline automatically:

* Pulls source code from GitHub
* Executes Maven commands
* Runs TestNG tests
* Publishes test results

## 2.15 Final Results

Successful execution:

```text
Tests Run : 59
Failures  : 0
Errors    : 0
Skipped   : 0
```

Build Status:

```text
BUILD SUCCESS
```

## 2.16 Key Learnings

Through this implementation I learned:

* OpenAPI Specification Design
* YAML-based API Contract Definition
* Client Code Generation
* API Schema Modeling
* TestNG Integration
* Maven Build Management
* Jenkins Pipeline Setup
* Positive and Negative API Testing
* Contract-Driven API Automation

## 2.17 Conclusion

This implementation demonstrated how an OpenAPI Specification can be used as a single source of truth to generate a Java API client, automate API testing, and integrate the solution with CI/CD tools such as Jenkins. The approach reduced manual coding effort, improved maintainability, and ensured alignment with the API contract.

This chapter can be saved as **`Chapter-2_FakeStore_Client_Generation_And_Automation_Framework.md`**.
