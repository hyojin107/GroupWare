package dao;

import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildedSqlMapClient;
import vo.BoardVO;
import vo.Boardemp_joinVO;
import vo.EmployeeVO;

public class BoardDaoImpl implements IBoardDao{

	private static BoardDaoImpl instance;
	private SqlMapClient smc;
	
	private BoardDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static BoardDaoImpl getInstance(){
		if(instance == null) instance = new BoardDaoImpl();
		return instance;
	}

	@Override
	public int insertBoard(Boardemp_joinVO boardVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("groupware.insertBoard", boardVo);
			cnt = obj == null ? 1 : 0;
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<BoardVO> getAllBoard(Map<String, Integer> map) {
		List<BoardVO> boardList = null;

		try {
			boardList = smc.queryForList("groupware.getAllBoard", map);
		} catch (Exception e) {
			boardList = null;
			e.printStackTrace();
		}			
		return boardList;
	}

	@Override
	public int updateBoard(Boardemp_joinVO boardVo) {
		int cnt = 0;
		try {
			Object obj = smc.update("groupware.updateBoard", boardVo);
			cnt = obj == null ? 1 : 0;
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt = 0;
		try {
			cnt = smc.update("groupware.deleteBoard", boardNo);
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<Boardemp_joinVO> searchBoard(Map<String, String> map) {
		List<Boardemp_joinVO> boardList = null;

		try {
			boardList = smc.queryForList("groupware.searchBoard", map);
		} catch (Exception e) {
			boardList = null;
			e.printStackTrace();
		}			
		return boardList;
	}

	@Override
	public List<Boardemp_joinVO> boardjoinList(Map<String, Integer> map) {
		List<Boardemp_joinVO> boardList = null;

		try {
			boardList = smc.queryForList("groupware.boardjoinList",map);
		} catch (Exception e) {
			boardList = null;
			e.printStackTrace();
		}			
		return boardList;
	}

}
