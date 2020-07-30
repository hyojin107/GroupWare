package dao;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildedSqlMapClient;
import vo.Address_BookVO;
import vo.Address_MemberVO;
import vo.Address_joinVO;
import vo.DepartmentsVO;
import vo.Departments_joinVO;
import vo.EmployeeVO;
import vo.OutsideVO;

public class AddressDaoImpl implements IAddressDao{

	private static AddressDaoImpl instance;
	private SqlMapClient smc;
	
	private AddressDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static AddressDaoImpl getInstance(){
		if(instance == null) instance = new AddressDaoImpl();
		return instance;
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
	public List<EmployeeVO> getAllAddress(Map<String, Integer> map) {
		List<EmployeeVO> employeeList = null;

		try {
			employeeList = smc.queryForList("groupware.getAllAddress", map);
		} catch (Exception e) {
			employeeList = null;
			e.printStackTrace();
		}			
		return employeeList;
	}

	@Override
	public List<Departments_joinVO> getDepAddress(int depNo) {
		List<Departments_joinVO> employeeList = null;

		try {
			employeeList = smc.queryForList("groupware.getDepAddress", depNo);
		} catch (Exception e) {
			employeeList = null;
			e.printStackTrace();
		}			
		return employeeList;
	}

	@Override
	public List<Address_BookVO> getGroupt(int empNo) {
		List<Address_BookVO> address_bookList = null;

		try {
			address_bookList = smc.queryForList("groupware.getGroupt", empNo);
		} catch (Exception e) {
			address_bookList = null;
			e.printStackTrace();
		}			
		return address_bookList;
	}

	@Override
	public int addToMyAddress(Address_MemberVO addrMemVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("groupware.addToMyAddress", addrMemVo);
			cnt = obj == null ? 1 : 0;
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}



	@Override
	public int deleteMyAddress(Map<String, Integer> map) {
		int cnt = 0;
		try {
			Object obj = smc.delete("groupware.deleteMyAddress", map);
			cnt = obj == null ? 1 : 0;
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteMyAddress(int outNo) {
		int cnt = 0;
		try {
			Object obj = smc.delete("groupware.deleteMyAddress2", outNo);
			cnt = obj == null ? 1 : 0;
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	
	//혜진 
	
	@Override // 나의 주소록 출력(이름은join으로 얻어오기)
	public List<Address_BookVO> getoutsideAll(Map<String, Integer> map) {
		List<Address_BookVO> outList = null;

		try {
			outList = smc.queryForList("groupware.getoutsideAll",map);
		} catch (Exception e) {
			outList = null;
			e.printStackTrace();
		}
		return outList;
	}
	
	@Override //그룹추가 ok
	public int addGroup(Address_BookVO addrBookVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("groupware.addGroup", addrBookVo);
			cnt = obj == null ? 1 : 0;
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override //그룹삭제 ok
	public int deleteGroup(int bookNo) {
		int cnt = 0;
		try {
			Object obj = smc.delete("groupware.deleteGroup", bookNo);
			cnt = obj == null ? 1 : 0;
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override // 사람추가 ok
	public int insertOutside(OutsideVO outVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("groupware.insertOutside", outVo);
			cnt = obj == null ? 1 : 0;
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}
	
	
	
	@Override
	public List<Departments_joinVO> addressAll(Map<String, Integer> map) {
		List<Departments_joinVO> address_bookList = null;

		try {
			address_bookList = smc.queryForList("groupware.addressall",map);
		} catch (Exception e) {
			address_bookList = null;
			e.printStackTrace();
		}
		return address_bookList;
	}
	
	
	
	@Override
	public List<Address_joinVO> myaddressAll(Map<String, Integer> map) {
		List<Address_joinVO> myaddress_bookList = null;

		try {
			myaddress_bookList = smc.queryForList("groupware.myaddressall",map);
		} catch (Exception e) {
			myaddress_bookList = null;
			e.printStackTrace();
		}
		return myaddress_bookList;
	}


	
	
	
	
}
























