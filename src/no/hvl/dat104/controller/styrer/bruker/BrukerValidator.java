package no.hvl.dat104.controller.styrer.bruker;

import javax.servlet.http.HttpServletRequest;

import no.hvl.dat104.util.ValidatorUtil;

public class BrukerValidator {
    private String fornavn;
    private String etternavn;
    private String mail;
    private String passord;
    private String salt;
    private String idRolle;
    
    private String fornavnFeilmelding;
    private String etternavnFeilmelding;
    private String mailFeilmelding;
    private String passordFeilmelding;
    private String saltFeilmelding;
    private String idRolleFeilmelding;
    
    public BrukerValidator() {
    	
    }
    
    public BrukerValidator(HttpServletRequest request) {
        fornavn = ValidatorUtil.escapeHtml(request.getParameter("fornavn"));
        etternavn =  ValidatorUtil.escapeHtml(request.getParameter("etternavn"));
        mail = ValidatorUtil.escapeHtml(request.getParameter("mail"));
        passord = ValidatorUtil.escapeHtml(request.getParameter("passord"));
        salt = ValidatorUtil.escapeHtml(request.getParameter("salt"));
        idRolle = ValidatorUtil.escapeHtml(request.getParameter("idRolle"));
    }
    
    private boolean erFornavnGyldig() {
    	return ValidatorUtil.isNotNull0(fornavn) &&
    			ValidatorUtil.isValidfornavn(fornavn);
    }
    private boolean erEtternavnGyldig() {
    	return ValidatorUtil.isNotNull0(etternavn) &&
    			ValidatorUtil.isValidetternavn(etternavn);
    }
    private boolean erMailGyldig() {
    	return ValidatorUtil.isNotNull0(mail) &&
    			ValidatorUtil.isValidMail(mail);
    }
    private boolean erPassordGyldig() {
    	return ValidatorUtil.isNotNull0(passord) && 
    			ValidatorUtil.isValidString(passord);
    }
    private boolean erSaltGyldig() {
    	return ValidatorUtil.isNotNull0(salt);
    }
    //Gj�re sjekk p� om den er gyldig?
    private boolean erIdRolleGyldig() {
    	return ValidatorUtil.isNotNull0(idRolle);
    }
    public boolean erAlleDataGyldig() {
    	return erFornavnGyldig()&&erEtternavnGyldig()&&erMailGyldig()&&erPassordGyldig()&&erSaltGyldig()&&erIdRolleGyldig();
    }
    public void settOppFeilmeldinger() {
    	if (!erFornavnGyldig()) {
    		fornavn = "";
    		fornavnFeilmelding = "Fornavn er ikke gyldig!";
    	}
    	if (!erEtternavnGyldig()) {
    		etternavn = "";
    		etternavnFeilmelding = "Etternavn er ikke gyldig!";
    	}
    	if (!erMailGyldig()) {
    		mail = "";
    		mailFeilmelding = "Mailadressen er ikke gyldig!";
    	}
    	if (!erPassordGyldig()) {
    		passord = "";
    		passordFeilmelding = "Passord er ikke gyldig!";
    	}
    	if (!erSaltGyldig()) {
    		salt = "";
    		saltFeilmelding = "Salt er ikke gyldig!";
    	}
    	if (!erIdRolleGyldig()) {
    		idRolle = "";
    		idRolleFeilmelding = "idRolle er ikke gyldig!";
    	}
    }

	public String getFornavn() {
		return fornavn;
	}

	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassord() {
		return passord;
	}

	public void setPassord(String passord) {
		this.passord = passord;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getIdRolle() {
		return idRolle;
	}

	public void setIdRolle(String idRolle) {
		this.idRolle = idRolle;
	}

	public String getFornavnFeilmelding() {
		return fornavnFeilmelding;
	}

	public void setFornavnFeilmelding(String fornavnFeilmelding) {
		this.fornavnFeilmelding = fornavnFeilmelding;
	}

	public String getEtternavnFeilmelding() {
		return etternavnFeilmelding;
	}

	public void setEtternavnFeilmelding(String etternavnFeilmelding) {
		this.etternavnFeilmelding = etternavnFeilmelding;
	}

	public String getMailFeilmelding() {
		return mailFeilmelding;
	}

	public void setMailFeilmelding(String mailFeilmelding) {
		this.mailFeilmelding = mailFeilmelding;
	}

	public String getPassordFeilmelding() {
		return passordFeilmelding;
	}

	public void setPassordFeilmelding(String passordFeilmelding) {
		this.passordFeilmelding = passordFeilmelding;
	}

	public String getSaltFeilmelding() {
		return saltFeilmelding;
	}

	public void setSaltFeilmelding(String saltFeilmelding) {
		this.saltFeilmelding = saltFeilmelding;
	}
	
	public String getIdRolleFeilmelding() {
		return idRolleFeilmelding;
	}

	public void setIdRolleFeilmelding(String idRolleFeilmelding) {
		this.idRolleFeilmelding = idRolleFeilmelding;
	}
    
    
}