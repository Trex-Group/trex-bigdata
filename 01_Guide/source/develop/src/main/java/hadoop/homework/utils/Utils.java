package hadoop.homework.utils;

import java.text.DecimalFormat;

import hadoop.homework.utils.ip.IPSeeker;

public class Utils
{
	private static String[] g_region = {"北京",
		"天津",
		"上海",
		"重庆",
		"河北",
		"山西",
		"陕西",
		"山东",
		"河南",
		"辽宁",
		"吉林",
		"黑龙江",
		"江苏",
		"浙江",
		"安徽",
		"江西",
		"福建",
		"湖北",
		"湖南",
		"四川",
		"贵州",
		"云南",
		"广东",
		"海南",
		"甘肃",
		"青海",
		"台湾",
		"内蒙古",
		"新疆",
		"西藏",
		"广西",
		"宁夏",
		"香港",
		"澳门"};
	//百分比计算
    public static String getPercent(int aPart, int aTotal)
    {
    	String result = "";
    	
    	double x = aPart * 1.0;
    	double tempresult = x/aTotal;
    	
    	DecimalFormat df = new DecimalFormat("0.00000000%");
    	
    	result = df.format(tempresult);
    	return result;
    }
    
    //获取ip的区域 只返回 省 直辖市 自治区 国外
    public static String ipRegion(String aIpStr)
    {
    	String ret = "";
    	
		IPSeeker ip = IPSeeker.getInstance();
		String region = ip.getCountry(aIpStr);

		ret = region;
		for(int i = 0; i < g_region.length; i++)
		{
			if(region.contains(g_region[i]))
			{
				ret = g_region[i];
				break;
			}
		}
    	return ret;
    }
}
