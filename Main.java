package com.example.raytrace;
/*
CS-255 Getting started code for the assignment
I do not give you permission to post this code online
Do not post your solution online
Do not copy code
Do not use JavaFX functions or other libraries to do the main parts of the assignment (i.e. ray tracing steps 1-7)
All of those functions must be written by yourself
You may use libraries to achieve a better GUI
*/
import javafx.scene.layout.StackPane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Toggle;
import javafx.scene.control.Slider;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.*;
import java.lang.Math.*;
import javafx.geometry.HPos;
import java.lang.Math;

public class Main extends Application {
  int Width = 700;
  int Height = 700;

  int green_col = 255; //just for the test example
  double r_value;
  double g_value;
  double b_value;



  ArrayList<Sphere> spheres = new ArrayList<>();

  @Override
  public void start(Stage stage) throws FileNotFoundException {
    stage.setTitle("Ray Tracing");

    //We need 3 things to see an image
    //1. We create an image we can write to
    WritableImage image = new WritableImage(Width, Height);
    //2. We create a view of that image
    ImageView view = new ImageView(image);
    //3. Add to the pane (below)

    //Create the simple GUI

    Slider r_slider = new Slider(0, 1, 0.2);
    Slider g_slider = new Slider(0, 1, 0.3);
    Slider b_slider = new Slider(0, 1, 0.4);

    Button One = new Button("Sphere 1");
    Button Two = new Button("Sphere 2");
    Button Three = new Button("Sphere3");

    r_value = r_slider.getValue();
    g_value = g_slider.getValue();
    b_value = b_slider.getValue();

    //Add all the event handlers
    r_slider.valueProperty().addListener(
            new ChangeListener<Number>() {
              public void changed(ObservableValue<? extends Number>
                                          observable, Number oldValue, Number newValue) {
                r_value = newValue.intValue();
                Render(image);
              }
            });

    g_slider.valueProperty().addListener(
            new ChangeListener<Number>() {
              public void changed(ObservableValue<? extends Number>
                                          observable, Number oldValue, Number newValue) {
                g_value = newValue.intValue();
                Render(image);
              }
            });

    b_slider.valueProperty().addListener(
            new ChangeListener<Number>() {
              public void changed(ObservableValue<? extends Number>
                                          observable, Number oldValue, Number newValue) {
                b_value = newValue.intValue();
                Render(image);
              }
            });



    //The following is in case you want to interact with the image in any way
    //e.g., for user interaction, or you can find out the pixel position for debugging
    view.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_PRESSED, event -> {
      System.out.println(event.getX() + " " + event.getY());
      event.consume();
    });

    Render(image);

    GridPane root = new GridPane();
    root.setVgap(12);
    root.setHgap(12);

    //3. (referring to the 3 things we need to display an image)
    //we need to add it to the pane
    root.add(view, 0, 0);
    root.add(r_slider, 0, 1);
    root.add(g_slider, 0, 2);
    root.add(b_slider, 0, 3);
    // create a stack pane
    StackPane r = new StackPane();

    // add button
    r.getChildren().add(One);
    r.getChildren().add(Two);
    r.getChildren().add(Three);


    //Display to user
    Scene scene = new Scene(root, 1200, 1200);
    stage.setScene(scene);
    stage.show();
  }

  public void Render(WritableImage image) {
    //Get image dimensions, and declare loop variables
    int w = (int) image.getWidth(), h = (int) image.getHeight();
    int i = 0;
    int j = 0;
    PixelWriter image_writer = image.getPixelWriter();


    int r = 50;

    Vector cs1 =new Vector(200, 350, 100);
    Vector cs2 =new Vector(100, 250, 300);
    Vector cs3 =new Vector(50, 100, 300);

    Vector col1 =new Vector(r_value,g_value,b_value);
    Vector col2 =new Vector(r_value,g_value,b_value);
    Vector col3 =new Vector(r_value,g_value,b_value);

    Sphere FirstSphere = new Sphere(20,cs1 , col1);
    Sphere SecondSphere = new Sphere(100,cs2 , col2);
    Sphere ThirdSphere = new Sphere(90,cs3 , col3);

    spheres.add(FirstSphere);
    spheres.add(SecondSphere);
    spheres.add(ThirdSphere);

    for (j = 0; j < h; j++) {
      for (i = 0; i < w; i++) {
        Vector color = new Vector(0, 0, 0);
        for (Sphere sphere : spheres) {
          Vector sphereColor = sphere.intersection(i, j, h, w);
          color = color.add(sphereColor);
        }
        image_writer.setColor(i, j, Color.color(color.getX(), color.getY(), color.getZ(), 1));
      }
    }
  }


    public static void main (String[]args){
      launch();
    }

  }
