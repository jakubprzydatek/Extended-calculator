package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import sample.calculations.*;

import java.awt.event.ActionEvent;
import java.math.BigInteger;
import java.util.ArrayList;

public class Controller { // TODO WSZYSTKO :(

    public Calculation calculation;
    @FXML
    private TextField argumentsTextField;
    @FXML
    private Label latestResultTextLabel;
    @FXML
    private Label currentOperationLabel;

    private ToggleGroup calcSystem;
    @FXML
    private RadioButton hexRadioBtn;
    @FXML
    private RadioButton decRadioBtn;
    @FXML
    private RadioButton binRadioBtn;

    private int mode;

    public Controller() {
        System.out.println("Działa");

    }

    @FXML
    void initialize() {
        mode = 10;
        //argumentsTextField.setDisable(true);
        argumentsTextField.setFocusTraversable(false);
        argumentsTextField.setMouseTransparent(true);
        calculation = new Calculation(mode);
        calcSystem = new ToggleGroup();
        hexRadioBtn.setToggleGroup(calcSystem);
        decRadioBtn.setToggleGroup(calcSystem);
        binRadioBtn.setToggleGroup(calcSystem);
        decRadioBtn.setSelected(true);
        setBinButtons(true);
        setDecButtons(true);
        setHexButtons(false);
    }

    @FXML
    public void resetMouseClicked() {
        calculation = new Calculation(mode);
        latestResultTextLabel.setText("");
        currentOperationLabel.setText("");
        argumentsTextField.setText("");
    }

    @FXML
    public void removeMouseClicked() {
        if (calculation.getCurrentArgument().equals("")) return;
        calculation.setCurrentArgument(calculation.getCurrentArgument().substring(0, calculation.getCurrentArgument().length() - 1));
        argumentsTextField.setText(calculation.getCurrentArgument());
    }

    @FXML
    public void signChangeMouseClicked() {
        if (calculation.getCurrentArgument().equals("")) {
            calculation.setCurrentArgument("-" + calculation.getCurrentArgument());
            argumentsTextField.setText(calculation.getCurrentArgument());
            return;
        }
        if (calculation.getCurrentArgument().charAt(0) == '!') {
            calculation.setCurrentArgument(calculation.getCurrentArgument().replace('!', '-'));
        } else if (calculation.getCurrentArgument().charAt(0) == '-') {
            calculation.setCurrentArgument(calculation.getCurrentArgument().substring(1));
        } else {
            calculation.setCurrentArgument("-" + calculation.getCurrentArgument());
        }
        argumentsTextField.setText(calculation.getCurrentArgument());
    }

    @FXML
    public void factoryMouseClicked() {
        if (calculation.getCurrentArgument().equals("")) {
            calculation.setCurrentArgument("!" + calculation.getCurrentArgument());
            argumentsTextField.setText(calculation.getCurrentArgument());
            return;
        }
        if (calculation.getCurrentArgument().charAt(0) == '!') {
            calculation.setCurrentArgument(calculation.getCurrentArgument().substring(1));
        } else if (calculation.getCurrentArgument().charAt(0) == '-') {
            calculation.setCurrentArgument(calculation.getCurrentArgument().replace('-', '!'));
        } else {
            calculation.setCurrentArgument("!" + calculation.getCurrentArgument());
        }
        argumentsTextField.setText(calculation.getCurrentArgument());
    }


    @FXML
    public void changeOnDecAction() {
        int prevMode = mode;
        mode = 10;
        setSystem(prevMode);
        setDecButtons(true);
        setHexButtons(false);
    }

    @FXML
    public void changeOnHexAction() {
        int prevMode = mode;
        mode = 16;
        setSystem(prevMode);
        setHexButtons(true);
    }

    @FXML
    public void changeOnBinAction() {
        int prevMode = mode;
        mode = 2;
        setSystem(prevMode);
        setBinButtons(true);
        setDecButtons(false);
        setHexButtons(false);
    }

    @FXML
    public void calcMouseClicked() {
        if (calculation.getLatestResult() == null) {
            if (!Argument.checkIfLegalArgument(calculation.getCurrentArgument())) {
                return;
            }
        }
        refreshCalculation();
        try {
            refreshTextFields();
        } catch (NullPointerException e) {
            calculation.setCurrentArgument("");
            argumentsTextField.setText(calculation.getCurrentArgument());
            latestResultTextLabel.setText(calculation.getLatestResult());
        }

        currentOperationLabel.setText("=");
        calculation = new Calculation(mode);
    }

    @FXML
    public void numberMouseClicked(MouseEvent mouseEvent) {
        calculation.setCurrentArgument(calculation.getCurrentArgument() + ((Button) mouseEvent.getSource()).getText());
        argumentsTextField.setText(calculation.getCurrentArgument());
    }

    @FXML
    public void addMouseClicked() {
        handleAdd();
    }

    @FXML
    public void substractionMouseClicked() {
        handleSubstract();
    }

    @FXML
    public void multMouseClicked() {
        handleMult();
    }

    @FXML
    public void divMouseClicked() {
        handleDiv();
    }

    @FXML
    public void moduloMouseClicked() {
        handleModulo();
    }

    @FXML
    public void powerMouseClicked() {
        handlePower();
    }

    @FXML
    public void newtonMouseClicked() {
        handleNewton();
    }

    public void handleAdd() {
        if (!Argument.checkIfLegalArgument(calculation.getCurrentArgument())) return;
        refreshCalculation();
        calculation.setOperation(new AddOperation(new Argument(calculation.getLatestResult(), calculation.getMode()), calculation.getMode()));
        refreshTextFields();
    }

    public void handleSubstract() {
        if (!Argument.checkIfLegalArgument(calculation.getCurrentArgument())) return;
        refreshCalculation();
        calculation.setOperation(new SubstractOperation(new Argument(calculation.getLatestResult(), calculation.getMode()), calculation.getMode()));
        refreshTextFields();
    }

    public void handleMult() {
        if (!Argument.checkIfLegalArgument(calculation.getCurrentArgument())) return;
        refreshCalculation();
        calculation.setOperation(new MultOperation(new Argument(calculation.getLatestResult(), calculation.getMode()), calculation.getMode()));
        refreshTextFields();
    }

    public void handleDiv() {
        if (!Argument.checkIfLegalArgument(calculation.getCurrentArgument())) return;
        refreshCalculation();
        calculation.setOperation(new DivOperation(new Argument(calculation.getLatestResult(), calculation.getMode()), calculation.getMode()));
        refreshTextFields();
    }

    public void handleModulo() {
        if (!Argument.checkIfLegalArgument(calculation.getCurrentArgument())) return;
        refreshCalculation();
        calculation.setOperation(new ModuloOperation(new Argument(calculation.getLatestResult(), calculation.getMode()), calculation.getMode()));
        refreshTextFields();
    }

    public void handlePower() {
        if (!Argument.checkIfLegalArgument(calculation.getCurrentArgument())) return;
        refreshCalculation();
        calculation.setOperation(new PowOperation(new Argument(calculation.getLatestResult(), calculation.getMode()), calculation.getMode()));
        refreshTextFields();
    }

    public void handleNewton() {
        if (!Argument.checkIfLegalArgument(calculation.getCurrentArgument())) return;
        refreshCalculation();
        calculation.setOperation(new NewtonOperation(new Argument(calculation.getLatestResult(), calculation.getMode()), calculation.getMode()));
        refreshTextFields();
    }

    public void refreshCalculation() {
        if (calculation.getLatestResult() == null) {
            calculation.setLatestResult(new Argument(calculation.getCurrentArgument(), calculation.getMode()).toString());
        } else {
            if (calculation.getCurrentArgument().equals("")) return;
            calculation.getOperation().setArg2(new Argument(calculation.getCurrentArgument(), calculation.getMode()));
            calculation.setLatestResult(calculation.getOperation().execute().toString(calculation.getMode()));

        }
    }

    public void refreshTextFields() {
        calculation.setCurrentArgument("");
        argumentsTextField.setText(calculation.getCurrentArgument());
        latestResultTextLabel.setText(calculation.getLatestResult());
        currentOperationLabel.setText(calculation.getOperation().getSign());
    }

    private void setSystem(int prevMode) {
        if (calculation.getLatestResult() == null) {
            if (!latestResultTextLabel.getText().equals("")) {
                latestResultTextLabel.setText(Calculation.convertResult(latestResultTextLabel.getText(), prevMode, mode));
            }
            calculation = new Calculation(mode);
            argumentsTextField.setText(calculation.getCurrentArgument());

        } else {
            int previousMode = calculation.getMode();
            String previousLatestResult = calculation.getLatestResult();
            Operation previousOperation = calculation.getOperation();

            calculation = new Calculation(mode);
            //calculation.setLatestResult((new BigInteger(previousLatestResult, previousMode)).toString());
            calculation.setLatestResult(Calculation.convertResult(previousLatestResult, previousMode, mode));
            calculation.setOperation(previousOperation);

            refreshTextFields();
        }

        System.out.println(calculation);
    }


    @FXML
    private Button oneBtn;
    @FXML
    private Button twoBtn;
    @FXML
    private Button threeBtn;
    @FXML
    private Button fourBtn;
    @FXML
    private Button fiveBtn;
    @FXML
    private Button sixBtn;
    @FXML
    private Button sevenBtn;
    @FXML
    private Button eightBtn;
    @FXML
    private Button nineBtn;
    @FXML
    private Button zeroBtn;
    @FXML
    private Button ABtn;
    @FXML
    private Button BBtn;
    @FXML
    private Button CBtn;
    @FXML
    private Button DBtn;
    @FXML
    private Button EBtn;
    @FXML
    private Button FBtn;
    @FXML
    private Button factoryBtn;
    @FXML
    private Button expBtn;


    private void setBinButtons(boolean turned) {
        boolean disable;
        double opacity;
        if (turned) {
            disable = false;
            opacity = 1;
        } else {
            disable = true;
            opacity = 0.5;
        }
        zeroBtn.setDisable(disable);
        zeroBtn.setOpacity(opacity);
        oneBtn.setDisable(disable);
        oneBtn.setOpacity(opacity);
    }

    private void setDecButtons(boolean turned) {
        boolean disable;
        double opacity;
        if (turned) {
            disable = false;
            opacity = 1;
            setBinButtons(turned);
        } else {
            disable = true;
            opacity = 0.5;
        }
        twoBtn.setDisable(disable);
        twoBtn.setOpacity(opacity);
        threeBtn.setDisable(disable);
        threeBtn.setOpacity(opacity);
        fourBtn.setDisable(disable);
        fourBtn.setOpacity(opacity);
        fiveBtn.setDisable(disable);
        fiveBtn.setOpacity(opacity);
        sixBtn.setDisable(disable);
        sixBtn.setOpacity(opacity);
        sevenBtn.setDisable(disable);
        sevenBtn.setOpacity(opacity);
        eightBtn.setDisable(disable);
        eightBtn.setOpacity(opacity);
        nineBtn.setDisable(disable);
        nineBtn.setOpacity(opacity);
        factoryBtn.setDisable(disable);
        factoryBtn.setOpacity(opacity);
        expBtn.setDisable(disable);
        expBtn.setOpacity(opacity);
    }

    public void setHexButtons(boolean turned) {
        boolean disable;
        double opacity;
        if (turned) {
            disable = false;
            opacity = 1;
            setBinButtons(turned);
            setDecButtons(turned);
        } else {
            disable = true;
            opacity = 0.5;
        }
        ABtn.setOpacity(opacity);
        ABtn.setDisable(disable);
        BBtn.setOpacity(opacity);
        BBtn.setDisable(disable);
        CBtn.setOpacity(opacity);
        CBtn.setDisable(disable);
        DBtn.setOpacity(opacity);
        DBtn.setDisable(disable);
        EBtn.setOpacity(opacity);
        EBtn.setDisable(disable);
        FBtn.setOpacity(opacity);
        FBtn.setDisable(disable);
    }

}
