package club.zhcs.hammer.module.acl;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Param;
import org.nutz.plugins.apidoc.annotation.Api;

import club.zhcs.hammer.biz.acl.RoleService;
import club.zhcs.titans.nutz.module.base.AbstractBaseModule;
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

}
