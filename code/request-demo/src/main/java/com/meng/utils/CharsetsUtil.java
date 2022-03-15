package com.meng.utils;

import java.nio.charset.StandardCharsets;

/**
 * 解决Get请求方式获取参数乱码的工具类！！！注意仅仅解决Get请求方式乱码
 * 由于Get请求底层获取参数为getQueryString() 返回值为String字符串
 * 乱码原因：首先中文参数通过浏览器的HTTP协议发送到Tomcat中，
 * 而浏览器不支持中文，则会对中文的字符串做出处理，会对中文进行URL编码
 * （ 浏览器数据：张三----浏览器对utf-8进行URL编码为----->%E5%BC%A0%E4%B8%89 ---传送到--> tomcat中 ）
 * （tomcat7对URL解码 ： %E5%BC%A0%E4%B8%89-------ISO-8859-1解码 --- 产生乱码-------> å¼ ä¸）
 * 解决思路：既然tomcat通过ISO_8859_1解码产生乱码，但是底层的字符编码始终没有改变
 * 我们可以得到乱码的字符编码 ( getBytes(StandardCharsets.ISO_8859_1) ) 要通过StandardCharsets.ISO_8859_1进行解码获得字节编码为：
 * [-27, -68, -96, -28, -72, -119]   就是 张三 对应的二进制的转为十进制的编码的字符集编码utf-8（一个汉字占三个字节）
 * 然后再通过 new String的构造器方法把字符集：[-27, -68, -96, -28, -72, -119] 按照utf-8编码得到 张三
 * <p>
 * <p>
 * 由于解决Post方式请求参数乱码可以直接通过设置字符输入流的编码进行解决：
 * 解决乱码:POST。
 * POST底层是通过getReader() 字符输入流获取数据，但是tomcat默认的获取流的数据的编码是ISO-8859-1的 所以读中文数据的时候乱码
 * request.setCharacterEncoding("UTF-8");//通过这行代码设置字符输入流的编码
 */
public class CharsetsUtil {
    public static String getChinese(String s) {
        //通过ISO_8859_1编码获取s的字节
        byte[] bytes = s.getBytes(StandardCharsets.ISO_8859_1);
        //直接使用utf-8对字节进行解码获取内容
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
