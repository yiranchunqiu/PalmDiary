package com.pxz.zhangshangriji.util;

import com.pxz.zhangshangriji.R;

import java.lang.reflect.Field;

/**
 * 类说明：根据文件名获取资源
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/17 17:31
 */
public class ResourcesUtils {
    /**
     * 获取资源文件ID
     * @return
     */
    public static int getResourceByReflect(String imageName){
        Class drawable  =  R.drawable.class;
        Field field = null;
        int r_id ;
        try {
            field = drawable.getField(imageName);
            r_id = field.getInt(field.getName());
        } catch (Exception e) {
            r_id=R.drawable.chunv_icon;
        }
        return r_id;
    }
}
