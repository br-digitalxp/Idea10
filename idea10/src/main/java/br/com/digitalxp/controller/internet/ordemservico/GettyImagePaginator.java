package br.com.digitalxp.controller.internet.ordemservico;

import java.io.Serializable;
import java.util.List;

public class GettyImagePaginator implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3654770158637235778L;
	private List<ImagemGettyImage> lista;
	private long total;
	private int pagina;
	
	public List<ImagemGettyImage> getLista() {
		return lista;
	}
	public void setLista(List<ImagemGettyImage> lista) {
		this.lista = lista;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public int getPagina() {
		return pagina;
	}
	public void setPagina(int pagina) {
		this.pagina = pagina;
	}
	public long getNumeroPaginas(){
		return total/10;
	}
	

}
