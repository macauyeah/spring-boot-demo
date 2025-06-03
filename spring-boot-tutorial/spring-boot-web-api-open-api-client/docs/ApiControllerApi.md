# ApiControllerApi

All URIs are relative to *http://localhost:9000/proxy/8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getRecords**](ApiControllerApi.md#getRecords) | **GET** /api/record/{uuid} |  |
| [**postDateQuery**](ApiControllerApi.md#postDateQuery) | **POST** /api/record |  |



## getRecords

> String getRecords(uuid, filter)



### Example

```java
// Import classes:
import io.github.macauyeah.springboot.tutorial.openapiclient.invoker.ApiClient;
import io.github.macauyeah.springboot.tutorial.openapiclient.invoker.ApiException;
import io.github.macauyeah.springboot.tutorial.openapiclient.invoker.Configuration;
import io.github.macauyeah.springboot.tutorial.openapiclient.invoker.models.*;
import io.github.macauyeah.springboot.tutorial.openapiclient.api.ApiControllerApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:9000/proxy/8080");

        ApiControllerApi apiInstance = new ApiControllerApi(defaultClient);
        String uuid = "uuid_example"; // String | 
        String filter = "filter_example"; // String | 
        try {
            String result = apiInstance.getRecords(uuid, filter);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ApiControllerApi#getRecords");
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
| **uuid** | **String**|  | |
| **filter** | **String**|  | |

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


## postDateQuery

> String postDateQuery(apiDateRequest)



### Example

```java
// Import classes:
import io.github.macauyeah.springboot.tutorial.openapiclient.invoker.ApiClient;
import io.github.macauyeah.springboot.tutorial.openapiclient.invoker.ApiException;
import io.github.macauyeah.springboot.tutorial.openapiclient.invoker.Configuration;
import io.github.macauyeah.springboot.tutorial.openapiclient.invoker.models.*;
import io.github.macauyeah.springboot.tutorial.openapiclient.api.ApiControllerApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:9000/proxy/8080");

        ApiControllerApi apiInstance = new ApiControllerApi(defaultClient);
        ApiDateRequest apiDateRequest = new ApiDateRequest(); // ApiDateRequest | 
        try {
            String result = apiInstance.postDateQuery(apiDateRequest);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ApiControllerApi#postDateQuery");
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
| **apiDateRequest** | [**ApiDateRequest**](ApiDateRequest.md)|  | |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

