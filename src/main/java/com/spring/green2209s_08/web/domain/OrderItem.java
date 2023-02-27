package com.spring.green2209s_08.web.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends CommonEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orders_orders_id")
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    int salePrice;
    int selectedCount;
    public static OrderItem createOrderItem(Orders orders, Item item, int salePrice, int selectedCount) {
        OrderItem orderItem = OrderItem.builder()
                .orders(orders)
                .item(item)
                .salePrice(salePrice)
                .selectedCount(selectedCount)
                .build();
        item.removeStock(selectedCount);
        return orderItem;
    }
}