package kr.or.ddit.batch.hello;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

// T -> processor로 부터 넘겨받는 타입
public class HelloWriter implements ItemWriter<String>{
	private Logger logger = LoggerFactory.getLogger(HelloWriter.class);
	
	// chunk : 한번에 기록할 사이즈를 정할 수 있다.
	@Override
	public void write(List<? extends String> rangers) throws Exception {
		for(String ranger : rangers){
			logger.debug("writer : {}", ranger);
		}
	}

	
	
	
}



