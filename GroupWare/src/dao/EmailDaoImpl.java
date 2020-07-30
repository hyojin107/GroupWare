package dao;

import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildedSqlMapClient;
import vo.Address_BookVO;
import vo.Attach_FileVO;
import vo.Boardemp_joinVO;
import vo.EmailSendListVO;
import vo.EmailVO;
import vo.Email_ReceptionVO;
import vo.EmployeeVO;

public class EmailDaoImpl implements IEmailDao{

	private static EmailDaoImpl instance;
	private SqlMapClient smc;
	
	private EmailDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static EmailDaoImpl getInstance(){
		if(instance == null) instance = new EmailDaoImpl();
		return instance;
	}

	@Override
	public int sendEmail(EmailVO emailVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("groupware.sendEmail", emailVo);
			cnt = obj == null ? 1 : 0;
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int sendEmail(Attach_FileVO attFileVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("groupware.sendEmail2", attFileVo);
			cnt = obj == null ? 1 : 0;
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<EmailVO> getAllEmail(Map<String, Integer> map) {
		List<EmailVO> emailList = null;

		try {
			emailList = smc.queryForList("groupware.getAllEmail", map);
		} catch (Exception e) {
			emailList = null;
			e.printStackTrace();
		}			
		return emailList;
	}

	@Override
	public int deleteEmail(int emailNo) {
		int cnt = 0;
		try {
			Object obj = smc.update("groupware.deleteEmail", emailNo);
			cnt = obj == null ? 1 : 0;
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<EmailVO> searchEmail(Map<String, String> map) {
		List<EmailVO> emailList = null;

		try {
			emailList = smc.queryForList("groupware.searchEmail", map);
		} catch (Exception e) {
			emailList = null;
			e.printStackTrace();
		}			
		return emailList;
	}
	
	@Override
	public int insertEmailReception(Email_ReceptionVO email_recVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("groupware.insertEmailReception", email_recVo);
			cnt = obj == null ? 1 : 0;
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<EmailSendListVO> getSendMailList(Map<String, Integer> map) {
		List<EmailSendListVO> emailList = null;

		try {
			emailList = smc.queryForList("groupware.getSendMailList", map);
		} catch (Exception e) {
			emailList = null;
			e.printStackTrace();
		}			
		return emailList;
	}

	@Override
	public int getLastEmailNo(int empNo) {
		int cnt = 0;
		try {
			cnt = (int) smc.queryForObject("groupware.getLastEmailNo", empNo);
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}
	
	
	
	

}
