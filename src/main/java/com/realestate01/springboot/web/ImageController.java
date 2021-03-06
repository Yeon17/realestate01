package com.realestate01.springboot.web;

import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Controller
public class ImageController {

    @PostMapping("/image_upload")
    @ResponseBody
    public JsonObject image_upload(@RequestParam("file") MultipartFile file){
        JsonObject mav = new JsonObject();
        String originalFileName = file.getOriginalFilename();

        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
        String savedFileName = UUID.randomUUID() + extension;

        String fileRoot = "/home/ec2-user/upload/";

        File targetFile = new File(fileRoot + savedFileName);

        try {
            //file.transferTo(targetFile);
            InputStream fileStream = file.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);
            mav.addProperty("url", "/upload/"+savedFileName);
            mav.addProperty("responseCode", "success");
        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
            mav.addProperty("responseCode", "error");
            e.printStackTrace();
        }

        return mav;
    }

    @DeleteMapping("/delete_img")
    @ResponseBody
    public void delete_img(@RequestParam("src") String src) throws IOException {
        String src2 = src.replace("/upload/","/home/ec2-user/upload/");
        File targetFile = new File(src2);
        java.nio.file.Files.delete(targetFile.toPath());
    }

}
