import java.util.Random;

public class Vector {

    private static final Random rand     = new Random();
    private double[] data   = new double[11];

    public Vector() {
        for (int i = 0; i < data.length; i++) {
            data[i] = rand.nextInt(100) - 50;
        }
    }

    public Vector(int x0, int x1, int x2, int x3, int x4, int x5, int x6, int x7, int x8, int x9, int x10) {
        data[0] = x0;
        data[1] = x1;
        data[2] = x2;
        data[3] = x3;
        data[4] = x4;
        data[5] = x5;
        data[6] = x6;
        data[7] = x7;
        data[8] = x8;
        data[9] = x9;
        data[10] = x10;
    }

    public double getMNorm() {
        double temp;

        double max = Math.abs(data[0]);
        for (double datum : data) {
            temp = Math.abs(datum);
            if (temp > max)
                max = temp;
        }
        return max;
    }

    public double getLNorm() {
        double sum = 0;

        for (double datum : data) {
            sum = sum + Math.abs(datum);
        }
        return sum;
    }

    public double getENorm() {
        double evkl = 0;

        for (double datum : data) {
            evkl = evkl + Math.pow(datum, 2);
        }
        return (Math.sqrt(evkl));
    }

    public double getCoordinate(int i) {
        return (data[i]);
    }
}
