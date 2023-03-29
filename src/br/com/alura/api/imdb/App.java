package br.com.alura.api.imdb;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;



public class App {

	public static void main(String[] args) throws Exception{

		// Fazer uma conexão HTTP e buscar os tops 250 filmes
		String url ="https://raw.githubusercontent.com/lukadev08/lukadev08.github.io/main/apidata/imdbtop250moviesdata.json";
		URI endereco = URI.create(url);
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder(endereco).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();
		
		// pegar somente os dados que interessam (titulo,poster,classificação)
		var parser = new JsonParser();
		List<Map<String,String>> listadeFilmes = parser.parse(body);
		
		// exibir e manipular os dados 
		for (Map<String, String> filme : listadeFilmes) {
			System.out.println("\u001b[1m Titulo:\u001b[0m"+filme.get("title"));
			System.out.println("Image:"+ filme.get("image"));
			System.out.println("Rating:"+ filme.get("imDbRating"));
			double classificacao = Double.parseDouble(filme.get("imDbRating"));
			int controle = (int) classificacao;
			for(int i=1;i<=controle;i++) {
				System.out.print("\uD83D\uDC99"); 
				
			}
			System.out.println("\n"); 	
			System.out.println(listadeFilmes.size());
			System.out.println();
		}

	}

}
