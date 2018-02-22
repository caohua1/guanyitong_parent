package com.guanyitong.service;


import org.springframework.web.multipart.MultipartFile;
import util.JsonResult;

import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.zip.ZipOutputStream;

public interface IFileService {

    /**
     * 通过MultipartFile对象保存文件，返回文件名称
     * @param multipartFile
     * @return
     */
    JsonResult upload(MultipartFile multipartFile) throws Exception;

    /**
     * 通过InputStream对象保存文件，返回文件名称
     * @param inputStream
     * @param type  文件类型
     * @param realName  文件真实名称
     * @return
     */
    JsonResult upload(InputStream inputStream, String type, String realName, long fileSize) throws Exception;

    /**
     * 下载文件
     * @param path
     * @param fileName
     * @param response
     */
    /*void download(String path, String fileName, HttpServletResponse response)throws Exception;*/

    /**
     * 删除文件
     * @param path
     */
    void delete(String path);

    /**
     * 获取解密路径
     * @param id
     * @return
     */
    public  String getDecodePathById(String id);

    public String getSavePath();

    public String getFileReadPath();

    public void zipFile(Map<String, File> files, ZipOutputStream outputStream) throws IOException, ServletException;

}

