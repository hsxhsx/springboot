package com.test.dbhappy.minio;


import cn.hutool.core.lang.UUID;
import com.test.dbhappy.gen.R;
import com.test.dbhappy.utils.MinioUtil;
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
@RequestMapping("/minio")
public class MinioController {

    @Value("${minio.bucketName}")
    private String bucketName;

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.port}")
    private String port;

    @Autowired
    private MinioUtil minioUtil;

    @ResponseBody
    @PostMapping("/upload")
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

    @RequestMapping("/download")
    public void download(HttpServletResponse response, String fileName) {
        minioUtil.downloadFile(bucketName,fileName,fileName,response);
    }

    @RequestMapping("/deleted")
    public R deleted(String fileName) {
        return R.ok(minioUtil.removeObject(bucketName,fileName));
    }

}
