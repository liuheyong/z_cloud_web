package com.cloud.web.springsourcecode;

import com.cloud.web.web.DefaultController;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author: HeYongLiu
 * @create: 05-26-2020
 * @description:
 **/
@Component
public class LHYClass extends DefaultController implements InitializingBean, Ordered {

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.debug("========================LHYClass========================");
    }


    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
