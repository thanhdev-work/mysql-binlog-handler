package cn.bucheng.mysql.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author buchengyin
 * @create 2019/7/27 8:48
 * @describe
 */
@Component
public class BeanFactoryUtils implements BeanFactoryAware {

    private static DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        BeanFactoryUtils.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    public static DefaultListableBeanFactory getBeanFactory(){
        return beanFactory;
    }
}
