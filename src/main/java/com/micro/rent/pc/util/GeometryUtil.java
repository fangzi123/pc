package com.micro.rent.pc.util;

public class GeometryUtil {
	private GeometryUtil() {
	}

//	private final static double EARTH_RADIUS = 6370693.5;// 6378137;
//
//	private static double rad(double d) {
//		return d * Math.PI / 180.0;
//	}
//
//	/**
//	 * 计算球面上两点间距离
//	 * 
//	 * @param x1
//	 * @param y1
//	 * @param x2
//	 * @param y2
//	 * @return
//	 */
//	public static double calcDistanceBetweenTwoPoint(double lat1, double lng1, double lat2, double lng2) throws Exception {
//		double radLat1 = rad(lat1);
//		double radLat2 = rad(lat2);
//
//		double a = radLat1 - radLat2;
//		double b = rad(lng1) - rad(lng2);
//
//		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
//		s = s * EARTH_RADIUS;
//		s = Math.round(s * 10000) / 10000;
//		return s;
//	}

//	/**
//	 * @param args
//	 * @throws Exception
//	 */
//	public static void main(String[] args) throws Exception {
//		double lat1 = 121.438955;
//		double lng1 = 31.226175;
//		double lat2 = 121.452681;
//		double lng2 = 31.229664;
//		double s = calcDistanceBetweenTwoPoint(lat1, lng1, lat2, lng2);
//		System.out.println(s);
//
//		double mLat1 = 39.90923; // point1纬度
//		double mLon1 = 116.357428; // point1经度
//		double mLat2 = 39.90923;// point2纬度
//		double mLon2 = 116.397428;// point2经度
//		double distance = GetShortDistance(lat1, lng1, lat2, lng2);
//		System.out.println(distance);
//	}

	static double DEF_PI = 3.14159265359; // PI
	static double DEF_2PI = 6.28318530712; // 2*PI
	static double DEF_PI180 = 0.01745329252; // PI/180.0
	static double DEF_R = 6370693.5; // radius of earth

	/**
	 * 百度标准算法
	 * @param lon1
	 * @param lat1
	 * @param lon2
	 * @param lat2
	 * @return
	 */
	public static double GetShortDistance(double lon1, double lat1, double lon2, double lat2) {
		double ew1, ns1, ew2, ns2;
		double dx, dy, dew;
		double distance;
		// 角度转换为弧度
		ew1 = lon1 * DEF_PI180;
		ns1 = lat1 * DEF_PI180;
		ew2 = lon2 * DEF_PI180;
		ns2 = lat2 * DEF_PI180;
		// 经度差
		dew = ew1 - ew2;
		// 若跨东经和西经180 度，进行调整
		if (dew > DEF_PI)
			dew = DEF_2PI - dew;
		else if (dew < -DEF_PI)
			dew = DEF_2PI + dew;
		dx = DEF_R * Math.cos(ns1) * dew; // 东西方向长度(在纬度圈上的投影长度)
		dy = DEF_R * (ns1 - ns2); // 南北方向长度(在经度圈上的投影长度)
		// 勾股定理求斜边长
		distance = Math.sqrt(dx * dx + dy * dy);
		return distance;
	}

	/**
	 * 百度标准算法
	 * @param lon1
	 * @param lat1
	 * @param lon2
	 * @param lat2
	 * @return
	 */
	public double GetLongDistance(double lon1, double lat1, double lon2, double lat2) {
		double ew1, ns1, ew2, ns2;
		double distance;
		// 角度转换为弧度
		ew1 = lon1 * DEF_PI180;
		ns1 = lat1 * DEF_PI180;
		ew2 = lon2 * DEF_PI180;
		ns2 = lat2 * DEF_PI180;
		// 求大圆劣弧与球心所夹的角(弧度)
		distance = Math.sin(ns1) * Math.sin(ns2) + Math.cos(ns1) * Math.cos(ns2) * Math.cos(ew1 - ew2);
		// 调整到[-1..1]范围内，避免溢出
		if (distance > 1.0)
			distance = 1.0;
		else if (distance < -1.0)
			distance = -1.0;
		// 求大圆劣弧长度
		distance = DEF_R * Math.acos(distance);
		return distance;
	}
	
	
	private static final double PI = 3.14159265;
    private static final double EARTH_RADIUS = 6378137;
    private static final double RAD = Math.PI / 180.0;

    //@see http://snipperize.todayclose.com/snippet/php/SQL-Query-to-Find-All-Retailers-Within-a-Given-Radius-of-a-Latitude-and-Longitude--65095/ 
	//The circumference of the earth is 24,901 miles.
    //24,901/360 = 69.17 miles / degree  
    /**
     * @param raidus 单位米
     * return minLat,minLng,maxLat,maxLng
     */
    public static double[] getAround(double lat,double lon,int raidus){
		
		Double latitude = lat;
		Double longitude = lon;
		
		Double degree = (24901*1609)/360.0;
		double raidusMile = raidus;
		
		Double dpmLat = 1/degree;
		Double radiusLat = dpmLat*raidusMile;
		Double minLat = latitude - radiusLat;
		Double maxLat = latitude + radiusLat;
		
		Double mpdLng = degree*Math.cos(latitude * (PI/180));
		Double dpmLng = 1 / mpdLng;
		Double radiusLng = dpmLng*raidusMile;
		Double minLng = longitude - radiusLng;
		Double maxLng = longitude + radiusLng;
		//System.out.println("["+minLat+","+minLng+","+maxLat+","+maxLng+"]");
		return new double[]{minLat,minLng,maxLat,maxLng};
	}
    
    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
     * @param lng1
     * @param lat1
     * @param lng2
     * @param lat2
     * @return
     */
    public static double getDistance(double lng1, double lat1, double lng2, double lat2)
    {
       double radLat1 = lat1*RAD;
       double radLat2 = lat2*RAD;
       double a = radLat1 - radLat2;
       double b = (lng1 - lng2)*RAD;
       double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
        Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
       s = s * EARTH_RADIUS;
       s = Math.round(s * 10000) / 10000;
       return s;
    }
    
    /**
     * @param raidus 单位米
     * return minLat,minLng,maxLat,maxLng
     */
    public static double getRadiusLat(int raidus) {
    	Double degree = (24901*1609)/360.0;
		double raidusMile = raidus;
		Double dpmLat = 1/degree;
		Double radiusLat = dpmLat*raidusMile;
		
		return radiusLat;
    }


}
