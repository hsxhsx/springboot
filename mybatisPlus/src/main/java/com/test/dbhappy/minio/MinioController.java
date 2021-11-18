package com.test.dbhappy.minio;


import cn.hutool.core.lang.UUID;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.test.dbhappy.gen.R;
import com.test.dbhappy.utils.MinioUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@RestController
@RequestMapping("minio")
@Api(tags = "minio文件系统")
public class MinioController {

    @Value("${minio.bucketName}")
    private String bucketName;

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.port}")
    private String port;

    @Autowired
    private MinioUtil minioUtil;

    @PostMapping("upload")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "上传文件")
    public R upload(@RequestParam(name = "file", required = false) MultipartFile file) throws Exception {
        if (file == null || file.getSize() == 0) {
            throw new Exception("上传文件不能为空");
        }
        Date now = new Date();
        String date_file = DateFormatUtils.format(now, "yyyyMMdd")+"/";
        String prefix_file = DateFormatUtils.format(now, "yyyyMMddHHmmss") + UUID.fastUUID();
        minioUtil.putObject(bucketName,file,date_file+prefix_file+file.getOriginalFilename());
        return R.ok(endpoint+":"+port+"/"+bucketName+"/"+ UriUtils.encode(date_file+prefix_file+file.getOriginalFilename(), StandardCharsets.UTF_8));
    }

    @GetMapping("download")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "下载文件")
    public void download(HttpServletResponse response, String fileName) {
        minioUtil.downloadFile(bucketName,fileName,fileName,response);
    }

    @GetMapping("deleted")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "删除文件")
    public R deleted(String fileName) {
        return R.ok(minioUtil.removeObject(bucketName,fileName));
    }

}
