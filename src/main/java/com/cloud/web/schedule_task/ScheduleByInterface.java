package com.cloud.web.schedule_task;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.cloud.commons.dto.ECooperateMer;
import com.cloud.commons.service.ECooperateMerService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalDateTime;

/**
 * @author: liuheyong
 * @create: 2019-11-16
 * @description:
 */
@EnableScheduling
@Configuration
public class ScheduleByInterface implements SchedulingConfigurer {

    @Reference(check = false, version = "${dubbo.service.version}", timeout = 60000)
    private ECooperateMerService eCooperateMerService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> {
                    try {
                        eCooperateMerService.queryECooperateMerListPage(new ECooperateMer());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    //String cron = cronMapper.getCron();
                    String cron = "0/30 * * * * ?";
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty(cron)) {
                        // Omitted Code ..
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }
}
