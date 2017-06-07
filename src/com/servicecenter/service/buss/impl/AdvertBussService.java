package com.servicecenter.service.buss.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.db.AdvertInfo;
import com.model.json.AdvertInfoJson;
import com.servicecenter.BaseService;
import com.servicecenter.service.buss.IAdvertBussService;
import com.servicecenter.service.db.IAdvertInfoMgrService;

@Service
public class AdvertBussService extends BaseService implements
		IAdvertBussService {

	@Autowired
	private IAdvertInfoMgrService advertInfoMgrService;

	public Boolean add(AdvertInfo ad) {
		return advertInfoMgrService.add(ad);
	}

	public List<AdvertInfo> findAllAdverts() {
		return advertInfoMgrService.findAll();
	}

	public boolean mergeAds(List<AdvertInfo> ads) {
		boolean flag = true;
		if (null != ads && ads.size() > 0) {
			for (AdvertInfo ad : ads) {
				flag &= add(ad);
			}
		}
		return flag;
	}

	public AdvertInfoJson findById(Integer id) {
		if (null != id) {
			AdvertInfo ad = advertInfoMgrService.findById(id);
			if (null != ad) {
				return dbModel2Json(ad);
			}
		}
		return null;
	}

	private AdvertInfoJson dbModel2Json(AdvertInfo ad) {
		AdvertInfoJson json = new AdvertInfoJson();
		json.setId(ad.getId().toString());
		json.setAdvertName(ad.getAdvertName());
		json.setRelUrl(ad.getRelUrl());
		json.setStatus(ad.getIsEffective() == 0 ? "有效" : "无效");

		return json;

	}

}
