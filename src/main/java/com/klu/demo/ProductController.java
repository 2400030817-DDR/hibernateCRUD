package com.klu.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repo;

    // Add Product
    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return repo.save(product);
    }

    // Get All Products
    @GetMapping("/all")
    public List<Product> getAll() {
        return repo.findAll();
    }

    // Sorting
    @GetMapping("/sort/price/asc")
    public List<Product> sortPriceAsc() {
        return repo.sortByPriceAsc();
    }

    @GetMapping("/sort/price/desc")
    public List<Product> sortPriceDesc() {
        return repo.sortByPriceDesc();
    }

    @GetMapping("/sort/quantity")
    public List<Product> sortQuantity() {
        return repo.sortByQuantityDesc();
    }

    // Pagination
    @GetMapping("/page")
    public Page<Product> getPage(@RequestParam int page,
                                 @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repo.findAll(pageable);
    }

    // Aggregate Functions
    @GetMapping("/count")
    public long countProducts() {
        return repo.countProducts();
    }

    @GetMapping("/count/available")
    public long countAvailable() {
        return repo.countAvailable();
    }

    @GetMapping("/minprice")
    public Double minPrice() {
        return repo.minPrice();
    }

    @GetMapping("/maxprice")
    public Double maxPrice() {
        return repo.maxPrice();
    }

    @GetMapping("/group")
    public List<Object[]> groupByDescription() {
        return repo.groupByDescription();
    }

    // Price Range Filter
    @GetMapping("/filter")
    public List<Product> filterByPrice(@RequestParam double min,
                                       @RequestParam double max) {
        return repo.findByPriceRange(min, max);
    }

    // LIKE Search
    @GetMapping("/search")
    public List<Product> search(@RequestParam String name) {
        return repo.findByNameLike("%" + name + "%");
    }
}