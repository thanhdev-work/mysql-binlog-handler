package vn.thanhdev.core.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@NoArgsConstructor
@AllArgsConstructor
public class BinLogConfig {
    private String host;
    private Integer port;
    private String username;
    private String pass;
    private String file;
    private Long position;
}
