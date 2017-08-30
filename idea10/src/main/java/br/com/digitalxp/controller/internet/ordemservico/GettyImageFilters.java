package br.com.digitalxp.controller.internet.ordemservico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GettyImageFilters implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3654770158637235778L;
	private boolean comPessoas;
	private boolean semPessoas;
	private boolean umaPessoas;
	private boolean duasPessoas;
	
	private boolean colorido;
	private boolean pretoBranco;
	
	private boolean fotografia;
	private boolean ilustracao;
	
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
	public boolean isUmaPessoas() {
		return umaPessoas;
	}
	public void setUmaPessoas(boolean umaPessoas) {
		this.umaPessoas = umaPessoas;
	}
	public boolean isDuasPessoas() {
		return duasPessoas;
	}
	public void setDuasPessoas(boolean duasPessoas) {
		this.duasPessoas = duasPessoas;
	}
	public boolean isColorido() {
		return colorido;
	}
	public void setColorido(boolean colorido) {
		this.colorido = colorido;
	}
	public boolean isPretoBranco() {
		return pretoBranco;
	}
	public void setPretoBranco(boolean pretoBranco) {
		this.pretoBranco = pretoBranco;
	}
	public boolean isFotografia() {
		return fotografia;
	}
	public void setFotografia(boolean fotografia) {
		this.fotografia = fotografia;
	}
	public boolean isIlustracao() {
		return ilustracao;
	}
	public void setIlustracao(boolean ilustracao) {
		this.ilustracao = ilustracao;
	}
	
	public String getURI(){
		String filters = "";
		
		List<String> filtroPessoas  = new ArrayList<String>();
		if(this.isSemPessoas()){
			filtroPessoas.add("none");
		}
		if(this.isComPessoas()){
			filtroPessoas.add("group");
		}
		if(this.isUmaPessoas()){
			filtroPessoas.add("one");
		}
		if(this.isDuasPessoas()){
			filtroPessoas.add("two");
		}
		
		if(!filtroPessoas.isEmpty()){
			StringBuilder sb = new StringBuilder();
			for (String pessoas : filtroPessoas) {
				sb.append(pessoas).append(",");
		    }
			sb.deleteCharAt(sb.length()-1);
			filters+="&number_of_people="+sb.toString();
		}
		
		
		List<String> filtroOrientacao  = new ArrayList<String>();
		if(this.isHorizonalPanoramica()){
			filtroOrientacao.add("PanoramicHorizontal");
		}
		if(this.isHorizontal()){
			filtroOrientacao.add("Horizontal");
		}
		if(this.isQuadrado()){
			filtroOrientacao.add("Square");
		}
		if(this.isVertical()){
			filtroOrientacao.add("Vertical");
		}
		if(this.isVerticalPanoramica()){
			filtroOrientacao.add("PanoramicVertical");
		}
		
		if(!filtroOrientacao.isEmpty()){
			StringBuilder sb = new StringBuilder();
			for (String orientacao : filtroOrientacao) {
				sb.append(orientacao).append(",");
		    }
			sb.deleteCharAt(sb.length()-1);
			filters+="&orientations="+sb.toString();
		}
		
		List<String> filtroImagem  = new ArrayList<String>();
		if(this.isIlustracao()){
			filtroImagem.add("illustration");
		}
		if(this.isFotografia()){
			filtroImagem.add("photography");
		}
		
		if(!filtroImagem.isEmpty()){
			StringBuilder sb = new StringBuilder();
			for (String imagem : filtroImagem) {
				sb.append(imagem).append(",");
		    }
			sb.deleteCharAt(sb.length()-1);
			filters+="&graphical_styles="+sb.toString();
		}
		return filters;
	}

}
