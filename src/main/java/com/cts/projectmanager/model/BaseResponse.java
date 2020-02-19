package com.cts.projectmanager.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseResponse {

	private String errorCode;
	private String errorMessage;
}
