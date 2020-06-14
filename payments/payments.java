package com.trsvax.paypal.services;

import org.slf4j.Logger;

import com.paypal.api.payments.Payment;
import com.paypal.core.rest.PayPalRESTException;
import com.trsvax.paypal.CreditCardPayment;
import com.trsvax.shop.Invoice;
import com.trsvax.shop.PaymentMethod;
import com.trsvax.shop.PaymentSource;

public class CreditCardPaymentSource implements PaymentSource<CreditCardPayment> {
	private final Logger logger;
	private final PayPalRestService payPalService;
	private static final String STRIPE_API_KEY = "sk_live_devboxacct1DfwS2ClCIKljW";
	private static final String GOCARDLESS_API_KEY = "live_AlN-kpH1H4wGhpLgmwm5kg15snC6nVfL05tHSXRB";

	public CreditCardPaymentSource(Logger logger, PayPalRestService payPalService) {
		this.logger = logger;
		this.payPalService = payPalService;
	}

	public Object pay(CreditCardPayment payment, Invoice invoice) {
		logger.info("pay Credit Card {}",payment.getState());
		invoice.setPaymentMethod(payment);
		if ( payment.isState(PaymentMethod.AUTHORIZE)  ) {
			try {
				Payment result = payPalService.payCreditCard(payment, invoice);
				if ( result.getState().equals("approved")) {
					payment.setState(PaymentMethod.APPROVED);
					return "cart/Thanks";
				} else {
					logger.error("PayPal error {}",result.getState());
					payment.setError(result.getState());
				}
			} catch (PayPalRESTException e) {
				logger.error("PayPal error {}",e.getMessage());
				payment.setError(e.getMessage());
			}
			return null;
		}
		return "cart/CreditCardCheckout";
	}
}
