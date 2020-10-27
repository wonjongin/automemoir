package automemoirs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDate;
import java.util.Optional;

import static automemoirs.ControlHWP.*;

public class MainApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        int basicFontSize = 15;


        // 그리드
        GridPane gridPaneRoot = new GridPane();
        gridPaneRoot.setAlignment(Pos.CENTER);
        gridPaneRoot.setHgap(10);
        gridPaneRoot.setVgap(10);
        gridPaneRoot.setPadding(new Insets(25, 25, 25, 25));


        // 타이틀
        Label labelTitle = new Label("회고록 자동 작성 프로그램");
        labelTitle.setFont(new Font(25));
        gridPaneRoot.add(labelTitle, 0, 0, 6, 1);
        // 제목란
        Label labelTitleInput = new Label("제목: ");
        labelTitleInput.setFont(new Font(basicFontSize));
        gridPaneRoot.add(labelTitleInput, 0, 1, 1, 1);
        TextField textFieldTitle = new TextField();
        textFieldTitle.setFont(new Font(basicFontSize));
        textFieldTitle.setPromptText("제목을 입력하세요 ex> 학생 자치 법정");
        gridPaneRoot.add(textFieldTitle, 1, 1, 5, 1);
        // 장소란
        Label labelPlaceInput = new Label("장소: ");
        labelPlaceInput.setFont(new Font(basicFontSize));
        gridPaneRoot.add(labelPlaceInput, 0, 2, 1, 1);
        TextField textFieldPlace = new TextField();
        textFieldPlace.setFont(new Font(basicFontSize));
        textFieldPlace.setPromptText("장소를 입력하세요.");
        gridPaneRoot.add(textFieldPlace, 1, 2, 5, 1);
        // 날짜란
        Label labelDate = new Label("일시: ");
        labelDate.setFont(new Font(basicFontSize));
        gridPaneRoot.add(labelDate, 0, 3, 1, 1);
        DatePicker datePicker = new DatePicker();
        gridPaneRoot.add(datePicker, 1, 3, 2, 1);
        // 시간란
        Label labelTime = new Label("시간: ");
        labelTime.setFont(new Font(basicFontSize));
        gridPaneRoot.add(labelTime, 3, 3, 1, 1);
        TextField textFieldTime = new TextField();
        textFieldTime.setFont(new Font(basicFontSize));
        textFieldTime.setPromptText("시간을 입력하세요.");
        gridPaneRoot.add(textFieldTime, 4, 3, 2, 1);

        // 내용란
        Label labelDesc = new Label("내용: ");
        labelDesc.setFont(new Font(basicFontSize));
        gridPaneRoot.add(labelDesc, 0, 4, 1, 1);
        TextArea textAreaDesc = new TextArea();
        textAreaDesc.setFont(new Font(basicFontSize));
        textAreaDesc.setPromptText("내용을 입력하세요.");
        textAreaDesc.setMinWidth(300);
        gridPaneRoot.add(textAreaDesc, 1, 4, 4, 5);

        // 하단 버튼들
        HBox hBoxBottomBar = new HBox();
        hBoxBottomBar.setAlignment(Pos.BOTTOM_CENTER);
        hBoxBottomBar.setPadding(new Insets(25, 25, 25, 25));
        Button btnExit = new Button("종료");
        Button btnSave = new Button("저장");
        Button btnDev = new Button("개발");
        hBoxBottomBar.getChildren().addAll(btnExit, btnSave);
        hBoxBottomBar.setSpacing(25);

        // 전체 레이아웃
        VBox vBoxRoot = new VBox();
        vBoxRoot.getChildren().addAll(gridPaneRoot, hBoxBottomBar);
        vBoxRoot.setFillWidth(true);
        Scene scene = new Scene(vBoxRoot, 800, 400);
        stage.setMinWidth(400);
        stage.setMinHeight(400);
        stage.setMaxWidth(800);
        stage.setMaxHeight(450);
        stage.setScene(scene);
        stage.setTitle("Auto Memoriors");
        stage.show();

        // 다이얼로그 틀
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("ERROR Dialog");
        alertError.setHeaderText("에러! 에러!!");
        alertError.setContentText("에러가 발생했습니다.");

        // 안내 틀
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
        alertInfo.setTitle("Information Dialog");
        alertInfo.setHeaderText("완료");
        alertInfo.setContentText("저장이 완료되었습니다.");

        // 함수들
        // 종료 버튼
        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        // 저장 버튼
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooserSave = new FileChooser();
                fileChooserSave.getExtensionFilters().add(new FileChooser.ExtensionFilter("Hangul Word Processor (*.hwp)", "*.hwp"));
                File selectedFc = fileChooserSave.showSaveDialog(stage);
                String savePath = selectedFc.getPath();
                try {
                    createHWP(savePath);
                } catch (Exception e) {
//                    e.printStackTrace();
                    alertError.showAndWait();
                }
                String title = textFieldTitle.getText();
                String desc = textAreaDesc.getText();
                String place = textFieldPlace.getText();
                LocalDate date = datePicker.getValue();
                String time = textFieldTime.getText();
                try {
                    writeHWP(savePath, title, date, time, place, desc);
//                    readPropHWP(savePath);
                    alertInfo.showAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                    alertError.showAndWait();
                }
            }
        });
        btnDev.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooserSave = new FileChooser();
                File selectedFc = fileChooserSave.showOpenDialog(stage);
                String savePath = selectedFc.getPath();
                try {
                    readPropHWP(savePath);
                } catch (Exception e) {
                    e.printStackTrace();
                    alertError.showAndWait();
                }
            }
        });
    }

}
