package org.openapitools.client;


import org.openapitools.client.api.UsersApi;
import org.openapitools.client.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class FakeStoreUsersTest {
    private UsersApi usersApi;

    @BeforeClass
    public void setUp() {
        ApiClient client = Configuration.getDefaultApiClient();
        client.setBasePath("https://fakestoreapi.com");

        usersApi = new UsersApi(client);
    }

    @Test(description = "Verify that user get all Users Details")
    public void getUsersDetails() {
        try {
            ApiResponse<List<User>> response =
                    usersApi.getAllUsersWithHttpInfo();

            // Validate response is not null
            Assert.assertNotNull(
                    response,
                    "Response should not be null"
            );

            // Fetch and validate HTTP status code
            int statusCode = response.getStatusCode();

            Assert.assertEquals(
                    statusCode,
                    200,
                    "Expected HTTP status code should be 200"
            );

            // Validate user data
            Assert.assertNotNull(
                    response.getData(),
                    "User data should not be null"
            );

            Assert.assertFalse(
                    response.getData().isEmpty(),
                    "User data should be present"
            );

            System.out.println("Status Code: " + statusCode);
            System.out.println("Total Users: " + response.getData().size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
