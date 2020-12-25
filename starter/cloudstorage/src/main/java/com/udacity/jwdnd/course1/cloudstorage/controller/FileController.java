package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("files")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/view/{fileId}")
    public ResponseEntity<Resource> getFile(@PathVariable Integer fileId, Authentication authentication)  {
        File file = fileService.getOne(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                + file.getFileName() + "\"").body(new ByteArrayResource(file.getFileData()));
    }

    @PostMapping()
    public String createUpdate(Authentication authentication, @RequestParam("fileUpload") MultipartFile multipartFile, Model model) throws IOException {
        User currentUser = (User) authentication.getPrincipal();

        InputStream is = multipartFile.getInputStream();
        File file = new File();
        String error = null;

        // check file name availability
        if(fileService.isFileNameAvailable(multipartFile.getOriginalFilename())) {
            file.setFileData(is.readAllBytes());
        } else {
            error = "File name already exist! ";
        }

        // check for empty file
        if(multipartFile.getSize() == 0 )
            error = "File is empty!";

        // check for maximum file size, max size is 5MB as configured at properties file
        if(multipartFile.getSize() < 1000000 * 5 )
            file.setFileSize(multipartFile.getSize());
        else
            error = "File is bigger than 5MB  large! ";

        if(error == null) {
            file.setContentType(multipartFile.getContentType());
            file.setFileName(multipartFile.getOriginalFilename());

            file.setUserId(currentUser.getUserId());
            if (file.getFileId() != null)
                fileService.update(file);
            else
                fileService.create(file);

            model.addAttribute("success", "File Uploaded successfully! ");
        }

        model.addAttribute("error", error);
        return "result";
    }

    @GetMapping("delete-file")
    public String deleteFile(@RequestParam("fileId") int fileId, Model model){
        fileService.delete(fileId);
        model.addAttribute("success","File Deleted successfully!");
        return "result";
    }

}
