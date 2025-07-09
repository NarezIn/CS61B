package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;
import static utils.Utils.SHORT_WORDS_FILE;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    HashMap<String, TimeSeries> words;//[word, [year, num of this word this year]]
    TimeSeries counts;//[year, total words that year]

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        counts = new TimeSeries();
        In countsFile = new In(countsFilename);
        while (countsFile.hasNextLine()) {
            String nextLine = countsFile.readLine();
            String[] splitLine = nextLine.split(",");
            counts.put(Integer.valueOf(splitLine[0]), Double.valueOf(splitLine[1]));
        }

        words = new HashMap<>();
        In wordsFile = new In(wordsFilename);
        while (wordsFile.hasNextLine()) {
            String nextLine = wordsFile.readLine();
            String[] splitLine = nextLine.split("\t");//maybe shorten these two lines;
            //if words contains this word, get its TimeSeries;
            if (words.containsKey(splitLine[0])) {
                //if words doesn't contain this key, what would words.get(key) return? null?
                words.get(splitLine[0]).put(Integer.valueOf(splitLine[1]), Double.valueOf(splitLine[2]));
            }
            else{
                TimeSeries yearData = new TimeSeries();
                yearData.put(Integer.valueOf(splitLine[1]), Double.valueOf(splitLine[2]));
                words.put(splitLine[0], yearData);
            }
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        if (words.containsKey(word)) {
            return new TimeSeries(words.get(word), startYear, endYear);
        }
        else{
            return new TimeSeries();
        }
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        if (words.containsKey(word)) {
            TimeSeries yearData = words.get(word);
            List<Integer> years = yearData.years();
            int startYear = years.getFirst();
            int endYear = years.getLast();
            return new TimeSeries(yearData, startYear, endYear);
        }
        else{
            return new TimeSeries();
        }
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        TimeSeries totalCountCopy = new TimeSeries();
        List<Integer> years = counts.years();
        for (Integer year : years) {
            totalCountCopy.put(year, counts.get(year));
        }
        return totalCountCopy;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        if (words.containsKey(word)) {
            TimeSeries yearData = new TimeSeries(words.get(word), startYear, endYear);
            return yearData.dividedBy(counts);
        }
        return new TimeSeries();
    }


    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        if (words.containsKey(word)) {
            TimeSeries yearData = words.get(word);
            return words.get(word).dividedBy(counts);
        }
        return new TimeSeries();
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> wordCollects,
                                          int startYear, int endYear) {
        TimeSeries summed = new TimeSeries();
        for (String word : wordCollects) {
            if (words.containsKey(word)){
                summed = summed.plus(new TimeSeries(words.get(word), startYear, endYear));
            }
        }
        return summed.dividedBy(counts);
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> wordCollects) {
        TimeSeries summed = new TimeSeries();
        for (String word : wordCollects) {
            if (words.containsKey(word)){
                summed.plus(words.get(word));
            }
        }
        return summed.dividedBy(counts);
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
