package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import dao.IPaymentDao;
import dao.PaymentDaoImpl;
import vo.Document_FormVO;
import vo.PaymentListVO;
import vo.PaymentVO;
import vo.Payment_LineVO;
import vo.ReferenceVO;
import vo.SangsinSearchVO;

public class PaymentServiceImpl extends UnicastRemoteObject implements IPaymentService {

	private IPaymentDao dao;
	private static PaymentServiceImpl service;
	private PaymentServiceImpl() throws RemoteException {
		dao = PaymentDaoImpl.getInstance();
	}
	
	public static PaymentServiceImpl getInstance() {
		try {
			if(service == null) service = new PaymentServiceImpl();
		} catch (Exception e) {	}
		return service;
	}

	@Override
	public List<PaymentListVO> getAllPay(int emp_no) throws RemoteException {
		return dao.getAllPay(emp_no);
	}

	@Override
	public List<SangsinSearchVO> getAllRef(int pay_no) throws RemoteException {
		return dao.getAllRef(pay_no);
	}

	@Override
	public List<Payment_LineVO> getAllPayLine(Map<String, Integer> map) throws RemoteException {
		return dao.getAllPayLine(map);
	}

	@Override
	public List<PaymentListVO> getComboPay(Map<String, Integer> map) throws RemoteException {
		return dao.getComboPay(map);
	}

	@Override
	public List<ReferenceVO> getComboRef(Map<String, Integer> map) throws RemoteException {
		return dao.getComboRef(map);
	}

	@Override
	public List<Payment_LineVO> getComboPayLine(Map<String, Integer> map) throws RemoteException {
		return dao.getComboPayLine(map);
	}

	@Override
	public List<Payment_LineVO> searchPay(Map<String, String> map) throws RemoteException {
		return dao.searchPay(map);
	}

	@Override
	public int insertPay(PaymentVO payVo) throws RemoteException {
		return dao.insertPay(payVo);
	}

	@Override
	public int insertRef(ReferenceVO refVo) throws RemoteException {
		return dao.insertRef(refVo);
	}

	@Override
	public int insertPayLine(Payment_LineVO payLineVo) throws RemoteException {
		return dao.insertPayLine(payLineVo);
	}

	@Override
	public List<SangsinSearchVO> searchName(String name) throws RemoteException {
		return dao.searchName(name);
	}

	@Override
	public int payCheck(Payment_LineVO payLineVo) throws RemoteException {
		return dao.payCheck(payLineVo);
	}
	
	public List<SangsinSearchVO> getAllPayLineName(int pay_no) throws RemoteException {
		return dao.getAllPayLineName(pay_no);
	}

	@Override
	public PaymentListVO getPayListNo(int payNo) throws RemoteException {
		return dao.getPayListNo(payNo);
	}

	@Override
	public int Check(Map map) throws RemoteException {
		return dao.Check(map);
	}

	@Override
	public int Check2(Map map) throws RemoteException {
		return dao.Check2(map);
	}

	@Override
	public int writeNo(int empNo) throws RemoteException {
		return dao.writeNo(empNo);
	}

	@Override
	public List<Document_FormVO> getDocument() throws RemoteException {
		return dao.getDocument();
	}

	@Override
	public List<Payment_LineVO> getCheckList(int emp_no) throws RemoteException {
		return dao.getCheckList(emp_no);
	}

	@Override
	public PaymentListVO getSign(int emp_no) throws RemoteException {
		return dao.getSign(emp_no);
	};

}
