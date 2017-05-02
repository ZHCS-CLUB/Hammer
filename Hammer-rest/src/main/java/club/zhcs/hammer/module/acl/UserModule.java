package club.zhcs.hammer.module.acl;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.filter.CrossOriginFilter;
import org.nutz.plugins.apidoc.annotation.Api;

import club.zhcs.hammer.ThunderApplication.SessionKeys;
import club.zhcs.hammer.biz.shiro.ShiroUserService;
import club.zhcs.hammer.rest.ApiRequest;
import club.zhcs.hammer.rest.dto.acl.UserLoginDto;
import club.zhcs.titans.nutz.captcha.JPEGView;
import club.zhcs.titans.nutz.module.base.AbstractBaseModule;
import club.zhcs.titans.utils.codec.DES;
import club.zhcs.titans.utils.db.Result;

/**
 * @author kerbores kerbores@gmail.com
 *
 */
@At("/user")
@Api(name = "User", description = "用户相关接口")
public class UserModule extends AbstractBaseModule {

	@Inject
	ShiroUserService shiroUserService;

	/**
	 * 登录
	 * 
	 * @param request
	 *            登录请求对象
	 * @param session
	 *            httpSession
	 * @return 登录结果
	 */
	@At
	@POST
	@Filters(@By(type=CrossOriginFilter.class))
	public Result login(ApiRequest<UserLoginDto> request, HttpSession session) {
		if (Strings.equalsIgnoreCase(request.getData().getCaptcha(), Strings.safeToString(session.getAttribute(JPEGView.CAPTCHA), "") )) {
			Result result = shiroUserService.login(request.getData().getUserName(), request.getData().getPassword(), Lang.getIP(Mvcs.getReq()));
			if (result.isSuccess()) {
				// 登录成功处理
				_putSession(SessionKeys.USER_KEY, result.getData().get("loginUser"));
				if (request.getData().isRememberMe()) {
					NutMap data = NutMap.NEW();
					data.put("user", request.getData().getUserName());
					data.put("password", request.getData().getPassword());
					data.put("rememberMe", request.getData().getPassword());
					_addCookie("kerbores", DES.encrypt(Json.toJson(data)), 24 * 60 * 60 * 365);
				}
			}
			return result;
		} else {
			return Result.fail("验证码输入错误");
		}
	}

	/**
	 * 退出登录
	 * 
	 * @param session
	 * @return
	 */
	@At
	@GET
	@Filters
	public Result logout() {
		SecurityUtils.getSubject().logout();
		return Result.success();
	}

}
