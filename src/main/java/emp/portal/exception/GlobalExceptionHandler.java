package emp.portal.exception;



import emp.portal.ResponseHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.access.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {
	private final Logger logger= LogManager.getLogger();

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Object> handleAccessDenied(HttpServletRequest request,AccessDeniedException ex)
	{
		logger.error("handleAccessDeniedException{}\n",request.getRequestURI(),ex);
		return ResponseHandler.generateresponse("Not Authorized to access",HttpStatus.FORBIDDEN);
	}


	//global exception
	//@ExceptionHandler(Exception.class)
	//public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception,WebRequest webrequest)
	//{
//		ErrorDetails errorDetails=new ErrorDetails(new Date(),exception.getMessage(),webrequest.getDescription(false));
//		return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
	@ExceptionHandler(value=UnauthorizedExpection.class)
	public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedExpection exception)
	{
		return ResponseHandler.generateresponse("Credential are wrong", HttpStatus.UNAUTHORIZED);
	}
	
	

}
