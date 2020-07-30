package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import vo.AttendanceVO;
import vo.DepartmentsVO;
import vo.EmployeeVO;

/**
 * Service객체는 DAO에 작성된 메서드를 원하는 작업에 맞게 호출하여 결과를 받아오고,
 * 받아온 자료를 Controller에게 보내주는 역할을 수행한다.
 * 
 * 보통 DAO의 메서드와 같은 구조로 되어있다.
 * 
 * @author 2팀
 *
 */
public interface IAttendanceService extends Remote {

	/**
	 * 출근시간을 입력하는 메서드
	 * @param empNo 사원번호
	 * @return 성공시 1
	 */
	int insertStartDate(int empNo) throws RemoteException;
	
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
	int updateEndDate(int empNo) throws RemoteException;
	
	/**
	 * 일별 근태를 불러오는 메서드
	 * @param empNo 사원번호
	 * @return VO객체가 담긴 List 객체
	 */
	AttendanceVO getAttCalander(Map<String, Integer> map) throws RemoteException;
	
	/**
	 * 전체 부서번호를 불러오는 메서드
	 * @return 부서VO 객체가 담긴 List
	 */
	List<DepartmentsVO> getDepartment() throws RemoteException;
	
	/**
	 * 부서원의 1년 근태를 불러오는 메서드
	 * @param map 부서번호, 년도가 담긴 Map 객체
	 * @return 근태VO 객체가 담긴 List
	 */
	List<AttendanceVO> getYearDep(Map<String, Integer> map) throws RemoteException;
	
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
