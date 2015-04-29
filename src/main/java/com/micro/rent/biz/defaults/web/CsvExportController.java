package com.micro.rent.biz.defaults.web;

import com.micro.rent.common.support.CSVGenerator;
import com.micro.rent.common.support.JsonUtils;
import com.micro.rent.common.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


/**
 * @author
 * @version 1.0
 * @Description csv导出
 * @date 2013-3-14
 */
@Controller
@RequestMapping("/export")
public class CsvExportController extends BaseController {
    /**
     * @param charset
     * @param csvdata
     * @param response
     * @throws Exception
     * @Description csv当页导出(下载)
     * @author
     */
    @RequestMapping("csvExport")
    public void csvExport(
            @RequestParam("charset") String charset,
            @RequestParam("csvdata") String csvdata,
            HttpServletResponse response) throws Exception {
        log.debug("Get data: {}", new Object[]{csvdata});
        List<Object> datalist = JsonUtils.jsonString2Object(csvdata, ArrayList.class);
        //输出csv到response
        CSVGenerator.generateCsvFile(response, charset, CSVGenerator.list2StringArray(datalist), ',');
    }
}
