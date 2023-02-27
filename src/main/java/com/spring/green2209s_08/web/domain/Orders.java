package com.spring.green2209s_08.web.domain;

import com.spring.green2209s_08.web.exception.OrderException;
import com.spring.green2209s_08.web.exception.errorResult.OrderErrorResult;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders extends CommonEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Long id;

    //아임포트 uid
    private String impUid;
    private String orderUid;
    private String paymentMethod;
    private String orderItemName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private String recipient;
    private String zipcode;
    private String address;
    private String currency;

    private int totalPrice;
    private int totalDeliveryPrice;

    @OneToMany(mappedBy = "orders")
    private List<OrderItem> orderItems;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    public void assignOrderItems(List<OrderItem> orderItems){
        this.orderItems = orderItems;
    }

    public void cancel(){
        if(deliveryStatus == deliveryStatus.ON_DELIVERY ||
        this.deliveryStatus == deliveryStatus.COMPLETE){
            throw new OrderException(OrderErrorResult.TRY_CANCEL_ORDER_IN_ILLEGALSTATE);
        }
    }
}
