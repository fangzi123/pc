import org.apache.commons.httpclient.methods.GetMethod;

import java.net.URLEncoder;


public class TestMain {

    public static void main(String[] args) {
//        String a1 = URLEncoder.encode("北京");
//        String a2 = URLEncoder.encode("北京");
//        String a = "http://api.map.baidu.com/direction/v1?output=xml&mode=driving&origin=39.887291269737%2C116.46234722015&destination=40.000000000000%2C117.000000000000"
//                + "&ak=A122a2bb3e8ff58a9490406760e978f9&tactics=11&origin_region="
//                + a1
//                + "&destination_region="
//                + a2;
//        GetMethod g = new GetMethod(a);
//        System.out.println("a");
        String houseIds=",1,2";
        houseIds= houseIds.replaceAll("^,", "");
        System.out.println(houseIds);
    }

    
}
