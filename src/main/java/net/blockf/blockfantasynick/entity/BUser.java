package net.blockf.blockfantasynick.entity;

import java.sql.Timestamp;
import java.util.UUID;


//table bf_nick_user
public class BUser {
    UUID uuid;
    String user_name;
    String display_name;
    String display_name_noc;
    Integer status;
    UUID update_user_uuid;
    String update_user;
    Timestamp update_time;

    public BUser() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getDisplay_name_noc() {
        return display_name_noc;
    }

    public void setDisplay_name_noc(String display_name_noc) {
        this.display_name_noc = display_name_noc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public UUID getUpdate_user_uuid() {
        return update_user_uuid;
    }

    public void setUpdate_user_uuid(UUID update_user_uuid) {
        this.update_user_uuid = update_user_uuid;
    }

    public String getUpdate_user() {
        return update_user;
    }

    public void setUpdate_user(String update_user) {
        this.update_user = update_user;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }


    @Override
    public String toString() {
        return "BUser{" +
                "uuid=" + uuid +
                ", user_name='" + user_name + '\'' +
                ", display_name='" + display_name + '\'' +
                ", display_name_noc='" + display_name_noc + '\'' +
                ", status=" + status +
                ", update_user_uuid=" + update_user_uuid +
                ", update_user='" + update_user + '\'' +
                ", update_time=" + update_time +
                '}';
    }
}
