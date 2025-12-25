package com.sumeet.erp.inventory;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;

@Entity
@Table(name = "warehouses")
public class Warehouse {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 255)
  private String name;

  @Size(max = 500)
  private String location;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "company_id")
  private com.sumeet.erp.org.Company company;

  @Column(name = "created_at")
  private Instant createdAt = Instant.now();

  // Getters and Setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getLocation() { return location; }
  public void setLocation(String location) { this.location = location; }

  public com.sumeet.erp.org.Company getCompany() { return company; }
  public void setCompany(com.sumeet.erp.org.Company company) { this.company = company; }

  public Instant getCreatedAt() { return createdAt; }
  public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}