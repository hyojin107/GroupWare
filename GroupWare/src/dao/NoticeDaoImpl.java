package dao;

import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildedSqlMapClient;
import vo.Address_BookVO;
import vo.NoticeVO;
import vo.NoticeVO_join;

public class NoticeDaoImpl implements INoticeDao{

	private static NoticeDaoImpl instance;
	private SqlMapClient smc;
	
	private NoticeDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static NoticeDaoImpl getInstance(){
		if(instance == null) instance = new NoticeDaoImpl();
		return instance;
	}

	@Override
	public int insertNotice(NoticeVO_join noticeVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("groupware.insertNotice", noticeVo);
			cnt = obj == null ? 1 : 0;
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<NoticeVO> getAllNotice(Map<String, Integer> map) {
		List<NoticeVO> noticeList = null;

		try {
			noticeList = smc.queryForList("groupware.getAllNotice", map);
		} catch (Exception e) {
			noticeList = null;
			e.printStackTrace();
		}			
		return noticeList;
	}

	@Override
	public int updateNotice(NoticeVO_join noticeVo) {
		int cnt = 0;
		try {
			cnt = smc.update("groupware.updateNotice", noticeVo);
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteNotice(int noticeNo) {
		int cnt = 0;
		try {
			cnt = smc.update("groupware.deleteNotice", noticeNo);
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<NoticeVO_join> searchNotice(Map<String, String> map) {
		List<NoticeVO_join> noticeList = null;

		try {
			noticeList = smc.queryForList("groupware.searchNotice", map);
		} catch (Exception e) {
			noticeList = null;
			e.printStackTrace();
		}			
		return noticeList;
	}

	@Override
	public List<NoticeVO_join> getAllNotiEmp(Map<String, Integer> map) {
		List<NoticeVO_join> notiEmpList = null;

		try {
			notiEmpList = smc.queryForList("groupware.getAllNotiEmp", map);
		} catch (Exception e) {
			notiEmpList = null;
			e.printStackTrace();
		}			
		return notiEmpList;
	}

}
