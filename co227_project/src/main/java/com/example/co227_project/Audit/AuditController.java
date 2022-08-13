package com.example.co227_project.Audit;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class AuditController {

    private final AuditRepository auditRepository;

    public AuditController(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @GetMapping("/findAlldata")
    public ResponseEntity<List<Audit>> getAllAuditData() {
        return ResponseEntity.ok(this.auditRepository.findAll());

    }

    @PostMapping("/saveData")
    public ResponseEntity<Audit> createAuditData(@RequestBody AuditRequest auditRequest){

        Audit audit = new Audit();


        audit.setClientTime(auditRequest.getClientTime());
       // audit.setServer_time(auditRequest.getServer_time());
        audit.setIp(auditRequest.getIp());
        audit.setType(auditRequest.getType());
        audit.setServerity(auditRequest.getServerity());
        audit.setKeyword(auditRequest.getKeyword());
        audit.setDescription(auditRequest.getDescription());
        audit.setGenerated_from(auditRequest.getGenerated_from());
        audit.setRemarks(auditRequest.getRemarks());

        audit.setRequestURI(auditRequest.getRequestURI());
        audit.setRequestBody(auditRequest.getRequestBody());
        audit.setRequestMethod(auditRequest.getRequestMethod());
        audit.setResponseBody(auditRequest.getResponseBody());
        audit.setResponseStatus(auditRequest.getResponseStatus());


        return ResponseEntity.status(201).body(this.auditRepository.save(audit));
    }
}