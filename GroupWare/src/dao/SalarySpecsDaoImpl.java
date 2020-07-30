package dao;

import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildedSqlMapClient;
import vo.Address_BookVO;
import vo.Salary_SpecsVO;

public class SalarySpecsDaoImpl implements ISalarySpecsDao{

	private static SalarySpecsDaoImpl instance;
	private SqlMapClient smc;
	
	private SalarySpecsDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static SalarySpecsDaoImpl getInstance(){
		if(instance == null) instance = new SalarySpecsDaoImpl();
		return instance;
	}

	@Override
	public List<Salary_SpecsVO> getAllSalary(int emp_no) {
		List<Salary_SpecsVO> salarySpecList = null;

		try {
			salarySpecList = smc.queryForList("groupware.getAllSalary",emp_no);
		} catch (Exception e) {
			salarySpecList = null;
			e.printStackTrace();
		}			
		return salarySpecList;
	}

	@Override
	public List<Salary_SpecsVO> searchSalary(Map<String, Integer> map) {
		List<Salary_SpecsVO> salarySpecList = null;

		try {
			salarySpecList = smc.queryForList("groupware.searchSalary", map);
		} catch (Exception e) {
			salarySpecList = null;
			e.printStackTrace();
		}			
		return salarySpecList;
	}

}
