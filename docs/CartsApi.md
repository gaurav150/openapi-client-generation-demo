# CartsApi

All URIs are relative to *https://fakestoreapi.com*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**addCart**](CartsApi.md#addCart) | **POST** /carts | Add a new cart |
| [**deleteCart**](CartsApi.md#deleteCart) | **DELETE** /carts/{id} | Delete a cart |
| [**getAllCarts**](CartsApi.md#getAllCarts) | **GET** /carts | Get all carts |
| [**getCartById**](CartsApi.md#getCartById) | **GET** /carts/{id} | Get a single cart |
| [**updateCart**](CartsApi.md#updateCart) | **PUT** /carts/{id} | Update a cart |


<a id="addCart"></a>
# **addCart**
> Cart addCart(cart)

Add a new cart

Create a new cart.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CartsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://fakestoreapi.com");

    CartsApi apiInstance = new CartsApi(defaultClient);
    Cart cart = new Cart(); // Cart | 
    try {
      Cart result = apiInstance.addCart(cart);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CartsApi#addCart");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **cart** | [**Cart**](Cart.md)|  | |

### Return type

[**Cart**](Cart.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Cart created successfully |  -  |
| **400** | Bad request |  -  |

<a id="deleteCart"></a>
# **deleteCart**
> deleteCart(id)

Delete a cart

Delete a specific cart by ID.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CartsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://fakestoreapi.com");

    CartsApi apiInstance = new CartsApi(defaultClient);
    Integer id = 56; // Integer | 
    try {
      apiInstance.deleteCart(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling CartsApi#deleteCart");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Integer**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Cart deleted successfully |  -  |
| **400** | Bad request |  -  |

<a id="getAllCarts"></a>
# **getAllCarts**
> List&lt;Cart&gt; getAllCarts()

Get all carts

Retrieve a list of all available carts.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CartsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://fakestoreapi.com");

    CartsApi apiInstance = new CartsApi(defaultClient);
    try {
      List<Cart> result = apiInstance.getAllCarts();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CartsApi#getAllCarts");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;Cart&gt;**](Cart.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |
| **400** | Bad request |  -  |

<a id="getCartById"></a>
# **getCartById**
> Cart getCartById(id)

Get a single cart

Retrieve details of a specific cart by ID.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CartsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://fakestoreapi.com");

    CartsApi apiInstance = new CartsApi(defaultClient);
    Integer id = 56; // Integer | 
    try {
      Cart result = apiInstance.getCartById(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CartsApi#getCartById");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Integer**|  | |

### Return type

[**Cart**](Cart.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |
| **400** | Bad request |  -  |

<a id="updateCart"></a>
# **updateCart**
> Cart updateCart(id, cart)

Update a cart

Update an existing cart by ID.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CartsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://fakestoreapi.com");

    CartsApi apiInstance = new CartsApi(defaultClient);
    Integer id = 56; // Integer | 
    Cart cart = new Cart(); // Cart | 
    try {
      Cart result = apiInstance.updateCart(id, cart);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CartsApi#updateCart");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Integer**|  | |
| **cart** | [**Cart**](Cart.md)|  | |

### Return type

[**Cart**](Cart.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Cart updated successfully |  -  |
| **400** | Bad request |  -  |

