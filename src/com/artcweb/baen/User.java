
package com.artcweb.baen;

public class User extends BaseBean {

	private static final long serialVersionUID = 1L;

	// 手机号码
	private String mobile;

	/**
	 * 排序
	 */
	private Integer sort;

	public String getMobile() {

		return mobile;
	}

	public void setMobile(String mobile) {

		this.mobile = mobile;
	}

	public Integer getSort() {

		return sort;
	}

	public void setSort(Integer sort) {

		this.sort = sort;
	}

}
