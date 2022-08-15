public class main {
    public static void main(String args[]) {
        Vector vect = new Vector();
        for (int i = 0; i < 11; i++) {
            System.out.println("Координата " + i + ": " + vect.getCoordinate(i) + ".");
        }
        System.out.println("MNorm: " + vect.getMNorm());
        System.out.println("LNorm: " + vect.getLNorm());
        System.out.println("ENorm: " + vect.getENorm());
    }
}
