/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package com.micro.rent.common.comm.aio;

import com.micro.rent.biz.enum_.ETranfficType;
import com.micro.rent.biz.map.baidu.direction.RequestParam;
import com.micro.rent.biz.map.service.DirectionReqParam;
import com.micro.rent.biz.myrent.vo.MatchResultVo;
import com.micro.rent.biz.myrent.vo.MyRentQueryVo;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * This example demonstrates a fully asynchronous execution of multiple HTTP exchanges
 * where the result of an individual operation is reported using a callback interface.
 */
public class AsyncClientHttpExchangeFutureCallback {
    private static int MSG_OK = 0;
    private static transient Logger log = LoggerFactory.getLogger(AsyncClientHttpExchangeFutureCallback.class);
    private List<MatchResultVo> list;
    private MyRentQueryVo queryVo;
    private double wpLon;
    private double wpLat;

    public AsyncClientHttpExchangeFutureCallback(List<MatchResultVo> list, MyRentQueryVo queryVo, double wpLon, double wpLat) {
        this.list = list;
        this.queryVo = queryVo;
        this.wpLon = wpLon;
        this.wpLat = wpLat;
    }

    public AsyncClientHttpExchangeFutureCallback() {
    }

    public static String timeUrl(DirectionReqParam reqParam) throws Exception {
        StringBuilder baseUrlBuildString = new StringBuilder("http://api.map.baidu.com/direction/v1?");
        baseUrlBuildString.append("output=xml")
                .append("&mode=")
                .append(ETranfficType.getNameByCode(reqParam.getMode()))
                .append("&origin=")
                .append(URLEncoder.encode(reqParam.getOrigin(), "UTF-8"))
                .append("&destination=")
                .append(URLEncoder.encode(reqParam.getDestination(), "UTF-8"))
                .append("&ak=")
                .append("A122a2bb3e8ff58a9490406760e978f9")
                .append("&tactics=11");

        if (ETranfficType.DRIVING.getCode().equals(reqParam.getMode())) {
            baseUrlBuildString.append("&origin_region=").append(URLEncoder.encode(reqParam.getOrigin_region(), "UTF-8"));
            baseUrlBuildString.append("&destination_region=").append(URLEncoder.encode(reqParam.getDestination_region(), "UTF-8"));
        } else {
            baseUrlBuildString.append("&region=").append(URLEncoder.encode(reqParam.getRegion(), "UTF-8"));
        }
        return baseUrlBuildString.toString();
    }

    public static String duration(String responseString) {
        String status = StringUtils.substringBetween(responseString, "<status>", "</status>");
        String duration = "1";
        if (!StringUtils.isNumeric(status)) {
            duration = "0";
        }
        if (MSG_OK != Integer.valueOf(status).intValue()) {
            log.info("baidu map redirect error:" + StringUtils.substringBetween(responseString, "<message>", "</message>"));
        } else {
            duration = StringUtils.substringBetween(responseString, "<duration>", "</duration>");
//			duration=StringUtil.secondToHm(Double.valueOf(duration)
//					.intValue());
        }
        return duration;
    }

    public static String readInputStream(InputStream input) throws IOException {
        byte[] buffer = new byte[128];
        int len = 0;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        while ((len = input.read(buffer)) >= 0) {
            bytes.write(buffer, 0, len);
        }
        return bytes.toString();
    }

    public void httpAsync() throws Exception {
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(3000)
                .setConnectTimeout(3000).build();
        final CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();
        try {
            httpclient.start();
            final HttpGet[] requests = new HttpGet[list.size()];
            for (int i = 0; i < list.size(); i++) {
                BigDecimal lat = list.get(i).getLatitude();
                BigDecimal lon = list.get(i).getLongitude();
                RequestParam reqParam = new RequestParam();
                reqParam.setDestination(lat.toString().concat(",")
                        .concat(lon.toString()));
                reqParam.setOrigin(String.valueOf(wpLat).concat(",")
                        .concat(String.valueOf(wpLon)));
                reqParam.setMode(queryVo.getTrafficType());
                switch (ETranfficType.getSelfByCode(queryVo.getTrafficType())) {
                    case DRIVING:
                        reqParam.setOrigin_region(queryVo.getCityName());
                        reqParam.setDestination_region(queryVo.getCityName());
                        break;
                    case TRANSIT:
                    case WALKING:
                        reqParam.setRegion(queryVo.getCityName());
                        break;
                    default:
                        break;
                }
                requests[i] = new HttpGet(timeUrl(reqParam));
            }
            long start = System.currentTimeMillis();
            final CountDownLatch latch = new CountDownLatch(requests.length);
            for (int j = 0; j < requests.length; j++) {
                final HttpGet request = requests[j];
                final int k = j;
                httpclient.execute(request, new FutureCallback<HttpResponse>() {
                    public void completed(final HttpResponse response) {
                        latch.countDown();
                        try {
                            InputStream a = response.getEntity().getContent();
                            String responseString = readInputStream(a);
                            String duration = duration(responseString);
                            list.get(k).setDuration(duration);
                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    public void failed(final Exception ex) {
                        latch.countDown();
                    }

                    public void cancelled() {
                        latch.countDown();
                    }

                });
            }
            latch.await();
            log.info(System.currentTimeMillis() - start + "----------异步调用时间-------ms----");
        } finally {
            httpclient.close();
        }
    }
}
