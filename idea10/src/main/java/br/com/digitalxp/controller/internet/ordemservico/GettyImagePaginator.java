package br.com.digitalxp.controller.internet.ordemservico;

import java.io.Serializable;
import java.util.List;

public class GettyImagePaginator implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3654770158637235778L;
	private List<ImagemGettyImage> lista;
	private Integer total;
	private Integer pagina;
	
	public List<ImagemGettyImage> getLista() {
		return lista;
	}
	public void setLista(List<ImagemGettyImage> lista) {
		this.lista = lista;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getPagina() {
		return pagina;
	}
	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}
	public Integer getNumeroPaginas(){
		return total/10;
	}
	

}
