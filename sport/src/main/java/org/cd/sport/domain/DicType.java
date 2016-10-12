package org.cd.sport.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

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
	 * 数据字典分类id
	 */
	private String dicId;

	/**
	 * 数据字典分类名称
	 */
	private String name;

	/**
	 * 数据字典分类父id
	 */
	private String pId;

	@Id
	@Column(name = "DIC_ID")
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	public String getDicId() {
		return dicId;
	}

	public void setDicId(String dicId) {
		this.dicId = dicId;
	}

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

}
