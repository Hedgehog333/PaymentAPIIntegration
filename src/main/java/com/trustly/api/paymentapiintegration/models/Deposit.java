package com.trustly.api.paymentapiintegration.models;

public class Deposit {

    private String username;

    private String password;

    private String notificationURL;
    private Long endUserID;
    private Long messageID;
    private Attributes attributes;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNotificationURL() {
        return notificationURL;
    }

    public void setNotificationURL(String notificationURL) {
        this.notificationURL = notificationURL;
    }

    public Long getEndUserID() {
        return endUserID;
    }

    public void setEndUserID(Long endUserID) {
        this.endUserID = endUserID;
    }

    public Long getMessageID() {
        return messageID;
    }

    public void setMessageID(Long messageID) {
        this.messageID = messageID;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return attributes.toString() +
                "Username" + username +
                "Password" + password +
                "NotificationURL" + notificationURL +
                "EndUserID" + endUserID +
                "MessageID" + messageID +
                "Attributes" + attributes;
    }
}