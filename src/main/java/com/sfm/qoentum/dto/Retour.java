package com.sfm.qoentum.dto;

public class Retour {

	private String file;
	private PageMeta metadata;

	public Retour() {
		super();
	}

	public Retour(String file, PageMeta metadata) {
		super();
		this.file = file;
		this.metadata = metadata;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public PageMeta getMetadata() {
		return metadata;
	}

	public void setMetadata(PageMeta metadata) {
		this.metadata = metadata;
	}

	@Override
	public String toString() {
		return "Retour [file=" + file + ", metadata=" + metadata + "]";
	}

}
