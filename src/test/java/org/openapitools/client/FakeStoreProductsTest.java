
package org.openapitools.client;

import org.openapitools.client.api.ProductsApi;
import org.openapitools.client.model.Product;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class FakeStoreProductsTest {

    private ProductsApi productsApi;

    @BeforeClass
    public void setUp() {

        ApiClient apiClient = new ApiClient();

        // Fake Store API base URL
        apiClient.setBasePath("https://fakestoreapi.com");

        productsApi = new ProductsApi(apiClient);
    }

    // GET method
    @Test(description = "Verify GET all products API")
    public void getAllProductsTest() {

        try {
            List<Product> products = productsApi.getAllProducts();

            Assert.assertNotNull(
                    products,
                    "Products response should not be null"
            );

            Assert.assertFalse(
                    products.isEmpty(),
                    "Products list should not be empty"
            );

            System.out.println("Total products: " + products.size());

            for (Product product : products) {
                System.out.println(product);
            }

        } catch (ApiException e) {
            Assert.fail("API call failed: " + e.getMessage());
        }
    }

    @Test(description = "Verify GET product by ID API")
    public void getProductByIdTest() {
        try {
            int productId = 1;

            Product product = productsApi.getProductById(productId);

            Assert.assertNotNull(
                    product,
                    "Product response should not be null"
            );

            Assert.assertEquals(
                    product.getId(),
                    productId,
                    "Product ID should match the requested ID"
            );

            System.out.println("Product ID: " + product.getId());
            System.out.println("Product title: " + product.getTitle());
            System.out.println("Product price: " + product.getPrice());
        } catch (Exception e) {
            Assert.fail("API call failed: " + e.getMessage());
        }
    }

    // Positive test case for Add User
    @Test(description = "Verify ADD product API")
    public void addProductTest() {
        try {
            Product product = new Product();

            product.setTitle("Test Product");
            product.setPrice(199.99f);
            product.setDescription(
                    "Product created for API automation testing"
            );
            product.setCategory("electronics");

            Product response = productsApi.addProduct(product);

            Assert.assertNotNull(
                    response,
                    "Add product response should not be null"
            );

            System.out.println("Added product response:");
            System.out.println(response);
        } catch (Exception e) {
            Assert.fail("API call failed: " + e.getMessage());
        }
    }

    // Negative test case for GET Method Products
    @Test(description = "Verify GET product API with zero product ID")
    public void getProductByZeroIdTest() {
        try {
            int productId = 0;

            Product response = productsApi.getProductById(productId);

            System.out.println("Product ID: " + productId);
            System.out.println("Response: " + response);

            Assert.assertNull(
                    response,
                    "Response should be null for invalid product ID"
            );

        } catch (Exception e) {
            System.out.println("Expected exception: " + e.getMessage());
        }
    }

    @Test(description = "Verify GET product API with negative product ID")
    public void getProductByNegativeIdTest() {
        try {
            int productId = -1;

            Product response = productsApi.getProductById(productId);

            System.out.println("Product ID: " + productId);
            System.out.println("Response: " + response);

            Assert.assertNull(
                    response,
                    "Response should be null for negative product ID"
            );

        } catch (Exception e) {
            System.out.println("Expected exception: " + e.getMessage());
        }
    }

    @Test(description = "Verify GET product API with non-existing product ID")
    public void getProductByNonExistingIdTest() {
        try {
            int productId = 9999;

            Product response = productsApi.getProductById(productId);

            System.out.println("Product ID: " + productId);
            System.out.println("Response: " + response);

            Assert.assertNull(
                    response,
                    "Response should be null for non-existing product ID"
            );

        } catch (Exception e) {
            System.out.println("Expected exception: " + e.getMessage());
        }
    }

    // Negative test cases for ADD method
    @Test(description = "Verify ADD product API with empty request body")
    public void addEmptyProductTest() {
        try {
            Product product = new Product();

            Product response = productsApi.addProduct(product);

            System.out.println("Response: " + response);

            Assert.assertNotNull(
                    response,
                    "Response should not be null"
            );

        } catch (Exception e) {
            System.out.println("Expected exception: " + e.getMessage());
        }
    }

    @Test(description = "Verify ADD product API without title")
    public void addProductWithoutTitleTest() {
        try {
            Product product = new Product();

            product.setPrice(199.99f);
            product.setDescription("Product without title");
            product.setCategory("electronics");

            Product response = productsApi.addProduct(product);

            System.out.println("Response: " + response);

            Assert.assertNotNull(
                    response,
                    "Response should not be null"
            );

        } catch (Exception e) {
            System.out.println("Expected exception: " + e.getMessage());
        }
    }

    @Test(description = "Verify ADD product API with negative price")
    public void addProductWithNegativePriceTest() {
        try {
            Product product = new Product();

            product.setTitle("Invalid Price Product");
            product.setPrice(-100.0f);
            product.setDescription("Product with negative price");
            product.setCategory("electronics");

            Product response = productsApi.addProduct(product);

            System.out.println("Response: " + response);

            Assert.assertNotNull(
                    response,
                    "Response should not be null"
            );

        } catch (Exception e) {
            System.out.println("Expected exception: " + e.getMessage());
        }
    }

    @Test(description = "Verify ADD product API without category")
    public void addProductWithoutCategoryTest() {
        try {
            Product product = new Product();

            product.setTitle("Product Without Category");
            product.setPrice(199.99f);
            product.setDescription("Product without category");

            Product response = productsApi.addProduct(product);

            System.out.println("Response: " + response);

            Assert.assertNotNull(
                    response,
                    "Response should not be null"
            );

        } catch (Exception e) {
            System.out.println("Expected exception: " + e.getMessage());
        }
    }

    // Positive Update Test Case
    @Test(description = "Verify UPDATE product API")
    public void updateProductTest() {
        try {
            int productId = 1;

            Product product = new Product();

            product.setTitle("Updated Test Product");
            product.setPrice(299.99f);
            product.setDescription(
                    "Updated product for API automation testing"
            );
            product.setCategory("electronics");

            Product response = productsApi.updateProduct(
                    productId,
                    product
            );

            Assert.assertNotNull(
                    response,
                    "Update product response should not be null"
            );

            System.out.println("Updated product response:");
            System.out.println(response);
        } catch (Exception e) {
            Assert.fail("API call failed: " + e.getMessage());
        }
    }

    @Test(description = "Verify UPDATE product API with invalid product ID")
    public void updateProductWithInvalidIdTest() {
        try {
            int productId = 9999;

            Product product = new Product();

            product.setTitle("Updated Product");
            product.setPrice(299.99f);
            product.setDescription("Updated product");
            product.setCategory("electronics");

            Product response =
                    productsApi.updateProduct(productId, product);

            System.out.println("Product ID: " + productId);
            System.out.println("Response: " + response);

            Assert.assertNotNull(
                    response,
                    "Response should not be null"
            );

        } catch (ApiException e) {
            System.out.println("Status Code: " + e.getCode());

            Assert.assertTrue(
                    e.getCode() == 400 || e.getCode() == 404,
                    "Expected 400 or 404 for invalid product ID"
            );
        }
    }

    @Test(description = "Verify UPDATE product API with zero product ID")
    public void updateProductWithZeroIdTest() {
        try {
            int productId = 0;

            Product product = new Product();

            product.setTitle("Updated Product");
            product.setPrice(299.99f);
            product.setDescription("Updated product");
            product.setCategory("electronics");

            Product response =
                    productsApi.updateProduct(productId, product);

            System.out.println("Product ID: " + productId);
            System.out.println("Response: " + response);

            Assert.assertNotNull(
                    response,
                    "Response should not be null"
            );

        } catch (ApiException e) {
            System.out.println("Status Code: " + e.getCode());

            Assert.assertTrue(
                    e.getCode() == 400 || e.getCode() == 404,
                    "Expected 400 or 404 for invalid product ID"
            );
        }
    }

    @Test(description = "Verify UPDATE product API with negative product ID")
    public void updateProductWithNegativeIdTest() {
        try {
            int productId = -1;

            Product product = new Product();

            product.setTitle("Updated Product");
            product.setPrice(299.99f);
            product.setDescription("Updated product");
            product.setCategory("electronics");

            Product response =
                    productsApi.updateProduct(productId, product);

            System.out.println("Product ID: " + productId);
            System.out.println("Response: " + response);

            Assert.assertNotNull(
                    response,
                    "Response should not be null"
            );

        } catch (ApiException e) {
            System.out.println("Status Code: " + e.getCode());

            Assert.assertTrue(
                    e.getCode() == 400 || e.getCode() == 404,
                    "Expected 400 or 404 for invalid product ID"
            );
        }
    }

    @Test(description = "Verify UPDATE product API with empty request body")
    public void updateProductWithEmptyBodyTest() {
        try {
            int productId = 1;

            Product product = new Product();

            Product response =
                    productsApi.updateProduct(productId, product);

            System.out.println("Response: " + response);

            Assert.assertNotNull(
                    response,
                    "Response should not be null"
            );

        } catch (Exception e) {
            System.out.println("Expected exception: " + e.getMessage());
        }
    }

    @Test(description = "Verify UPDATE product API with null request body")
    public void updateProductWithNullBodyTest() {
        try {
            int productId = 1;

            Product response =
                    productsApi.updateProduct(productId, null);

            System.out.println("Response: " + response);

            Assert.assertNotNull(
                    response,
                    "Response should not be null"
            );

        } catch (Exception e) {
            System.out.println("Expected exception: " + e.getMessage());
        }
    }

    // positive test case for Delete
    @Test(description = "Verify DELETE product API and HTTP status code")
    public void deleteProductTest() throws Exception {

        int productId = 1;

        ApiResponse<Void> response =
                productsApi.deleteProductWithHttpInfo(productId);

        Assert.assertNotNull(
                response,
                "Delete product response should not be null"
        );

        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Expected HTTP status code 200 for DELETE product"
        );

        System.out.println(
                "Product with ID " + productId +
                        " deleted successfully"
        );

        System.out.println(
                "HTTP Status Code: " + response.getStatusCode()
        );
    }

    // Negative test cases for Delete test cases
    @Test(description = "Verify DELETE product API with negative product ID")
    public void deleteProductWithNegativeIdTest() {
        try {
            int productId = -1;

            ApiResponse<Void> response =
                    productsApi.deleteProductWithHttpInfo(productId);

            System.out.println("Product ID: " + productId);
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response Headers: " + response.getHeaders());
            System.out.println("Response Data: " + response.getData());

            Assert.assertNotNull(
                    response,
                    "Delete response should not be null"
            );

        } catch (ApiException e) {
            System.out.println("Status Code: " + e.getCode());

            Assert.assertTrue(
                    e.getCode() == 400 || e.getCode() == 404,
                    "Expected 400 or 404 for invalid product ID"
            );
        }
    }

    @Test(description = "Verify DELETE product API with zero product ID")
    public void deleteProductWithZeroIdTest() {
        try {
            int productId = 0;

            ApiResponse<Void> response =
                    productsApi.deleteProductWithHttpInfo(productId);

            System.out.println("Product ID: " + productId);
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response Headers: " + response.getHeaders());
            System.out.println("Response Data: " + response.getData());

            Assert.assertNotNull(
                    response,
                    "Delete response should not be null"
            );

        } catch (ApiException e) {
            System.out.println("Status Code: " + e.getCode());

            Assert.assertTrue(
                    e.getCode() == 400 || e.getCode() == 404,
                    "Expected 400 or 404 for invalid product ID"
            );
        }
    }


    @Test(description = "Verify DELETE product API with non-existing product ID")
    public void deleteNonExistingProductTest() {
        try {
            int productId = 9999;

            ApiResponse<Void> response =
                    productsApi.deleteProductWithHttpInfo(productId);

            System.out.println("Product ID: " + productId);
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response Headers: " + response.getHeaders());
            System.out.println("Response Data: " + response.getData());

            Assert.assertNotNull(
                    response,
                    "Delete response should not be null"
            );

        } catch (ApiException e) {
            System.out.println("Status Code: " + e.getCode());

            Assert.assertTrue(
                    e.getCode() == 400 || e.getCode() == 404,
                    "Expected 400 or 404 for non-existing product"
            );
        }
    }

    @Test(
            description = "Verify GET product API with invalid product ID"

    )
    public void getProductByInvalidIdTest() {
        try {
            int invalidProductId = -1;

            productsApi.getProductById(invalidProductId);
        } catch (ApiException e) {
            Assert.fail("API call failed: " + e.getMessage());
        }
    }
}

