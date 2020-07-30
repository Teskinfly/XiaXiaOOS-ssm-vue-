enum Hurt{
    LITTLE,
    MILD,
    MUCH
}
public class enumDemo {
    public static void main(String[] args) {
        for (Hurt hurt:Hurt.values()) {
            System.out.println(hurt);
        }
    }
}
