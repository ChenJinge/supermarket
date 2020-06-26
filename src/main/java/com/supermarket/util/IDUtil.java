package com.supermarket.util;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class IDUtil {

    public static Long getId() {
        String sdf = new SimpleDateFormat("MMddHHmmssSSS").format(new Date());
        return Long.parseLong(sdf);
    }

}
