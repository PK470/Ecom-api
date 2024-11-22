package com.spring_api.Api.controller;

import com.spring_api.Api.model.Product;
import com.spring_api.Api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService ps;
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProduct(){
        return new ResponseEntity<>(ps.getAllProducts(), HttpStatus.OK);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        Product p = ps.getProductById(id);
        if(p != null){
            return new ResponseEntity<>(p,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/product")
    public ResponseEntity<?> postProduct(@RequestPart Product p, @RequestPart MultipartFile image){
        try {
            ps.addProduct(p, image);
            return new ResponseEntity<>(ps.getProductById(p.getId()), HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestPart(required = false) Product p, @RequestPart(required = false) MultipartFile image){
        try{
            Product p1 = ps.getProductById(id);
            ps.addProduct(p1, image);
            return new ResponseEntity<>(ps.getProductById(id), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id){
        try {
            ps.deleteProduct(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/product/search")
    public ResponseEntity<?> searchProduct(@RequestParam String keyword){
        try{
            List<Product> l = ps.searchByKeyword(keyword);
            return new ResponseEntity<>(l,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Dose Not exist", HttpStatus.NOT_FOUND);
        }
    }
}
