package service.Graphics;

import dao.Comment.CommentDao;
import dao.Company.CompanyDao;
import dao.Contract.ContractDao;
import entity.Company;
import entity.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vladkvn on 13.12.2016.
 */
@Component
public class GraphicsServiceImpl implements GraphicsService {
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private ContractDao contractDao;
    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Integer> getGraphicData()
    {
        List<Contract> list = contractDao.getAllContracts();
        List<Integer> resultList = new ArrayList<Integer>();
        Integer kol = 0;
        for (int i = 0; i < list.size(); i++) {
            kol+=commentDao.getCommentToContract(list.get(i).getId()).size();
            System.out.println(kol);
            resultList.add(i,kol);
        }
        return resultList;
    }

    @Override
    public List<Integer> getGraphicData2() {
        List<Company> compamies = companyDao.allCompanies();
        List<Integer> gd = new ArrayList<Integer>();
        int kol=0;
        for (int i = 0; i < compamies.size(); i++) {
            kol = contractDao.getAllForCompany(compamies.get(i).getCompanyId()).size();
            System.out.println("Количество у комании : "+kol);
            gd.add(i, kol);
        }
        return gd;
    }
    @Override
    public List<String> getGraphic2Legend() {
        List<Company> compamies = companyDao.allCompanies();
        List<String> legend = new ArrayList<String>();
        for (int i = 0; i < compamies.size(); i++) {
            legend.add(i, compamies.get(i).getCompanyName());
        }
        return legend;
    }
}
