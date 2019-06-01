package com.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author make
 * @creare 18/09/2018
 */
public class LogTest
{
    private final static Logger logger = LogManager.getLogger();
    public static long i = 0;
    public static void main(String[] args) throws InterruptedException {

        log(20);

    }


    public static void log(int threadNum) {

        for (int i = 0; i < threadNum; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    System.out.println("thread: " + Thread.currentThread().getName());

                    while (true) {

                        logger.info("allowed=true   ugi=presto (auth:SIMPLE)        ip=/10.208.53.156       cmd=open        src=/bip/developer/vipdm/dm_log_traffic_pageview_day_dev/dt=20180903/log_date=2018-09-03/hhmm=2100/pltfrm=app/000098_0  dst=null        perm=null       proto=rpc");

                    }
                }
            }).start();


        }
    }
}
