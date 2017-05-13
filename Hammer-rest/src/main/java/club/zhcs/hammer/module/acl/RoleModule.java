package club.zhcs.hammer.module.acl;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;
import org.nutz.plugins.apidoc.annotation.Api;

import club.zhcs.hammer.bean.acl.Role;
import club.zhcs.hammer.biz.acl.RoleService;
import club.zhcs.titans.nutz.module.base.AbstractBaseModule;
import club.zhcs.titans.utils.db.Pager;
import club.zhcs.titans.utils.db.Result;

/**
 * @author kerbores@gmail.com
 *
 */
@At("/role")
@Api(name = "User", description = "用户相关接口")
public class RoleModule extends AbstractBaseModule {

	@Inject
	RoleService roleService;

	@At
	@GET
	public Result list(@Param(value = "page", df = "1") int page) {
		return Result.success().addData("pager", roleService.searchByPage(_fixPage(page)));
	}

	@At
	@GET
	public Result search(@Param("key") String key, @Param(value = "page", df = "1") int page) {
		page = _fixPage(page);
		key = _fixSearchKey(key);
		Pager<Role> pager = roleService.searchByKeyAndPage(key, page, "name", "description");
		pager.addParas("key", key);
		return Result.success().addData("pager", pager);
	}

	@At
	@POST
	@AdaptBy(type = JsonAdaptor.class)
	public Result save(Role role) {
		return roleService.save(role) == null ? Result.fail("保存角色失败!") : Result.success().addData("role", role);
	}

	@At("/?")
	@GET
	public Result detail(long id) {
		return Result.success().addData("role", roleService.fetch(id));
	}

	@At("/delete/?")
	@GET
	public Result delete(long id) {
		return roleService.delete(id) == 1 ? Result.success() : Result.fail("删除角色失败!");
	}

	@At
	@POST
	@AdaptBy(type = JsonAdaptor.class)
	public Result update(Role role) {
		return roleService.updateIgnoreNull(role) != 1 ? Result.fail("更新角色失败!") : Result.success().addData("role", role);
	}

}
