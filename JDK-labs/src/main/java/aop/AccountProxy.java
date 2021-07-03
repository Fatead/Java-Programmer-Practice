package aop;

public class AccountProxy implements IAccountService{

    //目标对象
    private IAccountService target;

    public AccountProxy(IAccountService target) {
        this.target = target;
    }

    @Override
    public void transfer() {
        before();
        target.transfer();
    }

    /**
     * 前置增强
     */
    private void before(){
        System.out.println("转帐前先进行身份验证");
    }

}
