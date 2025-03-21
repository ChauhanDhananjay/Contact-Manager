package com.contact.Smart.Contact.Manager.Validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FileValidator implements ConstraintValidator<ValidFile, MultipartFile> {
    private static final long MAX_FILE_SIZE = 1024 * 1024 * 2; // 2MB
    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()) {

            context.disableDefaultConstraintViolation();
             context.buildConstraintViolationWithTemplate("File cannot be empty").
                     addConstraintViolation();
            return true;

        } // file size

        System.out.println("file size: " + file.getSize());

        if (file.getSize() > MAX_FILE_SIZE) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File size should be less than 2MB").addConstraintViolation();
            return false;
        }

        // resolution

//        try {
//         BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
//
//         if(bufferedImage.getHeight())
//
//         } catch (IOException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//         }
        return true;
    }
}