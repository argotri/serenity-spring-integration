package com.gdn.qa.module.ui.testautothon.annotation;

import com.gdn.qa.module.ui.testautothon.AppConfig;
import org.springframework.test.context.ContextConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: argo.triwidodo
 * Date: 26-Jun-19
 * Time: 10:26
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ContextConfiguration(classes = AppConfig.class)
public @interface BlibliSteps {
}
