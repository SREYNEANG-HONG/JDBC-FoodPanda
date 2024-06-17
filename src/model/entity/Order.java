package model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Order {
    private Integer id;
    private String orderName;
    private String orderDescription;
    private Date orderDate;
    private Customer customer;
    private List<Product> productsList;
}
