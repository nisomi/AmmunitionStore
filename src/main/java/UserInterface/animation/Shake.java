package UserInterface.animation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shake {
    private TranslateTransition translateTransition;

    public Shake(Node node){
        translateTransition = new TranslateTransition(Duration.millis(100),node);
        translateTransition.setFromX(-5f);
        translateTransition.setByX(5f);
        node.setStyle("-fx-border-color: #da655c ; -fx-border-radius: 3; -fx-border-width: 1.5");
        translateTransition.setCycleCount(3);
        translateTransition.setAutoReverse(true);

    }

    public void playAnimation(){
        translateTransition.playFromStart();
    }
}
