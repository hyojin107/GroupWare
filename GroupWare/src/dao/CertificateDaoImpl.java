package dao;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildedSqlMapClient;
import vo.Address_BookVO;
import vo.CertificateVO;

public class CertificateDaoImpl implements ICertificateDao{

	private static CertificateDaoImpl instance;
	private SqlMapClient smc;
	
	private CertificateDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static CertificateDaoImpl getInstance(){
		if(instance == null) instance = new CertificateDaoImpl();
		return instance;
	}

	@Override
	public int insertCerti(CertificateVO cerVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("groupware.insertCerti", cerVo);
			cnt = obj == null ? 1 : 0;
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateCerti(Map<String, Integer> map) {
		int cnt = 0;
		try {
			Object obj = smc.update("groupware.updateCerti", map);
			cnt = obj == null ? 1 : 0;
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<CertificateVO> getAllCertificate(int empNo) {
		List<CertificateVO> CertificateList = null;

		try {
			CertificateList = smc.queryForList("groupware.getAllCertificate", empNo);
		} catch (Exception e) {
			CertificateList = null;
			e.printStackTrace();
		}			
		return CertificateList;
	}


	@Override
	public List<CertificateVO> getAllCertificate() {
		List<CertificateVO> CertificateList = null;

		try {
			CertificateList = smc.queryForList("groupware.IgetAllCertificate");
		} catch (Exception e) {
			CertificateList = null;
			e.printStackTrace();
		}			
		return CertificateList;
	}

	@Override
	public List<CertificateVO> getAllCertificate(Map<String, Integer> map) {
		
		List<CertificateVO> CertificateList = null;

		try {
			CertificateList = smc.queryForList("groupware.AllCertificate",map);
		} catch (Exception e) {
			CertificateList = null;
			e.printStackTrace();
		}			
		return CertificateList;
	}
	

}
