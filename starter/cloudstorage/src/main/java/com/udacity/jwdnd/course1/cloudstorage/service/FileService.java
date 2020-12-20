package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public File getOne(int id) {
        return  fileMapper.find(id);
    }

    public int create(File file) {
        return fileMapper.create(file);
    }

    public void update(File file){
        fileMapper.update(file);
    }

    public void delete(int id) {
        fileMapper.deleteFile(id);
    }

    public List<File> getUserFiles(int userId) {
        return fileMapper.findByUser(userId);
    }

    public boolean isFileNameAvailable(String originalFilename) {
        List<File> file = fileMapper.getFileByName(originalFilename);
        return  file.size() == 0;
    }
}
