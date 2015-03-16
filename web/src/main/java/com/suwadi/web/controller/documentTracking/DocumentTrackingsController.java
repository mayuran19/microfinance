package com.suwadi.web.controller.documentTracking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.suwadi.domain.DocumentSendingMedium;
import com.suwadi.domain.DocumentStatus;
import com.suwadi.domain.DocumentType;
import com.suwadi.service.DocumentSendingMediumService;
import com.suwadi.service.DocumentStatusService;
import com.suwadi.service.DocumentTrackingService;
import com.suwadi.service.DocumentTypeService;
import com.suwadi.web.model.documentTracking.DocumentTrackingForm;
import com.suwadi.web.pagination.Pager;

@Controller
public class DocumentTrackingsController {
	private DocumentTrackingService documentTrackingService;
	private DocumentStatusService documentStatusService;
	private DocumentTypeService documentTypeService;
	private DocumentSendingMediumService documentSendingMediumService;

	@Autowired
	public void setDocumentTrackingService(
			DocumentTrackingService documentTrackingService) {
		this.documentTrackingService = documentTrackingService;
	}

	@Autowired
	public void setDocumentStatusService(
			DocumentStatusService documentStatusService) {
		this.documentStatusService = documentStatusService;
	}

	@Autowired
	public void setDocumentTypeService(DocumentTypeService documentTypeService) {
		this.documentTypeService = documentTypeService;
	}

	@Autowired
	public void setDocumentSendingMediumService(
			DocumentSendingMediumService documentSendingMediumService) {
		this.documentSendingMediumService = documentSendingMediumService;
	}

	@RequestMapping(value = "/documentTracking/documentTrackings/inboundList")
	public String inboundList(Model model, Pager pager) {
		model.addAttribute("pager",
				this.documentTrackingService.paginateInboundDocuments(pager));
		return "/documentTracking/documentTrackings/inboundList";
	}

	@RequestMapping(value = "/documentTracking/documentTrackings/outboundList")
	public String outboundList(Model model, Pager pager) {
		model.addAttribute("pager",
				this.documentTrackingService.paginateOutboundDocuments(pager));
		return "/documentTracking/documentTrackings/outboundList";
	}

	@RequestMapping(value = "/documentTracking/documentTrackings/addInbound")
	public String addInbound(Model model) {
		List<DocumentStatus> documentStatus = this.documentStatusService
				.findAll();
		List<DocumentType> documentTypes = this.documentTypeService.findAll();
		List<DocumentSendingMedium> documentSendingMediums = this.documentSendingMediumService
				.findAll();
		DocumentTrackingForm documentTrackingForm = new DocumentTrackingForm();
		documentTrackingForm.setInboundOutBound(1);
		model.addAttribute("documentStatus", documentStatus);
		model.addAttribute("documentTypes", documentTypes);
		model.addAttribute("documentSendingMediums", documentSendingMediums);
		model.addAttribute("documentTrackingForm", documentTrackingForm);

		return "/documentTracking/documentTrackings/addInbound";
	}

	@RequestMapping(value = "/documentTracking/documentTrackings/processAddInbound")
	public String processAddInbound(
			@ModelAttribute("documentTrackingForm") DocumentTrackingForm documentTrackingForm,
			BindingResult result, Model model) {
		this.documentTrackingService.save(documentTrackingForm);

		return "redirect:/documentTracking/documentTrackings/inboundList";
	}

	@RequestMapping(value = "/documentTracking/documentTrackings/addOutbound")
	public String addOutbound(Model model) {
		List<DocumentStatus> documentStatus = this.documentStatusService
				.findAll();
		List<DocumentType> documentTypes = this.documentTypeService.findAll();
		List<DocumentSendingMedium> documentSendingMediums = this.documentSendingMediumService
				.findAll();
		DocumentTrackingForm documentTrackingForm = new DocumentTrackingForm();
		documentTrackingForm.setInboundOutBound(2);
		model.addAttribute("documentStatus", documentStatus);
		model.addAttribute("documentTypes", documentTypes);
		model.addAttribute("documentSendingMediums", documentSendingMediums);
		model.addAttribute("documentTrackingForm", documentTrackingForm);

		return "/documentTracking/documentTrackings/addOutbound";
	}

	@RequestMapping(value = "/documentTracking/documentTrackings/processAddOutbound")
	public String processAddOutbound(
			@ModelAttribute("documentTrackingForm") DocumentTrackingForm documentTrackingForm,
			BindingResult result, Model model) {
		this.documentTrackingService.save(documentTrackingForm);

		return "redirect:/documentTracking/documentTrackings/outboundList";
	}

	@RequestMapping(value = "/documentTracking/documentTrackings/{id}/edit")
	public String editDocumentTracking(@PathVariable("id") Long id, Model model) {
		List<DocumentStatus> documentStatus = this.documentStatusService
				.findAll();
		List<DocumentType> documentTypes = this.documentTypeService.findAll();
		List<DocumentSendingMedium> documentSendingMediums = this.documentSendingMediumService
				.findAll();
		DocumentTrackingForm documentTrackingForm = this.documentTrackingService
				.findByDocumentTrackingId(id);
		model.addAttribute("documentStatus", documentStatus);
		model.addAttribute("documentTypes", documentTypes);
		model.addAttribute("documentSendingMediums", documentSendingMediums);
		model.addAttribute("documentTrackingForm", documentTrackingForm);

		return "/documentTracking/documentTrackings/edit";
	}

	@RequestMapping(value = "/documentTracking/documentTrackings/{id}/processEditDocumentTracking", method = RequestMethod.POST)
	public String processEditDocumentTracking(
			@ModelAttribute("documentTrackingForm") DocumentTrackingForm documentTrackingForm,
			BindingResult result, Model model) {
		this.documentTrackingService.update(documentTrackingForm);
		if (documentTrackingForm.getInboundOutBound().intValue() == 1) {
			return "redirect:/documentTracking/documentTrackings/inboundList";
		} else if (documentTrackingForm.getInboundOutBound().intValue() == 2) {
			return "redirect:/documentTracking/documentTrackings/outboundList";
		}
		return "";
	}
}
