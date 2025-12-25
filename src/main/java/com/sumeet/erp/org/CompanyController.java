package com.sumeet.erp.org;

import com.sumeet.erp.common.web.Pagination;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
@Tag(name = "Company Management", description = "APIs for managing companies")
public class CompanyController {

  private final CompanyService companyService;
  private final Pagination pagination;

  public CompanyController(CompanyService companyService, Pagination pagination) {
    this.companyService = companyService;
    this.pagination = pagination;
  }

  @PostMapping
  @Operation(summary = "Create a new company")
  public ResponseEntity<Company> createCompany(@RequestBody Company company) {
    Company saved = companyService.createCompany(company);
    return ResponseEntity.created(URI.create("/api/v1/companies/" + saved.getId())).body(saved);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get company by ID")
  public ResponseEntity<Company> getCompany(@PathVariable Long id) {
    return companyService.getCompanyById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping
  @Operation(summary = "Get all companies (paginated)")
  public Page<Company> getCompanies(
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "20") Integer size,
      @RequestParam(defaultValue = "id,desc") String sort) {
    Pagination.Page p = pagination.of(page, size, sort);
    return companyService.getCompaniesPaginated(p);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update company")
  public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company company) {
    try {
      Company updated = companyService.updateCompany(id, company);
      return ResponseEntity.ok(updated);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete company")
  public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
    try {
      companyService.deleteCompany(id);
      return ResponseEntity.noContent().build();
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }
}