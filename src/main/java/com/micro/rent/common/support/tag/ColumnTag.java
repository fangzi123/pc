package com.micro.rent.common.support.tag;

import com.micro.rent.common.support.DisplayPropUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.Properties;

public class ColumnTag extends BodyTagSupport {

    private static final long serialVersionUID = 1L;

    private static Logger log = LoggerFactory.getLogger(ColumnTag.class);

    private static Properties display = null;

    private String name;

    private String value;

    private String fill;

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    @Override
    public int doStartTag() throws JspException {
        if (display == null) {
            try {
                display = DisplayPropUtil.getDisplayProp();
            } catch (Exception e) {
                log.error("display.properties load error:" + e);
            }
        }

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspTagException {
        String content = (String) display.get(name + value);
        try {
            if (content != null) {
                pageContext.getOut().write(content);
            } else {
                if (fill != null) {
                    pageContext.getOut().write(fill);
                } else {
                    pageContext.getOut().write(value);
                }

            }
        } catch (Exception e) {
            log.error("pageContext write error:" + e);
        }

        return EVAL_PAGE;
    }
}
