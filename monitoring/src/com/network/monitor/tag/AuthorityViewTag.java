package com.network.monitor.tag;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.network.monitor.service.MenuServiceImpl;
import com.network.monitor.service.TagService;

/**
 * 权限控制标签
 * 
 * @author xiejiaan
 */
public class AuthorityViewTag extends BaseTag {
	private static final long serialVersionUID = 6332741277200616979L;

	/**
	 * 菜单id
	 */
	private Long menuId;

	@Override
	public String getTemplateFile() {
		return null;
	}

	@Override
	public Map<String, Object> getTemplateParams(TagService tagService) {
		return null;
	}

	// <a href="#">管理中心</a><a href="#">管理员管理</a><a href="#"
	// class="current">管理员资料</a></div>
	@Override
	protected void populateParams() {
		super.populateParams();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);// 手动注入@Autowired修饰的bean
		if (null == menuId) {
			return;
		}
		String result = MenuServiceImpl.titleMap.get(menuId);
		if (null != result) {
			try {
				this.pageContext.getOut().println(result);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

}
