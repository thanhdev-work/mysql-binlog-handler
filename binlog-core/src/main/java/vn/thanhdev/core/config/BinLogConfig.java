package vn.thanhdev.core.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@NoArgsConstructor
@AllArgsConstructor
public class BinLogConfig {
    @Value("${binlog.host}")
    private String host;
    @Value("${binlog.port}")
    private Integer port;
    @Value("${binlog.username}")
    private String username;
    @Value("${binlog.password}")
    private String pass;
}
