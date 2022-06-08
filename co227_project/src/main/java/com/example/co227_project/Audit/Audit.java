package com.example.co227_project.Audit;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("auditmsg")
public class Audit {

    @Id
    private String id;

    private String clientTime;

    private String server_time;

    private String ip;

    private String type;

    private String serverity;

    private String keyword;

    private String description;

    private String generated_from;

    private String remarks;

    public Audit() {
    }

    public Audit(String clientTime, String server_time, String ip, String type, String serverity, String keyword, String description, String generated_from, String remarks) {
        this.clientTime = clientTime;
        this.server_time = server_time;
        this.ip = ip;
        this.type = type;
        this.serverity = serverity;
        this.keyword = keyword;
        this.description = description;
        this.generated_from = generated_from;
        this.remarks = remarks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientTime() {
        return clientTime;
    }

    public void setClientTime(String clientTime) {
        this.clientTime = clientTime;
    }

    public String getServer_time() {
        return server_time;
    }

    public void setServer_time(String server_time) {
        this.server_time = server_time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getServerity() {
        return serverity;
    }

    public void setServerity(String serverity) {
        this.serverity = serverity;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenerated_from() {
        return generated_from;
    }

    public void setGenerated_from(String generated_from) {
        this.generated_from = generated_from;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
