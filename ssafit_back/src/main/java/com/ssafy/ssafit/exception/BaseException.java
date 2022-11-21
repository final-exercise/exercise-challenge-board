package com.ssafy.ssafit.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends Exception {
	
	private boolean isSuccess;
    private int code;
    private String message;
    
}
