package vn.thanhdev.core.listener;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.Event;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class  BinLogEventListenter implements BinaryLogClient.EventListener {

    @Override
    public void onEvent(Event event) {
        log.info("Event: {}", event.toString());
    }
}
