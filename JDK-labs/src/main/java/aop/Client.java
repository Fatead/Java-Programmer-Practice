package aop;

import java.lang.reflect.Proxy;

/**
 * 静态代理：为每一个业务增强都提供一个代理类，由代理类来创建代理对象
 * 动态代理：代理对象直接由代理生成工具动态生成
 */
public class Client {

    public static void dynamicProxy(){
        IAccountService target = new AccountServiceImpl();
        AccountProxy proxy = new AccountProxy(target);
        proxy.transfer();
    }

    /**
     * 使用JDK实现动态代理，代理类必须要有接口
     */
    public static void staticProxy(){
        IAccountService target = new AccountServiceImpl();
        //创建代理对象
        IAccountService proxy = (IAccountService) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new AccountAdvice(target)
        );
        proxy.transfer();
    }

    public static void main(String[] args) {
        staticProxy();
    }

}
