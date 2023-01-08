package ir.alirezaalijani.genetic.chart;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ir.alirezaalijani.genetic.core.GeneticNewPopulationGenerate;
import ir.alirezaalijani.genetic.core.PopulationSelection;
import ir.alirezaalijani.genetic.share.domain.KromozomUtil;
import ir.alirezaalijani.genetic.share.domain.PopulationGenerate;
import ir.alirezaalijani.genetic.share.domain.equation.Equation3RandomPopulationGenerate;
import ir.alirezaalijani.genetic.share.domain.vezir.EightVezirRandomPopulationGenerate;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

/**
 * @author Alireza Alijani : <a href="https://alirezaalijani.ir">https://alirezaalijani.ir</a>
 * @email alirezaalijani.ir@gmail.com
 * @date 1/6/2023
 */

public class TableChartGeneticController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblKromozomValue;

    @FXML
    private Label lblFitnessValue;

    private GeneticRunConfig geneticRunConfig;

    private ObservableList<GenerationView> generationViewObservableList;
    @FXML
    private TableView<GenerationView> firstTable;

    private final CategoryAxis xAxis = new CategoryAxis();
    private final NumberAxis yAxis = new NumberAxis();
    @FXML
    private LineChart<String, Number> firstChart=new LineChart<>(xAxis, yAxis);
    @FXML
    private LineChart<String, Number> secChart= new LineChart<>(xAxis,yAxis);
    @FXML
    void initialize() {
        assert firstTable != null : "fx:id=\"firstTable\" was not injected: check your FXML file 'table-chart.fxml'.";
        assert firstChart != null : "fx:id=\"firstChart\" was not injected: check your FXML file 'table-chart.fxml'.";

        this.generationViewObservableList= firstTable.getItems();

        xAxis.setLabel("Fitness");
        yAxis.setLabel("Generation");

        firstChart.setTitle("Generation Fitness Avg Chart");
        firstChart.setAnimated(false);

        secChart.setTitle("Generation Best Fitness Chart");
        secChart.setAnimated(false);

        Platform.runLater(this::startGeneration);
    }

    public void setGeneticRunConfig(GeneticRunConfig geneticRunConfig) {
        this.geneticRunConfig = geneticRunConfig;
    }

    private void startGeneration(){
        int generationMax=geneticRunConfig.getGenerationMax();
        double acceptableFitness=geneticRunConfig.getAcceptableFitness();

        List<GenerationView> generationViewList = new ArrayList<>();

        PopulationGenerate populationGenerate=null;
        if (geneticRunConfig.getProblem()==0){
           populationGenerate = new Equation3RandomPopulationGenerate((geneticRunConfig.getEqu3Range()*-1),geneticRunConfig.getEqu3Range());
        }else if(geneticRunConfig.getProblem() ==1) {
            populationGenerate = new EightVezirRandomPopulationGenerate(geneticRunConfig.getNumberOfVazir());
        }
        if (populationGenerate!=null)
        {
            var population = populationGenerate.populationBuilder(geneticRunConfig.getPopulationSize());

            PopulationSelection populationSelection = new PopulationSelection(geneticRunConfig.getSelectionType(),
                    geneticRunConfig.getElitismPresent(),geneticRunConfig.isOrder(),geneticRunConfig.getTournamentSize(),geneticRunConfig.getRouletteing());
            GeneticNewPopulationGenerate newPopulationGenerate=new GeneticNewPopulationGenerate(geneticRunConfig.getCrossoverPresent(),
                    geneticRunConfig.getMutationPresent(),populationSelection);

            int generation=0;
            while (true){
                double best = KromozomUtil.bestFitness(population,true);
                var gv= GenerationView.builder()
                        .generation(generation)
                        .averageOfGeneration(KromozomUtil.populationAvg(population))
                        .bestOfGeneration(best)
                        .build();
                generationViewList.add(gv);
                System.out.println(generation);
                if (best<=acceptableFitness){
                    break;
                }
                if (generation==generationMax){
                    break;
                }
                population = KromozomUtil.deepCopy(newPopulationGenerate.start(population));
                generation++;
            }

            generationViewObservableList.clear();
            generationViewObservableList.addAll(generationViewList);
            drawChart(generationViewList);

            var bestKromozom=KromozomUtil.bestFitnessKromozom(population,geneticRunConfig.isOrder());
            lblFitnessValue.setText(String.valueOf(bestKromozom.fitness()));
            lblKromozomValue.setText(bestKromozom.toString());
        }

    }

    private void drawChart(List<GenerationView> generationViewList){

        var generationAveragesData = new XYChart.Series<String, Number>();
        generationAveragesData.setName("Average Generation Fitness");

        var generationBestOfData = new XYChart.Series<String, Number>();
        generationBestOfData.setName("Best Of Fitness");

        generationViewList.forEach(item ->{
            generationAveragesData.getData().add(new XYChart.Data<>(String.valueOf(item.getGeneration()),item.getAverageOfGeneration()));
            generationBestOfData.getData().add(new XYChart.Data<>(String.valueOf(item.getGeneration()),item.getBestOfGeneration()));
        });

        firstChart.getData().clear();
        secChart.getData().clear();
        firstChart.getData().add(generationAveragesData);
        secChart.getData().add(generationBestOfData);
    }
}
