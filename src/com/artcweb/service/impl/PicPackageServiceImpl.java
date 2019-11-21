
package com.artcweb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artcweb.baen.LayUiResult;
import com.artcweb.baen.PicPackage;
import com.artcweb.dao.PicPackageDao;
import com.artcweb.service.PicPackageService;

@Service
public class PicPackageServiceImpl extends BaseServiceImpl<PicPackage, Integer> implements PicPackageService {

	@Autowired
	private PicPackageDao  picPackageDao;
	
	/**
	* @Title: checkSaveParam
	* @Description: 参数验证
	* @param entity
	* @return
	*/
	@Override
	public String checkSaveParam(PicPackage entity) {
		Integer packageId = entity.getPackageId();
		if(null == packageId){
			return "参数[packageId]不能为空!";
		}
		String packageName  = entity.getPackageName();
		if(StringUtils.isBlank(packageName)){
			return "参数[packageName]不能为空!";
		}
		// 图片验证
		//后面验证
		//
		String step =entity.getStep();
		if(StringUtils.isBlank(step)){
			return "参数[step]不能为空!";
		}
		return null;
	}

	/**
	* @Title: checkUpdateUnique
	* @Description: 更新唯一性验证
	* @param entity
	* @return
	*/
	@Override
	public String checkUpdateUnique(PicPackage entity) {
		List<PicPackage> list = null;
		// 分类ID与分类名唯一性验证
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", entity.getId());
		paramMap.put("packageId", entity.getPackageId());
		paramMap.put("packageName", entity.getPackageName());
		list = picPackageDao.checkUnique(paramMap);
		if (null != list && list.size() > 0) {
			return "套餐ID与套餐名已存在!";
		}

		// 分类ID一性验证
		paramMap.remove("packageName");
		list = picPackageDao.checkUnique(paramMap);
		if (null != list && list.size() > 0) {
			return "套餐ID已存在!";
		}
		// 分类名唯一性验证
		paramMap.remove("packageId");
		paramMap.put("packageName", entity.getPackageName());
		list = picPackageDao.checkUnique(paramMap);
		if (null != list && list.size() > 0) {
			return "套餐名已存在!";
		}
		return null;
	}


	/**
	* @Title: checkAddUnique
	* @Description: 保存唯一性验证
	* @param entity
	* @return
	*/
	@Override
	public String checkAddUnique(PicPackage entity) {
		List<PicPackage> list = null;
		// 分类ID与分类名唯一性验证
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("packageId", entity.getPackageId());
		paramMap.put("packageName", entity.getPackageName());
		list = picPackageDao.checkUnique(paramMap);
		if (null != list && list.size() > 0) {
			return "套餐ID与套餐名已存在!";
		}

		// 分类ID一性验证
		paramMap.remove("packageName");
		list = picPackageDao.checkUnique(paramMap);
		if (null != list && list.size() > 0) {
			return "套餐ID已存在!";
		}
		// 分类名唯一性验证
		paramMap.remove("packageId");
		paramMap.put("packageName", entity.getPackageName());
		list = picPackageDao.checkUnique(paramMap);
		if (null != list && list.size() > 0) {
			return "套餐名已存在!";
		}
		return null;
	}

	/**
	* @Title: findByPage
	* @Description: 分页查找
	* @param entity
	* @param result
	* @return
	*/
	@Override
	public LayUiResult findByPage(PicPackage entity, LayUiResult result) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("entity", entity);
		paramMap.put("page", result);
		int count = findByPageCount(paramMap);
		if (count > 0) {
			List<PicPackage> dataList = findByPage(paramMap);
			result.setData(dataList);
			result.setCount(count);
		}
		return result;
	}


	/**
	* @Title: deleteByBatch
	* @Description: 批量删除
	* @param array
	* @return
	*/
	@Override
	public int deleteByBatch(String array) {
		return picPackageDao.deleteByBatch(array);
	}

}
