package entity;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vladkvn on 12.12.2016.
 */

@Entity
@Table(name= "company")
@Transactional
public class Company {
    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int companyId;

    public Company(String companyName) {

        this.companyName = companyName;
    }

    @Column(name = "company_name")
    String companyName="";

    @OneToMany
    @JoinTable(name = "company_contracts",
            joinColumns = {@JoinColumn(name = "company_id")},
            inverseJoinColumns = {@JoinColumn(name = "contract_id")})
    List<Contract> contractList = new ArrayList<Contract>();

    public Company() {
    }

    public List<Contract> getContractList() {

        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

}
