
package com.artcweb.baen;

public class Order extends BaseBean {

	private static final long serialVersionUID = 1L;

	/**
	 * 订单名称
	 */
	private String orderName;

	/**
	 * 订单图片
	 */
	private String orderImage;

	/**
	 * 执行步骤
	 */
	private String step;

	/**
	 * 当前步骤
	 */
	private String currentStep;

	/**
	 * 用户Id
	 */
	private Integer userId;

	/**
	 * 排序
	 */
	private Integer sort;

	public String getOrderName() {

		return orderName;
	}

	public void setOrderName(String orderName) {

		this.orderName = orderName;
	}

	public String getOrderImage() {

		return orderImage;
	}

	public void setOrderImage(String orderImage) {

		this.orderImage = orderImage;
	}

	public String getStep() {

		return step;
	}

	public void setStep(String step) {

		this.step = step;
	}

	public String getCurrentStep() {

		return currentStep;
	}

	public void setCurrentStep(String currentStep) {

		this.currentStep = currentStep;
	}

	public Integer getUserId() {

		return userId;
	}

	public void setUserId(Integer userId) {

		this.userId = userId;
	}

	
	public Integer getSort() {
	
		return sort;
	}

	
	public void setSort(Integer sort) {
	
		this.sort = sort;
	}
	
	

}
