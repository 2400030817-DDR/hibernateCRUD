package com.klu.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Sorting by price ASC
    @Query("FROM Product p ORDER BY p.price ASC")
    List<Product> sortByPriceAsc();

    // Sorting by price DESC
    @Query("FROM Product p ORDER BY p.price DESC")
    List<Product> sortByPriceDesc();

    // Sort by quantity highest first
    @Query("FROM Product p ORDER BY p.quantity DESC")
    List<Product> sortByQuantityDesc();

    // Count total products
    @Query("SELECT COUNT(p) FROM Product p")
    long countProducts();

    // Count products where quantity > 0
    @Query("SELECT COUNT(p) FROM Product p WHERE p.quantity > 0")
    long countAvailable();

    // Minimum price
    @Query("SELECT MIN(p.price) FROM Product p")
    double minPrice();

    // Maximum price
    @Query("SELECT MAX(p.price) FROM Product p")
    double maxPrice();

    // Group by description
    @Query("SELECT p.description, COUNT(p) FROM Product p GROUP BY p.description")
    List<Object[]> groupByDescription();

    // Filter by price range
    @Query("FROM Product p WHERE p.price BETWEEN ?1 AND ?2")
    List<Product> findByPriceRange(double min, double max);

    // LIKE query
    @Query("FROM Product p WHERE p.name LIKE ?1")
    List<Product> findByNameLike(String pattern);

    // Pagination
    Page<Product> findAll(Pageable pageable);
}