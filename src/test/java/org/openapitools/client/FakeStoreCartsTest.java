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

    // ==================== ADD CART NEGATIVE TESTS ====================

    @Test(description = "Verify cart creation with null user ID")
    public void addCartWithNullUserId() {
        try {
            Cart cart = new Cart();
            cart.setUserId(null);

            CartProduct product = new CartProduct();
            product.setProductId(1);
            product.setQuantity(1);

            cart.addProductsItem(product);

            Cart response = cartsApi.addCart(cart);

            System.out.println("Response: " + response);

            Assert.assertNotNull(
                    response,
                    "Response should not be null"
            );

        } catch (Exception e) {
            System.out.println("Expected exception: " + e.getMessage());
        }
    }


    @Test(description = "Verify cart creation with null product ID")
    public void addCartWithNullProductId() {
        try {
            Cart cart = new Cart();
            cart.setUserId(1);

            CartProduct product = new CartProduct();
            product.setProductId(null);
            product.setQuantity(1);

            cart.addProductsItem(product);

            Cart response = cartsApi.addCart(cart);

            System.out.println("Response: " + response);

            Assert.assertNotNull(
                    response,
                    "Response should not be null"
            );

        } catch (Exception e) {
            System.out.println("Expected exception: " + e.getMessage());
        }
    }


    @Test(description = "Verify cart creation with zero quantity")
    public void addCartWithZeroQuantity() {
        try {
            Cart cart = new Cart();
            cart.setUserId(1);

            CartProduct product = new CartProduct();
            product.setProductId(1);
            product.setQuantity(0);

            cart.addProductsItem(product);

            Cart response = cartsApi.addCart(cart);

            System.out.println("Response: " + response);

            Assert.assertNotNull(
                    response,
                    "Response should not be null"
            );

        } catch (Exception e) {
            System.out.println("Expected exception: " + e.getMessage());
        }
    }


    @Test(description = "Verify cart creation with negative quantity")
    public void addCartWithNegativeQuantity() {
        try {
            Cart cart = new Cart();
            cart.setUserId(1);

            CartProduct product = new CartProduct();
            product.setProductId(1);
            product.setQuantity(-1);

            cart.addProductsItem(product);

            Cart response = cartsApi.addCart(cart);

            System.out.println("Response: " + response);

            Assert.assertNotNull(
                    response,
                    "Response should not be null"
            );

        } catch (Exception e) {
            System.out.println("Expected exception: " + e.getMessage());
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

    // ==================== GET CART NEGATIVE TESTS ====================

    @Test(description = "Verify get cart with zero cart ID")
    public void getCartWithZeroId() {
        try {
            int cartId = 0;

            Cart response = cartsApi.getCartById(cartId);

            System.out.println("Cart ID: " + cartId);
            System.out.println("Response: " + response);

            Assert.assertNull(
                    response,
                    "Response should be null for invalid cart ID"
            );

        } catch (Exception e) {
            Assert.fail("Get Cart API call failed: " + e.getMessage());
        }
    }


    @Test(description = "Verify get cart with negative cart ID")
    public void getCartWithNegativeId() {
        try {
            int cartId = -1;

            Cart response = cartsApi.getCartById(cartId);

            System.out.println("Cart ID: " + cartId);
            System.out.println("Response: " + response);

            Assert.assertNull(
                    response,
                    "Response should be null for negative cart ID"
            );

        } catch (Exception e) {
            Assert.fail("Get Cart API call failed: " + e.getMessage());
        }
    }


    @Test(description = "Verify get cart with non-existing cart ID")
    public void getCartWithNonExistingId() {
        try {
            int cartId = 9999;

            Cart response = cartsApi.getCartById(cartId);

            System.out.println("Cart ID: " + cartId);
            System.out.println("Response: " + response);

            Assert.assertNull(
                    response,
                    "Response should be null for non-existing cart ID"
            );

        } catch (Exception e) {
            Assert.fail("Get Cart API call failed: " + e.getMessage());
        }
    }

    // positive test case for get
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

    @Test(description = "verify User get carts data by cartID")
    public void getCartDetailsByCartId() {
        try {
            int cartId = 2;
            Cart cartDetails = cartsApi.getCartById(cartId);
            // Validate cart details is not null
            Assert.assertNotNull(
                    cartDetails,
                    "cart details should not be null"
            );

            Assert.assertEquals(
                    cartDetails.getId(),
                    cartId,
                    "Cart ID should match requested cart ID"
            );
        } catch (Exception e) {
            Assert.fail("Get API call failed: " + e.getMessage());
        }
    }

    @Test(description = "Verify User updated cart details successfully")
    public void updateCartDetails() {
        try{
            int cartId = 3;
            // Create product
            CartProduct cartProduct = new CartProduct();

            cartProduct.setProductId(2);

            // Create updated cart
            Cart cart = new Cart();
            cart.setUserId(1);
            cart.addProductsItem(cartProduct);

            // Update cart
            Cart response = cartsApi.updateCart(cartId, cart);

            // Validate response
            Assert.assertNotNull(
                    response,
                    "Response should not be null"
            );

            Assert.assertEquals(
                    response.getUserId(),
                    Integer.valueOf(1),
                    "User ID should match"
            );

            Assert.assertNotNull(
                    response.getProducts(),
                    "Products list should not be null"
            );

            Assert.assertFalse(
                    response.getProducts().isEmpty(),
                    "Products list should not be empty"
            );

            CartProduct updatedProduct = response.getProducts().get(0);

            Assert.assertEquals(
                    updatedProduct.getProductId(),
                    Integer.valueOf(2),
                    "Product ID should match updated value"
            );

            System.out.println("Cart ID: " + response.getId());
            System.out.println("User ID: " + response.getUserId());
            System.out.println("Updated Product ID: " + updatedProduct.getProductId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ==================== UPDATE CART NEGATIVE TESTS ====================

    @Test(description = "Verify update cart with invalid cart ID")
    public void updateCartWithInvalidId() {
        try {
            int cartId = 9999;

            CartProduct product = new CartProduct();
            product.setProductId(1);
            product.setQuantity(1);

            Cart cart = new Cart();
            cart.setUserId(1);
            cart.addProductsItem(product);

            Cart response =
                    cartsApi.updateCart(cartId, cart);

            System.out.println("Response: " + response);

            Assert.assertNotNull(
                    response,
                    "Response should not be null"
            );

        } catch (ApiException e) {
            System.out.println("Status Code: " + e.getCode());

            Assert.assertTrue(
                    e.getCode() == 400 || e.getCode() == 404,
                    "Expected 400 or 404 for invalid cart ID"
            );
        }
    }


    @Test(description = "Verify update cart with empty products list")
    public void updateCartWithEmptyProducts() {
        try {
            int cartId = 3;

            Cart cart = new Cart();
            cart.setUserId(1);

            Cart response =
                    cartsApi.updateCart(cartId, cart);

            System.out.println("Response: " + response);

            Assert.assertNotNull(
                    response,
                    "Response should not be null"
            );

        } catch (Exception e) {
            System.out.println("Expected exception: " + e.getMessage());
        }
    }


    @Test(description = "Verify update cart with null request body")
    public void updateCartWithNullRequestBody() {
        try {
            int cartId = 3;

            Cart response =
                    cartsApi.updateCart(cartId, null);

            System.out.println("Response: " + response);

            Assert.assertNotNull(
                    response,
                    "Response should not be null"
            );

        } catch (Exception e) {
            System.out.println("Expected exception: " + e.getMessage());
        }
    }


    @Test(description = "Verify update cart with zero cart ID")
    public void updateCartWithZeroId() {
        try {
            int cartId = 0;

            CartProduct product = new CartProduct();
            product.setProductId(1);
            product.setQuantity(1);

            Cart cart = new Cart();
            cart.setUserId(1);
            cart.addProductsItem(product);

            Cart response =
                    cartsApi.updateCart(cartId, cart);

            System.out.println("Response: " + response);

            Assert.assertNotNull(
                    response,
                    "Response should not be null"
            );

        } catch (ApiException e) {
            System.out.println("Status Code: " + e.getCode());

            Assert.assertTrue(
                    e.getCode() == 400 || e.getCode() == 404,
                    "Expected 400 or 404 for invalid cart ID"
            );
        }
    }


    @Test(description = "Verify update cart with negative cart ID")
    public void updateCartWithNegativeId() {
        try {
            int cartId = -1;

            CartProduct product = new CartProduct();
            product.setProductId(1);
            product.setQuantity(1);

            Cart cart = new Cart();
            cart.setUserId(1);
            cart.addProductsItem(product);

            Cart response =
                    cartsApi.updateCart(cartId, cart);

            System.out.println("Response: " + response);

            Assert.assertNotNull(
                    response,
                    "Response should not be null"
            );

        } catch (ApiException e) {
            System.out.println("Status Code: " + e.getCode());

            Assert.assertTrue(
                    e.getCode() == 400 || e.getCode() == 404,
                    "Expected 400 or 404 for invalid cart ID"
            );
        }
    }
}
