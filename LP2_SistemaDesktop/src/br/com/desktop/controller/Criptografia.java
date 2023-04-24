package br.com.desktop.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {
	
	public static final String SHA256 = "SHA-256";
	public static final String MD5 = "MD5";
	protected String informacao;	
	protected String padrao;
	
	public Criptografia(String informacao, String padrao) {
		super();
		this.informacao = informacao;
		this.padrao = padrao;
	}

	public String getInformacao() {
		return informacao;
	}
	
	public void setInformacao(String informacao) {
		this.informacao = informacao;
	}

	public String getPadrao() {
		return padrao;
	}

	public void setPadrao(String padrao) {
		this.padrao = padrao;
	}

	public String criptografar() {
		String informacao = getInformacao();
		//123456 - 8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92
		MessageDigest digest;
		StringBuilder hexString = null;
		try {
			digest = MessageDigest.getInstance(getPadrao());
			byte[] hash = digest.digest(
				informacao.getBytes(StandardCharsets.UTF_8));
			hexString = new StringBuilder(2 * hash.length);
		    for (int i = 0; i < hash.length; i++) {
		        String hex = Integer.toHexString(0xff & hash[i]);
		        if(hex.length() == 1) {
		            hexString.append('0');
		        }
		        hexString.append(hex);
		    }
		    
//		    System.out.println(hexString.toString());
//		    System.out.println("8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92");
//		    System.out.println(hexString.toString().equalsIgnoreCase("8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92"));
		    
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hexString.toString().toUpperCase();
	    
		}
}
