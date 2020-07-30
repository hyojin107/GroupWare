package util;

import java.io.Reader;
import java.nio.charset.Charset;
import org.apache.log4j.Logger;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class BuildedSqlMapClient {
	private static Logger logger = Logger.getLogger(BuildedSqlMapClient.class);
	private static SqlMapClient smc;
	
	static{
		try {
			logger.info("환경설정 파일의 인코딩 케릭터셋 설정 시작...");
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			logger.info("환경설정 파일의 인코딩 케릭터셋 설정 완료...");
			
			logger.info("환경설정 파일(sqlMapConfig.xml) 읽어오기 ==> Reader객체 생성");
			Reader rd = Resources.getResourceAsReader("sqlMapConfig.xml");
			
			logger.info("환경설정 완료 후 SqlMapClient객체 생성...");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			logger.debug("Reader객체 닫기");
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("SqlMapClient객체 생성 실패!!!", e);
		}		
	}
	
	public static SqlMapClient getSqlMapClient(){
		logger.debug("SqlMapClient객체 반환하기...");
		return smc;
	}

}
