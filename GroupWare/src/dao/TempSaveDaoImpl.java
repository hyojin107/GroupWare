package dao;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildedSqlMapClient;
import vo.Salary_SpecsVO;
import vo.Temp_SaveVO;

public class TempSaveDaoImpl implements ITempSaveDao{

	private static TempSaveDaoImpl instance;
	private SqlMapClient smc;
	
	private TempSaveDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static TempSaveDaoImpl getInstance(){
		if(instance == null) instance = new TempSaveDaoImpl();
		return instance;
	}

	@Override
	public int insertTempSave(Temp_SaveVO temVo) {
		int cnt = 0;
//		System.out.println("doc"+temVo.getDoc_no() + temVo.getEmp_no()+"line"+temVo.getPay_line()+"ref"+temVo.getRef_line()+"con"+temVo.getTemp_content()+"ti"+temVo.getTemp_title());
		try {
			Object obj = smc.insert("groupware.insertTempSave", temVo);
			cnt = obj == null ? 1 : 0;
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateTempSave(Temp_SaveVO temVo) {
		int cnt = 0;
//		System.out.println("doc"+temVo.getDoc_no() + temVo.getEmp_no()+"line"+temVo.getPay_line()+"ref"+temVo.getRef_line()+"con"+temVo.getTemp_content()+"ti"+temVo.getTemp_title());
		try {
			Object obj = smc.update("groupware.updateTempSave", temVo);
			cnt = obj == null ? 1 : 0;
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteTempSave(int empNo) {
		int cnt = 0;
		try {
			Object obj = smc.delete("groupware.deleteTempSave", empNo);
			cnt = obj == null ? 1 : 0;
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public Temp_SaveVO getTempSave(int emp_no) {
		Temp_SaveVO tempVo = null;

		try {
			tempVo = (Temp_SaveVO) smc.queryForObject("groupware.getTempSave", emp_no);
		} catch (Exception e) {
			tempVo = null;
			e.printStackTrace();
		}			
		return tempVo;
	}
	
}
