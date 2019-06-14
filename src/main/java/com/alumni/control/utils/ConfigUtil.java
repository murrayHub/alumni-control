package com.alumni.control.utils;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * description : spring启动读取配置工具类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/3/20 16:03
 */
public class ConfigUtil {
    private ConfigUtil() {
    }

    public static void webInitProperties() {
        Properties pps = new Properties();

        try {
            URL resource = ConfigUtil.class.getClassLoader().getResource("/");
            if (resource == null) {
                System.exit(0);
            }

            File file = new File(resource.getPath().concat("config.properties"));
            InputStream in = Files.newInputStream(Paths.get(file.toURI()));
            Throwable var4 = null;

            try {
                pps.load(in);
                Iterator var5 = pps.entrySet().iterator();

                while(var5.hasNext()) {
                    Map.Entry<Object, Object> item = (Map.Entry)var5.next();
                    if (!"@idc@".equals(String.valueOf(item.getValue()))) {
                        System.setProperty(String.valueOf(item.getKey()), String.valueOf(item.getValue()));
                    }
                }
            } catch (Throwable var15) {
                var4 = var15;
                throw var15;
            } finally {
                if (in != null) {
                    if (var4 != null) {
                        try {
                            in.close();
                        } catch (Throwable var14) {
                            var4.addSuppressed(var14);
                        }
                    } else {
                        in.close();
                    }
                }

            }
        } catch (Exception var17) {
            var17.printStackTrace();
        }

    }

    public static void initProperties() {
        Properties pps = new Properties();

        try {
            InputStream in = ConfigUtil.class.getResourceAsStream("/config.properties");
            Throwable var2 = null;

            try {
                pps.load(in);
                Iterator var3 = pps.entrySet().iterator();

                while(var3.hasNext()) {
                    Map.Entry<Object, Object> item = (Map.Entry)var3.next();
                    if (!"@idc@".equals(String.valueOf(item.getValue()))) {
                        System.setProperty(String.valueOf(item.getKey()), String.valueOf(item.getValue()));
                    }
                }
            } catch (Throwable var13) {
                var2 = var13;
                throw var13;
            } finally {
                if (in != null) {
                    if (var2 != null) {
                        try {
                            in.close();
                        } catch (Throwable var12) {
                            var2.addSuppressed(var12);
                        }
                    } else {
                        in.close();
                    }
                }

            }
        } catch (Exception var15) {
            var15.printStackTrace();
        }

    }
}
