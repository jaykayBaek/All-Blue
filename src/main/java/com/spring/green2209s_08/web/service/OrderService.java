package com.spring.green2209s_08.web.service;

import com.spring.green2209s_08.web.controller.order.OrderDateEnum;
import com.spring.green2209s_08.web.controller.order.OrderListResponse;
import com.spring.green2209s_08.web.controller.order.OrderRequest;
import com.spring.green2209s_08.web.controller.order.OrderSearchCond;
import com.spring.green2209s_08.web.domain.*;
import com.spring.green2209s_08.web.exception.MemberException;
import com.spring.green2209s_08.web.exception.OrderException;
import com.spring.green2209s_08.web.exception.errorResult.MemberErrorResult;
import com.spring.green2209s_08.web.exception.errorResult.OrderErrorResult;
import com.spring.green2209s_08.web.repository.MemberRepository;
import com.spring.green2209s_08.web.repository.OrderItemRepository;
import com.spring.green2209s_08.web.repository.OrderRepository;
import com.spring.green2209s_08.web.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final WishlistService wishlistService;
    private final WishlistRepository wishlistRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public void order(Long memberId, OrderRequest orderRequest) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorResult.MEMBER_NOT_FOUND));

        Orders orders = getOrders(orderRequest, member);

        List<Wishlist> findWishlists = wishlistRepository.findAllByItemIdAndMemberId(orderRequest.getOrderItemIdList(), memberId);

        List<OrderItem> orderItems = new ArrayList<>();
        for (Wishlist wishlist : findWishlists) {
            OrderItem orderItem = OrderItem.createOrderItem(orders, wishlist.getItem(), wishlist.getItem().getSalePrice(), wishlist.getSelectedQuantity());
            orderItems.add(orderItem);
        }

        orderItemRepository.saveAll(orderItems);
        orders.assignOrderItems(orderItems);

        orderRepository.save(orders);
        wishlistService.deleteWishlists(findWishlists);
    }

    private static OrderItem getOrderItem(Orders orders, Wishlist wishlist) {
        return OrderItem.builder()
                .orders(orders)
                .item(wishlist.getItem())
                .salePrice(wishlist.getItem().getSalePrice())
                .selectedCount(wishlist.getSelectedQuantity())
                .build();
    }

    private static Orders getOrders(OrderRequest orderRequest, Member member) {
        return Orders.builder()
                .impUid(orderRequest.getImpUid())
                .orderUid(orderRequest.getOrderUid())
                .paymentMethod(orderRequest.getPaymentMethod())
                .orderItemName(orderRequest.getOrderItemName())
                .member(member)
                .recipient(orderRequest.getRecipient())
                .zipcode(orderRequest.getZipcode())
                .address(orderRequest.getAddress())
                .phoneNo(orderRequest.getPhoneNo())
                .currency(orderRequest.getCurrency())
                .totalPrice(orderRequest.getTotalPrice())
                .totalDeliveryPrice(orderRequest.getTotalDeliveryPrice())
                .deliveryStatus(DeliveryStatus.READY)
                .orderStatus(OrderStatus.PAYMENT_OK)
                .build();
    }

    public Page<OrderListResponse> findAllByMemberId(Long memberId, OrderSearchCond orderSearchCond, Pageable pageable) {
        OrderDateEnum orderDate = orderSearchCond.getOrderDate() ;
        LocalDateTime now = LocalDateTime.now();
        if(orderDate == null){
            orderSearchCond.createItemCond(null, null);
        } else if(orderDate.equals(OrderDateEnum.WHILE_SIX_MONTH)){
            orderSearchCond.createItemCond(now, now.minusMonths(6));
            orderSearchCond.createItemCond(now, now.minusMonths(6));
        }else if(orderDate.equals(OrderDateEnum.WHILE_YEAR)){
            orderSearchCond.createItemCond(now, now.minusMonths(12));
        }else{
            orderSearchCond.createItemCond(null, null);
        }

        return orderRepository.findAllByMemberId(memberId, orderSearchCond, pageable);
    }

    public Orders findById(Long orderId, Long memberId) {
        Member findMember = memberRepository.findById(memberId).orElseThrow(() ->
                new MemberException(MemberErrorResult.MEMBER_NOT_FOUND));

        Orders findOrder = orderRepository.findById(orderId).orElseThrow(() ->
                new OrderException(OrderErrorResult.ORDER_NOT_FOUND));

        if(!findMember.getId().equals(findOrder.getMember().getId())){
            throw new OrderException(OrderErrorResult.UNAUTHORIZED);
        }
        return findOrder;

    }
}
