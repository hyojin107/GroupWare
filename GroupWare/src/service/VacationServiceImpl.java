package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import dao.IVacationDao;
import dao.VacationDaoImpl;
import vo.MyVacationVO;
import vo.Payment_LineVO;
import vo.VacationCheckVO;
import vo.VacationVO;

public class VacationServiceImpl extends UnicastRemoteObject implements IVacationService{

	private IVacationDao dao;
	private static VacationServiceImpl service;
	private VacationServiceImpl() throws RemoteException {
		dao = VacationDaoImpl.getInstance();
	}
	public static VacationServiceImpl getInstance() {
		try {
			if(service == null) service = new VacationServiceImpl();
		} catch (Exception e) {	}
		return service;
	}

	@Override
	public List<MyVacationVO> getAllVacation(Map<String, Integer> map) throws RemoteException {
		return dao.getAllVacation(map);
	}

	@Override
	public int insertVacation(VacationVO vacVo) throws RemoteException {
		return dao.insertVacation(vacVo);
	}

	@Override
	public int updateVacCheck(Map<String, Integer> map) throws RemoteException {
		return dao.updateVacCheck(map);
	}
	@Override
	public List<Payment_LineVO> getCheckVacList(int emp_no) throws RemoteException {
		return dao.getCheckVacList(emp_no);
	}
	@Override
	public VacationCheckVO getVacListNo(int pay_no) throws RemoteException {
		return dao.getVacListNo(pay_no);
	}

}
