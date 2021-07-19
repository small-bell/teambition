package com.smallbell.teambition.fastdfs.controller;

import com.smallbell.teambition.fastdfs.model.FileSystem;
import com.smallbell.teambition.fastdfs.utils.FastDFSUtil;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@RestController
@CrossOrigin
@RequestMapping("/filesystem")
public class FileServerController {
    @Value("${fastdfs.upload_location}")
    private String upload_location;

    @PostMapping(value = "/upload")
    @ResponseBody
    public FileSystem upload(@RequestParam("file") MultipartFile file){

        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileId = UUID.randomUUID().toString();
        String newFileName = fileId + extension;
        File f = new File(upload_location + newFileName);
        try {
            file.transferTo(f);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        String local_filename = f.getAbsolutePath();
        String[] result = FastDFSUtil.upload(local_filename, extension);
        if (result == null) {
            return null;
        }

        FileSystem fileSystem = new FileSystem();
        fileSystem.setFileId(fileId);
        fileSystem.setFileName(originalFilename);
        fileSystem.setGroupName(result[0]);
        fileSystem.setFilePath(result[1]);
        fileSystem.setFileSize(file.getSize());
        fileSystem.setFileType(file.getContentType());
        f.delete();

        return fileSystem;
    }

    @GetMapping(value = "/download")
    @ResponseBody
    public String download(String groupName, String remoteFileName, String localFileName) {
        FastDFSUtil.download(groupName, remoteFileName, localFileName);
        return new String();
    }

    @GetMapping(value = "/delete")
    @ResponseBody
    public static int delete(String groupName, String remoteFileName) {
        int result = FastDFSUtil.delete(groupName, remoteFileName);
        return result;
    }


}
