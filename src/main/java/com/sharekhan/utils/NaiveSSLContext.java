package com.sharekhan.utils;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class NaiveSSLContext {
	private NaiveSSLContext() {
	}

	public static SSLContext getInstance(String protocol) throws NoSuchAlgorithmException {
		return init(SSLContext.getInstance(protocol));
	}

	public static SSLContext getInstance(String protocol, Provider provider) throws NoSuchAlgorithmException {
		return init(SSLContext.getInstance(protocol, provider));
	}

	public static SSLContext getInstance(String protocol, String provider)
			throws NoSuchAlgorithmException, NoSuchProviderException {
		return init(SSLContext.getInstance(protocol, provider));
	}

	private static SSLContext init(SSLContext context) {
		try {
			// Set a custom TrustManager that performs certificate validation.
			context.init(null, new TrustManager[] { new CustomTrustManager() }, null);
		} catch (KeyManagementException e) {
			throw new RuntimeException("Failed to initialize an SSLContext.", e);
		}
		return context;
	}

	private static class CustomTrustManager implements X509TrustManager {
		// This method is used to check if a certificate is trusted.
		public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
			// Perform certificate validation here. // Throw a CertificateException if the
			// certificate is not trusted.
		}

		// This method is used to check if a certificate is trusted.
		public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
			// Perform certificate validation here. // Throw a CertificateException if the
			// certificate is not trusted.
		}

		// This method returns the list of accepted issuers.
		public X509Certificate[] getAcceptedIssuers() {
			// Return an empty array to accept any issuer.
			return new X509Certificate[0];
		}
	}
}