package com.artcweb.baen;

public class PicPackage extends BaseBean {

	private static final long serialVersionUID = 1L;

	/**
	 * 套餐ID
	 */
	private Integer packageId;
	/**
	 * 套餐名称
	 */
	private String packageName;

	/**
	 * imageUrl图片
	 */
	private String imageUrl;
	/**
	 * 执行步骤
	 */
	private String step;

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

}
