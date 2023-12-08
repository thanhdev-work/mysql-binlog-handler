package vn.thanhdev.core;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import vn.thanhdev.core.config.BinLogConfig;
import vn.thanhdev.core.exception.BinLogCoreException;
import vn.thanhdev.core.listener.BinLogEventListenter;

import javax.annotation.PreDestroy;

@Component
@Log4j2
@Order
public class BinLogConnector {

    private BinaryLogClient binaryLogClient;
    @Autowired
    private BinLogConfig binaryLogConfig;
    @Autowired
    private BinLogEventListenter listener;

    public void start() {
        initBinLog();
    }

    private void initBinLog() {
        Thread init = new Thread(() -> {
            if (ObjectUtils.isEmpty(binaryLogConfig.getUsername()) ||
                    ObjectUtils.isEmpty(binaryLogConfig.getPass()) ||
                    ObjectUtils.isEmpty(binaryLogConfig.getPort()) ||
                    ObjectUtils.isEmpty(binaryLogConfig.getHost())) {
                throw new BinLogCoreException("MySQL binlog config is invalid");
            }

            binaryLogClient = new BinaryLogClient(
                    binaryLogConfig.getHost(),
                    binaryLogConfig.getPort(),
                    binaryLogConfig.getUsername(),
                    binaryLogConfig.getPass()
            );
            binaryLogClient.registerEventListener(listener);

            try {
                log.info("## Connecting to MySQL binlog ...");
                binaryLogClient.connect();
            } catch (Exception e) {
                log.error("## Error when connect binlog: {}", e.getMessage());
                e.printStackTrace();
                throw new BinLogCoreException(e.getMessage());
            }
        });
        init.start();
    }

    @PreDestroy
    @SneakyThrows
    private void destroy() {
        log.info("## Disconnected to MySQL binlog");
        binaryLogClient.disconnect();
    }

}
