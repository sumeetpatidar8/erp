package com.sumeet.erp.orders;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "company_id")
  private com.sumeet.erp.org.Company company;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private com.sumeet.erp.auth.User createdBy;

  @Enumerated(EnumType.STRING)
  private OrderStatus status = OrderStatus.PENDING;

  private BigDecimal totalAmount = BigDecimal.ZERO;

  @Column(name = "created_at")
  private Instant createdAt = Instant.now();

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<OrderLine> orderLines;

  public enum OrderStatus {
    PENDING, APPROVED, SHIPPED, DELIVERED, CANCELLED
  }

  // Getters and Setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public com.sumeet.erp.org.Company getCompany() { return company; }
  public void setCompany(com.sumeet.erp.org.Company company) { this.company = company; }

  public com.sumeet.erp.auth.User getCreatedBy() { return createdBy; }
  public void setCreatedBy(com.sumeet.erp.auth.User createdBy) { this.createdBy = createdBy; }

  public OrderStatus getStatus() { return status; }
  public void setStatus(OrderStatus status) { this.status = status; }

  public BigDecimal getTotalAmount() { return totalAmount; }
  public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

  public Instant getCreatedAt() { return createdAt; }
  public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

  public List<OrderLine> getOrderLines() { return orderLines; }
  public void setOrderLines(List<OrderLine> orderLines) { this.orderLines = orderLines; }
}