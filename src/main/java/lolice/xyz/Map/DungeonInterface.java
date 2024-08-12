package lolice.xyz.Map;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;

public class DungeonInterface extends Application {
    private static boolean isJavaFXLaunched = false;
    private static final Object lock = new Object();
    private static Stage primaryStage;



    public static void startJavaFX() {
        if (!isJavaFXLaunched) {
            new Thread(() -> {
                synchronized (lock) {
                    Application.launch(DungeonInterface.class);
                    isJavaFXLaunched = true;
                    lock.notifyAll();
                }
            }).start();
        }
    }

    private static DirectionClickListener directionClickListener;

    public interface DirectionClickListener {
        void onDirectionClick(String direction);
    }

    public static void setDirectionClickListener(DirectionClickListener listener) {
        directionClickListener = listener;
    }

    public static void startInterface() {
        startJavaFX();
        synchronized (lock) {
            while (!isJavaFXLaunched) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Platform.runLater(() -> {
            if (primaryStage == null) {
                primaryStage = new Stage();
                try {
                    new DungeonInterface().start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                primaryStage.show();
            }
        });
    }


    @Override
    public void start(Stage stage) throws Exception {
        Image dungeon = new Image("file:src/main/resources/dungeon.jpg");

        ImageView imageView = new ImageView(dungeon);

        Rectangle highlight = new Rectangle();
        highlight.setFill(Color.RED);
        highlight.setOpacity(0.1);
        highlight.setVisible(false);
        highlight.setMouseTransparent(true);

        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            double x = event.getX();
            double y = event.getY();
            String direction = handleClick(x, y);
            if (directionClickListener != null) {
                directionClickListener.onDirectionClick(direction);
            }
        });

        imageView.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {
            double x = event.getX();
            double y = event.getY();
            handleMouseOver(x, y, highlight);
        });

        Pane root = new Pane(imageView, highlight);

        Scene scene = new Scene(root, 1024, 1024);
        stage.setScene(scene);
        stage.setTitle("Dungeon Interface");
        stage.show();
    }

    private String handleClick(double x, double y) {
        if (x >= 40 && x <= 261 && y >= 392 && y <= 762) {
            return "Left";
        } else if (x >= 796 && x <= 1017 && y >= 392 && y <= 762) {
            return "Right";
        } else if (x >= 326 && x <= 761 && y >= 119 && y <= 651) {
            return "Up";
        } else if (x >= 370 && x <= 712 && y >= 838 && y <= 995) {
            return "Down";
        } else {
            return "null";
        }
    }

    private void handleMouseOver(double x, double y, Rectangle highlight) {
        if (x >= 40 && x <= 261 && y >= 392 && y <= 762) {
            highlight.setX(40);
            highlight.setY(392);
            highlight.setWidth(221);
            highlight.setHeight(370);
            highlight.setVisible(true);
        } else if (x >= 796 && x <= 1017 && y >= 392 && y <= 762) {
            highlight.setX(796);
            highlight.setY(392);
            highlight.setWidth(221);
            highlight.setHeight(370);
            highlight.setVisible(true);
        } else if (x >= 326 && x <= 761 && y >= 119 && y <= 651) {
            highlight.setX(326);
            highlight.setY(119);
            highlight.setWidth(435);
            highlight.setHeight(532);
            highlight.setVisible(true);
        } else if (x >= 370 && x <= 712 && y >= 838 && y <= 995) {
            highlight.setX(370);
            highlight.setY(838);
            highlight.setWidth(342);
            highlight.setHeight(157);
            highlight.setVisible(true);
        } else {
            highlight.setVisible(false);
        }
    }
}