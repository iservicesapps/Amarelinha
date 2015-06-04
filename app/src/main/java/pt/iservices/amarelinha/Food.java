package pt.iservices.amarelinha;

/**
 * Created by mariocosme on 04/06/15.
 */
public class Food {

    private String name, image;
    private int price;

    public Food() {
        // default
    }

    public Food(String image, String name, int price) {
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
