package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildedSqlMapClient;
import vo.EmployeeVO;
import vo.Employee_joinVO;
import vo.ImgFileVO;

public class EmployeeDaoImpl implements IEmployeeDao{

	private static EmployeeDaoImpl instance;
	private SqlMapClient smc;
	
	private EmployeeDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static EmployeeDaoImpl getInstance(){
		if(instance == null) instance = new EmployeeDaoImpl();
		return instance;
	}

	@Override
	public int insertEmp(EmployeeVO empVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("groupware.insertEmp", empVo);
			cnt = obj == null ? 1 : 0;
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateEmp(Map<String, String> map) {
		int cnt = 0;
		try {
			Object obj = smc.update("groupware.updateEmp", map);
			cnt = obj == null ? 1 : 0;
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}
	
	
	@Override
	public int gikwonenroll (EmployeeVO empVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("groupware.gikwonenroll", empVo);
			cnt = obj == null ? 1 : 0;
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}
	
	

	@Override
	public List<EmployeeVO> getAllEmp() {
		List<EmployeeVO> employeeList = null;

		try {
			employeeList = smc.queryForList("groupware.getAllEmp");
		} catch (Exception e) {
			employeeList = null;
			e.printStackTrace();
		}			
		return employeeList;
	}

	@Override
	public List<Employee_joinVO> searchEmp(Map<String, String> map) {
		List<Employee_joinVO> employeeList = null;

		try {
			employeeList = smc.queryForList("groupware.searchEmp", map);
		} catch (Exception e) {
			employeeList = null;
			e.printStackTrace();
		}			
		return employeeList;
	}

	@Override
	public EmployeeVO getEmpLogin(int empNo) {
		EmployeeVO employee = null;

		try {
			employee = (EmployeeVO) smc.queryForObject("groupware.getEmpLogin", empNo);
		} catch (Exception e) {
			employee = null;
			e.printStackTrace();
		}			
		return employee;
	}
	
	@Override
	public List<Employee_joinVO> kido(Map<String, Integer> map) {
		List<Employee_joinVO> employeeList = null;

		try {
			employeeList = smc.queryForList("groupware.kido",map);
		} catch (Exception e) {
			employeeList = null;
			e.printStackTrace();
		}
		return employeeList;
	}
	
	
	@Override
	public List<Employee_joinVO> allemplist(Map<String, Integer> map) {
		List<Employee_joinVO> employeeList = null;

		try {
			employeeList = smc.queryForList("groupware.allemplist",map);
		} catch (Exception e) {
			employeeList = null;
			e.printStackTrace();
		}
		return employeeList;
	}

	
	@Override
	public int setImgPhoto(ImgFileVO fileVo) {
		int cnt;
		FileOutputStream fout = null;
		String dir = "D:/D_Other/middleProject/photo/";
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("field", "emp_photo");
			map.put("data", dir + fileVo.getEmp_no() + ".jpg");
			map.put("empNo", fileVo.getEmp_no()+"");
			cnt = smc.update("groupware.updateEmp", map);		// DB에 파일 경로 저장
			
			File direc = new File(dir);
			direc.mkdirs();
			
			File file = new File(dir + fileVo.getEmp_no() + ".jpg");
			fout = new FileOutputStream(file);
//			fout = new FileOutputStream(new File(dir + fileVo.getEmp_no() + ".jpg"));
			fout.write(fileVo.getEmp_photo());	//파일 내용 저장
			
			fout.flush();
			fout.close();
			
		} catch (IOException e) {
			cnt = 0;
			e.printStackTrace();
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		
		return cnt;
	}

	
	@Override
	public int setImgSign(ImgFileVO fileVo) {
		int cnt;
		FileOutputStream fout = null;
		String dir = "D:/D_Other/middleProject/sign/";
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("field", "emp_sign");
			map.put("data", dir + fileVo.getEmp_no() + ".jpg");
			map.put("empNo", fileVo.getEmp_no()+"");
			cnt = smc.update("groupware.updateEmp", map);	// DB에 파일 경로 저장
			
			File direc = new File(dir);
			direc.mkdirs();
			
			File file = new File(dir + fileVo.getEmp_no() + ".jpg");
			fout = new FileOutputStream(file);
//			fout = new FileOutputStream(new File(dir + fileVo.getEmp_no() + ".jpg"));
			fout.write(fileVo.getEmp_sign());
			
			fout.flush();
			fout.close();
			
		} catch (IOException e) {
			cnt = 0;
			e.printStackTrace();
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public ImgFileVO getImgFile(int empNo) {
		ImgFileVO fileVo = new ImgFileVO();
		String dir = "D:/D_Other/middleProject/";
		File filePhoto;
		File fileSign;
		try {
			filePhoto = new File(dir + "photo/"+empNo + ".jpg");
			
			EmployeeVO emp = getEmpLogin(empNo);
			if(emp.getEmp_photo() != null) {
				FileInputStream fis = new FileInputStream(filePhoto);
				byte[] fileData = new byte[(int)filePhoto.length()];
				fis.read(fileData);
				
				fileVo.setEmp_photo(fileData);
			}else {
				filePhoto = new File(dir + "photo/photoDefult.jpg");
				FileInputStream fis = new FileInputStream(filePhoto);
				byte[] fileData = new byte[(int)filePhoto.length()];
				fis.read(fileData);
				
				fileVo.setEmp_photo(fileData);
			}
			
			fileSign = new File(dir + "sign/"+empNo + ".jpg");
			if(emp.getEmp_sign() != null) {
				FileInputStream fis = new FileInputStream(fileSign);
				byte[] fileData = new byte[(int)fileSign.length()];
				fis.read(fileData);
				
				fileVo.setEmp_sign(fileData);
			}else {
				fileSign = new File(dir + "sign/signDefult.jpg");
				FileInputStream fis = new FileInputStream(fileSign);
				byte[] fileData = new byte[(int)fileSign.length()];
				fis.read(fileData);
				fileVo.setEmp_sign(fileData);
			}
			fileVo.setEmp_no(empNo);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return fileVo;
	}
	
	



}
