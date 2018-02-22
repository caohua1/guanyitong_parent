package com.guanyitong.service.impl;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.guanyitong.service.IFileService;
import util.EncodeUtils;
import util.FileUtils;
import util.JsonResult;


import javax.imageio.ImageIO;
import javax.servlet.ServletException;

import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by 7 on 2017/9/26.
 */
@Service("fileServiceImpl")
@PropertySource("classpath:conf/file.properties")
public class FileServiceImpl implements IFileService {
    @Value("${FILE_PATH}")
    private String savePath;
    @Value("${FILE_READ_PATH}")
    private String fileReadPath;
    private String fileType;
    @Override
    public String getSavePath() {
        return savePath;
    }
    @Override
    public String getFileReadPath() {
        return fileReadPath;
    }
    @Override
    public JsonResult upload(MultipartFile multipartFile)
            throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        String realName = "";
        if(fileName.indexOf(".")!=-1){
            realName = fileName.substring(0, fileName.lastIndexOf("."));
        }else{
            realName = new Date() +"."+ fileName;
        }
        String type = FileUtils.getFileType(fileName).toLowerCase();
        long fileSize = multipartFile.getSize();

        return this.upload(multipartFile.getInputStream(), type, realName,
                fileSize);
    }
    @Override
    public void delete(String filePath) {
        FileUtils.deleteFile(filePath);
    }
    /**
     * 生成新的文件名
     * @param type
     * @return
     */
    private String getFileName(String type) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        StringBuffer sb = new StringBuffer();
        sb.append("/" + type + "/"); //根据文件类型划分文件夹
        sb.append(dateFormat.format(new Date())); //上传文件日期
        sb.append("/");
        sb.append(UUID.randomUUID().toString());
        sb.append(".");
        sb.append(type);

        return sb.toString();
    }
    @Override
    public JsonResult upload(InputStream inputStream, String type,
                             String realName, long fileSize) throws IOException {
        String fileName = "/summary" + this.getFileName(type);
        String filePath = savePath + fileName;
        FileUtils.saveFile(inputStream, filePath);
        String[] video = new String[]{"rmvb","avi","mp4","3gp"};
        String[] audio = new String[]{"mp3","amr"};
        String[] img = new String[]{"jpg","png","bmp","gif"};
        String[] file = new String[]{"doc","docx","ppt","pptx","xls","xlsx","pdf","txt","zip"};
        if(Lists.asList("",video).contains(type)){
            fileType = "video";
        }
        if(Lists.asList("",audio).contains(type)){
            fileType = "audio";
        }
        if(Lists.asList("",img).contains(type)){
            fileType = "img";
        }
        if(Lists.asList("",file).contains(type)){
            fileType = "file";
        }
        File oldFile = new File(filePath);
        BufferedImage image = ImageIO.read(oldFile);
       /* if (image != null) {
            Integer[] xs = new Integer[] { 80, 400 }; //生成对象像素图片文件

            for (Integer x : xs) {
                File f = new File(filePath.replace("." + type,
                        "_" + x + "_" + x + "." + type));
                DimensionConstrain constrain = DimensionConstrain.createAbsolutionDimension(x,
                        x);
                AdvancedResizeOp op = new ThumbnailRescaleOp(constrain);
                BufferedImage dest = op.filter(image, null);
                ImageIO.write(dest, type, f);
            }
        }*/
        /*String fileName=this.getFileName(type);
        String filePath = savePath + fileName;
        FileUtils.saveFile(inputStream,filePath);*/

        //如果是flv 格式将flv转换为mp4格式
        /*if(type.toLowerCase().equals("flv")){
          try{
              String mp4FilePath = flvToMp4(filePath);
              //删除源文件
              FileUtils.deleteFile(filePath);
              fileName = fileName.replace(".flv",".mp4");
          }catch (Exception e){
              e.printStackTrace();
          }
        }*/
        return getResult(fileName, realName, fileSize);
    }
    //下载
   /* @Override
    public void download(String path, String fileName,
                         HttpServletResponse response) throws Exception {
        //String filePath=this.getPath(path,fileName);
        DownloadUtils.download(response, path, fileName);
    }*/
    //获取真实路径
    private String getPath(String path, String fileName) {
        return savePath + "/" + path;
    }
    /**
     * 构建结果集
     * @param filePath 文件路径
     * @param realName 上传前文件名
     * @param fileSize
     * @return
     */
    private JsonResult getResult(String filePath, String realName, long fileSize) {
        JsonResult result = new JsonResult();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("filePath", savePath + filePath);
        //文件上传前的文件名
        data.put("fileName", realName);
        /*String id = getEncodeFileId(savePath+filePath+"_"+realName);
        data.put("viewPath",fileReadPath+"/"+id+".do");
        data.put("viewPath","Myfile" + filePath);*/
        String id = getEncodeFileId(filePath + "_" + realName+"_"+fileSize); //加密路径  只加密后半部分
        data.put("viewPath", fileReadPath + "/" + id + ".do"); //程序读取先保留
        data.put("readPath", filePath);
        data.put("id", id);
        data.put("fileSize", fileSize);
        data.put("fileType",fileType);
        result.setState(JsonResult.SUCCESS);
        result.setData(data);

        return result;
    }

    //获取加密id
    private String getEncodeFileId(String filePath) {
        String url = EncodeUtils.urlEncode(filePath);
        String fileId = EncodeUtils.base64Encode(url.getBytes());

        return fileId;
    }

    //获取解密路径
    @Override
    public String getDecodePathById(String id) {
        String url = null;

        try {
            String filePath = new String(EncodeUtils.base64Decode(id), "UTF-8");
            url = EncodeUtils.urlDecode(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * 视频格式转换
     * @param filePath  源文件路径
     */
   /* private String flvToMp4(String filePath) throws Exception {
        String sourcePath = filePath;
        String targetPath = filePath.replace(".flv", ".mp4");

        File source = new File(sourcePath);
        File target = new File(targetPath); // 转MP4
        System.out.println(source.length());

        AudioAttributes audio = new AudioAttributes();

        audio.setCodec("libmp3lame");
        audio.setBitRate(new Integer(128000));
        audio.setChannels(new Integer(1));
        audio.setSamplingRate(new Integer(22050));

        VideoAttributes video = new VideoAttributes();
        video.setCodec("flv"); // 转MP4
        video.setBitRate(new Integer(160000)); // 160kb/s比特率
        video.setFrameRate(new Integer(1)); // 1f/s帧频，1是目前测试比较清楚的，越大越模糊

        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp4"); // 转MP4
        attrs.setAudioAttributes(audio);
        attrs.setVideoAttributes(video);

        Encoder encoder = new Encoder();
        long beginTime = System.currentTimeMillis();

        // 获取时长
        MultimediaInfo m = encoder.getInfo(source);
        System.out.println(m.getDuration());
        System.out.println("获取时长花费时间是：" +
                (System.currentTimeMillis() - beginTime));
        beginTime = System.currentTimeMillis();
        encoder.encode(source, target, attrs);
        System.out.println("视频转码花费时间是：" +
                (System.currentTimeMillis() - beginTime));

        return targetPath;
    }
*/
    /**
     * 压缩文件列表中的文件
     * @param files
     * @param outputStream
     * @throws IOException
     */
    public void zipFile(Map<String,File> files, ZipOutputStream outputStream) throws IOException, ServletException {
        try {
            for(String fileName:files.keySet()){
                File file =  files.get(fileName);
                String ext = file.getPath().substring(file.getPath().lastIndexOf("."));
                zipFile2(file, outputStream,fileName+ext);
            }
        } catch (IOException e) {
            throw e;
        }
    }
    /**
     * 将文件写入到zip文件中
     *
     * @param inputFile
     * @param outputstream
     * @throws Exception
     */
    public  void zipFile2(File inputFile, ZipOutputStream outputstream, String fileName) throws IOException, ServletException {
        try {
            if (inputFile.exists()) {
                if (inputFile.isFile()) {
                    FileInputStream inStream = new FileInputStream(inputFile);
                    BufferedInputStream bInStream = new BufferedInputStream(inStream);
                    ZipEntry entry = new ZipEntry(fileName);
                    outputstream.putNextEntry(entry);
                    final int MAX_BYTE = 10 * 1024 * 1024; // 最大的流为10M
                    long streamTotal = 0; // 接受流的容量
                    int streamNum = 0; // 流需要分开的数量
                    int leaveByte = 0; // 文件剩下的字符数
                    byte[] inOutbyte; // byte数组接受文件的数据
                    streamTotal = bInStream.available(); // 通过available方法取得流的最大字符数
                    streamNum = (int) Math.floor(streamTotal / MAX_BYTE); // 取得流文件需要分开的数量
                    leaveByte = (int) streamTotal % MAX_BYTE; // 分开文件之后,剩余的数量
                    if (streamNum > 0) {
                        for (int j = 0; j < streamNum; ++j) {
                            inOutbyte = new byte[MAX_BYTE];
                            bInStream.read(inOutbyte, 0, MAX_BYTE);// 读入流,保存在byte数组
                            outputstream.write(inOutbyte, 0, MAX_BYTE); // 写出流
                        }
                    }
                    inOutbyte = new byte[MAX_BYTE];
                    bInStream.read(inOutbyte, 0, MAX_BYTE);// 读入流,保存在byte数组
                    outputstream.write(inOutbyte, 0, MAX_BYTE); // 写出流
                    // 写出剩下的流数据
                    inOutbyte = new byte[leaveByte];
                    bInStream.read(inOutbyte, 0, leaveByte);
                    outputstream.write(inOutbyte);
                    outputstream.closeEntry(); //关闭zipEntry
                    bInStream.close(); // 关闭
                    inStream.close();
                }
            } else {
                throw new ServletException("文件不存在！");
            }
        } catch (IOException e) {
            throw e;
        }
    }
}

