package com.davinchicoder.spring_security_cero_a_experto.product.infrastructure.database.entity;

import com.davinchicoder.spring_security_cero_a_experto.category.infrastructure.CategoryEntity;
import com.davinchicoder.spring_security_cero_a_experto.productDetail.infrastructure.ProductDetailEntity;
import com.davinchicoder.spring_security_cero_a_experto.review.infrastructure.ReviewEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 500)
    private String description;
    private Double price;
    private String image;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_detail_id")
    private ProductDetailEntity productDetail;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ReviewEntity> reviews = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "products_categories", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<CategoryEntity> categories = new ArrayList<>();
}
