
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

