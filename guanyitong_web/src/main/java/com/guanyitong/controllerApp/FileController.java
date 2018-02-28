package com.guanyitong.controllerApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.guanyitong.service.IFileService;
import util.JsonResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
@RequestMapping("/api")
public class FileController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IFileService fileService;
    /**
     * 文件上传(头像上传)
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public JsonResult upload(HttpServletRequest request, HttpServletResponse response) {
        JsonResult result = new JsonResult();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");
        try {
            if (file != null) {
                result = fileService.upload(file);
            } else {
                throw new Exception("file is null");
            }
        } catch (Exception e) {
            logger.error("------------- file update error --------------------");
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
        }
        return result;
    }
}
