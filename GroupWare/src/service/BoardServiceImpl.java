package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import dao.BoardDaoImpl;
import dao.IBoardDao;
import vo.BoardVO;
import vo.Boardemp_joinVO;

public class BoardServiceImpl extends UnicastRemoteObject implements IBoardService{
	
	private IBoardDao dao;
	private static BoardServiceImpl service;
	private BoardServiceImpl() throws RemoteException {
		dao = BoardDaoImpl.getInstance();
	}
	
	public static BoardServiceImpl getInstance() {
		try {
			if(service == null) service = new BoardServiceImpl();
		} catch (Exception e) {	}
		return service;
	}

	@Override
	public int insertBoard(Boardemp_joinVO boardVo) throws RemoteException {
		return dao.insertBoard(boardVo);
	}

	@Override
	public List<BoardVO> getAllBoard(Map<String, Integer> map) throws RemoteException {
		return dao.getAllBoard(map);
	}

	@Override
	public int updateBoard(Boardemp_joinVO boardVo) throws RemoteException {
		return dao.updateBoard(boardVo);
	}

	@Override
	public int deleteBoard(int boardVo) throws RemoteException {
		return dao.deleteBoard(boardVo);
	}

	@Override
	public List<Boardemp_joinVO> searchBoard(Map<String, String> map) throws RemoteException {
		return dao.searchBoard(map);
	}

	@Override
	public List<Boardemp_joinVO> boardjoinList(Map<String, Integer> map) throws RemoteException {

		return dao.boardjoinList(map);
	}
	
	
}
