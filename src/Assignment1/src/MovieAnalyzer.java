import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class MovieAnalyzer {

    public Stream<Movie> movieStream;

    public static class Movie {
        private String link;
        private String title;
        private int year;
        private String certificate;
        private int runtime;
        private String genre;
        private float rating;
        private String overview;
        private int score;
        private String director;
        private String star1;
        private String star2;
        private String star3;
        private String star4;
        private int votes;
        private int gross;


        public Movie(String link, String title, int year, String certificate, int runtime, String genre, float rating, String overview,
                     int score, String director, String star1, String star2, String star3, String star4, int votes, int gross) {
            this.link = link;
            this.title = title;
            this.year = year;
            this.certificate = certificate;
            this.runtime = runtime;
            this.genre = genre;
            this.rating = rating;
            this.overview = overview;
            this.score = score;
            this.director = director;
            this.star1 = star1;
            this.star2 = star2;
            this.star3 = star3;
            this.star4 = star4;
            this.votes = votes;
            this.gross = gross;
        }

        public String getLink() {
            return link;
        }

        public String getTitle() {
            return title;
        }

        public int getYear() {
            return year;
        }

        public String getCertificate() {
            return certificate;
        }

        public int getRuntime() {
            return runtime;
        }

        public String getGenre() {
            return genre;
        }

        public float getRating() {
            return rating;
        }

        public String getOverview() {
            return overview;
        }

        public int getScore() {
            return score;
        }

        public String getDirector() {
            return director;
        }

        public String getStar1() {
            return star1;
        }

        public String getStar2() {
            return star2;
        }

        public String getStar3() {
            return star3;
        }

        public String getStar4() {
            return star4;
        }

        public int getVotes() {
            return votes;
        }

        public int getGross() {
            return gross;
        }
    }

    public MovieAnalyzer(String data_set) throws IOException {

        movieStream = Files.lines(Paths.get(data_set))
                .skip(1)
                .map(l -> {
                    if (l.endsWith(",")) {
                        l += "0";
                    }
                    return l.trim().split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", -1);
                })
                .map(a -> new Movie(a[0], a[1], Integer.parseInt(a[2]), a[3], Integer.parseInt(a[4].replace(" min", "")),
                        a[5], Float.parseFloat(a[6]), a[7], Integer.parseInt(a[8].replace("", "0")),
                        a[9], a[10], a[11], a[12], a[13], Integer.parseInt(a[14]),
                        Integer.parseInt(a[15].replace(",", "").replace("\"", ""))));

    }

    public Map<Integer, Integer> getMovieCountByYear() {
        return null;
    }

    public static void main(String[] args) throws IOException {
        Stream<Movie> m = Files.lines(Paths.get("F:\\Sustech-CS209-java2\\src\\Assignment1\\resources\\imdb_top_500.csv"))
                .skip(1)
                .map(l -> {
                    if (l.endsWith(",")) {
                        l += "0";
                    }
                    return l.trim().split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", -1);
                })
                .map(a -> new Movie(a[0], a[1], Integer.parseInt(a[2]), a[3], Integer.parseInt(a[4].replace(" min", "")), a[5], Float.parseFloat(a[6]),
                        a[7], Integer.parseInt(a[8].replace("", "0")), a[9], a[10], a[11], a[12], a[13], Integer.parseInt(a[14]),
                        Integer.parseInt(a[15].replace(",", "").replace("\"", ""))));

        m.forEach(System.out::println);


    }

}