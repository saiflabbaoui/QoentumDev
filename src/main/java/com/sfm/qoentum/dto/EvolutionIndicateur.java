package com.sfm.qoentum.dto;

public class EvolutionIndicateur {

	private long date;
	private String parametre;
	private Double value;
	private String modelPhone;

	private String systeme;
	private String versionOS;
	private String technologie;

	private Integer ci;
	private Integer pci;
	private Integer tac;
	private Integer earfcn;
	private Integer bandwidth;
	private Integer psc;
	private Integer uarfcn;
	private Integer cid;
	private Integer lac;
	private Integer bsic;
	private Integer arfcn;

	public EvolutionIndicateur() {
		super();
	}
	public EvolutionIndicateur(long date, String parametre, Double value, String systeme,
							   String versionOS, String technologie, Integer ci, Integer pci, Integer tac, Integer earfcn,
							   Integer bandwidth, Integer psc, Integer uarfcn, Integer cid, Integer lac, Integer bsic, Integer arfcn) {
		super();
		this.date = date;
		this.parametre = parametre;
		this.value = value;
		this.systeme = systeme;
		this.versionOS = versionOS;
		this.technologie = technologie;
		this.ci = ci;
		this.pci = pci;
		this.tac = tac;
		this.earfcn = earfcn;
		this.bandwidth = bandwidth;
		this.psc = psc;
		this.uarfcn = uarfcn;
		this.cid = cid;
		this.lac = lac;
		this.bsic = bsic;
		this.arfcn = arfcn;
	}
	public EvolutionIndicateur(long date, String parametre, Double value, String modelPhone, String systeme,
			String versionOS, String technologie, Integer ci, Integer pci, Integer tac, Integer earfcn,
			Integer bandwidth, Integer psc, Integer uarfcn, Integer cid, Integer lac, Integer bsic, Integer arfcn) {
		super();
		this.date = date;
		this.parametre = parametre;
		this.value = value;
		this.modelPhone = modelPhone;
		this.systeme = systeme;
		this.versionOS = versionOS;
		this.technologie = technologie;
		this.ci = ci;
		this.pci = pci;
		this.tac = tac;
		this.earfcn = earfcn;
		this.bandwidth = bandwidth;
		this.psc = psc;
		this.uarfcn = uarfcn;
		this.cid = cid;
		this.lac = lac;
		this.bsic = bsic;
		this.arfcn = arfcn;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public String getParametre() {
		return parametre;
	}

	public void setParametre(String parametre) {
		this.parametre = parametre;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getModelPhone() {
		return modelPhone;
	}

	public void setModelPhone(String modelPhone) {
		this.modelPhone = modelPhone;
	}

	public String getSysteme() {
		return systeme;
	}

	public void setSysteme(String systeme) {
		this.systeme = systeme;
	}

	public String getVersionOS() {
		return versionOS;
	}

	public void setVersionOS(String versionOS) {
		this.versionOS = versionOS;
	}

	public String getTechnologie() {
		return technologie;
	}

	public void setTechnologie(String technologie) {
		this.technologie = technologie;
	}

	public Integer getCi() {
		return ci;
	}

	public void setCi(Integer ci) {
		this.ci = ci;
	}

	public Integer getPci() {
		return pci;
	}

	public void setPci(Integer pci) {
		this.pci = pci;
	}

	public Integer getTac() {
		return tac;
	}

	public void setTac(Integer tac) {
		this.tac = tac;
	}

	public Integer getEarfcn() {
		return earfcn;
	}

	public void setEarfcn(Integer earfcn) {
		this.earfcn = earfcn;
	}

	public Integer getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(Integer bandwidth) {
		this.bandwidth = bandwidth;
	}

	public Integer getPsc() {
		return psc;
	}

	public void setPsc(Integer psc) {
		this.psc = psc;
	}

	public Integer getUarfcn() {
		return uarfcn;
	}

	public void setUarfcn(Integer uarfcn) {
		this.uarfcn = uarfcn;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getLac() {
		return lac;
	}

	public void setLac(Integer lac) {
		this.lac = lac;
	}

	public Integer getBsic() {
		return bsic;
	}

	public void setBsic(Integer bsic) {
		this.bsic = bsic;
	}

	public Integer getArfcn() {
		return arfcn;
	}

	public void setArfcn(Integer arfcn) {
		this.arfcn = arfcn;
	}

	@Override
	public String toString() {
		return "EvolutionIndicateur [date=" + date + ", parametre=" + parametre + ", value=" + value + ", modelPhone="
				+ modelPhone + ", systeme=" + systeme + ", versionOS=" + versionOS + ", technologie=" + technologie
				+ ", ci=" + ci + ", pci=" + pci + ", tac=" + tac + ", earfcn=" + earfcn + ", bandwidth=" + bandwidth
				+ ", psc=" + psc + ", uarfcn=" + uarfcn + ", cid=" + cid + ", lac=" + lac + ", bsic=" + bsic
				+ ", arfcn=" + arfcn + "]";
	}

}
