package com.demo.imagewithcrud.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.demo.imagewithcrud.model.Image;
import com.demo.imagewithcrud.repository.ImageRepo;

@Service
public class ImageService{

  private ImageRepo imagerepo;


  public ImageService(ImageRepo imagerepo){
    this.imagerepo=imagerepo;
  }


  public Image saveImage(Image img){
    return imagerepo.save(img);
  }

    public List<Image> getAllImages() {
        return imagerepo.getAllImages();
    }


    public Image getImageById(Long id){
         return imagerepo.getImageById(id);
                // .orElseThrow(() -> new RuntimeException("Image not found with id: " + id));
    }

    public Image UpdateImg(Image img){
      return imagerepo.updatImage(img);
    }


    public boolean DeleteImageById(Long id){
      return imagerepo.DeleteImageById(id);
    }


}