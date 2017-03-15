package hadoop.homework.kpi.dto;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import hadoop.homework.useragent.UserAgentParser;

/**
 * 183.60.193.30 - - [04/Jan/2012:19:38:39 +0800] "GET /index.php?gid=1 target= HTTP/1.1" 301 14094 "-" "-"
 *
 * @author xenron
 */
/*
 * AccessRecord
 * 保存一行的访问记录
 */
public class AccessRecord {
    public static int g_processCount = 0; //用于记录处理的合法的log条数，最后计算百分比
    private static AccessRecord g_accessRecord = null;

    private String m_remote_addr;      // 记录客户端的ip地址
    private String m_remote_user;      // 记录客户端用户名称,忽略属性"-"
    private String m_time_local;       // 记录访问时间与时区
    private String m_request;          // 记录请求的url与http协议
    private String m_status;           // 记录请求状态；成功是200
    private String m_body_bytes_sent;  // 记录发送给客户端文件主体内容大小
    private String m_http_referer;     // 用来记录从那个页面链接访问过来的
    private String m_http_user_agent;  // 记录客户浏览器的相关信息
    private String m_user_agent;       // useragent

    private boolean m_isvalid = true;// 判断数据是否合法

    public static AccessRecord getInstance() {
        if (g_accessRecord == null) {
            g_accessRecord = new AccessRecord();
        }

        return g_accessRecord;
    }

    public AccessRecord parser(String aLine) {
        System.out.println(aLine);

        this.setValid(true);

        String[] arr = aLine.split(" ");
        if (arr.length > 11) {
            setRemote_addr(arr[0]);
            setRemote_user(arr[1]);
            setTime_local(arr[3].substring(1));
            setRequest(arr[6]);
            setStatus(arr[8]);
            setBody_bytes_sent(arr[9]);
            String ref = arr[10].replace("\"", "");
            setHttp_referer(ref);

            if (arr[7].contains("HTTP/1."))
            //排除log中的一个错误记录
            {
                if (arr.length > 12) {
                    setHttp_user_agent(arr[11] + " " + arr[12]);

                    String useragent = "";
                    //将完整的useragent组装 便于 后续的过滤操作
                    for (int i = 11; i < arr.length; i++) {
                        useragent += (arr[i] + " ");
                    }

                    this.setuser_agent(useragent);
                }// end of if (arr.length > 12)
                else {
                    setHttp_user_agent(arr[11]);
                    this.setuser_agent(arr[11]);
                }

                if (Integer.parseInt(getStatus()) >= 400) {// 大于400，HTTP错误
                    setValid(false);
                }
            }// end of if (arr[7].contains("HTTP/1."))
            else {
                setValid(false);
            }
        }//end of if (arr.length > 11)
        else {
            setValid(false);
        }

        return this;
    }

    /**
     * 按page的pv分类
     */
    public AccessRecord filterPVs(String aLine) {
        // 返回状态码在 400及以上的 已经过滤，以下为过滤 机器人和爬虫蜘蛛
        // 过滤列表来自 http://www.baidu.com/robots.txt 文件 及
        // http://www.360doc.com/content/12/1216/13/11271538_254353001.shtml

        AccessRecord kpi = g_accessRecord.parser(aLine);

        if (!kpi.isValid()) {
            return kpi;
        }

        ArrayList<String> filters = new ArrayList<String>();
        filters.add("Baiduspider");
        filters.add("Googlebot");
        filters.add("MSNBot");
        filters.add("Baiduspider-image");
        filters.add("YoudaoBot");
        filters.add("Sogou");
        filters.add("JikeSpider");
        filters.add("Sosospider");
        filters.add("PangusoSpider");
        filters.add("yisouspider");
        filters.add("Easouspider");
        filters.add("Yahoo! Slurp");
        filters.add("iaskspider");

        for (int i = 0; i < filters.size(); i++) {
            if (kpi.get_user_agent().contains(filters.get(i))) {
                System.out.println(kpi.get_user_agent());

                kpi.setValid(false);
                break;
            }
        }

        return kpi;
    }

    /**
     * 按page的独立ip分类
     */
    public AccessRecord filterIPs(String aLine) {
        return g_accessRecord.parser(aLine);
    }

    /**
     * 按page的发送字节数
     */
    public AccessRecord filterPageSize(String aLine) {
        return g_accessRecord.parser(aLine);
    }

    /**
     * PV按浏览器分类
     */
    public AccessRecord filterBroswer(String aLine) {
        AccessRecord kpi = g_accessRecord.parser(aLine);

        if (!kpi.isValid()) {
            return kpi;
        }

        if (!kpi.get_user_agent().equals("\"-\"")) {
            //获取浏览器信息
            UserAgentParser uaParser = new UserAgentParser(kpi.get_user_agent());
            String bowser = uaParser.getBrowserName();
            if ((bowser == null) || (bowser.length() < 3)) {
                kpi.setValid(false);
            } else {
                kpi.setuser_agent(bowser);
                AccessRecord.g_processCount++;
            }
        } else {
            kpi.setValid(false);
        }

        return kpi;
    }

    /**
     * PV按小时分类
     */
    public AccessRecord filterTime(String line) {
        return g_accessRecord.parser(line);
    }

    /**
     * PV按小时分类
     */
    public AccessRecord filterRegion(String aLine) {
        return g_accessRecord.parser(aLine);
    }

    /**
     * PV按访问域名分类
     */
    public AccessRecord filterDomain(String aLine) {
        AccessRecord kpi = g_accessRecord.parser(aLine);

        if (!kpi.isValid()) {
            return kpi;
        }

        try {
            URL url_referer = new URL(kpi.getHttp_referer());
            String host = url_referer.getHost();
            kpi.setHttp_referer(host);
        } catch (MalformedURLException e) {
            kpi.setValid(false);
        }

        return kpi;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("valid:" + this.m_isvalid);
        sb.append("\nremote_addr:" + this.m_remote_addr);
        sb.append("\nremote_user:" + this.m_remote_user);
        sb.append("\ntime_local:" + this.m_time_local);
        sb.append("\nrequest:" + this.m_request);
        sb.append("\nstatus:" + this.m_status);
        sb.append("\nbody_bytes_sent:" + this.m_body_bytes_sent);
        sb.append("\nhttp_referer:" + this.m_http_referer);
        sb.append("\nhttp_user_agent:" + this.m_http_user_agent);
        return sb.toString();
    }

    public String getRemote_addr() {
        return m_remote_addr;
    }

    public void setRemote_addr(String aRemoteAddr) {
        this.m_remote_addr = aRemoteAddr;
    }

    public String getRemote_user() {
        return m_remote_user;
    }

    public void setRemote_user(String aRemoteUser) {
        this.m_remote_user = aRemoteUser;
    }

    public String getTime_local() {
        return m_time_local;
    }

    public Date getTime_local_Date() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.US);
        return df.parse(this.m_time_local);
    }

    public String getTime_local_Date_hour() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHH");
        return df.format(this.getTime_local_Date());
    }

    public void setTime_local(String aTimeLocal) {
        this.m_time_local = aTimeLocal;
    }

    public String getRequest() {
        return m_request;
    }

    public void setRequest(String aRequest) {
        this.m_request = aRequest;
    }

    public String getStatus() {
        return m_status;
    }

    public void setStatus(String aStatus) {
        this.m_status = aStatus;
    }

    public String getBody_bytes_sent() {
        return m_body_bytes_sent;
    }

    public void setBody_bytes_sent(String aBodyBytesSent) {
        this.m_body_bytes_sent = aBodyBytesSent;
    }

    public String getHttp_referer() {
        return m_http_referer;
    }

    public String getHttp_referer_domain() {
        if (m_http_referer.length() < 8) {
            return null;
        }

        String str = this.m_http_referer.replace("\"", "").replace("http://", "").replace("https://", "");
        return str.indexOf("/") > 0 ? str.substring(0, str.indexOf("/")) : str;
    }

    public void setHttp_referer(String aHttpReferer) {
        this.m_http_referer = aHttpReferer;
    }

    public String getHttp_user_agent() {
        return m_http_user_agent;
    }

    public void setHttp_user_agent(String aUserAgent) {
        this.m_http_user_agent = aUserAgent;
    }

    public String get_user_agent() {
        return m_user_agent;
    }

    public void setuser_agent(String aUserAgent) {
        this.m_user_agent = aUserAgent;
    }

    public boolean isValid() {
        return this.m_isvalid;
    }

    public void setValid(boolean aIsValid) {
        this.m_isvalid = aIsValid;
    }

    public static void main(String args[]) {
        String line = "123.119.90.190 - - [05/Jan/2012:00:00:51 +0800] \"POST /forum.php?mod=attachment HTTP/1.1\" 200 4456448 \"http://www.itpub.net/thread-1472055-1-19.html\" \"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.63 Safari/535.7\"";
        System.out.println(line);
        AccessRecord kpi = AccessRecord.getInstance();
        String[] arr = line.split(" ");

        kpi.setRemote_addr(arr[0]);
        kpi.setRemote_user(arr[1]);
        kpi.setTime_local(arr[3].substring(1));
        kpi.setRequest(arr[6]);
        kpi.setStatus(arr[8]);
        kpi.setBody_bytes_sent(arr[9]);
        kpi.setHttp_referer(arr[10]);
        kpi.setHttp_user_agent(arr[11] + " " + arr[12]);
        System.out.println(kpi);

        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd:HH:mm:ss", Locale.US);
            System.out.println(df.format(kpi.getTime_local_Date()));
            System.out.println(kpi.getTime_local_Date_hour());
            System.out.println(kpi.getHttp_referer_domain());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
