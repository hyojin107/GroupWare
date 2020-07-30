package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import dao.EmployeeDaoImpl;
import dao.IEmployeeDao;
import vo.Boardemp_joinVO;
import vo.EmployeeVO;
import vo.Employee_joinVO;
import vo.ImgFileVO;

public class EmployeeServiceImpl extends UnicastRemoteObject implements IEmployeeService{

	private IEmployeeDao dao;
	private static EmployeeServiceImpl service;
	private EmployeeServiceImpl() throws RemoteException {
		dao = EmployeeDaoImpl.getInstance();
	}
	
	public static EmployeeServiceImpl getInstance() throws RemoteException {
		if(service == null) service = new EmployeeServiceImpl();
		return service;
	}

	@Override
	public int insertEmp(EmployeeVO empVo) throws RemoteException {
		return dao.insertEmp(empVo);
	}

	@Override
	public int updateEmp(Map<String, String> map) throws RemoteException {
		return dao.updateEmp(map);
	}

	@Override
	public List<EmployeeVO> getAllEmp() throws RemoteException {
		return dao.getAllEmp();
	}

	@Override
	public List<Employee_joinVO> searchEmp(Map<String, String> map) throws RemoteException {
		return dao.searchEmp(map);
	}

	@Override
	public EmployeeVO getEmpLogin(int empNo) throws RemoteException {
		return dao.getEmpLogin(empNo);
	}

	@Override
	public int setImgPhoto(ImgFileVO fileVo) throws RemoteException {
		return dao.setImgPhoto(fileVo);
	}

	@Override
	public int setImgSign(ImgFileVO fileVo) throws RemoteException {
		return dao.setImgSign(fileVo);
	}

	@Override
	public ImgFileVO getImgFile(int empNo) throws RemoteException {
		return dao.getImgFile(empNo);
	}
	
	@Override
	public List<Employee_joinVO> allemplist(Map<String, Integer> map) throws RemoteException {
		return dao.allemplist(map);
	}
	
	@Override
	public int gikwonenroll(EmployeeVO empVo) throws RemoteException {
		return dao.gikwonenroll(empVo);
	}
	
	
	@Override
	public List<Employee_joinVO> kido(Map<String, Integer> map) throws RemoteException {
		return dao.kido(map);
	}

	
	
}


















