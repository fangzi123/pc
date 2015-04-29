package com.micro.rent.common.support;

import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.CompactWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.Writer;

public class CustomXppDriver extends XppDriver {
    public HierarchicalStreamWriter createWriter(Writer out) {
        return new CompactWriter(out) {
            public void startNode(String name, @SuppressWarnings("rawtypes") Class clazz) {
                super.startNode(name, clazz);
            }

            protected void writeText(QuickWriter writer, String text) {
                boolean isCdata = false;
                int length = text.length();
                for (int i = 0; i < length; i++) {
                    char c = text.charAt(i);
                    switch (c) {
                        case '&':
                            isCdata = true;
                            break;
                        case '<':
                            isCdata = true;
                            break;
                        case '>':
                            isCdata = true;
                            break;
                        case '"':
                            isCdata = true;
                            break;
                        case '\'':
                            isCdata = true;
                            break;
                        case '\r':
                            isCdata = true;
                    }

                }

                if (isCdata) {
                    writer.write("<![CDATA[");
                    writer.write(text);
                    writer.write("]]>");
                } else {
                    writer.write(text);
                }
            }
        };
    }
}
