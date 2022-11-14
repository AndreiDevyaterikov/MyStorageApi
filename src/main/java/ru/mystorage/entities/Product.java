package ru.mystorage.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@Table(name = "products")
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(generator = "product_sequence")
    @Column(name = "product_id")
    private Integer id;

    @Column(name = "article")
    private String article;

    @Column(name = "product_name")
    private String name;

    @Column(name = "last_buy_price")
    private BigDecimal lastBuyPrice;

    @Column(name = "last_sell_price")
    private BigDecimal lastSellPrice;

    @Column(name = "amount")
    private Integer amount;

    @OneToOne
    @JoinColumn(name = "storage_id")
    private Storage storage;
}
