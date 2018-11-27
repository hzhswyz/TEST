package hzh.web.httpbean;

import java.io.Serializable;

public class ConnectResponse {
    private int status;
    private boolean success;
    private String reason;
    private Messagequeue messagequeue;

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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Messagequeue getMessagequeue() {
        return messagequeue;
    }

    public void setMessagequeue(Messagequeue messagequeue) {
        this.messagequeue = messagequeue;
    }

    public static class Messagequeue implements Serializable {
        private String communicationaddress;
        private String account;
        private String password;
        private String type;
        private String destination;

        public Messagequeue(String communicationaddress, String account, String password, String type, String destination) {
            this.communicationaddress = communicationaddress;
            this.account = account;
            this.password = password;
            this.type = type;
            this.destination = destination;
        }

        public String getCommunicationaddress() {
            return communicationaddress;
        }

        public void setCommunicationaddress(String communicationaddress) {
            this.communicationaddress = communicationaddress;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }
    }
}

