package com.example.demo;

import com.sensorsdata.analytics.javasdk.SensorsAnalytics;
import com.sensorsdata.analytics.javasdk.exceptions.InvalidArgumentException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws InvalidArgumentException {
        // 从神策分析获取的数据接收的 URL
        final String SA_SERVER_URL = "http://kamfupoc.datasink.sensorsdata.cn/sa?project=default&token=195f8dd6f384f867";
        // 使用 Debug 模式，并且导入 Debug 模式下所发送的数据
        final boolean SA_WRITE_DATA = true;

        // 使用 DebugConsumer 初始化 SensorsAnalytics
        final SensorsAnalytics sa = new SensorsAnalytics(
                new SensorsAnalytics.DebugConsumer(SA_SERVER_URL, SA_WRITE_DATA));

        // 用户的 Distinct Id
        String distinctId = "ABCDEF123456789";

        // 记录用户登录事件
//        sa.track(distinctId, true, "UserLogin");

        // 使用神策分析记录用户行为数据
        {
            Map<String, Object> properties = new HashMap<String, Object>();

            properties.put("$time", new Date());

            properties.put("$ip", "123.123.123.123");

            properties.put("is_success", true);

            properties.put("failure_reason", "失败原因");
            properties.put("name", "何总");
            properties.put("idCard", "1234567890234567");

            // 记录用户浏览商品事件
            sa.track(distinctId,false,"SubmitIdentity",properties);
        }


        SpringApplication.run(DemoApplication.class, args);
// 程序结束前，停止神策分析 SDK 所有服务
        sa.shutdown();
    }
}
