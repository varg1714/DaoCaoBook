package com.daocao.userweb.controller;

import com.daocao.myentity.Result;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author varg
 * @date 2020/4/14 0:36
 */
@RestController
public class UploadController {

    @Value("${fastdfs.host}")
    String host;

    @Resource
    FastFileStorageClient fileStorageClient;

    @PostMapping("/upload")
    public Result upload(MultipartFile file){
        if(file == null){
            return new Result(false,"未选择文件");
        }
        try {
            StorePath path = fileStorageClient.uploadFile(
                    file.getInputStream(),file.getSize(),
                    FilenameUtils.getExtension(file.getOriginalFilename()),
                    null);
            String fullPath = "http://"+host+path.getFullPath();
            return new Result(true,fullPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(false,"上传失败");
    }
}
