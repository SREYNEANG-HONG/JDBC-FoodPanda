package model.dao;

import model.entity.Customer;
import model.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> queryAllProducts();
    int deleteProductById(Integer id);
    int addNewProduct(Product product);
    int updateProductById(Integer id);
    Product searchProductById(Integer id);
}


//field -> column
//record -> row