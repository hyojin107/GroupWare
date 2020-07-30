package dao;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import vo.AttendanceVO;
import vo.DepartmentsVO;
import vo.EmployeeVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성해서
 * Service에 전달하는 DAO의 interface
 * 
 * @author 2팀
 *
 */
public interface IAttendanceDao {
	
	/**
	 * 출근시간을 입력하는 메서드
	 * @param empNo 사원번호
	 * @return 성공시 1
	 */
	int insertStartDate(int empNo);
	
	/**
	 * 출근했는지 확인하는 메서드
	 * @param empNo
	 * @return
	 * @throws RemoteException
	 */
	int selectStartDate(int empNo) throws RemoteException;
	
	/**
	 * 퇴근시간을 입력하는 메서드
	 * @param empNo 사원번호
	 * @return 성공시 1
	 */
	int updateEndDate(int empNo);
	
	/**
	 * 일별 근태를 불러오는 메서드
	 * @param empNo 사원번호
	 * @return VO객체가 담긴 List 객체
	 */
	AttendanceVO getAttCalander(Map<String, Integer> map);
	
	/**
	 * 전체 부서번호를 불러오는 메서드
	 * @return 부서VO 객체가 담긴 List
	 */
	List<DepartmentsVO> getDepartment();
	
	/**
	 * 부서원의 1년 근태를 불러오는 메서드
	 * @param map 부서번호, 년도가 담긴 Map 객체
	 * @return 근태VO 객체가 담긴 List
	 */
	List<AttendanceVO> getYearDep(Map<String, Integer> map);
	
	/**
	 * 1년치 근태 불러오는 메서드
	 * @param map
	 * @return
	 * @throws RemoteException
	 */
	List<AttendanceVO> getYear(Map<String, Integer> map) throws RemoteException;
	
	/**
	 * 부서원 사번 불러오는 메서드
	 * @param department_no
	 * @return
	 * @throws RemoteException
	 */
	List<EmployeeVO> getDepEmp(int department_no) throws RemoteException;
	
}
