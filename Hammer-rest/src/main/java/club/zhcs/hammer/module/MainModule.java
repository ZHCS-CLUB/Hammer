package club.zhcs.hammer.module;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

import club.zhcs.titans.nutz.module.base.AbstractBaseModule;
import club.zhcs.titans.utils.db.Result;

/**
 * @author kerbores kerbores@gmail.com
 *
 */
@Ok("json")
@Fail("http:500")
@Modules
@IocBy(type = ComboIocProvider.class, args = { "*anno", "club.zhcs", "*tx", "*js", "ioc" })
public class MainModule extends AbstractBaseModule {

	@Inject
	Dao dao;

	@At("/")
	public Result index() {
		return Result.success();
	}

	@At
	public Result db() {
		return Result.success().addData("db", dao.meta());
	}

}
