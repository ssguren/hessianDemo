package com.servicecenter.service.hessian.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.model.json.AdvertInfoJson;
import com.servicecenter.BaseService;
import com.servicecenter.service.buss.IAdvertBussService;
import com.servicecenter.service.hessian.IAdvertHessianService;

public class AdvertHessianService extends BaseService implements
		IAdvertHessianService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IAdvertBussService advertBussService;

	public AdvertInfoJson getAdvert(Integer id) {
		if (null != id) {
			return advertBussService.findById(id);
		}

		return null;
	}

}
