package com.pxz.zhangshangriji.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 类说明：json数据转换
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/17 15:17
 */
public class ConvertUtils {
    public static String toString(InputStream is) {
        return toString(is, "utf-8");
    }

    public static String toString(InputStream is, String charset) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                } else {
                    sb.append(line).append("\n");
                }
            }
            reader.close();
            is.close();
        } catch (IOException e) {
        }
        return sb.toString();
    }
}