package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import dao.AttendanceDaoImpl;
import dao.IAttendanceDao;
import vo.AttendanceVO;
import vo.DepartmentsVO;
import vo.EmployeeVO;

public class AttendanceServiceImpl extends UnicastRemoteObject implements IAttendanceService {
	
	private IAttendanceDao dao;
	private static AttendanceServiceImpl service;
	private AttendanceServiceImpl() throws RemoteException {
		dao = AttendanceDaoImpl.getInstance();
	}
	
	public static AttendanceServiceImpl getInstance() throws RemoteException {
			if(service == null) service = new AttendanceServiceImpl();
		return service;
	}

	@Override
	public int insertStartDate(int empNo) throws RemoteException {
		return dao.insertStartDate(empNo);
	}

	@Override
	public int updateEndDate(int empNo) throws RemoteException {
		return dao.updateEndDate(empNo);
	}

	@Override
	public AttendanceVO getAttCalander(Map<String, Integer> map) throws RemoteException {
		return dao.getAttCalander(map);
	}

	@Override
	public List<DepartmentsVO> getDepartment() throws RemoteException {
		return dao.getDepartment();
	}

	@Override
	public List<AttendanceVO> getYearDep(Map<String, Integer> map) throws RemoteException {
		return dao.getYearDep(map);
	}

	@Override
	public List<AttendanceVO> getYear(Map<String, Integer> map) throws RemoteException {
		return dao.getYear(map);
	}

	@Override
	public List<EmployeeVO> getDepEmp(int department_no) throws RemoteException {
		return dao.getDepEmp(department_no);
	}

	@Override
	public int selectStartDate(int empNo) throws RemoteException {
		return dao.selectStartDate(empNo);
	}
	
	
}





