package dao;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import vo.Address_BookVO;
import vo.Address_MemberVO;
import vo.Address_joinVO;
import vo.DepartmentsVO;
import vo.Departments_joinVO;
import vo.EmployeeVO;
import vo.OutsideVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성해서
 * Service에 전달하는 DAO의 interface
 * 
 * @author 2팀
 *
 */
public interface IAddressDao {
	
	/**
	 * 전체 부서 정보를 DB에서 가져와 각각 DepartmentVO에 담고, 이 DepartmentVO객체를 List에 넣어서 반환하는 메서드
	 * @return 전체 부서정보(DepartmentVO)가 저장된 List객체
	 */
	List<DepartmentsVO> getDepartment();
	
	/**
	 * Map에 설정된  start번째부터 end번째 까지의 EmployeeVO객체를 List에 넣어서 반환하는 메서드 
	 * @param map 시작(start)번호와 끝(end)번호가 저장된 Map객체
	 * @return 검색된 데이터의 List객체
	 */
	List<EmployeeVO> getAllAddress(Map<String, Integer> map);
	
	/**
	 * 부서 번호를 이용해서 부서원 전부를 EmployeeVO 리스트에 넣어서 반환하는 메서드
	 * @param depNo 부서번호
	 * @return 부서원 전부가 담긴 List 객체
	 */
	List<Departments_joinVO> getDepAddress(int depNo);
	
	/**
	 * 접속자가 생성한 나의 주소록들을 List객체에 담아 반환하는 메서드
	 * @param empNo 사원번호
	 * @return 나의 주소록 List 객체
	 */
	List<Address_BookVO> getGroupt(int empNo);
	
	/**
	 * 나의 주소록에 회사원을 등록하는 메서드
	 * @param addrMemVo 주소록 번호와 등록할 사원번호가 담긴 VO 객체
	 * @return 등록성공시 1 실패시 0
	 */
	int addToMyAddress(Address_MemberVO addrMemVo);
	
	/**
	 * 외부인을 나의 주소록에 등록하는 메서드
	 * @param outVo 외부인VO 객체
	 * @return 성공시 1
	 */
	int insertOutside(OutsideVO outVo);
	
	/**
	 * 나의 주소록에 인물들을 삭제하는 메서드 
	 * @param map 주소록 번호와 삭제할 사원번호가 저장된 Map 객체
	 * @return 성공시 1
	 */
	int deleteMyAddress(Map<String, Integer> map);
	
	/**
	 * 나의 주소록의 외부인을 삭제하는 메서드
	 * @param outNo 외부인 번호
	 * @return 성공시 1
	 */
	int deleteMyAddress(int outNo);
	
	/**
	 * 부서랑 사원 조인해서 전체주소록을 가져오는 메서드
	 * @return List<Departments_joinVO> 전체주소록VO
	 */
	List<Departments_joinVO> addressAll(Map<String, Integer> map);

	
	
	/**
	 * 주소록이랑 주소록멤버랑 외부인 조인해서 나의 주소록을 가져오는 메서드
	 * @return List<Address_joinVO> 나의주소록VO
	 */
	List<Address_joinVO> myaddressAll(Map<String, Integer> map);
	
	
	/**
	 * 주소록 outside외부인 다 불러오기 + 이름
	 * @param map
	 * @return
	 * @throws RemoteException
	 */
	List<Address_BookVO> getoutsideAll(Map<String, Integer> map);
	/**
	 * 나의 주소록을 추가하는 메서드
	 * @param addrBookVo 그룹명,사원번호가 담긴 VO객체
	 * @return 성공시 1
	 */
	int addGroup(Address_BookVO addrBookVo);
	
	/**
	 * 나의 주소록을 삭제하는 메서드
	 * @param bookNo 주소록 번호
	 * @return 성공시 1
	 */
	int deleteGroup(int bookNo);
	
}


















