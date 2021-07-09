package controller;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class DeckSuffleController extends Transition {

    private ImageView imageView;

    public DeckSuffleController(ImageView imageView) {
        this.imageView = imageView;
        setCycleDuration(Duration.millis(500));
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int) Math.floor(v * 5);
        imageView.setImage( new Image("/images/shuffle/" + frame + ".png"));
    }

}
