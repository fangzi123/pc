package com.micro.rent.common.support.tag;

import com.micro.rent.common.support.DisplayPropUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.Properties;


public class SelectTag extends BodyTagSupport {

    private static final long serialVersionUID = 1L;

    private static Logger log = LoggerFactory.getLogger(SelectTag.class);

    private static Properties display = null;

    private String id;

    private String name;

    private String mapping;

    private String value;

    private boolean filterAll = false;

    private boolean disabled = false;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setFilterAll(boolean filterAll) {
        this.filterAll = filterAll;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public int doStartTag() throws JspException {
        if (display == null) {
            try {
                display = DisplayPropUtil.getDisplaySelectProp();
            } catch (Exception e) {
                log.error("display.properties load error:" + e);
            }
        }

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspTagException {
        try {
            pageContext.getOut().write(buildSelect());
        } catch (Exception e) {
            log.error("pageContext write error:" + e);
        }

        return EVAL_PAGE;
    }

    private String buildSelect() {
        StringBuffer buff = new StringBuffer();
        String bSelectHtml;
        if (id == null || id.equals("")) {
            bSelectHtml = "<select id=\"" + name + "\" name=\"" + name
                    + "\"  class=\"span2.5\">";
        } else {
            bSelectHtml = "<select id=\"" + id + "\" name=\"" + name
                    + "\"  class=\"span2.5\">";
        }

        if (disabled) {
            bSelectHtml = bSelectHtml.replace(">", "disabled=\"disabled\">");
        }

        String eSelectHtml = "</select>";
        String optionHtml = "<option value=key>text</option>";
        String optionAll = "<option value=\"\">全部</option>";

        String texts = null;
        String keys = null;

        if (mapping != null && !mapping.equals("")) {
            texts = (String) display.get(mapping + "Text");
            keys = (String) display.get(mapping + "Key");
        } else {
            texts = (String) display.get(name + "Text");
            keys = (String) display.get(name + "Key");
        }

        buff.append(bSelectHtml);

        if (!filterAll) {
            buff.append(optionAll);
        }

        if (texts != null) {
            String[] textArray = texts.split(",");
            String[] keyArray = keys.split(",");
            if (textArray.length != keyArray.length) {
                log.error("error: texts != keys");
                return null;
            }

            for (int i = 0; i < textArray.length; i++) {
                String temp = optionHtml.replace("text", textArray[i]);

                if (keyArray[i].equals(value)) {
                    temp = temp.replace("key", "\"" + keyArray[i] + "\" selected=\"selected\"");
                }
                temp = temp.replace("key", "\"" + keyArray[i] + "\"");
                buff.append(temp);

            }
        }

        buff.append(eSelectHtml);

        return buff.toString();

    }
}
