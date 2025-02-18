package com.eazybytes.eazyschool.audit;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EazySchoolInfoContributor implements InfoContributor {


    @Override
    public void contribute(Info.Builder builder) {
        Map<String,String>map= new HashMap<>();
        map.put("App Name","EazySchool");
        map.put("Description","EazySchool Web App for Students and Admins");
        map.put("App Version","1.0.0");
        map.put("Contact Email","info@eazyschool.com");
        map.put("Contact Mobile","+1(21) 673 4587");
        builder.withDetail("eazyschool-info",map);
    }
}
