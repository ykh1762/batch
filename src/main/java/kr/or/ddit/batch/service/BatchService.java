package kr.or.ddit.batch.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.batch.dao.IBatchDao;
import kr.or.ddit.batch.model.BatchVo;
import kr.or.ddit.yogurt.dao.IYogurtDao;

@Service
public class BatchService implements IBatchService{
	
	@Resource(name="batchDao")
	private IBatchDao batchDao;
	
	@Resource(name="yogurtDao")
	private IYogurtDao yogurtDao;
	
	// 배치 아이디 획득
	// 배치 정보를 생성 -> 배치 정보 등록
	// 일실적 생성
	// 배치 정상완료로 업데이트
	
	@Override
	public int dailyBatchYm(String ym) {
		int bid = batchDao.getBatchBid();
		BatchVo batchVo = new BatchVo(bid, "01", "01");
		
		batchDao.createBatch(batchVo);
		
		int insertCnt = yogurtDao.dailyBatchYm(ym);
		
		batchVo.setSt("02");
		batchDao.updateBatch(batchVo);
		
		return insertCnt;
	}
	
	
	

}






