package dao;

import java.io.File;
import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildedSqlMapClient;
import vo.Document_FormVO;
import vo.PaymentListVO;
import vo.PaymentVO;
import vo.Payment_LineVO;
import vo.ReferenceVO;
import vo.SangsinSearchVO;

public class PaymentDaoImpl implements IPaymentDao{

	private static PaymentDaoImpl instance;
	private SqlMapClient smc;
	
	private PaymentDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static PaymentDaoImpl getInstance(){
		if(instance == null) instance = new PaymentDaoImpl();
		return instance;
	}

	@Override
	public List<PaymentListVO> getAllPay(int emp_no) {
		List<PaymentListVO> paymentList = null;

		try {
			paymentList = smc.queryForList("groupware.getAllPay", emp_no);
		} catch (Exception e) {
			paymentList = null;
			e.printStackTrace();
		}			
		return paymentList;
	}

	@Override
	public List<SangsinSearchVO> getAllRef(int pay_no) {
		List<SangsinSearchVO> refList = null;

		try {
			refList = smc.queryForList("groupware.getAllRef", pay_no);
		} catch (Exception e) {
			refList = null;
			e.printStackTrace();
		}			
		return refList;
	}

	@Override
	public List<Payment_LineVO> getAllPayLine(Map<String, Integer> map) {
		List<Payment_LineVO> lineList = null;

		try {
			lineList = smc.queryForList("groupware.getAllPayLine", map);
		} catch (Exception e) {
			lineList = null;
			e.printStackTrace();
		}			
		return lineList;
	}

	@Override
	public List<PaymentListVO> getComboPay(Map<String, Integer> map) {
		List<PaymentListVO> paymentList = null;

		try {
			paymentList = smc.queryForList("groupware.getComboPay", map);
		} catch (Exception e) {
			paymentList = null;
			e.printStackTrace();
		}			
		return paymentList;
	}

	@Override
	public List<ReferenceVO> getComboRef(Map<String, Integer> map) {
		List<ReferenceVO> refList = null;

		try {
			refList = smc.queryForList("groupware.getComboRef", map);
		} catch (Exception e) {
			refList = null;
			e.printStackTrace();
		}			
		return refList;
	}

	@Override
	public List<Payment_LineVO> getComboPayLine(Map<String, Integer> map) {
		List<Payment_LineVO> lineList = null;

		try {
			lineList = smc.queryForList("groupware.getComboPayLine", map);
		} catch (Exception e) {
			lineList = null;
			e.printStackTrace();
		}			
		return lineList;
	}

	@Override
	public List<Payment_LineVO> searchPay(Map<String, String> map) {
		List<Payment_LineVO> lineList = null;

		try {
			lineList = smc.queryForList("groupware.searchPay", map);
		} catch (Exception e) {
			lineList = null;
			e.printStackTrace();
		}			
		return lineList;
	}

	@Override
	public int insertPay(PaymentVO payVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("groupware.insertPay", payVo);
			cnt = obj == null ? 1 : 0;
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int insertRef(ReferenceVO refVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("groupware.insertRef", refVo);
			cnt = obj == null ? 1 : 0;
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int insertPayLine(Payment_LineVO payLineVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("groupware.insertPayLine", payLineVo);
			cnt = obj == null ? 1 : 0;
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<SangsinSearchVO> searchName(String name) {
		List<SangsinSearchVO> employeeList = null;

		try {
			employeeList = smc.queryForList("groupware.searchName", name);
		} catch (Exception e) {
			employeeList = null;
			e.printStackTrace();
		}			
		return employeeList;
	}

	@Override
	public int payCheck(Payment_LineVO payLineVo) {
		int cnt = 0;
		try {
			cnt = smc.update("groupware.payCheck", payLineVo);
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<SangsinSearchVO> getAllPayLineName(int pay_no) {//쿼리작성
		List<SangsinSearchVO> lineList = null;

		try {
			lineList = smc.queryForList("groupware.getAllPayLineName", pay_no);
		} catch (Exception e) {
			lineList = null;
			e.printStackTrace();
		}			
		return lineList;	
		
	}

	@Override
	public PaymentListVO getPayListNo(int payNo){
		PaymentListVO vo = null;
		try {
			vo = (PaymentListVO) smc.queryForObject("groupware.getPayListNo", payNo);
		} catch (Exception e) {
			vo = null;
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public int Check(Map map) {
		int cnt = 0;
		try {
			cnt = smc.update("groupware.check", map);
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int Check2(Map map) {
		int cnt = 0;
		try {
			cnt = smc.update("groupware.check2", map);
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int writeNo(int empNo) {
		int cnt = 0;
		try {
			cnt = (int) smc.queryForObject("groupware.writeNo", empNo);
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<Document_FormVO> getDocument() {
		List<Document_FormVO> list = null;
		try {
			list = smc.queryForList("groupware.getDocument");
		} catch (Exception e) {
			list = null;
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Payment_LineVO> getCheckList(int emp_no) {
		List<Payment_LineVO> list = null;
		try {
			list = smc.queryForList("groupware.getCheckList", emp_no);
		} catch (Exception e) {
			list = null;
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public PaymentListVO getSign(int emp_no) {
		PaymentListVO vo = null;
		try {
			vo = (PaymentListVO) smc.queryForObject("groupware.getSign", emp_no);
			if(vo.getEmp_sign() != null) {
				File file = new File(vo.getEmp_sign());
				FileInputStream fin = new FileInputStream(file);
				byte[] b = new byte[(int) file.length()];
				
				fin.read(b);
				
				vo.setEmp_sign(b.toString());
			}
		} catch (Exception e) {
			vo = null;
			e.printStackTrace();
		}
		return vo;
	}

}
