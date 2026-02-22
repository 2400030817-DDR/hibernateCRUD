package com.klu.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repo;

    // Insert Product
    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return repo.save(product);
    }

    // Get All Products
    @GetMapping("/all")
    public List<Product> getAll() {
        return repo.findAll();
    }

    // Get Product by ID
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    // Update Product
    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product p = repo.findById(id).orElse(null);
        if (p != null) {
            p.setPrice(product.getPrice());
            p.setQuantity(product.getQuantity());
            return repo.save(p);
        }
        return null;
    }

    // Delete Product
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        repo.deleteById(id);
        return "Product Deleted";
    }
}