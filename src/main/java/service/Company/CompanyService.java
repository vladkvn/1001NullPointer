package service.Company;

import entity.Company;
import entity.Contract;

import java.util.List;

/**
 * Created by vladkvn on 13.12.2016.
 */
public interface CompanyService {
    public void createCompany(Company company);
    public List<Contract> contractsCompany(int companyId);
    public Company viewCompany(int companyId);
    public List<Company> allCompanies();
}
