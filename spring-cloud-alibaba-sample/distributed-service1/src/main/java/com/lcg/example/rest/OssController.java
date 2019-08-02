package com.lcg.example.rest;

import com.aliyun.oss.OSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author linchuangang
 * @create 2019-08-02 14:45
 **/
@RestController
public class OssController {

    @Autowired
    private OSS ossClient;


    @PostMapping(value = "/file/upload")
    public Object uploadFile(
        MultipartFile file
    )throws Exception{
        //bucket可以先在oss界面上创建，或者通过代码来创建
        ossClient.putObject("bucket","oss-key",file.getInputStream());
        return ResponseEntity.ok();
    }

    @GetMapping(value = "/file/download")
    public String getFile(@RequestParam String fileKey){
        LocalDateTime dateTime = LocalDateTime.now();
        dateTime.plusDays(2);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = dateTime.atZone(zoneId);
        return ossClient.generatePresignedUrl("",fileKey, Date.from(zdt.toInstant())).toString();
    }
}
