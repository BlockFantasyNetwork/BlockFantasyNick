package net.blockf.blockfantasynick.entity;

import java.sql.Timestamp;
import java.util.UUID;

public class BLog {
    Integer id;
    UUID uuid;
    String user_name;
    String display_name;
    Integer status;
    UUID update_user_uuid;
    String update_user;
    Timestamp update_time;

    @Override
    public String toString() {
        return "BLog{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", user_name='" + user_name + '\'' +
                ", display_name='" + display_name + '\'' +
                ", status=" + status +
                ", update_user_uuid=" + update_user_uuid +
                ", update_user='" + update_user + '\'' +
                ", update_time=" + update_time +
                '}';
    }

    public BLog() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
