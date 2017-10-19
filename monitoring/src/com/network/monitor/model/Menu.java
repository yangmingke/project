package com.network.monitor.model;

import java.util.LinkedHashSet;
import java.util.Set;

public class Menu {
	private Long menu_id;
	private String menu_name;
	private String remark;
	private String menu_url;
	private String menu_class;
	private Integer menu_type;
	private Integer level;
	private Long parent_id;
	private String parent_id_tmp;
	private Integer sort;
	private Integer status;
	private Set<Menu> subMenus = new LinkedHashSet<Menu>();

	public Long getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(Long menu_id) {
		this.menu_id = menu_id;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMenu_url() {
		return menu_url;
	}

	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}

	public String getMenu_class() {
		return menu_class;
	}

	public void setMenu_class(String menu_class) {
		this.menu_class = menu_class;
	}

	public Integer getMenu_type() {
		return menu_type;
	}

	public void setMenu_type(Integer menu_type) {
		this.menu_type = menu_type;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Set<Menu> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(Set<Menu> subMenus) {
		this.subMenus = subMenus;
	}

	public String getParent_id_tmp() {
		return parent_id_tmp;
	}

	public void setParent_id_tmp(String parent_id_tmp) {
		this.parent_id_tmp = parent_id_tmp;
		if(null != parent_id_tmp){
			String [] pt = parent_id_tmp.split(",");
			String tmp = pt[pt.length - 1];
			try {
				this.parent_id = Long.valueOf(tmp);
			} catch (Throwable e) {
			}
		}
	}

	public void addSubMenu(Menu menu) {
		if (null != menu) {
			this.subMenus.add(menu);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((menu_id == null) ? 0 : menu_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menu other = (Menu) obj;
		if (menu_id == null) {
			if (other.menu_id != null)
				return false;
		} else if (!menu_id.equals(other.menu_id))
			return false;
		return true;
	}
}
