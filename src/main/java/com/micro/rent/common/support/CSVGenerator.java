package com.micro.rent.common.support;

import com.csvreader.CsvWriter;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @Description CSV生成
 * @date 2013-3-14
 */
public class CSVGenerator {

    public static final char DELIMITER_COMMA_SEPERATED = ',';
    private static Logger log = LoggerFactory.getLogger(CSVGenerator.class);

    /**
     * @param response
     * @param charset
     * @param list
     * @param delimiter
     * @throws Exception
     * @Description 生成csv
     * @author
     */
    public static void generateCsvFile(HttpServletResponse response, String charset, List<String[]> list, char delimiter)
            throws Exception {
        //设置response头
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-disposition", "attachment; filename="
                .concat(DateTime.now().toString("yyyyMMddHHmmss")).concat(".csv"));
        OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream(), charset);


        CsvWriter out = new CsvWriter(writer, delimiter);
        if (list != null && !list.isEmpty()) {
            for (String[] line : list) {
                out.writeRecord(line, true);
            }
        }

        writer.flush();
        response.flushBuffer();
        IOUtils.closeQuietly(writer);
    }

    /**
     * @param list
     * @return
     * @Description 把list转换成可以生成csv的数据格式
     * @author
     */
    public static List<String[]> list2StringArray(List<Object> list) {
        if (list != null && !list.isEmpty()) {
            List<String[]> lstResult = new ArrayList<String[]>();
            for (Object o : list) {
                List<Map> lstCells = (List) ((Map) o).get("line");
                List<String> lstArray = new ArrayList<String>();
                for (Map cell : lstCells) {
                    lstArray.add((String) cell.get("cell"));
                }
                lstResult.add(lstArray.toArray(new String[]{}));
            }
            return lstResult;
        }
        return null;
    }
}
