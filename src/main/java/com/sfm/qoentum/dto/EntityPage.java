package com.sfm.qoentum.dto;

import java.util.List;

public class EntityPage<T> {

	private List<T> list;
	private PageUtil pageUtil;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public PageUtil getPageUtil() {
		return pageUtil;
	}

	public void setPageUtil(PageUtil pageUtil) {
		this.pageUtil = pageUtil;
	}

	@Override
	public String toString() {
		return "EntityPage [list=" + list + ", pageUtil=" + pageUtil + "]";
	}

}
