package com.ctyun.dao;

import com.ctyun.domain.Company;

import java.util.List;

/**
 * @author hga
 * @date 2019-07-21 21:47
 */
public interface CompanyDao {
    List<Company> findAllCompany();

    void addCompany(Company company);

    void update(Company company);

    void delete(String id);
    Company findCompanyById(String id);
}