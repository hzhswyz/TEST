package hzh.web.bean;

public class ConnectionResult {
    private int status;
    private boolean success;
    private String result;
    public final static int Server_error = 0;
    public final static int Failure_of_connection_model = 1;
    public final static int Failure_of_model_startup = 2;
    public final static int Network_error = 3;
    public final static int Success = 4;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


}
