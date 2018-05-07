package com.kingsoft.lcgl.business.api.File.controller;

import com.kingsoft.lcgl.business.common.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

/**
 * Created by yangdiankang on 2017/11/15.
 */

@RestController
@RequestMapping("/api/file")
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private HttpServletRequest httpRequest;

    @RequestMapping(value="/testuploadimg", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String uploadImg(@RequestParam("file") MultipartFile file,
                                          HttpServletRequest request) {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        String filePath = request.getSession().getServletContext().getRealPath("imgs/");
        System.out.println("ip:"+request.getLocalAddr()+":"+request.getLocalPort());
        String img = request.getLocalAddr()+":"+request.getLocalPort()+"imgs/"+fileName;
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);

        } catch (Exception e) {
           return "上传失败";
        }

        return "上传成功";
    }
}
