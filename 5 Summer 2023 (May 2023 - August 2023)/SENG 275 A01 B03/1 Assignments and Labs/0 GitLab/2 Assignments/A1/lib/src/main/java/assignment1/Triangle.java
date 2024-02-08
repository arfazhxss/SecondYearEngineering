package assignment1;

public class Triangle {
    public enum TriangleType {
        EQUILATERAL,
        ISOSCELES,
        SCALENE,
        INVALID
    }

    public static TriangleType categorize(int x, int y, int z) {
        if (x > 0 && y > 0 && z > 0 && x + y >= z && x + z >= y && y + z >= x) {
            if (x == y && y == z) {
                return TriangleType.EQUILATERAL;
            } else if (x == y || y == z || x == z) {
                return TriangleType.ISOSCELES;
            } else {
                return TriangleType.SCALENE;
            }
        } else {
            return TriangleType.INVALID;
        }
    }

    public static void main(String[] args) {
        int x = 5;
        int y = 5;
        int z = 5;

        TriangleType type = Triangle.categorize(x, y, z);

        System.out.println("Triangle type: " + type);
    }
}
