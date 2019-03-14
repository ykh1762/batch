package kr.or.ddit.batch.hello;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class HelloReader implements ItemReader<String>{
	private Logger logger = LoggerFactory.getLogger(HelloReader.class);

	private List<String> rangers;
	private int index = 0;
	
	public HelloReader() {
		rangers = new ArrayList<>();
		rangers.add("brown");
		rangers.add("cony");
		rangers.add("sally");
		rangers.add("james");
		rangers.add("moon");
	}
	
	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if(index < rangers.size()){
			String ranger = rangers.get(index);
			index++;
			
			logger.debug("read : {}", ranger);
			
			return ranger;
		}else{
			index = 0; // 다 읽고나서 인덱스 초기화.
			return null; // spring batch에게 더이상 읽을 데이터가 없다고 알림.			
		}
	}
	
	
	
	
	

}










