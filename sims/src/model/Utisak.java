/***********************************************************************
 * Module:  Utisak.java
 * Author:  Dragan
 * Purpose: Defines the Class Utisak
 ***********************************************************************/
package model;

import java.util.Date;

/** @pdOid 860c919d-adfe-4aab-be94-4c35f03d3cb9 */
public abstract class Utisak {
	/** @pdOid 94d8a717-8716-419d-84be-9f6ef52a6f96 */
	private String text;
	/** @pdOid d37bfb49-ea3a-43b0-84fe-1c21cc5557b8 */
	private Date datumUpisa;
	/** @pdOid e50c8293-12de-4b2a-9124-d7b41f2abd4c */
	private boolean status;

	/** @pdRoleInfo migr=no name=MuzickoDelo assc=association5 mult=1..1 side=A */
	private MuzickoDelo delo;


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}

	public Date getDatumUpisa() {
		return datumUpisa;
	}

	public void setDatumUpisa(Date datumUpisa) {
		this.datumUpisa = datumUpisa;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public MuzickoDelo getDelo() {
		return delo;
	}

	public void setDelo(MuzickoDelo delo) {
		this.delo = delo;
	}

	public Utisak(String text, Date datumUpisa, boolean status, MuzickoDelo delo) {
		super();
		this.text = text;
		this.datumUpisa = datumUpisa;
		this.status = status;
		this.delo = delo;
	}

	public Utisak() {
		super();
	}


	public abstract String toFileString();
}