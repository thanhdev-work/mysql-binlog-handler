package vn.thanhdev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vn.thanhdev.core.BinLogConnector;

@Component
public class Main implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BinLogConnector binLogConnector;

    @Override
    public void run(String... args) {
        binLogConnector.start();
    }
}