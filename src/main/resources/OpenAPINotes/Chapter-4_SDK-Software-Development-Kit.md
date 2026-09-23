# SDK – Software Development Kit

## 1. What is an SDK?

**SDK** stands for **Software Development Kit**.

An SDK is a collection of code, libraries, classes, documentation, and tools that helps developers interact with a service or platform without having to build everything from scratch.

---

## 2. Simple Example

Suppose FakeStore API provides this endpoint:

`GET https://fakestoreapi.com/products/1`

### Without an SDK

You need to manually create and send the HTTP request:

```java
HttpClient client = HttpClient.newHttpClient();

HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://fakestoreapi.com/products/1"))
        .GET()
        .build();

HttpResponse<String> response =
        client.send(request, HttpResponse.BodyHandlers.ofString());
```

You must handle:

* URL creation
* HTTP requests
* JSON parsing
* Error handling
* Serialization
* Deserialization

### With an SDK

You can simply use Java methods:

```java
ProductsApi productsApi = new ProductsApi();

Product product = productsApi.getProductById(1);
```

The SDK handles the HTTP communication, request construction, serialization, deserialization, and response processing behind the scenes.

---

# 3. SDK in Your OpenAPI Project

In your project, you ran:

```bash
openapi-generator generate \
-i fakeStoreAPI.yml \
-g java \
-o generated-client
```

OpenAPI Generator created a **Java SDK** for the FakeStore API.

The generated project contains components such as:

```text
generated-client/
│
├── ApiClient.java
├── ApiException.java
├── Configuration.java
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

This generated code acts as the **Java SDK** for the FakeStore API.

---

# 4. What Does the SDK Provide?

## 4.1 API Methods

The SDK provides Java methods corresponding to API operations.

For example:

```java
ProductsApi productsApi = new ProductsApi();

productsApi.getAllProducts();

productsApi.getProductById(1);

productsApi.addProduct(product);

productsApi.updateProduct(1, product);

productsApi.deleteProduct(1);
```

Instead of manually constructing HTTP requests, you call methods provided by the SDK.

---

## 4.2 Models

The SDK also provides Java model classes that represent API request and response data.

For example, instead of manually creating JSON:

```json
{
  "title": "Laptop",
  "price": 999
}
```

You can work with a Java object:

```java
Product product = new Product();

product.setTitle("Laptop");
product.setPrice(BigDecimal.valueOf(999));
```

The `Product` class is a model generated from the OpenAPI specification.

---

# 5. Serialization

**Serialization** means converting a Java object into a format such as JSON.

For example:

```text
Java Object
    ↓
Product
    ↓
JSON
```

Example:

```java
Product product = new Product();

product.setTitle("Laptop");
product.setPrice(BigDecimal.valueOf(999));
```

The SDK can serialize the object into JSON similar to:

```json
{
  "title": "Laptop",
  "price": 999
}
```

This JSON can then be sent as part of an HTTP request.

---

# 6. Deserialization

**Deserialization** means converting JSON received from an API into a Java object.

For example:

```json
{
  "id": 1,
  "title": "Laptop"
}
```

The SDK can convert this response into:

```java
Product product
```

You can then access the response using Java methods:

```java
product.getId();
product.getTitle();
```

So the flow is:

```text
JSON Response
      ↓
Deserialization
      ↓
Java Object
      ↓
Product
```

---

# 7. SDK Request/Response Flow

A typical SDK-based API call looks like this:

```text
Test Code
   |
   |  productsApi.getProductById(1)
   ↓
Generated SDK
   |
   |  Creates HTTP Request
   ↓
FakeStore API
   |
   |  JSON Response
   ↓
Generated SDK
   |
   |  Deserialization
   ↓
Product Java Object
   |
   ↓
Test Assertions
```

This allows the test code to work primarily with Java methods and objects rather than raw HTTP requests and JSON.

---

# 8. Real-World SDK Examples

SDKs are commonly provided by cloud platforms and third-party services.

### AWS SDK

For example, AWS provides SDKs that allow applications to interact with AWS services using programming-language APIs.

Conceptually:

```java
S3Client s3 = S3Client.create();
```

You do not need to manually construct every AWS REST request.

---

### Google APIs

Google provides client libraries for many of its APIs.

For example:

```java
Drive drive = new Drive.Builder(...);
```

The client library handles much of the underlying API communication.

---

### Stripe SDK

Stripe provides SDKs that allow applications to interact with Stripe services through programming-language constructs.

For example:

```java
Customer customer = Customer.create(params);
```

Instead of manually constructing every HTTP request, the SDK provides higher-level methods.

---

# 9. Why SDKs Are Important for QA Engineers

Without an SDK, an API automation framework may need to handle:

```text
HTTP Requests
      ↓
JSON Creation
      ↓
HTTP Response
      ↓
JSON Parsing
      ↓
Model Creation
      ↓
Error Handling
      ↓
Assertions
```

With an SDK:

```text
API Method
    ↓
Java Object
    ↓
Assertions
```

This allows QA engineers to focus more on **test logic** instead of repeatedly implementing low-level API communication.

### Benefits

* Faster test development
* Cleaner test code
* Less boilerplate code
* Easier maintenance
* Type-safe Java models
* Reusable API methods
* Centralized request/response handling

---

# 10. SDK vs Raw HTTP Requests

| Raw HTTP Approach                 | SDK Approach                             |
| --------------------------------- | ---------------------------------------- |
| Build HTTP request manually       | Call a Java method                       |
| Create URLs manually              | SDK handles endpoint construction        |
| Create JSON manually              | Use Java model objects                   |
| Parse JSON manually               | SDK deserializes responses               |
| Handle serialization manually     | SDK handles serialization                |
| Implement request handling        | SDK provides reusable API methods        |
| More boilerplate                  | Less boilerplate                         |
| Test code focuses on HTTP details | Test code focuses on business/test logic |

---

# 11. SDK vs Generated Client

In the context of your OpenAPI project, these terms are closely related.

### Generated Client

The **generated client** is the code produced by OpenAPI Generator from an OpenAPI specification.

For example:

```bash
openapi-generator generate \
-i fakeStoreAPI.yml \
-g java \
-o generated-client
```

### SDK

When this generated client provides the API classes, models, configuration, serialization/deserialization, and supporting utilities needed to interact with the API, it effectively serves as a **Java SDK** for that API.

So:

```text
OpenAPI Specification
        ↓
OpenAPI Generator
        ↓
Generated Java Client
        ↓
Java SDK
        ↓
Automation Tests
```

---

# 12. Why This Is Useful in Your Project

In your FakeStore API automation project, instead of writing code such as:

```java
HttpClient client = HttpClient.newHttpClient();

HttpRequest request = ...
```

for every API operation, you can use the generated classes:

```java
ProductsApi productsApi = new ProductsApi();

Product product = productsApi.getProductById(1);
```

Your tests can then concentrate on validating the API behavior:

```java
Assert.assertNotNull(product);
Assert.assertEquals(product.getId(), Long.valueOf(1));
```

This separates the **API communication layer** from the **test/validation layer**.

---

# 13. Interview Answer

> **SDK (Software Development Kit)** is a set of libraries, classes, models, utilities, and tools that allows developers or testers to interact with a system through programming-language constructs instead of manually implementing raw HTTP requests.
>
> In my FakeStoreAPI assignment, I used OpenAPI Generator to generate a Java client from the OpenAPI specification. The generated client contains API classes such as `ProductsApi`, `UsersApi`, `CartsApi`, and `AuthApi`, along with model classes such as `Product`, `User`, and `Cart`.
>
> I used these generated classes in my automation tests to call API operations and work with Java objects instead of manually creating HTTP requests and parsing JSON responses.

---

# 14. Key Takeaway

The main idea is:

```text
Without SDK
───────────

Test Code
   ↓
HTTP Request
   ↓
JSON
   ↓
API
   ↓
JSON Response
   ↓
Manual Parsing


With SDK
────────

Test Code
   ↓
API Method
   ↓
SDK
   ↓
API
   ↓
SDK
   ↓
Java Object
   ↓
Test Assertions
```

**In simple terms:**

> An SDK gives you ready-to-use programming interfaces for interacting with a service, so you can work with methods and objects instead of dealing with the low-level HTTP and JSON details yourself.
