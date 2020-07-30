package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import dao.ISalarySpecsDao;
import dao.SalarySpecsDaoImpl;
import vo.Salary_SpecsVO;

public class SalarySpecsServicecImpl extends UnicastRemoteObject implements ISalarySpecsService {

	private ISalarySpecsDao dao;
	private static SalarySpecsServicecImpl service;
	private SalarySpecsServicecImpl() throws RemoteException {
		dao = SalarySpecsDaoImpl.getInstance();
	}
	public static SalarySpecsServicecImpl getInstance() {
		try {
			if(service == null) service = new SalarySpecsServicecImpl();
		} catch (Exception e) {	}
		return service;
	}

	@Override
	public List<Salary_SpecsVO> getAllSalary(int emp_no) throws RemoteException {
		return dao.getAllSalary(emp_no);
	}

	@Override
	public List<Salary_SpecsVO> searchSalary(Map<String, Integer> map) throws RemoteException {
		return dao.searchSalary(map);
	}

}
