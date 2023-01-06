package ir.alirezaalijani.genetic.chart;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ir.alirezaalijani.genetic.core.PopulationSelection;
import ir.alirezaalijani.genetic.core.SelectionType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 1/6/2023
 */
@Slf4j
public class AppController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtMaxGeneration;

    @FXML
    private TextField txtAcceptableFitness;

    @FXML
    private TextField txtPopulationSize;

    @FXML
    private ComboBox<SelectionType> cbSelectionType;

    @FXML
    private TextField txtElitismPresent;

    @FXML
    private TextField txtTournamentSize;

    @FXML
    private TextField txtRouletteing;

    @FXML
    private TextField txtCrossoverPresent;

    @FXML
    private TextField txtMutationPresent;

    @FXML
    private ComboBox<String> cbProblem;

    @FXML
    void initialize() {
        assert txtMaxGeneration != null : "fx:id=\"txtMaxGeneration\" was not injected: check your FXML file 'app.fxml'.";
        assert txtAcceptableFitness != null : "fx:id=\"txtAcceptableFitness\" was not injected: check your FXML file 'app.fxml'.";
        assert txtPopulationSize != null : "fx:id=\"txtPopulationSize\" was not injected: check your FXML file 'app.fxml'.";
        assert cbSelectionType != null : "fx:id=\"cbSelectionType\" was not injected: check your FXML file 'app.fxml'.";
        assert txtElitismPresent != null : "fx:id=\"txtElitismPresent\" was not injected: check your FXML file 'app.fxml'.";
        assert txtTournamentSize != null : "fx:id=\"txtTournamentSize\" was not injected: check your FXML file 'app.fxml'.";
        assert txtRouletteing != null : "fx:id=\"txtRouletteing\" was not injected: check your FXML file 'app.fxml'.";
        assert txtCrossoverPresent != null : "fx:id=\"txtCrossoverPresent\" was not injected: check your FXML file 'app.fxml'.";
        assert txtMutationPresent != null : "fx:id=\"txtMutationPresent\" was not injected: check your FXML file 'app.fxml'.";
        assert cbProblem != null : "fx:id=\"cbProblem\" was not injected: check your FXML file 'app.fxml'.";

        cbSelectionType.getItems().add(SelectionType.Tournament);
        cbSelectionType.getItems().add(SelectionType.RouletteWheel);
        cbSelectionType.getSelectionModel()
                        .select(0);
        cbProblem.getItems().add("Equation3");
        cbProblem.getItems().add("8Vazir");
    }

    @FXML
    public void startTableAndChart(MouseEvent mouseEvent) {
        URL resource = getClass().getClassLoader().getResource("view/table-chart.fxml");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(resource);
            loader.load();
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            TableChartGeneticController controller = loader.getController();
            controller.setGeneticRunConfig(geneticRunConfig());
            stage.setOnCloseRequest(event -> {
                stage.close();
            });
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private GeneticRunConfig geneticRunConfig(){
       return GeneticRunConfig.builder()
                .generationMax(Integer.parseInt(txtMaxGeneration.getText().trim()))
                .acceptableFitness(Double.parseDouble(txtAcceptableFitness.getText().trim()))
                .populationSize(Integer.parseInt(txtPopulationSize.getText().trim()))
                .selectionType(cbSelectionType.getValue())
                .crossoverPresent(Integer.parseInt(txtCrossoverPresent.getText().trim()))
                .mutationPresent(Integer.parseInt(txtMutationPresent.getText().trim()))
                .elitismPresent(Integer.parseInt(txtElitismPresent.getText().trim()))
                .order(true)
                .tournamentSize(Integer.parseInt(txtTournamentSize.getText().trim()))
                .rouletteing(Integer.parseInt(txtRouletteing.getText().trim()))
                .build();
    }
}

