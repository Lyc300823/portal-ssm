package com.leozhang.portalssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.leozhang.portalssm.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RequestMapping("/file")
@Controller
public class FileController {
    //填写public文件夹路径
    public static String fileFolder = "D:\\public\\";
    @RequestMapping("/upload")
    @ResponseBody
    public Result upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        //上传到服务器的步骤
        /*1.获取上传的源文件文件名，并获取后缀
        * 2.将文件名修改为随机名+后缀
        * 3.在目的路径下，创建当前名字的新文件
        * 4.然后把上传的文件multipartFile转移到刚才新建的文件目录
        * 5.上传完毕，返回数据
        * */
        String ext = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        String newName = UUID.randomUUID().toString()+ext;
        File file = new File(fileFolder+newName);
        multipartFile.transferTo(file);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url","public/"+newName);
        jsonObject.put("name",newName);
        return Result.end(200,jsonObject,"上传成功",0);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(@RequestParam("url") String url){
        String fileName = url.substring(url.lastIndexOf("/")+1);
        String path = fileFolder+fileName;
        File file = new File(path);
        if (file.exists()){
            file.delete();
        }
        return Result.end(200,null,"删除成功",0);
    }

}