package club.zhcs.hammer.module;

import org.nutz.dao.Dao;
import org.nutz.integration.shiro.ShiroSessionProvider;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.lang.random.R;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.View;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.ChainBy;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.annotation.SessionBy;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.annotation.UrlMappingBy;
import org.nutz.mvc.filter.CheckSession;
import org.nutz.mvc.ioc.provider.ComboIocProvider;
import org.nutz.plugins.apidoc.ApidocUrlMapping;
import org.nutz.plugins.apidoc.annotation.Api;
import org.nutz.plugins.apidoc.annotation.ApiMatchMode;

import club.zhcs.hammer.ThunderApplication.SessionKeys;
import club.zhcs.hammer.ThunderSetup;
import club.zhcs.hammer.chain.ThunderChainMaker;
import club.zhcs.hammer.ext.shiro.anno.ThunderRequiresRoles;
import club.zhcs.hammer.vo.InstalledRole;
import club.zhcs.titans.nutz.captcha.JPEGView;
import club.zhcs.titans.nutz.module.base.AbstractBaseModule;
import club.zhcs.titans.utils.db.Result;

/**
 * @author kerbores kerbores@gmail.com
 *
 */
@Modules
@Ok("json")
@Fail("http:500")
@AdaptBy(type = JsonAdaptor.class)
@SessionBy(ShiroSessionProvider.class)
@UrlMappingBy(ApidocUrlMapping.class)
@SetupBy(ThunderSetup.class)
@ChainBy(type = ThunderChainMaker.class, args = {})
@IocBy(type = ComboIocProvider.class, args = {
		"*anno", "club.zhcs",
		"*tx",
		"*js", "ioc",
		"*jedis" })
@Filters({ @By(type = CheckSession.class, args = { SessionKeys.USER_KEY, "/" }) })
@Api(name = "Hammer", author = "Kerbores", description = "Nutz Hammer", match = ApiMatchMode.ALL)
public class MainModule extends AbstractBaseModule {

	@Inject
	Dao dao;

	@At("/")
	@Filters
	public Result index() {
		return R.random(0, 10) > 5 ? Result.success() : Result.fail("test");
	}

	@At
	@ThunderRequiresRoles(InstalledRole.SU)
	public Result test() {
		return Result.success();
	}

	@At
	public Result post(@Param("id") int id, @Param("name") String name) {
		return Result.success(NutMap.NEW().addv("id", id).addv("name", name));
	}

	@At
	@AdaptBy(type = JsonAdaptor.class)
	public Result postBody(NutMap data) {
		return Result.success(data);
	}

	@At
	public Result db() {
		return Result.success().addData("db", dao.meta());
	}

	@At
	@Filters
	public View captcha() {
		return new JPEGView(null);
	}

}
