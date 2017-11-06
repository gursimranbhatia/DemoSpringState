package com.example.demo.exception;

public class ClaimServiceException extends Exception {
	  public ClaimServiceException() {
	  }

	  public ClaimServiceException(String message) {
	    super(message);
	  }

	  public ClaimServiceException(String message, Throwable cause) {
	    super(message, cause);
	  }

	  public ClaimServiceException(Throwable cause) {
	    super(cause);
	  }

	  public ClaimServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	    super(message, cause, enableSuppression, writableStackTrace);
	  }
}
