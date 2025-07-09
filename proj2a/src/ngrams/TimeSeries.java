package ngrams;

import java.util.*;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    /** If it helps speed up your code, you can assume year arguments to your NGramMap
     * are between 1400 and 2100. We've stored these values as the constants
     * MIN_YEAR and MAX_YEAR here. */
    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        List<Integer> yearKeys = ts.years();
        int startIndex = Collections.binarySearch(yearKeys, startYear);
        for (int i = startIndex; i < yearKeys.size(); i++) {
            int year = yearKeys.get(i);
            if (year > endYear) {
                break;
            }
            super.put(year, ts.get(year));
        }
    }

    /**
     *  Returns all years for this time series in ascending order.
     */
    public List<Integer> years() {
        return new ArrayList<>(this.keySet());
    }

    /**
     *  Returns all data for this time series. Must correspond to the
     *  order of years().
     */
    public List<Double> data() {
        List<Double> data = new ArrayList<>();
        List<Integer> years = this.years();
        for (Integer yearKey : years) {
            data.add(this.get(yearKey));
        }
        return data;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        Set<Integer> yearsMerged = new HashSet<>(this.keySet());
        yearsMerged.addAll(ts.keySet());
        if (yearsMerged.isEmpty()){
            return new TimeSeries();
        }
        TimeSeries total = new TimeSeries();
        for (Integer year : yearsMerged) {
            if (this.get(year) != null && ts.get(year) != null){
                total.put(year, this.get(year) + ts.get(year));
            }
            else if (this.get(year) != null){
                total.put(year, this.get(year));
            }
            else{
                total.put(year, ts.get(year));
            }
        }
        return total;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        TimeSeries quo = new TimeSeries();
        List<Integer> years = this.years();
        for (Integer yearKey : years){
            if (!ts.containsKey(yearKey)){
                throw new IllegalArgumentException();
            }
            else{
                quo.put(yearKey, this.get(yearKey) / ts.get(yearKey));
            }
        }
        return quo;
    }
}
