package com.chenjunyi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


/**
 CREATE TABLE default.abc_append
 (
 `name` String,
 `access_time` DateTime64(3),
 `status` Int64,
 `age` Int32,
 `lantitude` Float32,
 `longitude` Float64,
 `sip` IPv4,
 `dip` IPv4,
 `sipv6` IPv6,
 `dipv6` IPv6,
 `nowtime` DateTime DEFAULT now(),
 `serial_num` String,
 `raw_msg` String,
 `net_dom` Int32,
 `vendor_id` String,
 `src_mac` String,
 `dst_mac` String,
 `backrule_id` String,
 `sess_key` String,
 `method` String,
 `uri` String,
 `uri_raw` String,
 `uri_reraw` String,
 `uri_md5` String,
 `host` String,
 `host_md5` String,
 `host_raw` String,
 `host_reraw` String,
 `origin` String,
 `cookie` String,
 `agent` String,
 `data` String,
 `referer` String,
 `xff` String,
 `weibo` String,
 `qq` String,
 `content_type` String,
 `accept_language` String,
 `urlcategory` Int32,
 `geo_sip_subdivision` String,
 `geo_sip_city_name` String,
 `_all` String MATERIALIZED formatJSON(access_time, status, name, age, lantitude, longitude, sip, dip, sipv6, dipv6, nowtime, serial_num, raw_msg, net_dom, vendor_id, src_mac, dst_mac, backrule_id, sess_key, method, uri, uri_raw, uri_reraw, uri_md5, host, host_md5, host_raw, host_reraw, origin, cookie, agent, data, referer, xff, weibo, qq, content_type, accept_language, urlcategory, geo_sip_subdivision, geo_sip_city_name))
 ENGINE = MergeTree()
 PARTITION BY toYYYYMM(access_time)
 ORDER BY status
 SETTINGS index_granularity = 8192;
 */
public class GeneratorData {


    public static void main(String[] args) throws Exception {
        int size = Integer.MAX_VALUE;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/home/chenjunyi/ck/data/data10yi.json"), "UTF-8"));
        
        for (int i =0; i < size; i++) {
            JSONObject data = new JSONObject();
            data.put("name", Name());
            data.put("access_time", randomDate("2023-01-01", "2023-12-31"));
            data.put("status", (i+1));
            data.put("age", Age());
            data.put("lantitude", Lantitude());
            data.put("longitude", Longitude());
            data.put("sip", generateIPv4Address());
            data.put("dip", generateIPv4Address());
            data.put("sipv6", generateIPv6Address());
            data.put("dipv6", generateIPv6Address());
            data.put("nowtime", randomDate("2023-01-01", "2023-12-31"));
            data.put("serial_num", Name());
            data.put("raw_msg", Name());
            data.put("net_dom", Age());
            data.put("vendor_id", Name());
            data.put("src_mac", Name());
            data.put("dst_mac", Name());
            data.put("backrule_id", Name());
            data.put("sess_key", Name());
            data.put("method", method());
            data.put("uri", Name());
            data.put("uri_raw", Name());
            data.put("uri_reraw", Name());
            data.put("uri_md5", Name());
            data.put("host", host());
            data.put("host_md5", hostMD5());
            data.put("host_raw", host());
            data.put("host_reraw", host());
            data.put("origin", Name());
            data.put("cookie", Name());
            data.put("agent", Name());
            data.put("data", Name());
            data.put("referer", Name());
            data.put("xff", Name());
            data.put("weibo", Name());
            data.put("qq", Name());
            data.put("content_type", Name());
            data.put("accept_language", language());
            data.put("urlcategory", Age());
            data.put("geo_sip_subdivision", subdivision());
            data.put("geo_sip_city_name", Name());
            bw.write(JSON.toJSONString(data));
            bw.write(System.lineSeparator());
        }
        bw.close();

    }

    private static String method() {
        String[] str = {"GET", "PUT", "POST", ""};
        int number = (int) (Math.random() * str.length);
        return str[number];
    }

    private static String hostMD5() {
        String[] str = {"2bee33820b445ec1c0ee2d33fd3189ee", "f01743702e52e5d7a1d6074fdb7083ea", "68894df7d018f94f870a48f9aacc357a", "f01743702e52e5d7a1d6074fdb7083ea", "09181cb638d2cb5ad7002c406677a564", "709e88a0c7df8b29f8ec3c93c8755788"};
        int number = (int) (Math.random() * str.length);
        return str[number];
    }

    private static String host() {
        String[] str = {"www.haoyunx.cn", "zibo.comet6.com", "adc.yupoo.com", "oa.srmyy.com", "w2007.2xiaoshuo.cn", "www.ckdtiku.cn", "www.afzyzs.com"};
        int number = (int) (Math.random() * str.length);
        return str[number];
    }

    private static String language() {
        String[] str = {"zh-CN", "EN", ""};
        int number = (int) (Math.random() * str.length);
        return str[number];
    }

    private static String subdivision() {
        String[] str = {"香港", "北京", "上海", "广州", "深圳", "杭州"};
        int number = (int) (Math.random() * str.length);
        return str[number];
    }


    public static void write(ArrayList<JSONObject> datas) throws Exception {
           BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/home/chenjunyi/ck/data/data10.json"), "UTF-8"));
            for (JSONObject data : datas) {
                String s = JSON.toJSONString(data);
                bw.write(s);
                bw.write(System.lineSeparator());
            }
            bw.close();
    }


    public static String generateIPv4Address() {
        Random random = new Random();
        return random.nextInt(256) + "." + random.nextInt(256) + "." + random.nextInt(256) + "." + random.nextInt(256);
    }

    public static String generateIPv6Address() {
        Random random = new Random();
        StringBuilder ipv6 = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            ipv6.append(Integer.toHexString(random.nextInt(65536))).append(":");
        }
        // 删除最后一个冒号
        ipv6.deleteCharAt(ipv6.length() - 1);
        return ipv6.toString();
    }

    public static String Name() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer stringBuffer = new StringBuffer();
        // 长度在[1,100]范围内
        int length = (int)(Math.random() * 100 + 1);
        for (int i = 0; i < length; i++) {
            int number = (int) (Math.random() * str.length());
            stringBuffer.append(str.charAt(number));
        }
        return stringBuffer.toString();
    }

    private static String randomDate(String beginDate, String endDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);

            if (start.getTime() >= end.getTime()) {
                return null;
            }
            long date = random(start.getTime(), end.getTime());
            
            return format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static long random(long begin,long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }

    // r.nextFloat() 随机生成(0,1)之间的小数
    // r.nextFloat()*10 随机生成(0,10)之间的小数
    // r.nextFloat()*10+5 随机生成(5,10)之间的小数
    private static float Lantitude() {
        Random r = new Random();
        float f = (r.nextFloat() * 100 + 5);
        BigDecimal bd = new BigDecimal(f);
        bd.setScale(5, RoundingMode.HALF_UP);
        return bd.floatValue();
    }

    // nextDouble(); 随机生成一个0.0到1.0之间的随机double数
    //
    private static double Longitude() {
        Random r = new Random();
        double d = (r.nextDouble() * 100 + 5);
        BigDecimal bd = new BigDecimal(d);
        bd = bd.setScale(5, RoundingMode.HALF_UP); // 设置小数点后4位，四舍五入
        return bd.doubleValue();
    }


    private static int Age() {
       
        return ((int) (Math.random() * 119 +1));
    }

}
