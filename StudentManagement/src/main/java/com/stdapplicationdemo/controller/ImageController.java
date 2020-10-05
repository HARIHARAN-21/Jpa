package com.stdapplicationdemo.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stdapplicationdemo.model.ImageTest;
import com.stdapplicationdemo.repository.ImageTestRepository;

@RestController
public class ImageController {

	@Autowired
	public ImageTestRepository imagerep;
	
    @PostMapping("/insertImage")
    public String insertImage(@RequestParam("file") MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        ImageTest imagetest = new ImageTest();
        try {
            imagetest.setImagedata(file.getBytes());
            imagetest.setFilename(fileName);
            imagetest.setFiletype(file.getContentType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        imagerep.save(imagetest);
        return "Image inserted";
    }
   
     @GetMapping("/getfile/{Id}")
        public ResponseEntity<Resource> downloadFile(@PathVariable Integer Id) {
            // Load file from database
             Optional<ImageTest> image;
            image = imagerep.findById(Id);
            ImageTest ima=null;
            if(image.isPresent()) {
                ima = image.get();
            }
    return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(ima.getFiletype()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + ima.getFilename() + "\"")
                    .body(new ByteArrayResource(ima.getImagedata()));

     }
	
}
