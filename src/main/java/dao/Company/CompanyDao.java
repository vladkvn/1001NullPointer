package dao.Company;

import entity.Company;

import java.util.List;

/**
 * Created by vladkvn on 12.12.2016.
 */
public interface CompanyDao {
    List<Company> allCompanies();
    Company getCompanyById(int companyId);
    Company getCompanyByName(String companyName);
    void save(Company company);
}
