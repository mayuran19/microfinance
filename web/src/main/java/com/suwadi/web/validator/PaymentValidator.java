package com.suwadi.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.suwadi.domain.Payment;
import com.suwadi.service.PaymentService;

@Component("paymentValidator")
public class PaymentValidator implements Validator {
	private PaymentService paymentService;

	@Autowired
	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public boolean supports(Class<?> clazz) {
		return Payment.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Payment payment = (Payment) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date",
				"payment.date.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",
				"payment.description.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount",
				"payment.amount.required");
	}

}
