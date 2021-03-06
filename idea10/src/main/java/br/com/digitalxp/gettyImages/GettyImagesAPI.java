package br.com.digitalxp.gettyImages;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import br.com.digitalxp.controller.internet.ordemservico.CategoriaGettyImage;
import br.com.digitalxp.controller.internet.ordemservico.GettyImageFilters;
import br.com.digitalxp.controller.internet.ordemservico.GettyImagePaginator;
import br.com.digitalxp.controller.internet.ordemservico.ImagemGettyImage;
import br.com.digitalxp.model.ImagemModel;
import br.com.digitalxp.model.gettyimages.DisplaySize;
import br.com.digitalxp.model.gettyimages.GettyImagesResponse;
import br.com.digitalxp.model.gettyimages.Image;

public final class GettyImagesAPI {

	// TODO: configurable
	private static final String BASE_URL = "https://api.gettyimages.com/v3/";
	private static final String BASE_SEARCH = "search/images?fields=id,title,artist,caption,collection_name,thumb,comp&sort_order=best";
	private static final String SEARCH_BY_PHRASE = "&phrase=";
	private static final String API_KEY = "zn792qr9d2anmpay4zhg2xdr";
	private static final String SECRET = "Bearer mfM8EtNbUpssBbcygADYajdnCcnt3vUCyE5UnevPN7NF8";
	private static final String THUMBNAIL = "thumb";
	private static final String COMP = "comp";
	private static final String DEFAULT_RECORDS_NUMBER = "&page_size=40&page=";
	private static final GettyImagesAPI INSTANCE = new GettyImagesAPI();

	private GettyImagesAPI() {

	}

	public static GettyImagesAPI getInstance() {
		return INSTANCE;
	}

	public GettyImagePaginator search(final String phrase, int page, GettyImageFilters filtros) {
		HttpClient httpClient = HttpClientBuilder.create().build();

		HttpGet getRequest = null;
		String json = null;
		try {
			String filters = filtros.getURI();			
			
			getRequest = new HttpGet(BASE_URL+BASE_SEARCH+DEFAULT_RECORDS_NUMBER+page+filters+SEARCH_BY_PHRASE+URLEncoder.encode(phrase, "UTF-8"));
		
			getRequest.addHeader("Api-Key", API_KEY);
			
			getRequest.addHeader("Accept-Language", "pt-BR");
		
			HttpResponse response = httpClient.execute(getRequest);

			json =  EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        com.google.gson.Gson gson = new com.google.gson.Gson();
        GettyImagesResponse resposta = gson.fromJson(json, GettyImagesResponse.class);
        GettyImagePaginator paginator = new GettyImagePaginator();
        if(resposta != null && resposta.getResultCount() != null){        
	        List<ImagemGettyImage> listaRetorno = popular(resposta);
	        paginator.setLista(listaRetorno);
	        paginator.setTotal(resposta.getResultCount());
	        paginator.setPagina(page);
        }
		return paginator;
	}
	
	private List<ImagemGettyImage> popular(GettyImagesResponse imagesGI){		
		List<ImagemGettyImage> listaRetorno = new ArrayList<ImagemGettyImage>();
		if(imagesGI.getImages() != null){
			for(Image image : imagesGI.getImages()){
				ImagemGettyImage imagem = new ImagemGettyImage();
				imagem.setDescricao(image.getCaption());
				imagem.setTitulo(image.getTitle());
				
				CategoriaGettyImage categoria = new CategoriaGettyImage();
				categoria.setNomeCategria(image.getCollectionName());
				imagem.setCategoria(categoria);
				
				ImagemModel imagemModel = new ImagemModel();
				imagemModel.setCodigo(image.getId());
				//imagemModel.setAutor(image.getArtist());
				if(image.getDisplaySizes() != null && image.getDisplaySizes().size() > 0 ) {
					for(DisplaySize ds : image.getDisplaySizes()){
						if(COMP.equals(ds.getName())){
							imagemModel.setCaminhoImagemComp(ds.getUri());
						}else if(THUMBNAIL.equals(ds.getName())){
							imagemModel.setCaminhoImagem(ds.getUri());
						}
					}
					
					imagem.setImagem(imagemModel);
					listaRetorno.add(imagem);
				}
			}
		}
		return listaRetorno;
	}
}
