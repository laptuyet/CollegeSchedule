package com.schedule.model;

import com.schedule.utils.AppConstants;

public enum Major {
	CNTT(AppConstants.CNTT),
	ATTT(AppConstants.ATTT),
	DT(AppConstants.DT),
	QKTD(AppConstants.QTKD),
	KT(AppConstants.KT);
	
	private String detailName;
	
	private Major(String detailName) {
		this.detailName = detailName;
	}
	
	public String getDetailName() {
		return this.detailName;
	}
}
