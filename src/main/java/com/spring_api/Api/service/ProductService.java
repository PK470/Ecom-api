package com.spring_api.Api.service;

import com.spring_api.Api.model.Product;
import com.spring_api.Api.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository pr;

    public List<Product> getAllProducts() {
        return pr.findAll();
    }

    public Product getProductById(int id) {
        return pr.findById(id).orElse(null);
    }

    public void addProduct(Product p, MultipartFile image) throws IOException {
        Product sp = p;
        sp.setImageName(image.getOriginalFilename());
        sp.setImageType(image.getContentType());
        sp.setImageData(image.getBytes());
        pr.save(sp);
    }

    public void deleteProduct(int id) {
        pr.deleteById(id);
    }

    public List<Product> searchByKeyword(String keyword) {
        return pr.searchProduct(keyword);
    }
}
