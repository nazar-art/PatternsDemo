package patterns.builder;

class ContentOfProduct {
    private int portionSize;
    private int portions;
    private int calories;
    private int fat;
    private int sodium;
    private int carbohydrate;

    public static class Builder {
        // required
        private int portionSize;
        private int portions;
        // optional
        private int calories;
        private int fat;
        private int sodium;
        private int carbohydrate;

        public Builder(int portionSize, int portions) {
            this.portionSize = portionSize;
            this.portions = portions;
        }

        public Builder calories(int val) {
            calories = val;
            return this;
        }

        public Builder fat(int val) {
            fat = val;
            return this;
        }

        public Builder sodium(int val) {
            sodium = val;
            return this;
        }

        public Builder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }

        public ContentOfProduct build() {
            return new ContentOfProduct(this);
        }
    }

    public ContentOfProduct(Builder builder) {
        portionSize = builder.portionSize;
        portions = builder.portions;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }

    @Override
    public String toString() {
        return "ContentOfProduct{" +
                "portionSize=" + portionSize +
                ", portions=" + portions +
                ", calories=" + calories +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", carbohydrate=" + carbohydrate +
                '}';
    }
}

public class BuilderDemo {
    public static void main(String[] args) {
        ContentOfProduct cocaCola = new ContentOfProduct.Builder(300, 8)
                .calories(100).sodium(35).carbohydrate(27).build();
        System.out.println(cocaCola);
    }
}