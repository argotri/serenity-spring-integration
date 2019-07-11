package com.gdn.qa.module.ui.testautothon.properties;

import com.gdn.qa.module.ui.testautothon.annotation.BlibliSteps;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * User: argo.triwidodo
 * Date: 10-Jul-19
 * Time: 10:29
 */
@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "argo")
@BlibliSteps
@Component("com.gdn.qa.module.ui.testautothon.properties.Properties")
public class Properties {
    private boolean ganteng;
    private String baik;
}
