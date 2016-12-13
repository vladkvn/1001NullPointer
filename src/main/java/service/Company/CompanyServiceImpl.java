package service.Company;

import dao.Company.CompanyDao;
import dao.Contract.ContractDao;
import entity.Company;
import entity.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by vladkvn on 13.12.2016.
 */
@Component
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyDao companyDao;
    @Autowired
    ContractDao contractDao;

    @Override
    public void createCompany(Company company) {
        companyDao.save(company);
    }

    @Override
    public List<Contract> contractsCompany(int companyId)
    {
        return contractDao.getAllForCompany(companyId);
    }

    @Override
    public Company viewCompany(int companyId) {
        return companyDao.getCompanyById(companyId);
    }


    @Override
    public List<Company> allCompanies() {
        return companyDao.allCompanies();
    }
}
