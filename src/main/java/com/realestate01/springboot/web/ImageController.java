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

    @PostMapping("/uploadSummernoteImageFile")
    @ResponseBody
    public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {

        JsonObject jsonObject = new JsonObject();

        String fileRoot = "C:\\summernote_image\\";	//저장될 외부 파일 경로
        String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자

        String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명

        File targetFile = new File(fileRoot + savedFileName);

        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
            jsonObject.addProperty("url", "/summernoteImage/"+savedFileName);
            jsonObject.addProperty("responseCode", "success");
        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
            jsonObject.addProperty("responseCode", "error");
            e.printStackTrace();
        }

        return jsonObject;
    }


    @PostMapping("/image_upload")
    @ResponseBody
    public JsonObject image_upload(@RequestParam("file") MultipartFile file){
        JsonObject mav = new JsonObject();
        String originalFileName = file.getOriginalFilename();

        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
        String savedFileName = UUID.randomUUID() + extension;
        File targetFile = new File("c:\\upload\\property_image\\" + savedFileName);

        try {
            //file.transferTo(targetFile);
            InputStream fileStream = file.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);
            mav.addProperty("url", "/upload/property_image/"+savedFileName);
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
    public void delete_img(@RequestParam("src") String src){
        src.replace("/upload/property_image/","c:\\upload\\property_image\\");
        File targetFile = new File(src);
        FileUtils.deleteQuietly(targetFile);
    }

}
