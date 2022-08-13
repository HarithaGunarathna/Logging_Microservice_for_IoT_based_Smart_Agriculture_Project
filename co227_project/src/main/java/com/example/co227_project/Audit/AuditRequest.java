package com.example.co227_project.Audit;


import com.fasterxml.jackson.annotation.JsonProperty;

public class AuditRequest {

    @JsonProperty("ClientTime")
    private String clientTime;

    //@JsonProperty("ServerTime")
    //private String server_time;

    @JsonProperty("Ip")
    private String ip;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Serverity")
    private String serverity;

    @JsonProperty("Keyword")
    private String keyword;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("GeneratedFrom")
    private String generated_from;

    @JsonProperty("Remarks")
    private String remarks;



    @JsonProperty("RequestURI")
    private String requestURI;

    @JsonProperty("RequestMethod")
    private String requestMethod;

    @JsonProperty("RequestBody")
    private String requestBody;

    @JsonProperty("ResponseStatus")
    private String responseStatus;

    @JsonProperty("ResponseBody")
    private String responseBody;



    public AuditRequest() {
    }

    public AuditRequest(String clientTime, String ip, String type, String serverity, String keyword, String description, String generated_from, String remarks, String requestURI, String requestMethod, String requestBody, String responseStatus, String responseBody) {
        this.clientTime = clientTime;
      //  this.server_time = server_time;
        this.ip = ip;
        this.type = type;
        this.serverity = serverity;
        this.keyword = keyword;
        this.description = description;
        this.generated_from = generated_from;
        this.remarks = remarks;
        this.requestURI = requestURI;
        this.requestMethod = requestMethod;
        this.requestBody = requestBody;
        this.responseStatus = responseStatus;
        this.responseBody = responseBody;
    }

    public String getClientTime() {
        return clientTime;
    }

    public void setClientTime(String clientTime) {
        this.clientTime = clientTime;
    }

   // public String getServer_time() {
     //   return server_time;
    //}

    //public void setServer_time(String server_time) {
      //  this.server_time = server_time;
    //}

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

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
}
