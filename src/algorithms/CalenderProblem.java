package algorithms;

import java.util.LinkedList;
import java.util.Optional;

public class CalenderProblem {

    public static void main(String[] args) {
        LinkedList<String[]> input = new LinkedList<>();
        input.add(new String[]{"09:00", "10:30"});
        input.add(new String[]{"12:00", "13:00"});
        input.add(new String[]{"16:00", "18:00"});

        String[] range_1 = {"09:00", "20:00"};

        System.out.println(format(getPossibleTimes(input, range_1)));

        LinkedList<String[]> input_2 = new LinkedList<>();
        input_2.add(new String[]{"10:00", "11:30"});
        input_2.add(new String[]{"12:30", "14:30"});
        input_2.add(new String[]{"14:30", "15:00"});
        input_2.add(new String[]{"16:00", "17:00"});

        String[] range_2 = {"10:00", "18:30"};

        System.out.println(format(getPossibleTimes(input_2, range_2)));

        System.out.println(format(possibleTimes(input, range_1, input_2, range_2)));

        System.out.println("------");

        System.out.println(format(getPossibleTimes(getMergedEvents(input, range_1, input_2, range_2))));
    }

    public static LinkedList<String[]> possibleTimes(LinkedList<String[]> events_1, String[] time_window_1, LinkedList<String[]> events_2, String[] time_window_2) {
        LinkedList<String[]> result_times = new LinkedList<>();
        LinkedList<String[]> times_1 = getPossibleTimes(events_1, time_window_1);
        LinkedList<String[]> times_2 = getPossibleTimes(events_2, time_window_2);

        //find all possible intersections
        for (int i = 0; i < times_2.size(); i++) {
            String[] tmp_time = times_2.get(i);
            for (int a = 0; a < times_1.size(); a++) {
                Optional<String[]> intersection = getIntersectionOfTime(times_1.get(a), tmp_time);
                intersection.ifPresent(event -> result_times.add(intersection.get()));
            }
        }

        return result_times;
    }

    public static LinkedList<String[]> getMergedEvents(LinkedList<String[]> events_1, String[] time_window_1, LinkedList<String[]> events_2, String[] time_window_2) {
        LinkedList<String[]> result = getOneEventList(events_1, time_window_1, events_2, time_window_2);

        int i = 0;
        while (i < result.size() - 1) {
            String[] merged = mergeTwoTimes(result.get(i), result.get(i+1));
            if (merged != null) {
                System.out.println(format(result));
                result.remove(i+1);
                result.remove(i);
                result.add(i, merged);
            } else {
                i++;
            }
        }

        return result;
    }

    public static String[] mergeTwoTimes(String[] time_1, String[] time_2) {
        if (getTimeAsNumber(time_2[0]) <= getTimeAsNumber(time_1[1])) {
            return new String[]{time_1[0], time_2[1]};
        }
        return null;
    }

    /**
     * puts all events into one list without merging them
     * @param events_1
     * @param events_2
     * @return
     */
    public static LinkedList<String[]> getOneEventList(LinkedList<String[]> events_1, String[] time_window_1, LinkedList<String[]> events_2, String[] time_window_2) {
        LinkedList<String[]> result = (LinkedList<String[]>) events_1.clone();

        double a_0 = getTimeAsNumber(time_window_1[0]), a_1 = getTimeAsNumber(time_window_1[1]);
        double b_0 = getTimeAsNumber(time_window_2[0]), b_1 = getTimeAsNumber(time_window_2[1]);

        if (a_0 > b_0)
            result.add(0, new String[]{time_window_2[0], time_window_1[0]});
        else
            result.add(0, new String[]{time_window_1[0], time_window_2[0]});

        if (a_1 > b_1)
            result.add(result.size() - 1, new String[]{time_window_2[1], time_window_1[1]});
        else
            result.add(result.size() - 1, new String[]{time_window_1[1], time_window_2[1]});

        for (int i = 0; i < events_2.size(); i++) {
            for (int a = 0; a < result.size(); a++) {
                if (getTimeAsNumber(result.get(a)[0]) > getTimeAsNumber(events_2.get(i)[0])) {
                    result.add(a, events_2.get(i));
                    break;
                }
            }
        }

        return result;
    }

    /**
     * input: [0: '10:30', 1: '12:00'], [0: '10:00', 1: '11:00'] -> just get the later time of both index 0 and the earlier time of both index 1
     * output: ['10:30', '11:00']
     * @param time_1
     * @param time_2
     * @return
     */
    private static Optional<String[]> getIntersectionOfTime(String[] time_1, String[] time_2) {
        String[] result = new String[2];

        if (intersectionPossible(time_1, time_2)) {
            result[0] = (getTimeAsNumber(time_1[0]) > getTimeAsNumber(time_2[0])) ? time_1[0] : time_2[0];
            result[1] = (getTimeAsNumber(time_1[1]) < getTimeAsNumber(time_2[1])) ? time_1[1] : time_2[1];
            return Optional.of(result);
        }

        return Optional.empty();
    }

    /**
     * input: 03:15 -> 3.15, 12:30 -> 12,3
     * @param time
     * @return
     */
    private static double getTimeAsNumber(String time) {
        return Double.parseDouble(time.replace(':', '.'));
    }

    private static boolean intersectionPossible(String[] time_1, String[] time_2) {
        double a_1 = getTimeAsNumber(time_1[0]), b_1 = getTimeAsNumber(time_1[1]);
        double a_2 = getTimeAsNumber(time_2[0]), b_2 = getTimeAsNumber(time_2[1]);

        if (a_1 <= a_2 && a_2 <= b_1 || a_1 <= b_2 && b_2 <= b_1)
            return true;
        else
            return false;
    }

    /**
     * returns a list of all possible time windows out of the given events
     * input: [['09:00', '10:30']['12:00', '13:00']['16:00', '18:00']], ['9:00', '20:00']
     * output: [['10:30', '12:00']['13:00', '16:00']['18:00', '20:00']]
     * @param events
     * @param time_window
     * @return
     */
    private static LinkedList<String[]> getPossibleTimes(LinkedList<String[]> events, String[] time_window) {
        LinkedList<String[]> result_times = new LinkedList<>();

        if (isTimeBetween(time_window[0], events.get(0)[0]))
            result_times.add(new String[]{time_window[0], events.get(0)[0]});

        for (int i = 0; i < events.size() - 1; i++) {
            if (isTimeBetween(events.get(i)[1], events.get(i+1)[0]))
                result_times.add(new String[]{events.get(i)[1], events.get(i+1)[0]});
        }

        if (isTimeBetween(events.get(events.size() - 1)[1], time_window[1]))
            result_times.add(new String[]{events.get(events.size() - 1)[1], time_window[1]});

        return result_times;
    }

    private static LinkedList<String[]> getPossibleTimes(LinkedList<String[]> events) {
        LinkedList<String[]> result_times = new LinkedList<>();

        for (int i = 0; i < events.size() - 1; i++) {
            if (isTimeBetween(events.get(i)[1], events.get(i+1)[0]))
                result_times.add(new String[]{events.get(i)[1], events.get(i+1)[0]});
        }

        return result_times;
    }

    private static boolean isTimeBetween(String time_1, String time_2) {
        if (time_1.equals(time_2))
            return false;
        else
            return true;
    }

    private static String format(LinkedList<String[]> input) {
        String result = "[";
        for (String[] s : input)
            result += "['" + s[0] + "', '" + s[1] + "']";
        result += "]";
        return result;
    }
}

