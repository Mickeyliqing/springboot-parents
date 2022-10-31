package com.example.springbootexcel.files;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


/**
 * @Author:
 * @Date:
 * @Class:
 * @Discription:
 **/
public class ExportAndDownFiles {

    @Autowired
    private ResourceLoader resourceLoader;

    /**
     * 下载文件
     * @throws Exception
     */
    public void downRegionData(HttpServletResponse response) throws Exception{
        InputStream inputStream = null;
        ServletOutputStream servletOutputStream = null;
        try {
            /**
             * 定义文件名称
             */
            String fileName = "新增区划导入";

            /**
             * 定义文件路径，这里要使用相对路径，要不然部署到服务器，会报找不到下载文件
             * InputStream 是拿不到相对路径的，需要使用 ResourceLoader 这个类去获取
             */
            String path = "files/新增区划导入.csv";
            Resource resource = resourceLoader.getResource("classpath:" + path);

            /**
             * 封装返回接口数据
             */
            response.reset();
            response.setContentType("application/csv;charset=utf-8");
            response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.addHeader("charset", "utf-8");
            response.addHeader("Pragma", "no-cache");
//            String encodeName = URLEncoder.encode(filename, StandardCharsets.UTF_8.toString());
//            response.setHeader("Content-Disposition", "attachment; filename=" + encodeName);
            response.setHeader("Content-Disposition", String.format("attachment; filename=%s", URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()) + ".csv"));

            /**
             * 获取文件流
             */
            inputStream = resource.getInputStream();
            servletOutputStream = response.getOutputStream();
            IOUtils.copy(inputStream, servletOutputStream);
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (servletOutputStream != null) {
                    servletOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
