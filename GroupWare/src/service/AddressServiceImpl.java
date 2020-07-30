package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import dao.AddressDaoImpl;
import dao.IAddressDao;
import vo.Address_BookVO;
import vo.Address_MemberVO;
import vo.Address_joinVO;
import vo.DepartmentsVO;
import vo.Departments_joinVO;
import vo.EmployeeVO;
import vo.OutsideVO;

public class AddressServiceImpl extends UnicastRemoteObject implements IAddressService {
	private IAddressDao dao;
	
	private static AddressServiceImpl service;
	private AddressServiceImpl() throws RemoteException {
		dao = AddressDaoImpl.getInstance();
	}
	
	public static AddressServiceImpl getInstance() {
		try {
			if(service == null) service = new AddressServiceImpl();
		} catch (Exception e) { }
		return service;
	}

	@Override
	public List<DepartmentsVO> getDepartment() throws RemoteException {
		return dao.getDepartment();
	}

	@Override
	public List<EmployeeVO> getAllAddress(Map<String, Integer> map) throws RemoteException {
		return dao.getAllAddress(map);
	}

	@Override
	public List<Departments_joinVO> getDepAddress(int depNo) throws RemoteException {
		return dao.getDepAddress(depNo);
	}

	@Override
	public List<Address_BookVO> getGroupt(int empNo) throws RemoteException {
		return dao.getGroupt(empNo);
	}

	@Override
	public int addToMyAddress(Address_MemberVO addrMemVo) throws RemoteException {
		return dao.addToMyAddress(addrMemVo);
	}

	@Override
	public int insertOutside(OutsideVO outVo) throws RemoteException {
		return dao.insertOutside(outVo);
	}

	@Override
	public int deleteMyAddress(Map<String, Integer> map) throws RemoteException {
		return dao.deleteMyAddress(map);
	}

	@Override
	public int deleteMyAddress(int outNo) throws RemoteException {
		return dao.deleteMyAddress(outNo);
	}

	@Override
	public int addGroup(Address_BookVO addrBookVo) throws RemoteException {
		return dao.addGroup(addrBookVo);
	}

	@Override
	public int deleteGroup(int bookNo) throws RemoteException {
		return dao.deleteGroup(bookNo);
	}

	@Override
	public List<Departments_joinVO> addressAll(Map<String, Integer> map) throws RemoteException {
		return dao.addressAll(map);
	}
	
	@Override
	public List<Address_joinVO> myaddressAll(Map<String, Integer> map) throws RemoteException {
		return dao.myaddressAll(map);
	}

	@Override
	public int insertOutside(Address_joinVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Address_BookVO> getoutsideAll(Map<String, Integer> map) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getoutsideAll(map);
	}
	
	
	
}
























