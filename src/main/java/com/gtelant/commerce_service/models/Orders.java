package com.gtelant.commerce_service.models;

import com.gtelant.commerce_service.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus = OrderStatus.DELIVERED;
    @Column(name = "order_date")
    private LocalDate orderDate;
    @Column(name = "returned")
    private boolean returned = false;
    @Column(name = "delete_at")
    private LocalDateTime deleteAt;
    @CreationTimestamp
    @Column(name = "creation_date",nullable = false)
    private LocalDateTime creationDate;
    @Column(name = "created_by",nullable = false)
    private String createdBy;
    @UpdateTimestamp
    @Column(name = "last_update_date",nullable = false)
    private LocalDateTime lastUpdateDate;
    @Column(name = "last_updated_by",nullable = false)
    private String lastUpdatedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private Users users;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "od_detail_id",referencedColumnName = "od_detail_id")
    private OrderDetail orderDetails;
}
