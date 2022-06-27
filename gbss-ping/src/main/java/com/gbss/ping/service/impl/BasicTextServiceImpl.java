package com.gbss.ping.service.impl;

import com.gbss.commoncore.constants.FileNameConstants;
import com.gbss.ping.feign.HelloPongFeignService;
import com.gbss.ping.service.BasicTextService;
import com.gbss.ping.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <p>
 * <b>BasicTextServiceImpl</b>是文本信息业务实现类 。
 * </p>
 *
 * @author hjzhou
 * @since 2022/6/24
 */
@Slf4j
@Service
public class BasicTextServiceImpl implements BasicTextService {


    @Autowired
    private HelloPongFeignService helloPongFeignService;


    @Override
    public String getShowText() throws ExecutionException, InterruptedException {
        String result = "";
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            //写入
            String text = "ping__hello_" + DateUtils.dateTimeNow();
            String fileName = "ping__" + DateUtils.dateTimeNow() + ".txt";
            writeStrtoFile(text,  FileNameConstants.PONG_FILE_READ_NAME, fileName);
            //通知pong service
            Future future = executorService.submit(() -> (String) helloPongFeignService.helloPongRead(fileName));
            result = (String) future.get();

        } catch (Exception ex) {
            log.error("getShowText 发生异常:{}", ex.getMessage());
        } finally {
            executorService.shutdown();
        }

        return result;
    }


    /**
     * 保存文件到本地
     *
     * @param result
     * @param outPath
     * @param outFileName
     */
    public void writeStrtoFile(String result, String outPath, String outFileName) {
        File dir = new File(outPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File txt = new File(outPath + "/" + outFileName);
        FileOutputStream fos = null;
        try {
            if (!txt.exists()) {
                txt.createNewFile();
            }
            fos = new FileOutputStream(txt);
            byte bytes[] = new byte[512];
            bytes = result.getBytes();
            fos.write(bytes);
            fos.flush();
        } catch (Exception ex) {
            log.error("writeStrtoFile发生异常:{}", ex.getMessage());
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
