import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class StickerFactory {

    public void create(InputStream inputStream, String outputFile) throws Exception {
        // leitura da imagem
//        InputStream inputStream = new FileInputStream(new File("input/filme.jpg"));
//        InputStream inputStream = new URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_1.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar a fonte
        graphics.setColor(Color.BLUE);
        graphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 80));

        // escrever uma frase na nova imagem
        graphics.drawString("EXCELENTE!", largura / 4, novaAltura - 100);

        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(outputFile));
    }

}
