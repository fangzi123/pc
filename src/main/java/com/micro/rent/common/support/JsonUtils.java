package com.micro.rent.common.support;

import com.micro.rent.common.exceptions.SysException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator.Feature;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * JSON处理工具类
 *
 * @author
 */
public class JsonUtils {

    /**
     * 空JSON字符串
     */
    private final static String EMPTY_JSON_STRING = "{}";
    private static Log log = LogFactory.getLog(JsonUtils.class);

    /**
     * Map转换为JSON字符串
     *
     * @param map 需要转换的Map
     * @return
     */
    public static <E, V> String map2JsonString(Map<E, V> map) {
        try {
            ObjectMapper objectMapper = instanceObjectMapper();
            if (map == null || map.isEmpty()) return EMPTY_JSON_STRING;
            return objectMapper.writeValueAsString(map);
        } catch (JsonParseException e) {
            log.error(e.getMessage(), e);
            throw new SysException(e.getMessage(), e);
        } catch (JsonMappingException e) {
            log.error(e.getMessage(), e);
            throw new SysException(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new SysException(e.getMessage(), e);
        }
    }

    /**
     * List转换为JSON字符串
     *
     * @param list
     * @return
     * @Description
     * @author
     */
    public static <E> String list2JsonString(List<E> list) {
        try {
            ObjectMapper objectMapper = instanceObjectMapper();
            if (list == null || list.isEmpty()) return EMPTY_JSON_STRING;
            return objectMapper.writeValueAsString(list);
        } catch (JsonParseException e) {
            log.error(e.getMessage(), e);
            throw new SysException(e.getMessage(), e);
        } catch (JsonMappingException e) {
            log.error(e.getMessage(), e);
            throw new SysException(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new SysException(e.getMessage(), e);
        }
    }

    /**
     * Object转换为JSON字符串
     *
     * @param obj
     * @return
     * @Description
     * @author
     */
    public static String obj2JsonString(Object obj) {
        try {
            ObjectMapper objectMapper = instanceObjectMapper();
            if (obj == null) return EMPTY_JSON_STRING;
            return objectMapper.writeValueAsString(obj);
        } catch (JsonParseException e) {
            log.error(e.getMessage(), e);
            throw new SysException(e.getMessage(), e);
        } catch (JsonMappingException e) {
            log.error(e.getMessage(), e);
            throw new SysException(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new SysException(e.getMessage(), e);
        }
    }

    /**
     * JSON字符串转换为Map
     *
     * @param strJsonString 需要转换的JSON字符串
     * @return
     */
    public static <E, V> Map<E, V> jsonString2Map(String strJsonString) {
        try {
            ObjectMapper objectMapper = instanceObjectMapper();
            if (StringUtils.isBlank(strJsonString)) strJsonString = EMPTY_JSON_STRING;
            return objectMapper.readValue(strJsonString, Map.class);
        } catch (JsonParseException e) {
            log.error(e.getMessage(), e);
            throw new SysException(e.getMessage(), e);
        } catch (JsonMappingException e) {
            log.error(e.getMessage(), e);
            throw new SysException(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new SysException(e.getMessage(), e);
        }
    }

    /**
     * JSON字符串转换为Object
     *
     * @param strJsonString 需要转换的JSON字符串
     * @param clazz
     * @return
     */
    public static <T> T jsonString2Object(String strJsonString, Class<T> clazz) {
        try {
            ObjectMapper objectMapper = instanceObjectMapper();
            if (StringUtils.isBlank(strJsonString)) strJsonString = EMPTY_JSON_STRING;
            return objectMapper.readValue(strJsonString, clazz);
        } catch (JsonParseException e) {
            log.error(e.getMessage(), e);
            throw new SysException(e.getMessage(), e);
        } catch (JsonMappingException e) {
            log.error(e.getMessage(), e);
            throw new SysException(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new SysException(e.getMessage(), e);
        }
    }

    /**
     * 实例化ObjectMapper
     *
     * @return
     */
    public static ObjectMapper instanceObjectMapper() {
        JsonFactory jf = new JsonFactory();
        jf.configure(Feature.WRITE_NUMBERS_AS_STRINGS, true);
        return new ObjectMapper(jf);
    }
}
