package utils;

/**
 * Created by ddgdd on 2018/8/24 0024 8:19
 */
public class BaseResponse<T> {
    private String status;
    private T data;
    private String message;

    //默认设置为失败状态，因此失败情况只需要设置失败信息
    public BaseResponse() {
        this.status = "error";
        this.data = null;
    }

    public BaseResponse(String status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
