package com.micro.rent.biz.weixin;


import com.micro.rent.biz.weixin.message.BaseMessage;
import com.micro.rent.biz.weixin.message.EMsgType;
import com.micro.rent.biz.weixin.message.receive.event.LocationEventMessage;
import com.micro.rent.biz.weixin.message.receive.event.SubscribeEventMessage;
import com.micro.rent.biz.weixin.message.receive.normal.TextMessage;
import com.micro.rent.biz.weixin.message.send.passive.Article;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.Writer;
import java.util.Date;
import java.util.regex.Pattern;

public class MsgTransformer {

    public static XStream xstream = new XStream(new XppDriver() {
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

    public static void main(String[] args) {
        //XStream xstream = new XStream(new CustomXppDriver());
        xstream.processAnnotations(new Class[]{TextMessage.class, LocationEventMessage.class,
                BaseMessage.class, Article.class});

        System.out.println(xstream.fromXML(buildStrEventMsg()));
        String msgStr = xstream.toXML(buildArticle());
        System.out.println(msgStr);
        //event
        //System.out.println(xstream.toXML(buildSubscribeEventMsg()));
        //System.out.println(xstream.fromXML(buildStrSubscribeEventMsg()));

    }

    private static SubscribeEventMessage buildSubscribeEventMsg() {
        SubscribeEventMessage msg = new SubscribeEventMessage();

        msg.setFromUserName("jack");
        msg.setToUserName("mqweixin");
        msg.setMsgType(EMsgType.EVENT.getCode());
        msg.setCreateTime(new Date().getTime());
        msg.setEvent("subscribe");

        return msg;
    }

    private static String buildStrSubscribeEventMsg() {
        return "<xml><ToUserName><![CDATA[mqweixin]]></ToUserName>" +
                "<FromUserName><![CDATA[jack]]></FromUserName>" +
                "<CreateTime>1405935544057</CreateTime>" +
                "<MsgType><![CDATA[event]]></MsgType>" +
                "<Event><![CDATA[subscribe]]></Event></xml>";
    }

    private static TextMessage buildTextMsg() {
        TextMessage msg = new TextMessage();

        msg.setFromUserName("jack");
        msg.setToUserName("mqweixin");
        msg.setMsgType(EMsgType.EVENT.getCode());
        msg.setCreateTime(new Date().getTime());
        msg.setContent("hello world");
        msg.setMsgId(12345678901234L);

        return msg;
    }

    private static Article buildArticle() {
        Article article3 = new Article();
        article3.setTitle("互动");
        article3.setDescription("穆勒，C罗，梅西谁是当今之最");
        article3.setPicUrl("http://115.29.103.144/rental/images/com_pic2.jpg");
        article3.setUrl("");
        return article3;
    }

    private static String buildStrTxtMsg() {
        return "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[this is a test]]></Content></xml>";
    }

    private static String buildStrEventMsg() {
        return "<xml><ToUserName><![CDATA[gh_99f2cd3303bb]]></ToUserName><FromUserName><![CDATA[oYaa9t0cJLeBWgwpXImyu6zUYJ5M]]></FromUserName><CreateTime>1407155179</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[LOCATION]]></Event><Latitude>31.135111</Latitude><Longitude>121.437515</Longitude><Precision>37.000000</Precision></xml>";
    }
}
