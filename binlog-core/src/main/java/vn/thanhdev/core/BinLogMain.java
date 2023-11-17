package vn.thanhdev.core;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import vn.thanhdev.core.config.BinLogConfig;
import vn.thanhdev.core.listener.BinLogEventListenter;

import javax.annotation.PreDestroy;

@Component
@Log4j2
@Order
public class BinLogMain implements CommandLineRunner {

    private BinaryLogClient binaryLogClient;
    private final BinLogConfig binaryLogConfig;
    private final BinLogEventListenter listener;

    public BinLogMain(BinLogConfig binaryLogConfig,
                      BinLogEventListenter listener) {
        this.binaryLogConfig = binaryLogConfig;
        this.listener = listener;
    }

    public void initBinLog() {
        Thread init = new Thread(() -> {
            binaryLogClient = new BinaryLogClient(
                    binaryLogConfig.getHost(),
                    binaryLogConfig.getPort(),
                    binaryLogConfig.getUsername(),
                    binaryLogConfig.getPass()
            );
            binaryLogClient.registerEventListener(listener);

            try {
                log.info("Connecting to MySQL binlog start");
                binaryLogClient.connect();
                log.info("Connecting to MySQL binlog done");
            } catch (Exception e) {
                throw new RuntimeException();
            }
        });
        init.setName("binlog-listener-thread");
        init.start();

    }

    @PreDestroy
    public void destroy() {
        try {
            binaryLogClient.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error when destroy BinLogMain: {}", e.getMessage());
        }
    }

    @Override
    public void run(String... args) {
        try {
            initBinLog();
            log.info("MySQL binlog receiver started");
        } catch (RuntimeException e) {
            log.error("Error when init binlog: {}", e.getMessage());
            throw new RuntimeException();
        }
    }
}
