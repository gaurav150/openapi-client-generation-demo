# AuthApi

All URIs are relative to *https://fakestoreapi.com*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**loginUser**](AuthApi.md#loginUser) | **POST** /auth/login | Login |


<a id="loginUser"></a>
# **loginUser**
> LoginResponse loginUser(login)

Login

Authenticate a user.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AuthApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://fakestoreapi.com");

    AuthApi apiInstance = new AuthApi(defaultClient);
    Login login = new Login(); // Login | 
    try {
      LoginResponse result = apiInstance.loginUser(login);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AuthApi#loginUser");
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
| **login** | [**Login**](Login.md)|  | |

### Return type

[**LoginResponse**](LoginResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Login successful |  -  |
| **400** | Bad request |  -  |

