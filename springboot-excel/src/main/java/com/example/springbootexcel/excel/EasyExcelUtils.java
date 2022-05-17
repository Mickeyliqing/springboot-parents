package com.example.springbootexcel.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;


/**
 * @Author: liqing
 * @Date: 2022-04-18
 * @Class: EasyExcelUtils
 * @Discription: 定义读取和写入 Excel 的公共方法
 **/
@Component
@Slf4j
public class EasyExcelUtils {

    /**
     * 定义读取区划的 Excel 的数据
     * 这里采用了实体类 T 去封装数据，并采取了带返回值的方法
     *
     * @param inputStream
     * @return
     */
    public List<T> readRegionData(InputStream inputStream) throws Exception {
        try {
            List<T> list = EasyExcel.read(inputStream)
                    /**
                     * 这个转换是成全局的， 所有 java 为 string,excel为 string 的都会用这个转换器。
                     * 如果就想单个字段使用请使用 @ExcelProperty 指定 converter
                     */
                    .registerConverter(new StringConverter())
                    /**
                     * 注册监听器，可以在这里校验字段
                     */
                    .registerReadListener(new EasyExcelRegionListener())
                    .head(T.class)
                    .sheet()
                    .headRowNumber(1)
                    .doReadSync();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 读取 Excel 的 Sheet
     *
     * @param inputStream
     * @return
     */
    public List<ReadSheet> getExcelSheet(InputStream inputStream) {
        try {
            return EasyExcel.read(inputStream).build().excelExecutor().sheetList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 导出数据，文件有框架自动生成
     * @param list
     * @throws Exception
     */
    public void exportRegionData(List<T> list, HttpServletResponse response) throws Exception {
        try {
            /**
             * 定义导出的文件名并封装数据
             */
            String fileName = "导出";
            response.reset();
            response.setContentType("application/csv;charset=utf-8");
            response.setHeader("Content-Disposition", String.format("attachment; filename=%s", URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()) + ".csv"));

            /**
             * 导出方法，直接使用对应的类 T 去封装数据
             */
            EasyExcel.write(response.getOutputStream(), T.class)
                    .sheet("区划信息")
                    /**
                     * 封装时间字段信息
                     */
                    .registerConverter(new LocalDateStringConverter())
                    /**
                     * 表格属性自适应
                     */
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
