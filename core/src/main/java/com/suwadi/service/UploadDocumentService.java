package com.suwadi.service;

import java.util.List;
import com.suwadi.domain.UploadDocument;

public interface UploadDocumentService extends GenericService<UploadDocument>{
	public void save(List<UploadDocument> uploadDocuments);
}
