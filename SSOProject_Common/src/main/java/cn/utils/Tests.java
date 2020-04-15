package cn.utils;

import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.Test;
public class Tests {
    @Test
    public void test() throws Exception{
        ConfigTools configTools = new ConfigTools();
        String password = "root";
        String[] arr = configTools.genKeyPair(512);
        System.out.println("privateKey: " + arr[0]);
        System.out.println("publickey: " + arr[1]);
        String encryptPassword = configTools.encrypt(arr[0], password);
        System.out.println("password: " + encryptPassword);
        System.out.println("解密: " + configTools.decrypt(arr[1], encryptPassword));
    }
}
