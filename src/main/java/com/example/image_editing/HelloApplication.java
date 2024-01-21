package com.example.image_editing;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {

        //gifs zone
        ImageView gif = new ImageView(new Image("file:icons\\options.gif"));
        ImageView icon = new ImageView(new Image("file:icons\\options.png"));
        ImageView gif1 = new ImageView(new Image("file:icons\\image-editing.gif"));
        ImageView icon1 = new ImageView(new Image("file:icons\\image-editing.png"));
        //gifs zone end
    boolean candrag = true;

    Pane bottomcontent;
    ImageView image;
    int i = 1;
    String savepath = null;
    File outputFile = null;

    Image fxImage;

    PixelReader pixelReader;

    WritableImage wImage;

    PixelWriter pixelWriter;

    Canvas canvas;//doodling zone

    StackPane sp;//zone with photo and doodling

    HBox brightness = Brightness(); //brightness zone

    HBox saturation = Saturation();

    HBox hue = Hue();

    HBox contrast = Contrast();

    ColorAdjust colorAdjust;//brightness adjust
    ColorAdjust colorAdjust1;//Satruation adjust
    ColorAdjust colorAdjust2;//hue adjust
    ColorAdjust colorAdjust3;//contrast adjust
    HBox adjust = Adjust();

    HBox filters;

    HBox options = Options();

    {
        //fxImage = image.getImage();
        //pixelReader = fxImage.getPixelReader();
        //wImage = new WritableImage(
          //      (int) fxImage.getWidth(),
            //    (int) fxImage.getHeight()
        //);
        //pixelWriter = wImage.getPixelWriter();
    }
    public HBox Brightness()
    {
        ImageView icon = new ImageView(new Image("file:icons\\screen.png"));
        ImageView exit = new ImageView(new Image("file:icons\\button.png"));
        icon.setFitWidth(50);
        icon.setFitHeight(50);
        exit.setFitWidth(50);
        exit.setFitHeight(50);
        Button Exit = new Button();
        Exit.setStyle("-fx-background-color: transparent");
        Exit.setGraphic(exit);
        Exit.setOnAction(e->{
            bottomcontent.getChildren().set(0,adjust);
        });
        VBox ExitWrapper = new VBox(Exit);
        ExitWrapper.setPadding(new Insets(15,0,0,15));
        VBox iconwrapper = new VBox(icon);

        iconwrapper.setPadding(new Insets(75,0,0,0));
        HBox box = new HBox();
        Slider slider = new Slider();

        // Set the range of the slider
        slider.setMin(-1);
        slider.setMax(1);
        slider.setValue(0);
        slider.setPrefWidth(500);
        iconwrapper.setOnMouseClicked(e->{
        slider.setValue(0);
        });
        LinearGradient gradient = new LinearGradient(
                0, // start X
                0, // start Y
                1, // end X
                0, // end Y
                true, // proportional
                javafx.scene.paint.CycleMethod.NO_CYCLE, // cycle colors
                new Stop(0, Color.BLACK), // stops
                new Stop(1, Color.WHITE)
        );

        // Create the background fill
        BackgroundFill fill = new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY);

        // Set the background of the slider
        slider.setBackground(new Background(fill));
        Label label = new Label("Brightness");
        label.setStyle("-fx-text-fill: white");
        HBox sliderwrapper = new HBox(label,slider);
        sliderwrapper.setPadding(new Insets(90,0,0,110));
        box.getChildren().addAll(ExitWrapper,sliderwrapper,iconwrapper);
        colorAdjust = new ColorAdjust();

        slider.valueProperty().addListener((observable,oldvalue,newvalue)->{
            double brightnessFactor = slider.getValue(); // Change this to the value you want
            colorAdjust.setBrightness(brightnessFactor);
            // Apply the ColorAdjust effect to the ImageView

        });

        return box;
    }

    public HBox Saturation()
    {
        ImageView icon = new ImageView(new Image("file:icons\\saturation.png"));
        ImageView exit = new ImageView(new Image("file:icons\\button.png"));
        icon.setFitWidth(50);
        icon.setFitHeight(50);
        exit.setFitWidth(50);
        exit.setFitHeight(50);
        Button Exit = new Button();
        Exit.setStyle("-fx-background-color: transparent");
        Exit.setGraphic(exit);
        Exit.setOnAction(e->{
            bottomcontent.getChildren().set(0,adjust);
        });
        VBox ExitWrapper = new VBox(Exit);
        ExitWrapper.setPadding(new Insets(15,0,0,15));
        VBox iconwrapper = new VBox(icon);

        iconwrapper.setPadding(new Insets(75,0,0,0));
        HBox box = new HBox();
        Slider slider = new Slider();

        // Set the range of the slider
        slider.setMin(-1);
        slider.setMax(1);
        slider.setValue(0);
        slider.setPrefWidth(500);
        iconwrapper.setOnMouseClicked(e->{
            slider.setValue(0);
        });
        // Customize the slider using CSS
        slider.setStyle("-fx-control-inner-background: #BDBDBD;");
        Label label = new Label("Saturation");
        label.setStyle("-fx-text-fill: white");
        HBox sliderwrapper = new HBox(label,slider);
        sliderwrapper.setPadding(new Insets(90,0,0,110));
        box.getChildren().addAll(ExitWrapper,sliderwrapper,iconwrapper);
        colorAdjust1 = new ColorAdjust();

        slider.valueProperty().addListener((observable,oldvalue,newvalue)->{
            double brightnessFactor = slider.getValue(); // Change this to the value you want
            colorAdjust1.setSaturation(brightnessFactor);
            // Apply the ColorAdjust effect to the ImageView

        });

        return box;
    }

    public HBox Hue()
    {
        ImageView icon = new ImageView(new Image("file:icons\\wheel.png"));
        ImageView exit = new ImageView(new Image("file:icons\\button.png"));
        icon.setFitWidth(50);
        icon.setFitHeight(50);
        exit.setFitWidth(50);
        exit.setFitHeight(50);
        Button Exit = new Button();
        Exit.setStyle("-fx-background-color: transparent");
        Exit.setGraphic(exit);
        Exit.setOnAction(e->{
            bottomcontent.getChildren().set(0,adjust);
        });
        VBox ExitWrapper = new VBox(Exit);
        ExitWrapper.setPadding(new Insets(15,0,0,15));
        VBox iconwrapper = new VBox(icon);

        iconwrapper.setPadding(new Insets(75,0,0,0));
        HBox box = new HBox();
        Slider slider = new Slider();

        // Set the range of the slider
        slider.setMin(-1);
        slider.setMax(1);
        slider.setValue(0);
        slider.setPrefWidth(500);
        iconwrapper.setOnMouseClicked(e->{
            slider.setValue(0);
        });
        // Customize the slider using CSS
        slider.setStyle("-fx-control-inner-background: #BDBDBD;");
        Label label = new Label("Saturation");
        label.setStyle("-fx-text-fill: white");
        HBox sliderwrapper = new HBox(label,slider);
        sliderwrapper.setPadding(new Insets(90,0,0,110));
        box.getChildren().addAll(ExitWrapper,sliderwrapper,iconwrapper);
        colorAdjust2 = new ColorAdjust();

        slider.valueProperty().addListener((observable,oldvalue,newvalue)->{
            double brightnessFactor = slider.getValue(); // Change this to the value you want
            colorAdjust2.setHue(brightnessFactor);
            // Apply the ColorAdjust effect to the ImageView

        });

        return box;
    }

    public HBox Contrast()
    {
        ImageView icon = new ImageView(new Image("file:icons\\contrast.png"));
        ImageView exit = new ImageView(new Image("file:icons\\button.png"));
        icon.setFitWidth(50);
        icon.setFitHeight(50);
        exit.setFitWidth(50);
        exit.setFitHeight(50);
        Button Exit = new Button();
        Exit.setStyle("-fx-background-color: transparent");
        Exit.setGraphic(exit);
        Exit.setOnAction(e->{
            bottomcontent.getChildren().set(0,adjust);
        });
        VBox ExitWrapper = new VBox(Exit);
        ExitWrapper.setPadding(new Insets(15,0,0,15));
        VBox iconwrapper = new VBox(icon);

        iconwrapper.setPadding(new Insets(75,0,0,0));
        HBox box = new HBox();
        Slider slider = new Slider();

        // Set the range of the slider
        slider.setMin(-1);
        slider.setMax(1);
        slider.setValue(0);
        slider.setPrefWidth(500);
        iconwrapper.setOnMouseClicked(e->{
            slider.setValue(0);
        });
        // Customize the slider using CSS
        slider.setStyle("-fx-control-inner-background: #BDBDBD;");
        Label label = new Label("Saturation");
        label.setStyle("-fx-text-fill: white");
        HBox sliderwrapper = new HBox(label,slider);
        sliderwrapper.setPadding(new Insets(90,0,0,110));
        box.getChildren().addAll(ExitWrapper,sliderwrapper,iconwrapper);
        colorAdjust3 = new ColorAdjust();

        slider.valueProperty().addListener((observable,oldvalue,newvalue)->{
            double brightnessFactor = slider.getValue(); // Change this to the value you want
            colorAdjust3.setContrast(brightnessFactor);
            // Apply the ColorAdjust effect to the ImageView

        });

        return box;
    }
    public HBox Adjust() {
        ImageView img = new ImageView(new Image("file:icons\\sun.png"));
        ImageView icon = new ImageView(new Image("file:icons\\saturation.png"));
        ImageView icon1 = new ImageView(new Image("file:icons\\wheel.png"));
        ImageView icon2 = new ImageView(new Image("file:icons\\contrast.png"));
        ImageView exit = new ImageView(new Image("file:icons\\button.png"));
        exit.setFitWidth(50);
        exit.setFitHeight(50);
        img.setFitHeight(80);
        img.setFitWidth(80);
        icon.setFitHeight(80);
        icon.setFitWidth(80);
        icon1.setFitHeight(80);
        icon1.setFitWidth(80);
        icon2.setFitHeight(80);
        icon2.setFitWidth(80);
        Button Exit = new Button();
        Exit.setGraphic(exit);
        Exit.setStyle("-fx-background-color: transparent;");
        Exit.setOnMouseEntered(e->{
            exit.setTranslateY(5.0);
        });
        Exit.setOnMouseExited(e->{
            exit.setTranslateY(0);
        });
        Exit.setOnAction(e->{
            bottomcontent.getChildren().set(0,options);
        });
        Button brightnessbutton = new Button();
        buttonsetup(brightnessbutton,img,brightness);
        Button saturationbutton = new Button();
        buttonsetup(saturationbutton,icon,saturation);
        Button huebutton = new Button();
        buttonsetup(huebutton,icon1,hue);
        Button contrastbutton = new Button();
        buttonsetup(contrastbutton,icon2,contrast);
        HBox box = new HBox(Exit,brightnessbutton,saturationbutton,huebutton,contrastbutton);
        box.setSpacing(50);
        box.setPadding(new Insets(45,0,0,50));
        return box;

    }

    public HBox Options()
    {
        icon.setFitHeight(80);
        icon.setFitWidth(80);
        gif.setFitHeight(110);
        gif.setFitWidth(110);
        icon1.setFitHeight(80);
        icon1.setFitWidth(80);
        gif1.setFitHeight(110);
        gif1.setFitWidth(110);
        Button adjustzone = new Button();
        buttonsetup(adjustzone,icon,gif,adjust);
        Button filterszone = new Button();
        buttonsetup(filterszone,icon1,gif1,filters);

        HBox box = new HBox(adjustzone,filterszone);
        box.setSpacing(100);
        return box;

    }
    @Override
    public void start(Stage primaryStage) throws IOException
    {
        image = new ImageView();
        colorAdjust2.setInput(colorAdjust3);
        colorAdjust1.setInput(colorAdjust2);
        colorAdjust.setInput(colorAdjust1);
        image.setEffect(colorAdjust);
        Label label = new Label("\nDrag and drop a file here\nor click me and select a file ");
        ImageView icon = new ImageView(new Image("file:icons\\picture.png"));
        icon.setFitHeight(100);
        icon.setFitWidth(100);
        HBox iconwrapper = new HBox(icon);
        iconwrapper.setPadding(new Insets(20,0,0,45));
        label.setAlignment(Pos.CENTER);
        label.setUnderline(true);
        label.setFont(new Font("Montserrat",16));
        label.setStyle("-fx-text-fill: lightblue");
        label.setOnMouseEntered(e->{
            label.setStyle("-fx-text-fill: #2288FF");
        });
        label.setOnMouseExited(e->{
            label.setStyle("-fx-text-fill: lightblue");
        });
        Button clear = new Button("Clear");
        buttonsetup(clear);
        Button Save = new Button("Save Image");
        buttonsetup(Save);
        Button folderpath = new Button("Select Path for Saved Image");
        buttonsetup(folderpath);
        folderpath.setOnAction(e->{
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File dir = directoryChooser.showDialog(primaryStage);
            if (dir != null) {
                savepath = dir.toURI().toString();
                savepath = savepath.substring(6);
                folderpath.setStyle("-fx-background-color: orange;-fx-text-fill: black;-fx-border-color: white; -fx-border-width: 1;-fx-background-radius: 30; -fx-border-radius: 30;");
                folderpath.setOnMouseExited(ee->{
                    folderpath.setStyle("-fx-background-color: orange;-fx-text-fill: black;-fx-border-color: white; -fx-border-width: 1;-fx-background-radius: 30; -fx-border-radius: 30;");
                });
            }
        });
        HBox topcontent = new HBox(clear,Save,folderpath);
        topcontent.setSpacing(50);
        StackPane top = new StackPane(topcontent);
        bottomcontent = new Pane(new Pane());
        bottomcontent.setMinHeight(200);
        bottomcontent.setMaxWidth(1000);;
        StackPane bottom = new StackPane(bottomcontent);
        Pane beforeimage = new Pane();
        beforeimage.setMaxWidth(220);
        VBox vbox = new VBox(label,iconwrapper);
        vbox.setPadding(new Insets(0,0,0,15));
        beforeimage.getChildren().add(vbox);
        beforeimage.setStyle("-fx-border-color: white; -fx-border-width: 1;-fx-background-radius: 30; -fx-border-radius: 30;");
        bottomcontent.setStyle("-fx-border-color: white; -fx-border-width: 1;-fx-background-radius: 30; -fx-border-radius: 30;");
        beforeimage.setMaxHeight(230);
        bottom.setMinHeight(200);
        top.setMinHeight(100);
        bottom.setStyle("-fx-background-color: #141421");
        top.setStyle("-fx-background-color: #141421");
        BorderPane bp = new BorderPane();
        StackPane center = new StackPane(beforeimage);
        center.setStyle("-fx-border-color: darkgrey; -fx-border-width: 3");
        StackPane.setAlignment(image, Pos.CENTER);
        bp.setTop(top);
        bp.setCenter(center);
        bp.setBottom(bottom);
        center.setStyle("-fx-background-color: #141421");
        center.setMinHeight(400);
       beforeimage.setOnDragOver(event -> {
            if (event.getGestureSource() != beforeimage && event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        bp.getCenter().setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles() && candrag) {
                String filepath = db.getFiles().get(0).getAbsolutePath();
                image.setImage(new Image("file:" + filepath));
                image.setFitWidth(image.getImage().getWidth()/2);
                image.setFitHeight(image.getImage().getHeight()/2);
                FileHandler.DuplicateImageAndSave(filepath);
                canvas = new Canvas();
                canvasSetUp(canvas,Color.BLACK,20);
                sp = new StackPane(image,canvas);
                center.getChildren().set(0,sp);
                bottomcontent.getChildren().set(0,options);
                success = true;
                candrag=false;
            }

            event.setDropCompleted(success);

            event.consume();
        });
        label.setOnMouseClicked(e -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                Image im = new Image(file.toURI().toString());
                image.setFitWidth(im.getWidth()/2);
                image.setFitHeight(im.getHeight()/2);
                image.setImage(im);
                center.getChildren().set(0,image);
                bottomcontent.getChildren().set(0,options);
            }
        });
        iconwrapper.setOnMouseClicked(e -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                Image im = new Image(file.toURI().toString());
                image.setFitWidth(im.getWidth()/2);
                image.setFitHeight(im.getHeight()/2);
                image.setImage(im);
                center.getChildren().set(0,image);
                bottomcontent.getChildren().set(0,options);
            }
        });
        clear.setOnAction(e-> {
            try{
            center.getChildren().set(0,beforeimage);
            reset();
            }catch (Exception ite){
                System.out.println("caught");
            }
            bottomcontent.getChildren().set(0,new Pane());
        });
        Save.setOnAction(e->{
            if(savepath == null)
            {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                File dir = directoryChooser.showDialog(primaryStage);
                if (dir != null) {
                    savepath = dir.toURI().toString();
                    savepath = savepath.substring(6);
                    folderpath.setStyle("-fx-background-color: orange;-fx-text-fill: black;-fx-border-color: white; -fx-border-width: 1;-fx-background-radius: 30; -fx-border-radius: 30;");
                    folderpath.setOnMouseExited(ee->{
                        folderpath.setStyle("-fx-background-color: orange;-fx-text-fill: black;-fx-border-color: white; -fx-border-width: 1;-fx-background-radius: 30; -fx-border-radius: 30;");
                    });
                }
            }
            try{
                ImageView copy = new ImageView(image.getImage());
                copy.setEffect(image.getEffect());
                WritableImage snapshot = copy.snapshot(new SnapshotParameters(), null);
                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(snapshot, null);

                // Create a file to save the image
                String[] names = copy.getImage().getUrl().split("/");

                System.out.println(savepath + names[names.length-1]);
                outputFile = new File(savepath + "\\" + names[names.length-1]);
                i = 1;
                if(outputFile.exists()){

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirm choice");
                    alert.setHeaderText("File like this already exists in the folder\nOverride file or create separate one?");
                    alert.resultProperty().addListener((observable, oldValue, newValue) -> {
                        if (newValue == ButtonType.OK) {
                            while(outputFile.exists())
                            {
                                outputFile = new File(savepath + "\\(" + i + ")" + names[names.length-1]);
                                i++;
                            }
                        }
                    });


                    alert.showAndWait();

                }
                try {
                    // Write the BufferedImage to the file
                    ImageIO.write(bufferedImage, "png", outputFile);
                } catch (IOException ee) {
                    System.err.println("Error while saving image: " + ee.getMessage());
                }}catch (NullPointerException npe) {
                    System.out.println("No Image yet");}
        });
        File f = new File("C:\\Users\\Perdorues\\IdeaProjects\\Image_editing\\style.css");
        Scene scene = new Scene(bp,1000,800);
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        primaryStage.setTitle("Drag Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void buttonsetup(Button button , ImageView icon , HBox gotozone)
    {
        button.setGraphic(icon);
        button.setStyle("-fx-background-color: transparent;-fx-border-color: white;" +
                " -fx-border-width: 1;-fx-background-radius: 30; -fx-border-radius: 30;");
        button.setPrefSize(100,100);
        button.setOnAction(e->{
            bottomcontent.getChildren().set(0,gotozone);
        });
        button.setOnMouseEntered(e->{
            button.setStyle("-fx-background-color: rgb(255,255,255,0.8);-fx-border-color: white;" +
                    " -fx-border-width: 1;-fx-background-radius: 30; -fx-border-radius: 30;");
        });
        button.setOnMouseExited(e->{
            button.setStyle("-fx-background-color: transparent;-fx-border-color: white;" +
                    " -fx-border-width: 1;-fx-background-radius: 30; -fx-border-radius: 30;");
        });

    }

    public void buttonsetup(Button button)
    {
        button.setPrefSize(180,50);
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: white;-fx-border-color: white; -fx-border-width: 1;-fx-background-radius: 30; -fx-border-radius: 30;");
        button.setOnMouseEntered(e->{
            button.setStyle("-fx-background-color: rgb(255,255,255,0.5); -fx-text-fill: white;-fx-border-color: white; -fx-border-width: 1;-fx-background-radius: 30; -fx-border-radius: 30;");
        });
        button.setOnMouseExited(e->{
            button.setStyle("-fx-background-color: transparent; -fx-text-fill: white;-fx-border-color: white; -fx-border-width: 1;-fx-background-radius: 30; -fx-border-radius: 30;");
        });
    }

    public void buttonsetup(Button button , ImageView icon , ImageView gif , HBox gotozone)
    {
        button.setGraphic(icon);
        button.setStyle("-fx-background-color: white;-fx-border-color: white;" +
                " -fx-border-width: 1;-fx-background-radius: 30; -fx-border-radius: 30;");
        button.setPrefSize(135,120);
        button.setOnAction(e->{
            bottomcontent.getChildren().set(0,gotozone);
        });
        button.setOnMouseEntered(e->{
            button.setStyle("-fx-background-color: white;-fx-border-color: white;" +
                    " -fx-border-width: 1;-fx-background-radius: 30; -fx-border-radius: 30;");
            button.setGraphic(gif);
        });
        button.setOnMouseExited(e->{
            button.setStyle("-fx-background-color: white;-fx-border-color: white;" +
                    " -fx-border-width: 1;-fx-background-radius: 30; -fx-border-radius: 30;");
            button.setGraphic(icon);
        });
    }

    public void reset()
    {
        hue = Hue();
        contrast = Contrast();
        brightness = Brightness();
        saturation = Saturation();
        adjust = Adjust();
        options = Options();
        colorAdjust2.setInput(colorAdjust3);
        colorAdjust1.setInput(colorAdjust2);
        colorAdjust.setInput(colorAdjust1);
        image.setEffect(colorAdjust);
    }

    public void canvasSetUp(Canvas canvas,Color color,int width)
    {
        canvas.setHeight(image.getFitHeight());
        canvas.setWidth(image.getFitWidth());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(color);
        gc.setLineWidth(width);

        // Set up the mouse events
        canvas.setOnMousePressed(e -> {
            gc.beginPath();
            gc.moveTo(e.getX(), e.getY());
            gc.stroke();
        });

        canvas.setOnMouseDragged(e -> {
            gc.lineTo(e.getX(), e.getY());
            gc.stroke();
        });

        canvas.setOnMouseReleased(e -> {
            gc.closePath();
        });

    }

    public StackPane copyStackPane(StackPane stackPane)
    {
        WritableImage snapshot = new WritableImage((int) stackPane.getWidth(), (int) stackPane.getHeight());
        stackPane.snapshot(new SnapshotParameters(), snapshot);

        // Create a new ImageView with the snapshot
        ImageView snapshotView = new ImageView(snapshot);


        // Resize the ImageView
        snapshotView.setFitWidth(image.getImage().getWidth());
        snapshotView.setFitHeight(image.getImage().getHeight());
        snapshotView.setPreserveRatio(true);

        // Create a new StackPane to hold the resized ImageView
        StackPane resizedStackPane = new StackPane();
        resizedStackPane.getChildren().add(snapshotView);
        return resizedStackPane;
    }
}