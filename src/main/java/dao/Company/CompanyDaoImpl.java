package dao.Company;

import entity.Company;
import entity.Contract;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vladkvn on 12.12.2016.
 */
@Transactional
@Component
public class CompanyDaoImpl implements CompanyDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public List<Company> allCompanies() {
        String hql = "FROM entity.Company";
        String params[] = new String[]{};
        String values[] = new String[]{};
        List<Company> list = (List<Company>) hibernateTemplate.findByNamedParam(hql, params, values);
        return list;
    }

    @Override
    public Company getCompanyById(int companyId) {
       return hibernateTemplate.get(Company.class,companyId);
    }

    @Override
    public Company getCompanyByName(String companyName) {
        String hql = "FROM  entity.Company WHERE company_name = :companyName";
        String params[] = new String[]{"companyName"};
        String values[] = new String[]{companyName};
        List<?> list = hibernateTemplate.findByNamedParam(hql, params, values);
        return (Company)list.get(0);
    }

    @Override
    public void save(Company company) {
       // company.setCompanyId(3);
        hibernateTemplate.save(company);
    }
}
