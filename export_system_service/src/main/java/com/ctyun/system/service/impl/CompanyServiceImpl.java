package com.ctyun.system.service.impl;

import com.ctyun.dao.CompanyDao;
import com.ctyun.domain.Company;
import com.ctyun.system.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hga
 * @date 2019-07-21 21:53
 */
@Service


public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    public List<Company> findAllCompany() {
        List<Company> allCompany = companyDao.findAllCompany();
        return allCompany;
    }

    @Override

    public void addCompany(Company company) {
        companyDao.addCompany(company);

    }

    @Override
    public void update(Company company) {
        companyDao.update(company);
    }

    @Override
    public void delete(String id) {
        companyDao.delete(id);
        int a = 10 / 0;
    }

    @Override
    public Company findCompanyById(String id) {
        return companyDao.findCompanyById(id);
    }
}