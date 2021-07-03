package aop;

public class AccountServiceImpl implements IAccountService{
    @Override
    public void transfer() {
        System.out.println("完成转账业务");
    }

}
