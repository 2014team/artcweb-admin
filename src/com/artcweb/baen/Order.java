
package com.artcweb.baen;

public class Order extends PicPackage {

	private static final long serialVersionUID = 1L;

	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 当前步骤
	 */
	private String currentStep;
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

	public String getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(String currentStep) {
		this.currentStep = currentStep;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
