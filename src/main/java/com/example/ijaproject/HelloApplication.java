package com.example.ijaproject;

import com.fxgraph.graph.Graph;
import com.fxgraph.graph.ICell;
import com.fxgraph.graph.Model;
import com.fxgraph.layout.RandomLayout;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
    public BorderPane borderPaneSequence;
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
    private SequenceDiagram seqDiagram;
    private int parIndex = 0;

    private List<SequenceDiagram.ActorCell> actors;

    @Override
    public void start(Stage primaryStage) {
        graph = new Graph();
        model = graph.getModel();
        graph.beginUpdate();
        seqDiagram = new SequenceDiagram();
        seqDiagram.beginUpdate();

        toolbarClass = new ToolBar();
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
        addSequenceDiagram.setOnAction(this::addClassDiagramHandler);
        Button undo = new Button("↶ Undo");
        undo.setOnAction(this::undoHandler);

        toolbarClass.getItems().addAll(addClass, addAssociation, addAggregation, addComposition, addGeneralization, importClassDiagram, saveClassDiagram, addSequenceDiagram, undo);

        graph.beginUpdate();

        toolbarSequence = new ToolBar();
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
        toolbarSequence.getItems().addAll(addParticipant, addAsMessage, addSyMessage, addResponse, addTime);

        graph.beginUpdate();

        tabPane = new TabPane();
        Tab classDiagram = new Tab("Class Diagram");
        Tab addSequence = new Tab("+ Add Sequence Diagram");

        borderPaneClass = new BorderPane();
        borderPaneClass.setMinWidth(1000);
        borderPaneClass.setMinHeight(1000);
        borderPaneClass.setTop(toolbarClass);
        borderPaneClass.setCenter(graph.getCanvas());
        primaryStage.setScene(new Scene(borderPaneClass));
        primaryStage.show();

        borderPaneSequence = new BorderPane();
        borderPaneSequence.setMinWidth(1000);
        borderPaneSequence.setMinHeight(1000);
        borderPaneSequence.setTop(toolbarSequence);

        borderPaneSequence.setCenter(seqDiagram.getCanvas());
        primaryStage.setScene(new Scene(borderPaneSequence));
        primaryStage.show();

        tabPane.getTabs().add(classDiagram);
        tabPane.getTabs().add(addSequence);

        /*SequenceDiagram.ActorCell actorA = new SequenceDiagram.ActorCell("Actor A", 400d);
        SequenceDiagram.ActorCell actorB = new SequenceDiagram.ActorCell("Actor B", 400d);
        SequenceDiagram.ActorCell actorC = new SequenceDiagram.ActorCell("Actor C", 400d);
        Arrays.asList(actorA, actorB, actorC).forEach(actor -> seqDiagram.addActor(actor));*/

        /*seqDiagram.addMessage(actorA, actorB, "checkEmail");
        seqDiagram.addMessage(actorB, actorC, "readSavedUser");
        seqDiagram.addMessage(actorC, actorB, "savedUser");
        seqDiagram.addMessage(actorB, actorA, "noNewEmails");*/

        seqDiagram.layout();
        addSequence.setContent(borderPaneSequence);
        classDiagram.setContent(borderPaneClass);

        graph.beginUpdate();

        borderPaneClass = new BorderPane();
        borderPaneClass.setTop(tabPane);
        primaryStage.setMinHeight(1000);
        primaryStage.setMaxHeight(1000);
        primaryStage.setMinWidth(1500);
        primaryStage.setMaxWidth(1500);
        primaryStage.setScene(new Scene(borderPaneClass));
        primaryStage.show();
    }

    private void addTimeHandler(ActionEvent event) {
    }

    private void addResponseHandler(ActionEvent event) {
    }

    private void addSyMessageHandler(ActionEvent event) {
        if(this.sourceSQ != null && this.destinationSQ != null) {
            this.seqDiagram.addMessage(sourceSQ, destinationSQ, "message");
            seqDiagram.layout();
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
        String name = "par";
        if(this.umlProject == null) {
            name = name + this.parIndex;
            this.parIndex++;
        } else if(this.umlProject.classes == null) {
            name = name + this.parIndex;
            this.parIndex++;
        } else {
            name = this.umlProject.classes.get(0).name;
        }
        Pane localPane = new Pane();

        SequenceDiagram.ActorCell participant = new SequenceDiagram.ActorCell(name, 400d, localPane);
        System.out.println("keke");
        localPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
            //participant.pane.toFront();
            this.selectSQ(participant, participant.getActorName());
        });

        localPane.addEventHandler(MouseEvent.MOUSE_ENTERED, event2 -> {
            participant.selected();
        });

        localPane.addEventHandler(MouseEvent.MOUSE_EXITED, event3 -> {
            participant.unselected();
        });

        List.of(participant).forEach(actor -> seqDiagram.addActor(actor));
        seqDiagram.layout();
    }

    private void undoHandler(ActionEvent event) {
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
        fileChooser.showOpenDialog(primaryStage);

        this.umlProject = this.fileHandler.read();

        List<UMLClass> lc = umlProject.classes;

        List<ClassCell> cells = new ArrayList<>();
        List<MyArrow> arrows = new ArrayList<>();
        List<OperationHolder> operations = new ArrayList<>();
        graph.beginUpdate();
        OperationHolder holder = null;

        for(int i=0; i<lc.size(); i++) {
            ClassController classController = new ClassController(lc.get(i).name);
            classController.addAttributeBC(lc.get(i).attributes);
            classController.addMethodsBC(lc.get(i).methods);
            if(lc.get(i).operations != null) {
                for(int j = 0; j < lc.get(i).operations.size(); j++)
                 holder = new OperationHolder(lc.get(i).name, lc.get(i).operations.get(j).target, lc.get(i).operations.get(j).name);
                operations.add(holder);
            }
            cells.add(new ClassCell(classController));
            model.addCell(cells.get(i));
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

        graph.endUpdate();
        graph.layout(new RandomLayout());
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
            myArrow.textProperty().set(this.sourceCC.getName() + " is generalized by " + this.destinationCC.getName());
            this.model.addEdge(myArrow);
            this.graph.endUpdate();
        }
    }

    private void addCompositionHandler(ActionEvent event) {
        if(this.source != null && this.destination != null) {
            final MyArrow myArrow = new MyArrow(this.source, this.destination);
            myArrow.textProperty().set("<-             ->");
            this.model.addEdge(myArrow);
            this.graph.endUpdate();
        }
    }

    private void addAggregationHandler(ActionEvent event) {
        if(this.source != null && this.destination != null) {
            final MyArrow myArrow = new MyArrow(this.source, this.destination);
            myArrow.textProperty().set("<-             ->");
            this.model.addEdge(myArrow);
            this.graph.endUpdate();
        }
    }

    private void addAssociationHandler(ActionEvent event) {
        if(this.source != null && this.destination != null) {
            final MyArrow myArrow = new MyArrow(this.source, this.destination);
            myArrow.textProperty().set(this.sourceCC.getName() + " and " + this.destinationCC.getName() + " classifiers are associated");
            this.model.addEdge(myArrow);
            this.graph.endUpdate();
        }
    }
    

    private void addClassHandler(ActionEvent event) {
        ClassController classController = new ClassController("Class" + this.index);
        ICell cell = new ClassCell(classController);

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