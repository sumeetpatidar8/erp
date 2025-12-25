package com.sumeet.erp.org;

import com.sumeet.erp.common.web.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompanyService {

  private final CompanyRepository companyRepository;

  public CompanyService(CompanyRepository companyRepository) {
    this.companyRepository = companyRepository;
  }

  public Company createCompany(Company company) {
    if (companyRepository.existsByName(company.getName())) {
      throw new IllegalArgumentException("Company name already exists");
    }
    return companyRepository.save(company);
  }

  public Optional<Company> getCompanyById(Long id) {
    return companyRepository.findById(id);
  }

  public List<Company> getAllCompanies() {
    return companyRepository.findAll();
  }

  public Page<Company> getCompaniesPaginated(Pagination.Page page) {
    PageRequest pageRequest = PageRequest.of(page.page(), page.size(), Sort.by(page.sort()));
    return companyRepository.findAll(pageRequest);
  }

  public Company updateCompany(Long id, Company updatedCompany) {
    return companyRepository.findById(id)
        .map(company -> {
          company.setName(updatedCompany.getName());
          company.setDescription(updatedCompany.getDescription());
          return companyRepository.save(company);
        })
        .orElseThrow(() -> new IllegalArgumentException("Company not found"));
  }

  public void deleteCompany(Long id) {
    if (!companyRepository.existsById(id)) {
      throw new IllegalArgumentException("Company not found");
    }
    companyRepository.deleteById(id);
  }
}