package com.sfm.qoentum.dto;

public class DisconnectCause {

	private String code;
	private String description;
	private String reason;
	private String label;

	public DisconnectCause() {

	}

	public DisconnectCause(String code, String description, String reason, String label) {
		super();
		this.code = code;
		this.description = description;
		this.reason = reason;
		this.label = label;
	}
	
	public DisconnectCause(String disconnectCauseString) {
		this.code = findCode(disconnectCauseString, "Code");
		this.description = findCode(disconnectCauseString, "Description");
		this.reason = findCode(disconnectCauseString, "Reason");
		this.label = findCode(disconnectCauseString, "Label");
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "DisconnectCause [code=" + code + ", description=" + description + ", reason=" + reason + ", label="
				+ label + "]";
	}
	
	public String findCode(String disconnectCauseString, String key) {
		String word = disconnectCauseString.substring(
				disconnectCauseString.indexOf(key +": (")
				);
		word = word.replace(key + ": (", "").trim();
		if(word.charAt(0) == ')') {
			word = null;
		} else {
			word = word.substring(
					0, 
					word.indexOf(")")
					);
		}
		
		if(word != null) {
			String firstLetter = word.substring(0, 1);
			if(firstLetter == ",") {
				word = word.substring(1).trim();
			}
			
		}
		
		return word;
	}

}
