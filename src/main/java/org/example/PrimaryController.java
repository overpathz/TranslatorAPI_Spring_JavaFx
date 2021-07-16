package org.example;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.example.configuration.MyConfig;
import org.example.service.IReader;
import org.example.service.Languages;
import org.example.service.TranslateAPI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PrimaryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button answerBtn;

    @FXML
    private TextField textEntry;

    @FXML
    private Text translatedWord;

    @FXML
    private ChoiceBox<Languages> fromLang;

    @FXML
    private ChoiceBox<Languages> toLang;

    private ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
    private TranslateAPI translateAPI = context.getBean("translate", TranslateAPI.class);

    private IReader iReader = new IReader() {
        @Override
        public String getWord() {
            return textEntry.getText();
        }
    };

    @FXML
    void translateWord(ActionEvent event) {
        String translated = translateAPI.translate(iReader, fromLang.getValue(), toLang.getValue());
        translatedWord.setText(translated);
    }

    @FXML
    void initialize() {
        fromLang.getItems().add(Languages.ENG);
        fromLang.getItems().add(Languages.RU);
        fromLang.setValue(Languages.RU);

        toLang.getItems().add(Languages.RU);
        toLang.getItems().add(Languages.ENG);
        toLang.setValue(Languages.ENG);
    }
}










