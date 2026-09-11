package org.openapitools.client;

import org.openapitools.client.api.CartsApi;
import org.openapitools.client.model.Cart;
import org.openapitools.client.model.CartProduct;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class FakeStoreCartsTest {


    private CartsApi cartsApi;

    @BeforeClass
    public void setUp() {
        ApiClient client = Configuration.getDefaultApiClient();
        client.setBasePath("https://fakestoreapi.com");

        cartsApi = new CartsApi(client);
    }

    @Test(description = "Verify user can successfully create a cart")
    public void addCartSuccessfully() {
        try {
            int userId = 1;
            int productId = 1;

            // Create product
            CartProduct cartProduct = new CartProduct();
            cartProduct.setProductId(productId);
            cartProduct.setQuantity(1);

            // Create cart
            Cart cart = new Cart();
            cart.setUserId(userId);
            cart.addProductsItem(cartProduct);

            // Send POST request
            Cart response = cartsApi.addCart(cart);

            // Validate response
            Assert.assertNotNull(
                    response,
                    "Response should not be null"
            );

            // Validate cart ID
            Assert.assertNotNull(
                    response.getId(),
                    "Cart ID should not be null"
            );

            // Validate user ID
            Assert.assertEquals(
                    response.getUserId(),
                    Integer.valueOf(userId),
                    "User ID should match the requested user ID"
            );

            // Validate products
            Assert.assertNotNull(
                    response.getProducts(),
                    "Products should not be null"
            );

            Assert.assertFalse(
                    response.getProducts().isEmpty(),
                    "Products list should not be empty"
            );

            // Validate product
            CartProduct responseProduct = response.getProducts().get(0);

            Assert.assertNotNull(
                    responseProduct.getProductId(),
                    "Product ID should not be null"
            );

            Assert.assertEquals(
                    responseProduct.getProductId(),
                    Integer.valueOf(productId),
                    "Product ID should match the requested product ID"
            );

            // Printing response details
            System.out.println("Cart ID: " + response.getId());
            System.out.println("User ID: " + response.getUserId());
            System.out.println("Product ID: " + responseProduct.getProductId());
        } catch (Exception e) {
            Assert.fail("API call failed: " + e.getMessage());
        }
    }

    // positive test case for deleting carts
    @Test(description = "Verify user deletes a specific cart by ID.")
    public void deleteCartByID() {
        try {
            int cartId = 1;
            ApiResponse<Void> response =
                    cartsApi.deleteCartWithHttpInfo(cartId);

            // validating response should not  be null
            Assert.assertNotNull(
                    response,
                    "API response should not be null"
            );

            // validating status code should be 200
            Assert.assertEquals(
                    response.getStatusCode(),
                    200,
                    "Expected HTTP status code should be 200"
            );

            System.out.println("Delete cart status code: " + response.getStatusCode());
        } catch (Exception e) {
            Assert.fail("Delete API call failed: " + e.getMessage());
        }
    }

    @Test(description = "Verify User get all carts data")
    public void getCartAllCartDetails() {
        try {
            ApiResponse<List<Cart>> response =
                    cartsApi.getAllCartsWithHttpInfo();

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

            // Validate cart data
            Assert.assertNotNull(
                    response.getData(),
                    "Cart data should not be null"
            );

            Assert.assertFalse(
                    response.getData().isEmpty(),
                    "Cart data should be present"
            );

            System.out.println("Status Code: " + statusCode);
            System.out.println("Total Carts: " + response.getData().size());


        } catch (Exception e) {
            Assert.fail("Get API call failed: " + e.getMessage());
        }
    }
}
