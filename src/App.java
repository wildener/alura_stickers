import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // conexão HTTP
        String url = "https://api.mocki.io/v2/549a5d8b";
        URI uri = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();

        // buscar os top 250 filmes
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // obter (parse) apenas os seguintes dados: título, poster, classificação
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        StickerFactory sf = new StickerFactory();

        for (Map<String, String> filme: listaDeFilmes) {
            String urlImagem = filme.get("image");
            String titulo = filme.get("title").replace(":", "");

            InputStream inputStream = new URL(urlImagem).openStream();
            String saidaArquivo = "output/" + titulo + ".png";

            sf.create(inputStream, saidaArquivo);

            System.out.println(titulo);
            System.out.println();
        }
    }
}
