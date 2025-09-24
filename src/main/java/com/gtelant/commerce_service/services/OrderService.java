package com.gtelant.commerce_service.services;

import com.gtelant.commerce_service.models.*;
import com.gtelant.commerce_service.repositories.InvoiceRepository;
import com.gtelant.commerce_service.repositories.OrderDetailRepository;
import com.gtelant.commerce_service.repositories.OrderRepository;
import com.gtelant.commerce_service.repositories.ProductRepository;
import com.gtelant.commerce_service.requests.InvoiceRequest;
import com.gtelant.commerce_service.requests.OrderDetailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    //交集的寫法 Specification
//    public Page<Orders> getAllOrders(String query, Boolean hasNewsletter, Integer segmentId, PageRequest pageRequest) {
//        Specification<Orders> spec = orderSpecification(query, hasNewsletter, segmentId);
//        return orderRepository.findAll(spec, pageRequest);
//    }
//
//    private Specification<Orders> orderSpecification(String queryName) {
//        return ((root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//            // if predicates.size() = 3 how many "AND"? => 2
//            //if predicates.size() = 8  how many "AND"? => 7
//
//            if (queryName != null && !queryName.isEmpty()) {
//                predicates.add(criteriaBuilder.or(
//                        criteriaBuilder.like(criteriaBuilder
//                                .lower(root.get("firstName")), "%" + queryName.toLowerCase() + "%")
//                        , criteriaBuilder.like(criteriaBuilder
//                                .lower(root.get("lastName")), "%" + queryName.toLowerCase() + "%")
//                ));
//            }
//
//            Predicate[] predicateArray = predicates.toArray(new Predicate[0]);
//            return criteriaBuilder.and(predicateArray);
//        });
//    }

    public Optional<Orders> getOrderById(int id) {
        return orderRepository.findById(id);
    }

    public Orders createOrder(Orders orders) {
        return orderRepository.save(orders);
    }

    public Orders updateOrder(int id,Orders orders){
        if(orderRepository.existsById(id)){
            orders.setOrderId(id);
            return orderRepository.save(orders);
        }
        return null;
    }

    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }

    public void assignOrderDetail(int orderId, int productId, OrderDetailRequest orderDetailRequest) {
        Optional<Products> productsOptional = productRepository.findById(productId);
        Optional<Orders> ordersOptional = orderRepository.findById(orderId);

        if(ordersOptional.isPresent() && productsOptional.isPresent() ) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrders(ordersOptional.get());
            orderDetail.setProducts(productsOptional.get());
            orderDetail.setQuantity(orderDetailRequest.getQuantity());
            orderDetail.setCreationDate(orderDetailRequest.getCreationDate());
            orderDetail.setCreatedBy(orderDetailRequest.getCreatedBy());
            orderDetail.setLastUpdateDate(orderDetailRequest.getLastUpdateDate());
            orderDetail.setLastUpdatedBy(orderDetailRequest.getLastUpdatedBy());
            orderDetailRepository.save(orderDetail);
        }
    }

    public void assignInvoiceToOrder(int orderId, InvoiceRequest invoiceRequest) {
        Optional<Orders> orders = orderRepository.findById(orderId);

        if(orders.isPresent()) {
            Invoices invoices = new Invoices();
            invoices.setOrders(orders.get());
            invoices.setInvoiceDate(invoiceRequest.getInvoiceDate());
            invoices.setCreationDate(invoiceRequest.getCreationDate());
            invoices.setCreatedBy(invoiceRequest.getCreatedBy());
            invoices.setLastUpdateDate(invoiceRequest.getLastUpdateDate());
            invoices.setLastUpdatedBy(invoiceRequest.getLastUpdatedBy());
            invoiceRepository.save(invoices);
        }}
}