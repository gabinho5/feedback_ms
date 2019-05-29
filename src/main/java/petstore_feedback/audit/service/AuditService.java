package petstore_feedback.audit.service;

import org.springframework.stereotype.Service;

import petstore_feedback.audit.AuditLog;

@Service
public class AuditService {

	public void execute(AuditLog bean) {
            //TODO: Save the audit data to audit repository
	}
}
