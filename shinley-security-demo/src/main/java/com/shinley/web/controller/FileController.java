package com.shinley.web.controller;


import com.shinley.dto.FileInfo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/upload")
public class FileController {
    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        String folder = this.getClass().getResource(".").getPath();
        File localFile = new File(folder, System.currentTimeMillis() + ".txt");
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id,  HttpServletRequest request, HttpServletResponse response) {

            String folder = this.getClass().getResource(".").getPath();
            try(InputStream inputStream = new FileInputStream(new File(folder, id+".txt")); OutputStream outputStream = response.getOutputStream()) {
                response.setContentType("application/x-download");
                response.addHeader("content-Disposition", "attachment;filename=text.txt");
                IOUtils.copy(inputStream, outputStream);
                outputStream.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
