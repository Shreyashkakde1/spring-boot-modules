package com.shreyash.FileUpload.controller;

import com.shreyash.FileUpload.payload.FileResponse;
import com.shreyash.FileUpload.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @Value("${project.images}")
    private String path;


    // Handler for file uploading
    @PostMapping("/upload")
    public ResponseEntity<FileResponse> fileUpload(@RequestParam("image")MultipartFile image)  {
        String fileName = image.getOriginalFilename();

        try {
            fileName = this.fileService.uploadImage(path, image);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new FileResponse(fileName,"File was not uploaded due to some error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        FileResponse fileResponse = new FileResponse(
                fileName,"Image is successfully Uploaded"
        );
        return new ResponseEntity<>(fileResponse, HttpStatus.OK);
    }

    // Handler for file serving
    @GetMapping(value = "/download/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadFile(@PathVariable("imageName") String imageName,
                             HttpServletResponse response) throws IOException {
        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
