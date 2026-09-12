package org.openapitools.client;

import org.jetbrains.annotations.NotNull;
import org.openapitools.client.api.UsersApi;
import org.openapitools.client.model.Name;
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
            Assert.fail("Get All Users API call failed: " + e.getMessage());
        }
    }

    // Negative test cases for GET User API with invalid user IDs
    @Test(description = "Verify GET user API with zero user ID")
    public void getUserWithZeroId() {
        try {
            int userId = 0;

            User response = usersApi.getUserById(userId);

            System.out.println("User ID: " + userId);
            System.out.println("Response: " + response);

            Assert.assertNull(
                    response,
                    "Response should be null for invalid user ID"
            );

        } catch (Exception e) {
            System.out.println("Expected behavior for invalid user ID: " + e.getMessage());
        }
    }

    @Test(description = "Verify GET user API with negative user ID")
    public void getUserWithNegativeId() {
        try {
            int userId = -1;

            User response = usersApi.getUserById(userId);

            System.out.println("User ID: " + userId);
            System.out.println("Response: " + response);

            Assert.assertNull(
                    response,
                    "Response should be null for negative user ID"
            );

        } catch (Exception e) {
            System.out.println("Expected behavior for invalid user ID: " + e.getMessage());
        }
    }

    @Test(description = "Verify GET user API with non-existing user ID")
    public void getUserWithNonExistingId() {
        try {
            int userId = 9999;

            User response = usersApi.getUserById(userId);

            System.out.println("User ID: " + userId);
            System.out.println("Response: " + response);

            Assert.assertNull(
                    response,
                    "Response should be null for non-existing user ID"
            );

        } catch (Exception e) {
            System.out.println("Expected behavior for non-existing user ID: " + e.getMessage());
        }
    }

    @Test(description = "Verify GET user API returns expected response for invalid user ID")
    public void getUserWithInvalidIdAndStatusCode() {
        try {
            int userId = 9999;

            ApiResponse<User> response =
                    usersApi.getUserByIdWithHttpInfo(userId);

            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response Data: " + response.getData());

            Assert.assertNotNull(
                    response,
                    "API response should not be null"
            );

            Assert.assertEquals(
                    response.getStatusCode(),
                    200,
                    "Expected status code should be 200 for FakeStoreAPI"
            );

            Assert.assertNull(
                    response.getData(),
                    "Response data should be null for non-existing user"
            );

        } catch (Exception e) {
            System.out.println("Expected exception: " + e.getMessage());
        }
    }


    @Test
    public void testName() {
        Name name = new Name();

        name.setFirstname("Rohit");
        name.setLastname("Kumar");

        Assert.assertNotNull(name);
        Assert.assertEquals(name.getFirstname(), "Rohit");
        Assert.assertEquals(name.getLastname(), "Kumar");
    }

    // Positive test case for POST User API with valid request data
    @Test(description = "Verify User added successfully")
    public void addUserSuccessfully() {
        try {
            String userFirstName = "Rohit";
            String userLastName = "Kumar";
            String email = "rohit@gmail.com";
            String password = "qwerty";
            String phone = "1234567890";

            User createUser = getCreateUser(userFirstName, userLastName, email, password, phone);

            // Send POST request
            User response = usersApi.addUser(createUser);

            // validate response
            Assert.assertNotNull(
                    response,
                    "Response should not be null"
            );

            // Validate not null user ID
            Assert.assertNotNull(
                    response.getId(),
                    "User ID should not be null"
            );

            // Printing response details
            System.out.println("User Id: " + response.getId());

        } catch (Exception e) {
            Assert.fail("Post API for user account creation call failed: " + e.getMessage());
        }
    }

    // Negative test cases for POST User API with invalid request data
    @Test(description = "Verify POST user API with empty request body")
    public void addUserWithEmptyRequestBody() {
        try {
            User user = new User();

            User response = usersApi.addUser(user);

            System.out.println("Response: " + response);

            Assert.assertNotNull(
                    response,
                    "Response should not be null"
            );

        } catch (Exception e) {
            System.out.println("Expected exception for empty request body: " + e.getMessage());
        }
    }

    @Test(description = "Verify POST user API with missing email")
    public void addUserWithMissingEmail() {
        try {
            User user = getCreateUser(
                    "Rohit",
                    "Kumar",
                    null,
                    "qwerty",
                    "1234567890"
            );

            User response = usersApi.addUser(user);

            System.out.println("Response: " + response);

            Assert.assertNotNull(
                    response,
                    "Response should not be null"
            );

        } catch (Exception e) {
            System.out.println("Expected exception for missing email: " + e.getMessage());
        }
    }

    @Test(description = "Verify POST user API with missing password")
    public void addUserWithMissingPassword() {
        try {
            User user = getCreateUser(
                    "Rohit",
                    "Kumar",
                    "rohit@gmail.com",
                    null,
                    "1234567890"
            );

            User response = usersApi.addUser(user);

            System.out.println("Response: " + response);

            Assert.assertNotNull(
                    response,
                    "Response should not be null"
            );

        } catch (Exception e) {
            System.out.println("Expected exception for missing password: " + e.getMessage());
        }
    }

    @Test(description = "Verify POST user API with missing phone")
    public void addUserWithMissingPhone() {
        try {
            User user = getCreateUser(
                    "Rohit",
                    "Kumar",
                    "rohit@gmail.com",
                    "qwerty",
                    null
            );

            User response = usersApi.addUser(user);

            System.out.println("Response: " + response);

            Assert.assertNotNull(
                    response,
                    "Response should not be null"
            );

        } catch (Exception e) {
            System.out.println("Expected exception for missing phone: " + e.getMessage());
        }
    }


    @Test(description = "Verify retrieval of a single user")
    public void verifySingleUserDetails() {
        try {
            int userId = 2;
            int statusCode = 200;
            ApiResponse<User> localVarResp = usersApi.getUserByIdWithHttpInfo(userId);
            User response = localVarResp.getData();

            // validate userDetails is not null
            Assert.assertNotNull(response, "user details should not be null");

            // Validating userId should match
            Assert.assertEquals(response.getId(), userId,
                    "User id should match requested User Id");

            // validate status code should match
            Assert.assertEquals(localVarResp.getStatusCode(),
                    statusCode, "Status code should match with " + statusCode);

        } catch (Exception e) {
            Assert.fail("GET API for user account creation call failed: " + e.getMessage());
        }
    }

    // Positive test case for PUT User API with valid user ID and request data
    @Test(description = "Verify user details are updated successfully")
    public void verifyingSingleUserUpdateDetails() {
        try {
            int userId = 2;
            String firstName = "Amit";
            String lastName = "Singh";
            String email = "rohit@gmail.com";
            String password = "123456";
            String phone = "0987654321";

            User user = getCreateUser(
                    firstName,
                    lastName,
                    email,
                    password,
                    phone
            );

            ApiResponse<User> response =
                    usersApi.updateUserWithHttpInfo(userId, user);

            User updatedUser = response.getData();

            // Validate status code
            Assert.assertEquals(
                    response.getStatusCode(),
                    200,
                    "Status code should be 200"
            );

            // Validate response
            Assert.assertNotNull(
                    updatedUser,
                    "Response should not be null"
            );

            // Validate email
            Assert.assertEquals(
                    updatedUser.getEmail(),
                    email,
                    "Email should match updated value"
            );

            // Validate phone
            Assert.assertEquals(
                    updatedUser.getPhone(),
                    phone,
                    "Phone number should match updated value"
            );

            // Validate first name
            Assert.assertEquals(
                    updatedUser.getName().getFirstname(),
                    firstName,
                    "First name should match updated value"
            );

            // Validate last name
            Assert.assertEquals(
                    updatedUser.getName().getLastname(),
                    lastName,
                    "Last name should match updated value"
            );

        } catch (Exception e) {
            Assert.fail("Update User API call failed: " + e.getMessage());
        }
    }

    // Negative test cases for PUT User API with invalid user IDs and request data
    @Test(description = "Verify UPDATE user API with non-existing user ID")
    public void updateUserWithNonExistingId() {
        try {
            int userId = 9999;

            User user = getCreateUser(
                    "Invalid",
                    "User",
                    "invalid@gmail.com",
                    "password",
                    "1234567890"
            );

            ApiResponse<User> response =
                    usersApi.updateUserWithHttpInfo(userId, user);

            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response Data: " + response.getData());

            Assert.assertNotNull(
                    response,
                    "API response should not be null"
            );

        } catch (Exception e) {
            System.out.println(
                    "Expected exception for non-existing user ID: "
                            + e.getMessage()
            );
        }
    }

    @Test(description = "Verify UPDATE user API with zero user ID")
    public void updateUserWithZeroId() {
        try {
            int userId = 0;

            User user = getCreateUser(
                    "Test",
                    "User",
                    "test@gmail.com",
                    "password",
                    "1234567890"
            );

            ApiResponse<User> response =
                    usersApi.updateUserWithHttpInfo(userId, user);

            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response Data: " + response.getData());

        } catch (Exception e) {
            System.out.println(
                    "Expected exception for zero user ID: "
                            + e.getMessage()
            );
        }
    }

    @Test(description = "Verify UPDATE user API with negative user ID")
    public void updateUserWithNegativeId() {
        try {
            int userId = -1;

            User user = getCreateUser(
                    "Test",
                    "User",
                    "test@gmail.com",
                    "password",
                    "1234567890"
            );

            ApiResponse<User> response =
                    usersApi.updateUserWithHttpInfo(userId, user);

            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response Data: " + response.getData());

        } catch (Exception e) {
            System.out.println(
                    "Expected exception for negative user ID: "
                            + e.getMessage()
            );
        }
    }

    @Test(description = "Verify UPDATE user API with empty request body")
    public void updateUserWithEmptyBody() {
        try {
            int userId = 2;

            User user = new User();

            ApiResponse<User> response =
                    usersApi.updateUserWithHttpInfo(userId, user);

            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response Data: " + response.getData());

            Assert.assertNotNull(
                    response,
                    "API response should not be null"
            );

        } catch (Exception e) {
            System.out.println(
                    "Expected exception for empty request body: "
                            + e.getMessage()
            );
        }
    }

    @Test(description = "Verify user details are deleted successfully")
    public void verifyUserDataDeleted() {
        try {
            int userId = 3;
            ApiResponse<Void> response = usersApi.deleteUserWithHttpInfo(userId);

            // validate status code should match
            Assert.assertEquals(
                    response.getStatusCode(),
                    200,
                    "Status code should be 200");

            // Verify response object is not null
            Assert.assertNotNull(
                    response,
                    "API response should not be null"
            );

            //  Verify response body is empty
            Assert.assertNull(
                    response.getData(),
                    "Delete API response body should be null"
            );
        } catch (Exception e) {
            Assert.fail("GET API for user account creation call failed: " + e.getMessage());
        }
    }

    // Negative test cases for DELETE User API with invalid user IDs
    @Test(description = "Verify DELETE user API with zero user ID")
    public void deleteUserWithZeroId() {
        try {
            int userId = 0;

            ApiResponse<Void> response =
                    usersApi.deleteUserWithHttpInfo(userId);

            System.out.println("User ID: " + userId);
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response Data: " + response.getData());

            Assert.assertNotNull(
                    response,
                    "API response should not be null"
            );

        } catch (Exception e) {
            System.out.println(
                    "Expected exception for zero user ID: "
                            + e.getMessage()
            );
        }
    }

    @Test(description = "Verify DELETE user API with negative user ID")
    public void deleteUserWithNegativeId() {
        try {
            int userId = -1;

            ApiResponse<Void> response =
                    usersApi.deleteUserWithHttpInfo(userId);

            System.out.println("User ID: " + userId);
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response Data: " + response.getData());

            Assert.assertNotNull(
                    response,
                    "API response should not be null"
            );

        } catch (Exception e) {
            System.out.println(
                    "Expected exception for negative user ID: "
                            + e.getMessage()
            );
        }
    }

    @Test(description = "Verify DELETE user API with non-existing user ID")
    public void deleteUserWithNonExistingId() {
        try {
            int userId = 9999;

            ApiResponse<Void> response =
                    usersApi.deleteUserWithHttpInfo(userId);

            System.out.println("User ID: " + userId);
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response Data: " + response.getData());

            Assert.assertNotNull(
                    response,
                    "API response should not be null"
            );

        } catch (Exception e) {
            System.out.println(
                    "Expected exception for non-existing user ID: "
                            + e.getMessage()
            );
        }
    }

    /**
     * Creates and returns a {@link Name} object using the provided first and last names.
     *
     * @param userFirstName the user's first name
     * @param userLastName  the user's last name
     * @return a {@link Name} object populated with the provided first and last names
     */
    @NotNull
    private static Name getNameDetails(String userFirstName, String userLastName) {
        // Create Name Details
        Name name = new Name();
        name.setFirstname(userFirstName);
        name.setLastname(userLastName);
        return name;
    }

    /**
     * Creates and returns a {@link User} object using the provided user details.
     * <p>The user's first and last names are used to create the nested
     * {@link Name} object.</p>
     *
     * @param userFirstName the user's first name
     * @param userLastName  the user's last name
     * @param email         the user's email address
     * @param password      the user's password
     * @param phone         the user's phone number
     * @return a {@link User} object populated with the provided user details
     */
    @NotNull
    private static User getCreateUser(String userFirstName, String userLastName, String email, String password, String phone) {


        // Create Name Details
        Name name = getNameDetails(userFirstName, userLastName);

        // Create User Details
        User createUser = new User();

        createUser.setName(name);
        createUser.setEmail(email);
        createUser.setPassword(password);
        createUser.setPhone(phone);
        return createUser;
    }
}
