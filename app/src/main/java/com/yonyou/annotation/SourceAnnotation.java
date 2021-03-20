package com.yonyou.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by ufsoft on2020-11-18
 * describle:
 */
@Retention(RetentionPolicy.SOURCE)
public @interface SourceAnnotation {
    RuntimeAnnotation name();
}
