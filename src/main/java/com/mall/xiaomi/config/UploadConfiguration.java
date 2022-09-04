package com.mall.xiaomi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@Configuration
public class UploadConfiguration implements WebMvcConfigurer {

    private static final String filePath;
    private static final String fileUrlPre = "/file/";

    static {
        if (System.getProperty("user.dir").equals("/")) {
            filePath = "/upload/";
        } else {
            filePath = System.getProperty("user.dir") + "/upload/";
        }
    }

    /**
     * resource配置
     *
     * @param registry registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(fileUrlPre + "**")
                .addResourceLocations("file:" + filePath)
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.DAYS).cachePublic());
        registry
                .addResourceHandler("/**/*.js","/**/*.css","/**/*.woff2")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.DAYS).cachePublic());
    }

    @PostMapping(value = "api/upload")
    public String fileUpload(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "bucket", required = false) String bucket) {
        if (file.isEmpty()) {
            System.out.println("文件为空");
        }
        if (StringUtils.isEmpty(bucket)) {
            bucket = "img";
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + bucket + "/" + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileUrlPre.substring(1) + bucket + "/" + fileName;
    }

}
