import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Vrion
 */
public class CountingCodeLines {
    public static void main(String[] args) throws IOException{
        File f = new File("C:\\sample.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        int nrRreshtave = LineCounter.getNumberOfLines(br);
        System.out.println("____________________________________________________________________________________________________________");
        System.out.println("\t\t\t\t\t\tShenim:");
        System.out.println("Numri i rreshtave te cilet permbajne kod pa perfshire, rreshtat e zbrazet, komente inline dhe block komente:");
        System.out.println("");
        System.out.println("\t\tNe total numri i rreshtave te cilet permbajne kod eshte: " + nrRreshtave);
        System.out.println("____________________________________________________________________________________________________________");
        
    }
}
