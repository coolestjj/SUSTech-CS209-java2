/**
 *
 *  Due to JDK version problems, this javafx file cannot run in this Project.
 *  But the answer is still shown below as annotations
 */

//import javafx.animation.AnimationTimer;
//import javafx.application.Application;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.stage.Stage;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class SpaceInvaderGame extends Application {
//
//    private Pane root = new Pane();
//
//    private Sprite player = new Sprite(300, 750, 40, 40, "player", Color.BLUE);
//
//    private Parent createContent() {
//        root.setPrefSize(600, 800);
//
//        root.getChildren().add(player);
//
//        AnimationTimer timer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                update();
//            }
//        };
//
//        timer.start();
//
//        createEnemies();
//
//        return root;
//    }
//
//    private void createEnemies() {
//        for (int i = 0; i < 5; i++) {
//            Sprite s = new Sprite(90 + i*100, 150, 30, 30, "enemy", Color.RED);
//
//            root.getChildren().add(s);
//        }
//    }
//
//    private List<Sprite> sprites() {
//        return root.getChildren().stream().map(n -> (Sprite)n).collect(Collectors.toList());
//    }
//
//    private void update() {
//
//        sprites().forEach(s -> {
//            switch (s.type) {
//
//                case "enemybullet":
//                    // enemy's bullet moves down
//                    s.moveDown();
//
//                    // enemy's bullet hits the player
//                    if (s.getBoundsInParent().intersects(player.getBoundsInParent())) {
//                        player.dead = true; // player is dead
//                        s.dead = true; // bullet is dead
//                    }
//
//                    break;
//
//                case "playerbullet":
//
//                    // TODO player's bullet should move up
//                    s.moveUp();
//                    // TODO should also check whether the bullet hits each enemy
//                    List<Sprite> enemies = new ArrayList<>();
//                    for (Sprite sprite : sprites()) {
//                        if (sprite.type.equals("enemy")) {
//                            enemies.add(sprite);
//                        }
//                    }
//
//                    for (Sprite enemy : enemies) {
//                        if (s.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
//                            enemy.dead = true;
//                            s.dead = true;
//                        }
//                    }
//                    break;
//
//                case "enemy":
//                    // TODO enemies should shoot with random intervals
//                    int i = (int) (Math.random() * 100);
//                    if (i > 97) {
//                        shoot(s);
//                    }
//                    break;
//            }
//        });
//
//        // remove dead sprites from the screen
//        root.getChildren().removeIf(n -> {
//            Sprite s = (Sprite) n;
//            return s.dead;
//        });
//
//    }
//
//    private void shoot(Sprite who) {
//        // a rectangle with width 5, which looks like a bullet
//        Sprite s = new Sprite((int) who.getTranslateX() + 20, (int) who.getTranslateY(), 5, 20, who.type + "bullet", Color.BLACK);
//
//        root.getChildren().add(s);
//    }
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        Scene scene = new Scene(createContent());
//
//        scene.setOnKeyPressed(e -> {
//            switch (e.getCode()) {
//                case LEFT:
//                    player.moveLeft();
//                    break;
//                case RIGHT:
//                    player.moveRight();
//                    break;
//                case UP:
//                    player.moveUp();
//                    break;
//                case DOWN:
//                    player.moveDown();
//                    break;
//                case SPACE:
//                    shoot(player);
//                    break;
//            }
//        });
//
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    private static class Sprite extends Rectangle {
//        boolean dead = false;
//        final String type;
//
//        Sprite(int x, int y, int w, int h, String type, Color color) {
//            super(w, h, color);
//
//            this.type = type;
//            setTranslateX(x);
//            setTranslateY(y);
//        }
//
//        void moveLeft() {
//            setTranslateX(getTranslateX() - 5);
//        }
//
//        void moveRight() {
//            setTranslateX(getTranslateX() + 5);
//        }
//
//        void moveUp() {
//            setTranslateY(getTranslateY() - 5);
//        }
//
//        void moveDown() {
//            setTranslateY(getTranslateY() + 5);
//        }
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}