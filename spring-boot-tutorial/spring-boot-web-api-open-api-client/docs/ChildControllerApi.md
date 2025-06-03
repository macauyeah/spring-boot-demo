# ChildControllerApi

All URIs are relative to *http://localhost:9000/proxy/8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**directCall**](ChildControllerApi.md#directCall) | **GET** /api/direct |  |
| [**postfix**](ChildControllerApi.md#postfix) | **GET** /api/postfix |  |



## directCall

> String directCall()



### Example

```java
// Import classes:
import io.github.macauyeah.springboot.tutorial.openapiclient.invoker.ApiClient;
import io.github.macauyeah.springboot.tutorial.openapiclient.invoker.ApiException;
import io.github.macauyeah.springboot.tutorial.openapiclient.invoker.Configuration;
import io.github.macauyeah.springboot.tutorial.openapiclient.invoker.models.*;
import io.github.macauyeah.springboot.tutorial.openapiclient.api.ChildControllerApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:9000/proxy/8080");

        ChildControllerApi apiInstance = new ChildControllerApi(defaultClient);
        try {
            String result = apiInstance.directCall();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ChildControllerApi#directCall");
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

**String**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |


## postfix

> String postfix()



### Example

```java
// Import classes:
import io.github.macauyeah.springboot.tutorial.openapiclient.invoker.ApiClient;
import io.github.macauyeah.springboot.tutorial.openapiclient.invoker.ApiException;
import io.github.macauyeah.springboot.tutorial.openapiclient.invoker.Configuration;
import io.github.macauyeah.springboot.tutorial.openapiclient.invoker.models.*;
import io.github.macauyeah.springboot.tutorial.openapiclient.api.ChildControllerApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:9000/proxy/8080");

        ChildControllerApi apiInstance = new ChildControllerApi(defaultClient);
        try {
            String result = apiInstance.postfix();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ChildControllerApi#postfix");
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

**String**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

