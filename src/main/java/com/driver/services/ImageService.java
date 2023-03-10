package com.driver.services;
import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image = new Image();
        Blog blog = blogRepository2.findById(blogId).get();
        image.setDescription(description);
        image.setDimensions(dimensions);
        List<Image> imageList = blog.getImageList();
        imageList.add(image);
        blog.setImageList(imageList);
        image.setBlog(blog);
        blogRepository2.save(blog);
        return image;

    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        Image image = imageRepository2.findById(id).get();
        String dim = image.getDimensions();
        String[] imagearr = dim.split("X");
        String[] screenarr = screenDimensions.split("X");
        int count = (Integer.valueOf(screenarr[0])/Integer.valueOf(imagearr[0])) *(Integer.valueOf(screenarr[1])/Integer.valueOf(imagearr[1]));
        return count;

    }
}
