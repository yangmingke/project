package com.flypaas.admin.tag;

import java.util.Map;

import com.flypaas.admin.service.TagService;

/**
 * 权限控制标签
 * 
 * @author xiejiaan
 */
public class AuthorityTag extends BaseTag {
	private static final long serialVersionUID = 6332741277200616979L;

	/**
	 * 菜单id
	 */
	private Integer menuId;

	@Override
	public String getTemplateFile() {
		return null;
	}

	@Override
	public Map<String, Object> getTemplateParams(TagService tagService) {
		return null;
	}

	@Override
	public boolean isEvaluated(TagService tagService) {
		return tagService.isAuthority(menuId);
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

}
