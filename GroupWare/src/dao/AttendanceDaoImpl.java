package dao;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildedSqlMapClient;
import vo.AttendanceVO;
import vo.DepartmentsVO;
import vo.EmployeeVO;

public class AttendanceDaoImpl implements IAttendanceDao{

	private static AttendanceDaoImpl instance;
	private SqlMapClient smc;
	
	private AttendanceDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static AttendanceDaoImpl getInstance(){
		if(instance == null) instance = new AttendanceDaoImpl();
		return instance;
	}

	@Override
	public int insertStartDate(int empNo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("groupware.insertStartDate", empNo);
			cnt = obj == null ? 1 : 0;
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateEndDate(int empNo) {
		int cnt = 0;
		try {
			cnt = smc.update("groupware.updateEndDate", empNo);
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public AttendanceVO getAttCalander(Map<String, Integer> map) {
		AttendanceVO vo = null;

		try {
			vo = (AttendanceVO) smc.queryForObject("groupware.getAttCalander", map);
		} catch (Exception e) {
			vo = null;
			e.printStackTrace();
		}			
		return vo;
	}

	@Override
	public List<DepartmentsVO> getDepartment() {
		List<DepartmentsVO> departmentsList = null;

		try {
			departmentsList = smc.queryForList("groupware.getDepartment");
		} catch (Exception e) {
			departmentsList = null;
			e.printStackTrace();
		}			
		return departmentsList;
	}

	@Override
	public List<AttendanceVO> getYearDep(Map<String, Integer> map) {
		List<AttendanceVO> attendanceList = null;

		try {
			attendanceList = smc.queryForList("groupware.getYearDep", map);
		} catch (Exception e) {
			attendanceList = null;
			e.printStackTrace();
		}			
		return attendanceList;
	}

	@Override
	public List<AttendanceVO> getYear(Map<String, Integer> map) throws RemoteException {
		List<AttendanceVO> list = null;
		try {
			list = smc.queryForList("groupware.getYear", map);
		} catch (Exception e) {
			list = null;
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<EmployeeVO> getDepEmp(int department_no) throws RemoteException {
		List<EmployeeVO> list = null;
		try {
			list = smc.queryForList("groupware.getDepEmp", department_no);
		} catch (Exception e) {
			list = null;
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int selectStartDate(int empNo) throws RemoteException {
		int cnt = 0;
		try {
			cnt = (int) smc.queryForObject("groupware.selectStartDate",empNo);
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		
		return cnt;
	}

}
