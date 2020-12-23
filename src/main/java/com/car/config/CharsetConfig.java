package com.car.config;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class CharsetConfig implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String name = "applicationConfig: [classpath:/application.properties]";
        MapPropertySource propertySource = (MapPropertySource) environment.getPropertySources().get(name);
        Map<String, Object> source = propertySource.getSource();
        Map<String,Object> map = new HashMap();
        source.forEach((k, v) -> {
            if (check(k)) {
                System.out.println(k);
                map.put(k, new String(v.toString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
            }
        });
        environment.getPropertySources().replace(name, new MapPropertySource(name, map));
    }

    public boolean check(String key){
        if (key.equals("test.test.str") ||
                key.equals("file.scan") ||
                key.equals("file.uploadFolder") ||
                key.equals("file.staticAccessPath")
        ){
            return true;
        }
        return false;
    }
}
