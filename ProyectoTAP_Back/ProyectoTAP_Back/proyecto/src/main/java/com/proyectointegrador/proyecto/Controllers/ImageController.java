package com.proyectointegrador.proyecto.Controllers;

import com.proyectointegrador.proyecto.Models.ImageModel;
import com.proyectointegrador.proyecto.Repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@RestController
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "check")

    public class ImageController {

    @Autowired
    ImageRepository imageRepository;

    @PostMapping("/upload")
    public ImageModel uplaodImage(@RequestParam("myFile") MultipartFile file) throws IOException {

        ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
        final ImageModel savedImage = imageRepository.save(img);
        System.out.println("Image saved");
        return savedImage;
    }
    @GetMapping("/get/{id}")
    public ImageModel getImage(@PathVariable("id") Long id) throws IOException {
        final Optional<ImageModel> retrievedImage = imageRepository.findById(id);
        ImageModel img = new ImageModel(retrievedImage.get().getId(),retrievedImage.get().getName(), retrievedImage.get().getType(),
                decompressBytes(retrievedImage.get().getPic()));
        return img;
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}