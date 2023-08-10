package com.musala.drone.task.service;



import org.springframework.stereotype.Service;

import com.musala.drone.task.dto.APIResponse;


public interface APIResponseService {
	
	<T> APIResponse<T>  buildAPIResponse(T response, int code);

}
