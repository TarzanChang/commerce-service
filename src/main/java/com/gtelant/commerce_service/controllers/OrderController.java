package com.gtelant.commerce_service.controllers;

import com.gtelant.commerce_service.mappers.OrderMapper;
import com.gtelant.commerce_service.models.OrderDetail;
import com.gtelant.commerce_service.models.Orders;
import com.gtelant.commerce_service.requests.*;
import com.gtelant.commerce_service.responses.OrderDetailResponse;
import com.gtelant.commerce_service.responses.OrderResponse;
import com.gtelant.commerce_service.services.OrderDetailService;
import com.gtelant.commerce_service.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Orders",description = "Order management APIs.")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailService orderDetailService;

    @Operation(summary = "Get all Orders",description = "Return a list of all Orders.")
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders(){
        List<Orders> ordersList = orderService.getAllOrders();
        return ResponseEntity.ok(ordersList.stream()
                .map(orders -> {
                    OrderResponse response = new OrderResponse(orders);
                    response.setOrderdetailResponse(new OrderDetailResponse(orders.getOrderDetails()));
                    return response;
                }).toList());
    }

//    @Operation(summary = "Get all Orders pagination", description = "Returns a page of orders")
//    @GetMapping("/pageForOrders")
//    public Page<OrderResponse> getAllOrdersPage(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(defaultValue = "") String query,
//    ) {
//        PageRequest pageRequest = PageRequest.of(page, size);
//        return productService.getAllProducts(query, pageRequest).map(orderMapper::toOrderResponse);
//    }

    @Operation(summary = "Get order by ID",description = "Returns a single order by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Successfully retrieved order."),
            @ApiResponse(responseCode = "404",description = "Order not found.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable int id){
        Optional<Orders> orders = orderService.getOrderById(id);
        if(orders.isPresent()){
            OrderResponse response = new OrderResponse(orders.get());
            response.setOrderdetailResponse(new OrderDetailResponse(orders.get().getOrderDetails()));
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
        //方法二
//        return users.map(value -> ResponseEntity.ok(userMapper.toUserResponse(value)))
//                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new order.",description = "Creates a new order and returns the created order.")
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest){
        Orders orders = orderMapper.toOrders(orderRequest);
        Orders createdOrder = orderService.createOrder(orders);
        return ResponseEntity.ok(orderMapper.toOrderResponse(createdOrder));
    }

    @Operation(summary = "Delete a order.",description = "Deletes a order by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Successfully deleted order."),
            @ApiResponse(responseCode = "404",description = "Order not found.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update review status.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Successfully retrieved category."),
            @ApiResponse(responseCode = "404",description = "Category not found.")
    })

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable int id, @RequestBody OrderRequest request){
        Orders existingOrders = orderService.getOrderById(id).orElseThrow(() -> new RuntimeException("找不到訂單 ID: " + id));
        Orders orders = orderMapper.toOrders(request);
        Orders updateOrder = orderService.updateOrder(id,orders);

        if(updateOrder == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderMapper.toOrderResponse(updateOrder));
    }

    @PostMapping("/{orderId}/products/{productId}")
    public ResponseEntity<Void> assignOrderDetail(@PathVariable int orderId, @PathVariable int productId, @RequestBody OrderDetailRequest orderDetailRequest){
        orderService.assignOrderDetail(orderId, productId, orderDetailRequest);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{orderId}")
    public ResponseEntity<Void> assignInvoiceToOrder(@PathVariable int orderId, @RequestBody InvoiceRequest invoiceRequest){
        orderService.assignInvoiceToOrder(orderId, invoiceRequest);
        return ResponseEntity.noContent().build();
    }

}
