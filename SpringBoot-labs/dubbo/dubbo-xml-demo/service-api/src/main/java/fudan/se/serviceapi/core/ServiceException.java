package fudan.se.serviceapi.core;

/**
 * 服务异常
 *
 */
public final class ServiceException extends RuntimeException{

    /**
     * 错误码
     */
    private Integer code;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
