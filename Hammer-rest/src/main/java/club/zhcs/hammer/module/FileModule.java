package club.zhcs.hammer.module;

import java.io.File;

import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.UploadAdaptor;
import org.nutz.plugins.apidoc.annotation.Api;

import club.zhcs.titans.nutz.module.base.AbstractBaseModule;
import club.zhcs.titans.utils.db.Result;

@At("file")
@Api(name = "File", description = "文件相关接口")
public class FileModule extends AbstractBaseModule {

	@At
	@POST
	@AdaptBy(type = UploadAdaptor.class, args = { "${app.root}/WEB-INF/tmp" })
	public Result upload(@Param("file") File f) {
		return Result.success();
	}

}
