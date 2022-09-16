package com.example.mytube_forlecture.fileSystem.service;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileSystemService {
    public void createFolder(String root) {
        File fileCheck = new File(root);
        if(!fileCheck.exists()) fileCheck.mkdirs();
    }
}
