package playground.jpa.streamer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import playground.jpa.streamer.productData.entities.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductControllerBase {

    private final ProductService productService;

    public ProductControllerBase(ProductService productService) {
        this.productService = productService;
    }

    // Endpoint to get a product by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint to get products by title
    @GetMapping("/title/{title}")
    public ResponseEntity<List<Product>> getProductsByTitle(@PathVariable String title) {
        List<Product> products = productService.findByTitle(title);
        return ResponseEntity.ok(products);
    }

    // Endpoint to get products by description containing a keyword
    @GetMapping("/description")
    public ResponseEntity<List<Product>> getProductsByDescriptionContaining(@RequestParam String keyword) {
        List<Product> products = productService.findByDescriptionContaining(keyword);
        return ResponseEntity.ok(products);
    }

    // Endpoint to get products by tag name
    @GetMapping("/tags/{tagName}")
    public ResponseEntity<List<Product>> getProductsByTagsName(@PathVariable String tagName) {
        List<Product> products = productService.findByTagsName(tagName);
        return ResponseEntity.ok(products);
    }

    // Endpoint to get products by variants price less than a given price
    @GetMapping("/variants/price")
    public ResponseEntity<List<Product>> getProductsByVariantsPriceLessThan(@RequestParam Double price) {
        List<Product> products = productService.findByVariantsPriceLessThan(price);
        return ResponseEntity.ok(products);
    }

    // Endpoint to get products by variants available quantity greater than a given quantity
    @GetMapping("/variants/quantity")
    public ResponseEntity<List<Product>> getProductsByVariantsAvailableQuantityGreaterThan(@RequestParam Integer quantity) {
        List<Product> products = productService.findByVariantsAvailableQuantityGreaterThan(quantity);
        return ResponseEntity.ok(products);
    }

    // Endpoint to get products created after a given datetime
    @GetMapping("/createdAfter")
    public ResponseEntity<List<Product>> getProductsByCreatedAtAfter(@RequestParam String dateTime) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime);
        List<Product> products = productService.findByCreatedAtAfter(localDateTime);
        return ResponseEntity.ok(products);
    }

    // Endpoint to get products by variants name
    @GetMapping("/variants/name")
    public ResponseEntity<List<Product>> getProductsByVariantsName(@RequestParam String variantName) {
        List<Product> products = productService.findByVariantsName(variantName);
        return ResponseEntity.ok(products);
    }

    // Endpoint to get products by title or description
    @GetMapping("/titleOrDescription")
    public ResponseEntity<List<Product>> getProductsByTitleOrDescription(
            @RequestParam String title,
            @RequestParam String description) {
        List<Product> products = productService.findByTitleOrDescription(title, description);
        return ResponseEntity.ok(products);
    }

    // Endpoint to get products with variants price between a given range
    @GetMapping("/variants/priceBetween")
    public ResponseEntity<List<Product>> getProductsWithVariantsPriceBetween(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        List<Product> products = productService.findProductsWithVariantsPriceBetween(minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }

    // Endpoint to get products with a specific tag and variant price between a given range
    @GetMapping("/tagAndVariantPriceBetween")
    public ResponseEntity<List<Product>> getProductsWithTagAndVariantPriceBetween(
            @RequestParam String tagName,
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        List<Product> products = productService.findProductsWithTagAndVariantPriceBetween(tagName, minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }

    // Endpoint to get products with total availability below a certain limit
    @GetMapping("/availabilityBelow")
    public ResponseEntity<List<Product>> getProductsWithTotalAvailabilityBelow(@RequestParam Integer minLimit) {
        List<Product> products = productService.findProductsWithTotalAvailabilityBelow(minLimit);
        return ResponseEntity.ok(products);
    }

    // Endpoint to get products by multiple tags
    @GetMapping("/multipleTags")
    public ResponseEntity<List<Product>> getProductsByMultipleTags(@RequestParam List<String> tagNames) {
        List<Product> products = productService.findProductsByMultipleTags(tagNames);
        return ResponseEntity.ok(products);
    }

    // Endpoint to get basic info of products
    @GetMapping("/basicInfo")
    public ResponseEntity<Object> getProductBasicInfoList() {
        Object productInfo = productService.findProductBasicInfoList();
        return ResponseEntity.ok(productInfo);
    }

    // Endpoint to get product and total quantity list
    @GetMapping("/productAndTotalQuantity")
    public ResponseEntity<Object> getProductAndTotalQuantityList() {
        Object productAndQuantity = productService.findProductAndTotalQuantityList();
        return ResponseEntity.ok(productAndQuantity);
    }
}
