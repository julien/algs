import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
    private WordNet wordnet;

    public Outcast(WordNet wordnet) {
        this.wordnet = wordnet;
    }

    public String outcast(String[] nouns) {
        int[] distances = new int[nouns.length];
        int outcast = 0;
        int dist = 0;
        int max = 0;
        int index = 0;

        for (int i = 0; i < nouns.length; i++) {
            for (int j = i + 1; j < nouns.length; j++) {
                dist = wordnet.distance(nouns[i], nouns[j]);
                distances[i] += dist;
                distances[j] += dist;
            }
        }

        for (int i = 0; i < distances.length; i++) {
            if (distances[i] > max) {
                max = distances[i];
                index = i;
            }
        }

        return nouns[index];
    }

    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
