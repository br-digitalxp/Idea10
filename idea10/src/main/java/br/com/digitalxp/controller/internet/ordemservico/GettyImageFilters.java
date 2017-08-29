package br.com.digitalxp.controller.internet.ordemservico;

import java.io.Serializable;

public class GettyImageFilters implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3654770158637235778L;
	private boolean comPessoas;
	private boolean semPessoas;
	private boolean horizontal;
	private boolean vertical;
	private boolean horizonalPanoramica;
	private boolean verticalPanoramica;
	private boolean quadrado;
	public boolean isComPessoas() {
		return comPessoas;
	}
	public void setComPessoas(boolean comPessoas) {
		this.comPessoas = comPessoas;
	}
	public boolean isSemPessoas() {
		return semPessoas;
	}
	public void setSemPessoas(boolean semPessoas) {
		this.semPessoas = semPessoas;
	}
	public boolean isHorizontal() {
		return horizontal;
	}
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	public boolean isVertical() {
		return vertical;
	}
	public void setVertical(boolean vertical) {
		this.vertical = vertical;
	}
	public boolean isHorizonalPanoramica() {
		return horizonalPanoramica;
	}
	public void setHorizonalPanoramica(boolean horizonalPanoramica) {
		this.horizonalPanoramica = horizonalPanoramica;
	}
	public boolean isVerticalPanoramica() {
		return verticalPanoramica;
	}
	public void setVerticalPanoramica(boolean verticalPanoramica) {
		this.verticalPanoramica = verticalPanoramica;
	}
	public boolean isQuadrado() {
		return quadrado;
	}
	public void setQuadrado(boolean quadrado) {
		this.quadrado = quadrado;
	}
	
	

}
