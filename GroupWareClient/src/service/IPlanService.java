package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import vo.PlanVO;
import vo.PlanVO_join;

/**
 * Service객체는 DAO에 작성된 메서드를 원하는 작업에 맞게 호출하여 결과를 받아오고,
 * 받아온 자료를 Controller에게 보내주는 역할을 수행한다.
 * 
 * 보통 DAO의 메서드와 같은 구조로 되어있다.
 * 
 * @author 2팀
 *
 */
public interface IPlanService extends Remote {

	/**
	 * 페이지네이션 모든 일정 불러오는 메서드
	 * @param map 시작번호, 끝번호 담긴 Map 객체
	 * @return 일정VO 담긴 List
	 */
	List<PlanVO> getAllPlan(Map<String, Integer> map) throws RemoteException;
	
	/**
	 * 페이지 네이션 모든 일정과 emp_name을 불러오는 메소드
	 * @param map 시작번호, 끝번호 담긴 Map 객체 
	 * @return 일정VO가 담긴 List 
	 */
	List<PlanVO_join> getAllPlanEmp(Map<String, Integer> map) throws RemoteException;
	/**
	 * 일정 등록 메서드
	 * @param planVo 일정VO
	 * @return 성공시 1
	 */
	int insertPlan(PlanVO_join planVo) throws RemoteException;
	
	/**
	 * 일정 수정 메서드
	 * @param planVo 일정VO
	 * @return 성공시 1
	 */
	int updatePlan(PlanVO_join planVo) throws RemoteException;
	
	/**
	 * 일정 삭제 메서드
	 * @param planNo 일정번호
	 * @return 성공시 1
	 */
	int deletePlan(int planNo) throws RemoteException;
	
	
}
