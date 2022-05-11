package com.example.ijaproject;

import com.fxgraph.graph.Graph;
import com.fxgraph.graph.ICell;
import com.fxgraph.graph.Model;
import com.fxgraph.layout.RandomLayout;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;

/**
 * HelloApplication
 *
 * This constructor handles start of an application.
 *
 * @author xokruc00
 * @version 1.0
 */
public class HelloApplication extends Application {
    public BorderPane borderPaneClass;
    public List<BorderPane> borderPaneSequence;
    public ToolBar toolbarClass;
    public ToolBar toolbarSequence;
    public TabPane tabPane;
    public Model model;
    public Graph graph;
    int index = 0;
    public ICell source;
    public SequenceDiagram.ActorCell sourceSQ;
    public SequenceDiagram.ActorCell destinationSQ;
    public String sourceAH;
    public String destinationAH;
    public ClassController sourceCC;
    public ICell destination;
    public ClassController destinationCC;
    FileHandler fileHandler;
    Stage primaryStage = new Stage();
    private UMLProject umlProject;
    private List<SequenceDiagram> seqDiagram;
    private int parIndex = 0;
    private List<ICell> historyCells;
    private List<Tab> addSequence;

    private AppController appController;

    private List<SequenceDiagram.ActorCell> actors;

    @Override
    public void start(Stage primaryStage) {
        this.historyCells = new ArrayList<>();
        this.addSequence = new ArrayList<>();
        this.appController = new AppController();
        this.umlProject = new UMLProject("tmp");
        this.umlProject.sequenceDiagrams = new ArrayList<>();
        this.umlProject.sequenceDiagrams.add(new UMLSequenceDiagram("yay"));
        this.appController.addOperation(umlProject.clone());
        this.borderPaneSequence = new ArrayList<>();


        this.graph = new Graph();
        this.model = graph.getModel();
        this.graph.beginUpdate();
        this.seqDiagram = new ArrayList<>();
        this.seqDiagram.add(new SequenceDiagram());
        this.seqDiagram.get(0).beginUpdate();

        this.toolbarClass = new ToolBar();
        Button addClass = new Button("Add Class");
        addClass.setOnAction(this::addClassHandler);
        Button addAssociation = new Button("Add Association");
        addAssociation.setOnAction(this::addAssociationHandler);
        Button addAggregation = new Button("Add Aggregation");
        addAggregation.setOnAction(this::addAggregationHandler);
        Button addComposition = new Button("Add Composition");
        addComposition.setOnAction(this::addCompositionHandler);
        Button addGeneralization = new Button("Add Generalization");
        addGeneralization.setOnAction(this::addGeneralizationHandler);
        Button importClassDiagram = new Button("Import Class Diagram");
        importClassDiagram.setOnAction(this::importHandler);
        Button saveClassDiagram = new Button("Save");
        saveClassDiagram.setOnAction(this::saveHandler);
        Button addSequenceDiagram = new Button("Add Sequence Diagram");
        addSequenceDiagram.setOnAction(this::addSequenceDiagramHandler);
        Button undo = new Button("↶ Undo");
        undo.setOnAction(this::undoHandler);
        Button random = new Button("Randomize");
        random.setOnAction(this::randomHandler);

        this.toolbarClass.getItems().addAll(addClass, addAssociation, addAggregation, addComposition, addGeneralization, importClassDiagram, saveClassDiagram, addSequenceDiagram, undo, random);

        this.graph.beginUpdate();


        tabPane = new TabPane();
        Tab classDiagram = new Tab("Class Diagram");
        classDiagram.setClosable(false);

        this.borderPaneClass = new BorderPane();
        this.borderPaneClass.setMinWidth(1000);
        this.borderPaneClass.setMinHeight(1000);
        this.borderPaneClass.setTop(this.toolbarClass);
        this.borderPaneClass.setCenter(this.graph.getCanvas());
        primaryStage.setScene(new Scene(this.borderPaneClass));
        primaryStage.show();

        this.borderPaneSequence.add(new BorderPane());
        this.borderPaneSequence.get(0).setMinWidth(1000);
        this.borderPaneSequence.get(0).setMinHeight(1000);
        this.borderPaneSequence.get(0).setTop(this.toolbarSequence);

        this.borderPaneSequence.get(0).setCenter(this.seqDiagram.get(0).getCanvas());
        primaryStage.setScene(new Scene(this.borderPaneSequence.get(0)));
        primaryStage.show();

        this.tabPane.getTabs().add(classDiagram);

        this.seqDiagram.get(0).layout();
        classDiagram.setContent(this.borderPaneClass);

        this.graph.beginUpdate();

        this.borderPaneClass = new BorderPane();
        this.borderPaneClass.setTop(this.tabPane);
        primaryStage.setFullScreen(true);
        primaryStage.setScene(new Scene(this.borderPaneClass));
        primaryStage.show();
    }

    private ToolBar getToolbar() {
        ToolBar toolBar = new ToolBar();

        Button addParticipant = new Button("Add Participant");
        addParticipant.setOnAction(this::addParticipantHandler);
        Button addAsMessage = new Button("Add Asynchronous Message");
        addAsMessage.setOnAction(this::addAsMessageHandler);
        Button addSyMessage = new Button("Add Synchronous Message");
        addSyMessage.setOnAction(this::addSyMessageHandler);
        Button addResponse = new Button("Add Response");
        addResponse.setOnAction(this::addResponseHandler);
        Button addTime = new Button("Add Time");
        addTime.setOnAction(this::addTimeHandler);

        toolBar.getItems().addAll(addParticipant, addAsMessage, addSyMessage, addResponse, addTime);
        return toolBar;
    }

    private void addSequenceDiagramHandler(ActionEvent event) {
        Tab tab = new Tab("Sequence Diagram");
        addSequence.add(tab);
        this.tabPane.getTabs().add(tab);
        this.borderPaneSequence.add(new BorderPane());
        this.borderPaneSequence.get(this.borderPaneSequence.size()-1).setTop(getToolbar());
        this.borderPaneSequence.get(this.borderPaneSequence.size()-1).setCenter(this.seqDiagram.get(this.seqDiagram.size()-1).getCanvas());
        tab.setContent(this.borderPaneSequence.get(this.borderPaneSequence.size()-1));

        primaryStage.setScene(new Scene(this.borderPaneSequence.get(this.borderPaneSequence.size()-1)));
        primaryStage.show();
    }

    private void randomHandler(ActionEvent actionEvent) {
        this.graph.layout(new RandomLayout());
    }

    private void addTimeHandler(ActionEvent event) {
    }

    private void addResponseHandler(ActionEvent event) {
    }

    private void addSyMessageHandler(ActionEvent event) {
        if(this.sourceSQ != null && this.destinationSQ != null) {
            this.seqDiagram.get(0).addMessage(sourceSQ, destinationSQ, "message");
            this.seqDiagram.get(0).layout();
        }
    }

    private void addAsMessageHandler(ActionEvent event) {
    }

    public void selectSQ(SequenceDiagram.ActorCell actorCell, String actorName) {
        if(this.sourceSQ == null && this.sourceAH == null) {
            this.sourceSQ = actorCell;
            this.sourceAH = actorName;
            return;
        } else if(this.destinationSQ == null) {
            if(!Objects.equals(sourceAH, actorName)) {
                this.destinationSQ = actorCell;
                this.destinationAH = actorName;
                return;
            }
        }
        if(!Objects.equals(destinationAH, actorName)) {
            this.sourceSQ = this.destinationSQ;
            this.sourceAH = this.destinationAH;
            this.destinationSQ = actorCell;
            this.destinationAH = actorName;
        }
    }

    private void addParticipantHandler(ActionEvent event) {
        String name = "participant";
        if(this.umlProject == null) {
            name = name + this.parIndex;
            this.parIndex++;
        } else if(this.umlProject.classes == null || this.umlProject.classes.size() == 0) {
            name = name + this.parIndex;
            this.parIndex++;
        } else {
            name = this.umlProject.classes.get(0).name;
        }
        Pane localPane = new Pane();
        System.out.println(name);
        this.umlProject.sequenceDiagrams.get(0).addParticipant(new UMLParticipant(name));

        SequenceDiagram.ActorCell participant = new SequenceDiagram.ActorCell(name, 400d, localPane, this.umlProject, this.appController);
        localPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
            this.selectSQ(participant, participant.getActorName());
        });

        localPane.addEventHandler(MouseEvent.MOUSE_ENTERED, event2 -> {
            participant.selected();
        });

        localPane.addEventHandler(MouseEvent.MOUSE_EXITED, event3 -> {
            participant.unselected();
        });

        List.of(participant).forEach(actor -> seqDiagram.get(0).addActor(actor));
        seqDiagram.get(0).layout();
    }

    private void undoHandler(ActionEvent event) {
        this.appController.undo();
        this.umlProject = appController.getTop();

        List<UMLClass> classes = umlProject.classes;

        if(classes.size() == this.historyCells.size()) {
            //undo something else
        } else if(classes.size() < this.historyCells.size()) {
            this.historyCells.remove(this.historyCells.size()-1);
            this.model.clear();
            this.graph.beginUpdate();
            for(int i=0; i < this.historyCells.size(); i++) {
                this.model.addCell(this.historyCells.get(i));
            }
            this.graph.endUpdate();
            this.source = null;
            this.sourceCC = null;
            this.sourceAH = null;
            this.sourceSQ = null;
            this.destination = null;
            this.destinationCC = null;
            this.destinationAH = null;
            this.destinationSQ = null;
        }
    }

    private void addClassDiagramHandler(ActionEvent event) {
    }

    public static void main(String[] args) {
        launch();
    }

    private void saveHandler(ActionEvent event) {
        if (this.fileHandler == null) {
            FileChooser fileChooser = new FileChooser();

            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
            fileChooser.getExtensionFilters().add(extFilter);

            File file = fileChooser.showSaveDialog(primaryStage);
            fileHandler = new FileHandler(file.toString());
            fileHandler.save(umlProject);
        } else {
            fileHandler.save(umlProject);
        }

    }

    private void importHandler(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(primaryStage);

        System.out.println(file);

        this.appController.addPath(file.toString());

        this.umlProject = this.appController.read();

        this.source = null;
        this.sourceCC = null;
        this.sourceAH = null;
        this.sourceSQ = null;
        this.destination = null;
        this.destinationCC = null;
        this.destinationAH = null;
        this.destinationSQ = null;

        List<UMLClass> lc = umlProject.classes;

        List<ClassCell> cells = new ArrayList<>();
        List<MyArrow> arrows = new ArrayList<>();
        List<OperationHolder> operations = new ArrayList<>();
        this.model.clear();
        this.graph.beginUpdate();
        OperationHolder holder = null;

        for(int i=0; i<lc.size(); i++) {
            ClassController classController = new ClassController(lc.get(i).name, this.umlProject, this.appController);
            classController.addAttributeBC(lc.get(i).attributes);
            classController.addMethodsBC(lc.get(i).methods);
            if(lc.get(i).operations != null) {
                for(int j = 0; j < lc.get(i).operations.size(); j++)
                 holder = new OperationHolder(lc.get(i).name, lc.get(i).operations.get(j).target, lc.get(i).operations.get(j).name);
                operations.add(holder);
            }
            cells.add(new ClassCell(classController));
            this.model.addCell(cells.get(i));
        }

        int sIndex;
        int tIndex;
        for(int i=0; i < operations.size(); i++) {
            sIndex = this.getIndex(cells, operations.get(i).sourceName);
            tIndex = this.getIndex(cells, operations.get(i).targetName);

            MyArrow arrow = new MyArrow(cells.get(sIndex), cells.get(tIndex));
            arrow.textProperty().set(makeMessage(operations.get(i)));
            this.model.addEdge(arrow);
        }

        this.graph.endUpdate();
        this.graph.layout(new RandomLayout());
    }

    private String makeMessage(OperationHolder operationHolder) {
        String msg;
        if(Objects.equals(operationHolder.operation, "association")) {
            msg = operationHolder.sourceName + " and " + operationHolder.targetName + " classifiers are associated";
        } else if(Objects.equals(operationHolder.operation, "generalization")) {
            msg = operationHolder.sourceName + " is generalized by " + operationHolder.targetName;
        } else {
            msg = "";
        }

        return msg;
    }

    private int getIndex(List<ClassCell> cells, String name) {
        for(int i=0; i < cells.size(); i++) {
            if(Objects.equals(cells.get(i).getName(), name)) {
                return i;
            }
        }
        return -1;
    }

    private void addGeneralizationHandler(ActionEvent event) {
        if(this.source != null && this.destination != null) {
            final MyArrow myArrow = new MyArrow(this.source, this.destination);
            myArrow.textProperty().set(this.sourceCC.getName() + " —▷ is generalized by " + this.destinationCC.getName());

            myArrow.operation = "GE";
            myArrow.source = this.sourceCC.getName();
            myArrow.target = this.destinationCC.getName();

            this.sourceCC.addArrow(myArrow, true);
            this.destinationCC.addArrow(myArrow, false);

            this.model.addEdge(myArrow);
            this.graph.endUpdate();
        }
    }

    private void addCompositionHandler(ActionEvent event) {
        if(this.source != null && this.destination != null) {
            final MyArrow myArrow = new MyArrow(this.source, this.destination);
            myArrow.textProperty().set(this.destinationCC.getName() + " ◀▶— has " + this.sourceCC.getName());

            myArrow.operation = "CO";
            myArrow.target = this.destinationCC.getName();
            myArrow.source = this.sourceCC.getName();

            this.sourceCC.addArrow(myArrow, true);
            this.destinationCC.addArrow(myArrow, false);

            this.model.addEdge(myArrow);
            this.graph.endUpdate();
        }
    }

    private void addAggregationHandler(ActionEvent event) {
        if(this.source != null && this.destination != null) {
            final MyArrow myArrow = new MyArrow(this.source, this.destination);
            myArrow.textProperty().set(this.destinationCC.getName() + " ◇— has collection of " + this.sourceCC.getName());

            myArrow.operation = "AG";
            myArrow.source = this.sourceCC.getName();
            myArrow.target = this.destinationCC.getName();

            this.sourceCC.addArrow(myArrow, true);
            this.destinationCC.addArrow(myArrow, false);

            this.model.addEdge(myArrow);
            this.graph.endUpdate();
        }
    }

    private void addAssociationHandler(ActionEvent event) {
        if(this.source != null && this.destination != null) {
            final MyArrow myArrow = new MyArrow(this.source, this.destination);
            myArrow.textProperty().set(this.sourceCC.getName() + " —> " + this.destinationCC.getName() + " classifiers are associated");
            myArrow.operation = "AS";
            myArrow.target = this.destinationCC.getName();
            myArrow.source = this.sourceCC.getName();

            this.sourceCC.addArrow(myArrow, true);
            this.destinationCC.addArrow(myArrow, false);

            this.model.addEdge(myArrow);
            this.graph.endUpdate();
        }
    }

    private void addClassHandler(ActionEvent event) {
        String name = "Class" + this.index;
        ClassController classController = new ClassController(name, this.umlProject, this.appController);
        UMLProject tmp = new UMLProject(this.umlProject.projectName);

        tmp.sequenceDiagrams = this.umlProject.sequenceDiagrams;
        try {
            umlProject.addClass(new UMLClass(name));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        tmp.classes = this.umlProject.classes;

        System.out.println("HELLO CLASSES: " + umlProject.classes.size());
        appController.addOperation(tmp);

        ICell cell = new ClassCell(classController);
        this.historyCells.add(cell);

        classController.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
            classController.toFront();
            this.select(cell, classController);
        });

        this.index++;

        this.model.addCell(cell);
        this.graph.endUpdate();
    }

    private void select(ICell cell, ClassController classController) {
        if(this.source == null && this.sourceCC == null) {
            this.source = cell;
            this.sourceCC = classController;
            return;
        } else if(this.destination == null) {
            if(!Objects.equals(sourceCC.getName(), classController.getName())) {
                this.destination = cell;
                this.destinationCC = classController;
                return;
            }
        }
        if(!Objects.equals(destinationCC.getName(), classController.getName())) {
            this.source = this.destination;
            this.sourceCC = this.destinationCC;
            this.destination = cell;
            this.destinationCC = classController;
        }
    }
}