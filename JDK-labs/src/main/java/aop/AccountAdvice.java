package aop;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AccountAdvice implements InvocationHandler {

    //目标对象
    private IAccountService target;

    public AccountAdvice(IAccountService target) {
        this.target = target;
    }

    /**
     * 代理方法：每次调用目标方法都会进入到这里
     */
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        before();
        return method.invoke(target,objects);
    }

    /**
     * 前置增强
     */
    private void before(){
        System.out.println("转帐前先进行身份验证");
    }

}
