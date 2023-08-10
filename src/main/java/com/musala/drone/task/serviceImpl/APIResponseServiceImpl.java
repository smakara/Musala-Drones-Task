package com.musala.drone.task.serviceImpl;

import org.springframework.stereotype.Service;

import com.musala.drone.task.dto.APIResponse;
import com.musala.drone.task.service.APIResponseService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class APIResponseServiceImpl implements APIResponseService{

	@Override
	public <T> APIResponse<T> buildAPIResponse(T response, int code) {

		return (APIResponse<T>) APIResponse.builder().response(response).statusCode(code).build();
	}

}
