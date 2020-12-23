package algorithms;

public class ClockAngle {

    public static void main(String[] args) {
        System.out.println(computeAngle(10, 42));
        System.out.println(computeAngle(12, 30));
        System.out.println(computeAngle(7, 45));
    }

    /**
     * computes the angle between the hour hand and the minute hand of any time
     * @param hour int
     * @param minutes int
     * @return angle of type double
     */
    public static double computeAngle(int hour, int minutes) {
        if (hour < 0 || hour > 23 || minutes < 0 || minutes >= 60) return -1;
        else if (hour >= 12) hour -= 12;
        double anglePerMinute = (double) 360 / 60, hourAnglePerMinute = (double) 360 / 12 / 60;
        return Math.abs((minutes * anglePerMinute) - ((hour * 60 + minutes) * hourAnglePerMinute));
    }
}
