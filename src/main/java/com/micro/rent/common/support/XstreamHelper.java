package com.micro.rent.common.support;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.Writer;
import java.util.regex.Pattern;

public class XstreamHelper {

    public static XStream crtXstream() {
        return new XStream(new XppDriver() {
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out) {
                    // 对那些xml节点的转换增加CDATA标记 true增加 false反之
                    boolean cdata = false;

                    @SuppressWarnings("unchecked")
                    public void startNode(String name, Class clazz) {

						/*if (!name.equals("xml")) {
                            char[] arr = name.toCharArray();
							if (arr[0] >= 'a' && arr[0] <= 'z') {
								// arr[0] -= 'a' - 'A';
								// ASCII码，大写字母和小写字符之间数值上差32
								arr[0] = (char) ((int) arr[0] - 32);
							}
							name = new String(arr);
						}*/

                        super.startNode(name, clazz);

                    }

                    @Override
                    public void setValue(String text) {

                        if (text != null && !"".equals(text)) {
                            // 浮点型判断
                            Pattern patternInt = Pattern
                                    .compile("[0-9]*(\\.?)[0-9]*");
                            // 整型判断
                            Pattern patternFloat = Pattern.compile("[0-9]+");
                            // 如果是整数或浮点数 就不要加[CDATA[]了
                            if (patternInt.matcher(text).matches()
                                    || patternFloat.matcher(text).matches()) {
                                cdata = false;
                            } else {
                                cdata = true;
                            }
                        }

                        super.setValue(text);
                    }

                    protected void writeText(QuickWriter writer, String text) {

						/*
						 * if (cdata) { text = "<![CDATA["+text+"]]>"; }
						 * 
						 * super.writeText(writer, text);
						 */
                        if (cdata) {
                            writer.write("<![CDATA[");
                            writer.write(text);
                            writer.write("]]>");
                        } else {
                            writer.write(text);
                        }
                    }
                };
            }

        });
    }
}
