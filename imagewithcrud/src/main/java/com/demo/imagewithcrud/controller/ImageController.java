package com.demo.imagewithcrud.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.demo.imagewithcrud.model.Image;
import com.demo.imagewithcrud.service.ImageService;

@RestController
@RequestMapping("/img")

@CrossOrigin(origins = { "http://127.0.0.1:5500", "http://localhost:5500" })
public class ImageController {

    private ImageService imageservice;

    public ImageController(ImageService imageservice) {
        this.imageservice = imageservice;
    }

    @PostMapping("/upload")
    public ResponseEntity<Image> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        Image img = new Image();
        img.setImagename(file.getOriginalFilename());
        img.setImagetype(file.getContentType());
        img.setImagedata(file.getBytes());

        return new ResponseEntity<>(imageservice.saveImage(img), HttpStatus.CREATED);
    }

    // @GetMapping
    // public ResponseEntity<List<Image>> getAllImages() {
    // return new ResponseEntity<>(imageservice.getAllImages(), HttpStatus.OK);
    // }

    @GetMapping
    public List<Image> getAllImages() {
        return imageservice.getAllImages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getImage(@PathVariable Long id) {
        try {
            Image img = imageservice.getImageById(id);
            return ResponseEntity.ok(img);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entered Image Was Not Found " + id);
        }
    }

    // TO Update the Image
@PutMapping("/{id}")
public Image UpdateImg(
        @PathVariable long id,
        @RequestParam("imagename") String imagename,
        @RequestParam(value = "file", required = false) MultipartFile file // optional
) throws IOException {

    // Fetch existing image from DB
    Image img = imageservice.getImageById(id); // make a method to get existing record

    // Update name
    img.setImagename(imagename);

    // Update file if provided
    if (file != null && !file.isEmpty()) {
        img.setImagetype(file.getContentType());
        img.setImagedata(file.getBytes());
    }

    // Save updated image
    return imageservice.UpdateImg(img);
}

    @DeleteMapping("/{id}")
    public ResponseEntity<?> DeleteImageById(@PathVariable Long id) {
        boolean val = imageservice.DeleteImageById(id);
        if (val) {
            return ResponseEntity.ok("Entered Image Was SuccessFully Deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entered Image Was Not Found");
        }
    }
}
