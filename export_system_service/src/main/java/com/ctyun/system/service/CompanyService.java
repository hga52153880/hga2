package com.ctyun.system.service;

import com.ctyun.domain.Company;

import java.util.List;

public interface CompanyService {
    List<Company> findAllCompany();
    void addCompany(Company company);

    void update(Company company);

    void delete(String id);
    Company findCompanyById(String id);
}
