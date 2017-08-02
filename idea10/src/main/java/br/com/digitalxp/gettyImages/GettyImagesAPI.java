package br.com.digitalxp.gettyImages;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public final class GettyImagesAPI {

	// TODO: configurable
	private static final String BASE_URL = "https://api.gettyimages.com/v3/";
	private static final String BASE_SEARCH = "search/images?fields=id,title,thumb,referral_destinations&sort_order=best";
	private static final String SEARCH_BY_PHRASE = "&phrase=%s";
	private static final String API_KEY = "fmyzcqbxybbp7bfa9th4kpyt";
	private static final String SECRET = "Bearer mfM8EtNbUpssBbcygADYajdnCcnt3vUCyE5UnevPN7NF8";
	private static final GettyImagesAPI INSTANCE = new GettyImagesAPI();

	private GettyImagesAPI() {

	}

	public static GettyImagesAPI getInstance() {
		return INSTANCE;
	}

	public Object search(final String phrase) {
		final WebTarget myResource = ClientBuilder.newClient()
		.target("https://api.gettyimages.com/oauth2/token");		
		myResource.queryParam("grant_type", "client_credentials");
		myResource.queryParam("client_id", API_KEY);
		myResource.queryParam("client_secret", SECRET);
		
		final Invocation.Builder response = myResource.request();
		Response response2 = response.get();
		System.out.println(response2);
				
//				(MediaType.TEXT_PLAIN).header("Api-Key", API_KEY)
//				.header("Authorization", SECRET).get(String.class);

//		. target(BASE_URL + BASE_SEARCH + String.format(SEARCH_BY_PHRASE, phrase));
		return response;
	}
	
	public static void main(String[] args) {
		final String response = (String) GettyImagesAPI.getInstance().search("kitties");

		System.out.println(response);
	}
}
