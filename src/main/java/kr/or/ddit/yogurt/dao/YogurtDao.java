package kr.or.ddit.yogurt.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class YogurtDao implements IYogurtDao{

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	@Override
	public int deleteDailyAllYm(String ym) {
		return template.delete("yogurt.deleteDailyAllYm", ym);
	}

	@Override
	public int dailyBatchYm(String ym) {
		return template.insert("yogurt.dailyBatchYm", ym);
	}

	
	
	
}




