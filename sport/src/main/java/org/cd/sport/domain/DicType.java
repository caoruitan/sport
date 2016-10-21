package org.cd.sport.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 数据字典分类
 * 
 * @author liuyk
 *
 */
@Entity
@Table(name = "SPORT_DIC_TYPE")
public class DicType {
	/**
	 * 数据字典分类名称
	 */
	private String name;

	/**
	 * 数据字典分类父id
	 */
	private String pId;

	/**
	 * 数字字典类型code
	 */
	private String code;

	/**
	 * 排序
	 */
	private int sort;

	/**
	 * 是否有子节点
	 */
	private boolean hasChild;

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "P_ID")
	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	@Id
	@Column(name = "CODE")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "HAS_CHILD")
	public boolean isHasChild() {
		return hasChild;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	@Column(name = "SORT")
	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

}
