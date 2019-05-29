package petstore_feedback.logging;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggingManual{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public long startLog(String className,String methodName){
		long startTime = System.currentTimeMillis();
		log.info("***" +new Date(startTime)+" " + "Entering in Method :  "
				+className+"."+ methodName + "()");
		return startTime;
	}
	
	public void endLog(long startTime,String className,String methodName){
		Long elapsedTime = System.currentTimeMillis() - startTime;
		log.info("Method execution time for API " +className+"."+ methodName+"()"+ " : " + elapsedTime + " milliseconds.");
	}
	
}
