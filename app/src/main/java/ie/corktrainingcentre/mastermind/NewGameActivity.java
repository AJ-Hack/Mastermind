package ie.corktrainingcentre.mastermind;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import static java.lang.System.currentTimeMillis;

public class NewGameActivity extends AppCompatActivity implements OnClickListener {

    Resources res;

    //region TableLayouts
    TableLayout table1;
    TableLayout table2;
    TableLayout table3;
    TableLayout table4;
    TableLayout table5;
    TableLayout table6;
    TableLayout table7;
    TableLayout table8;
    TableLayout table9;
    TableLayout table10;
    TableLayout table11;
    TableLayout table12;
    //endregion

    //region TextViews
    private TextView peg1_1;
    private TextView peg1_2;
    private TextView peg1_3;
    private TextView peg1_4;

    private TextView peg2_1;
    private TextView peg2_2;
    private TextView peg2_3;
    private TextView peg2_4;

    private TextView peg3_1;
    private TextView peg3_2;
    private TextView peg3_3;
    private TextView peg3_4;

    private TextView peg4_1;
    private TextView peg4_2;
    private TextView peg4_3;
    private TextView peg4_4;

    private TextView peg5_1;
    private TextView peg5_2;
    private TextView peg5_3;
    private TextView peg5_4;

    private TextView peg6_1;
    private TextView peg6_2;
    private TextView peg6_3;
    private TextView peg6_4;

    private TextView peg7_1;
    private TextView peg7_2;
    private TextView peg7_3;
    private TextView peg7_4;

    private TextView peg8_1;
    private TextView peg8_2;
    private TextView peg8_3;
    private TextView peg8_4;

    private TextView peg9_1;
    private TextView peg9_2;
    private TextView peg9_3;
    private TextView peg9_4;

    private TextView peg10_1;
    private TextView peg10_2;
    private TextView peg10_3;
    private TextView peg10_4;

    private TextView peg11_1;
    private TextView peg11_2;
    private TextView peg11_3;
    private TextView peg11_4;

    private TextView peg12_1;
    private TextView peg12_2;
    private TextView peg12_3;
    private TextView peg12_4;
    //endregion

    // region Private Variables
    private long startTime;
    private long endtime;
    private long timeTaken;
    private long hours;
    private long minutes;
    private long seconds;

    private LinearLayout lytCode;

    private List<TableLayout> tables;
    private List<TextView> hints;
    private Map<String, TextView> pegs;

    private List<TextView> currentPegsRow;
    private List<TextView> currentHintsRow;
    private List<TextView> currentCode;

    private TextView currentlySelectedPeg;
    private int defaultPegColor;

    private int currentRow = 1;
    private int currentPeg = 1;
    private int pegsSetSinceLastCheck = 0;
    // endregion

    // region onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        getWindow().getDecorView().setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));

        res = getResources();

        setupBoard();
    }
    // endregion

    // region Listeners
    public void btnRedClicked(View v) {
        if (this.currentlySelectedPeg.getText().equals("1-1")) {
            this.startTime = currentTimeMillis();
        }

        if (incrementPegsSetSinceLastCheck()) {
            this.currentlySelectedPeg.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
            getNextPeg();
        }
    }

    public void btnGreenClicked(View v) {
        if (this.currentlySelectedPeg.getText().equals("1-1")) {
            this.startTime = currentTimeMillis();
        }

        if (incrementPegsSetSinceLastCheck()) {
            this.currentlySelectedPeg.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
            getNextPeg();
        }
    }

    public void btnBlueClicked(View v) {
        if (this.currentlySelectedPeg.getText().equals("1-1")) {
            this.startTime = currentTimeMillis();
        }

        if (incrementPegsSetSinceLastCheck()) {
            this.currentlySelectedPeg.setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
            getNextPeg();
        }
    }

    public void btnYellowClicked(View v) {
        if (this.currentlySelectedPeg.getText().equals("1-1")) {
            this.startTime = currentTimeMillis();
        }

        if (incrementPegsSetSinceLastCheck()) {
            this.currentlySelectedPeg.setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
            getNextPeg();
        }
    }

    public void btnCyanClicked(View v) {
        if (this.currentlySelectedPeg.getText().equals("1-1")) {
            this.startTime = currentTimeMillis();
        }

        if (incrementPegsSetSinceLastCheck()) {
            this.currentlySelectedPeg.setBackgroundColor(ContextCompat.getColor(this, R.color.cyan));
            getNextPeg();
        }
    }

    public void btnMagentaClicked(View v) {
        if (this.currentlySelectedPeg.getText().equals("1-1")) {
            this.startTime = currentTimeMillis();
        }

        if (incrementPegsSetSinceLastCheck()) {
            this.currentlySelectedPeg.setBackgroundColor(ContextCompat.getColor(this, R.color.magenta));
            getNextPeg();
        }
    }

    public void btnSubmitClicked(View v) {
        for (TextView peg : currentPegsRow) {
            if (!isPegSet(peg)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("Note")
                        .setMessage( "All pegs have not been placed for this row.")
                        .setCancelable(true)
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
                return;
            }
        }

        if (checkGuess()) {
            this.endtime = currentTimeMillis();
            this.timeTaken = (endtime - startTime) / 1000;
            this.hours = (this.timeTaken / 3600);
            this.minutes = (this.timeTaken % 3600) / 60;
            this.seconds = this.timeTaken % 60;


            Log.d("Result", "Winner!");

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Victory")
                   .setCancelable(true)
                   .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            finish();
                        }
                    });

            if (currentRow == 1) {
                builder.setMessage( "You won the game in " + this.currentRow + " turn.\n\n" +
                                    "Time to complete: " + this.hours + ":" + this.minutes + ":" +
                                    this.seconds);
            }
            else {
                builder.setMessage( "You won the game in " + this.currentRow + " turns.\n\n" +
                                    "Time to complete: " + this.hours + ":" + this.minutes + ":" +
                                    this.seconds);
            }

            AlertDialog alert = builder.create();
            alert.show();
        }
        else {
            getNextRow();
        }
    }

    public void btnToggleDebugClicked(View v) {
        if (lytCode == null) lytCode = (LinearLayout) findViewById(R.id.layout_code);

        if (Settings.DEBUG_MODE == 0) {
            Settings.DEBUG_MODE = 1;

            for (TextView codePeg : currentCode) {
                if (codePeg.getText().equals("TEST")) {
                    codePeg.setVisibility(View.VISIBLE);
                }
                else {
                    TableRow.LayoutParams codeParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 2f);
                    codePeg.setLayoutParams(codeParams);
                    codePeg.setText("TEST");
                    codePeg.setGravity(Gravity.CENTER);
                    lytCode.addView(codePeg);
                }
            }
        }
        else {
            Settings.DEBUG_MODE = 0;

            for (TextView codePeg : currentCode) {
                codePeg.setVisibility(View.INVISIBLE);
            }
        }


    }

    public void onClick(View v) {
        String currentTag = (String)v.getTag();

        Log.d("Is " + currentTag + " equal to " + currentRow + "-1?", "" + (currentTag.equals(currentRow + "-1")));

        if (currentTag.equals(currentRow + "-1") || currentTag.equals(currentRow + "-2") ||
            currentTag.equals(currentRow + "-3") || currentTag.equals(currentRow + "-4")) {

            currentlySelectedPeg = (TextView) v;

            if (isPegSet(currentlySelectedPeg) && decrementPegsSetSinceLastCheck()) {
                currentlySelectedPeg.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
            }

            currentPeg = Integer.parseInt(currentTag.substring(currentTag.length() - 1));

            if (currentTag.length() == 3) {
                currentRow = Integer.parseInt(currentTag.substring(0, 1));
            } else if (currentTag.length() == 4) {
                currentRow = Integer.parseInt(currentTag.substring(0, 2));
            }
        }
    }
    // endregion
    
    // region Private Methods
    private void getNextPeg() {
//        if (currentPeg < 4) {
//            currentPeg++;
//        }
//        else if (currentPeg >= 4) {
//            if (currentRow < 12) {
//                currentRow++;
//                currentPeg = 1;
//            }
//            else {
//                // Game Over
//            }
//        }
//        currentlySelectedPeg = pegs.get(currentRow + "-" + currentPeg);

        for (TextView peg : currentPegsRow) {
            if (!isPegSet(peg)) {
                String currentTag = (String)peg.getTag();
                this.currentPeg = Integer.parseInt(currentTag.substring(currentTag.length() - 1));

                this.currentlySelectedPeg = pegs.get(this.currentRow + "-" + this.currentPeg);
                break;
            }
        }
    }

    private void getPreviousPeg() {
        if (currentPeg > 1) {
            currentPeg--;
        }
        else if (currentPeg <= 1) {
            if (currentRow > 1) {
                currentRow--;
                currentPeg = 4;
            }
        }
        currentlySelectedPeg = pegs.get(currentRow + "-" + currentPeg);
    }

    private void getNextRow() {
        currentRow++;
        pegsSetSinceLastCheck = 0;
        getCurrentRowAsList();
        getNextPeg();
    }

    private void initialiseTableLayouts() {
        table1 = (TableLayout)findViewById(R.id.row3);
        table2 = (TableLayout)findViewById(R.id.row3);
        table3 = (TableLayout)findViewById(R.id.row3);
        table4 = (TableLayout)findViewById(R.id.row3);
        table5 = (TableLayout)findViewById(R.id.row3);
        table6 = (TableLayout)findViewById(R.id.row3);
        table7 = (TableLayout)findViewById(R.id.row3);
        table8 = (TableLayout)findViewById(R.id.row3);
        table9 = (TableLayout)findViewById(R.id.row3);
        table10 = (TableLayout)findViewById(R.id.row3);
        table11 = (TableLayout)findViewById(R.id.row3);
        table12 = (TableLayout)findViewById(R.id.row3);
    }
    
    private void populateTableLayoutList() {
        this.tables = new ArrayList<>();
        this.tables.add(table1);
        this.tables.add(table2);
        this.tables.add(table3);
        this.tables.add(table4);
        this.tables.add(table5);
        this.tables.add(table6);
        this.tables.add(table7);
        this.tables.add(table8);
        this.tables.add(table9);
        this.tables.add(table10);
        this.tables.add(table11);
        this.tables.add(table12);
    }

    private void initialiseHints() {
        hints = new ArrayList<>();
        // Populated in populateRows
    }

    private void initialisePegs() {
        peg1_1 = new TextView(this);
        peg1_2 = new TextView(this);
        peg1_3 = new TextView(this);
        peg1_4 = new TextView(this);

        peg2_1 = new TextView(this);
        peg2_2 = new TextView(this);
        peg2_3 = new TextView(this);
        peg2_4 = new TextView(this);

        peg3_1 = new TextView(this);
        peg3_2 = new TextView(this);
        peg3_3 = new TextView(this);
        peg3_4 = new TextView(this);

        peg4_1 = new TextView(this);
        peg4_2 = new TextView(this);
        peg4_3 = new TextView(this);
        peg4_4 = new TextView(this);

        peg5_1 = new TextView(this);
        peg5_2 = new TextView(this);
        peg5_3 = new TextView(this);
        peg5_4 = new TextView(this);

        peg6_1 = new TextView(this);
        peg6_2 = new TextView(this);
        peg6_3 = new TextView(this);
        peg6_4 = new TextView(this);

        peg7_1 = new TextView(this);
        peg7_2 = new TextView(this);
        peg7_3 = new TextView(this);
        peg7_4 = new TextView(this);

        peg8_1 = new TextView(this);
        peg8_2 = new TextView(this);
        peg8_3 = new TextView(this);
        peg8_4 = new TextView(this);

        peg9_1 = new TextView(this);
        peg9_2 = new TextView(this);
        peg9_3 = new TextView(this);
        peg9_4 = new TextView(this);

        peg10_1 = new TextView(this);
        peg10_2 = new TextView(this);
        peg10_3 = new TextView(this);
        peg10_4 = new TextView(this);

        peg11_1 = new TextView(this);
        peg11_2 = new TextView(this);
        peg11_3 = new TextView(this);
        peg11_4 = new TextView(this);

        peg12_1 = new TextView(this);
        peg12_2 = new TextView(this);
        peg12_3 = new TextView(this);
        peg12_4 = new TextView(this);
    }
    
    private void populatePegsMap() {
        this.pegs = new HashMap<>();
        
        this.pegs.put("1-1", peg1_1);
        this.pegs.put("1-2", peg1_2);
        this.pegs.put("1-3", peg1_3);
        this.pegs.put("1-4", peg1_4);

        this.pegs.put("2-1", peg2_1);
        this.pegs.put("2-2", peg2_2);
        this.pegs.put("2-3", peg2_3);
        this.pegs.put("2-4", peg2_4);

        this.pegs.put("3-1", peg3_1);
        this.pegs.put("3-2", peg3_2);
        this.pegs.put("3-3", peg3_3);
        this.pegs.put("3-4", peg3_4);

        this.pegs.put("4-1", peg4_1);
        this.pegs.put("4-2", peg4_2);
        this.pegs.put("4-3", peg4_3);
        this.pegs.put("4-4", peg4_4);

        this.pegs.put("5-1", peg5_1);
        this.pegs.put("5-2", peg5_2);
        this.pegs.put("5-3", peg5_3);
        this.pegs.put("5-4", peg5_4);

        this.pegs.put("6-1", peg6_1);
        this.pegs.put("6-2", peg6_2);
        this.pegs.put("6-3", peg6_3);
        this.pegs.put("6-4", peg6_4);

        this.pegs.put("7-1", peg7_1);
        this.pegs.put("7-2", peg7_2);
        this.pegs.put("7-3", peg7_3);
        this.pegs.put("7-4", peg7_4);

        this.pegs.put("8-1", peg8_1);
        this.pegs.put("8-2", peg8_2);
        this.pegs.put("8-3", peg8_3);
        this.pegs.put("8-4", peg8_4);

        this.pegs.put("9-1", peg9_1);
        this.pegs.put("9-2", peg9_2);
        this.pegs.put("9-3", peg9_3);
        this.pegs.put("9-4", peg9_4);

        this.pegs.put("10-1", peg10_1);
        this.pegs.put("10-2", peg10_2);
        this.pegs.put("10-3", peg10_3);
        this.pegs.put("10-4", peg10_4);

        this.pegs.put("11-1", peg11_1);
        this.pegs.put("11-2", peg11_2);
        this.pegs.put("11-3", peg11_3);
        this.pegs.put("11-4", peg11_4);

        this.pegs.put("12-1", peg12_1);
        this.pegs.put("12-2", peg12_2);
        this.pegs.put("12-3", peg12_3);
        this.pegs.put("12-4", peg12_4);

        for (TextView txt : this.pegs.values()) {
            txt.setTag(getKeyByValue(this.pegs, txt));
            Log.d(txt.toString(), (String)txt.getTag());
        }
    }

    private void populateRows() {
        int rowNumber = 1;

        for (TableLayout currentTable : this.tables) {

            TableRow currentRow = new TableRow(this);
            currentTable.addView(currentRow);

            for (int i = 0; i < 4; i++) {
                TextView currentHint = new TextView(this);
                currentRow.addView(currentHint);
                hints.add(currentHint);
            }

            String firstKey = rowNumber + "-1";
            String secondKey = rowNumber + "-2";
            String thirdKey = rowNumber + "-3";
            String fourthKey = rowNumber + "-4";

            currentRow.addView(pegs.get(firstKey));
            currentRow.addView(pegs.get(secondKey));
            currentRow.addView(pegs.get(thirdKey));
            currentRow.addView(pegs.get(fourthKey));

            rowNumber++;
        }

        // Debug View For Code
        if (Settings.DEBUG_MODE == 1) {
            if (lytCode == null) lytCode = (LinearLayout) findViewById(R.id.layout_code);
            for (TextView codePeg : currentCode) {
                TableRow.LayoutParams codeParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 2f);
                codePeg.setLayoutParams(codeParams);
                codePeg.setText("TEST");
                codePeg.setGravity(Gravity.CENTER);
                lytCode.addView(codePeg);
            }
        }
    }

    private void setPegParams() {
        for (TextView peg : this.pegs.values()) {
//            peg.getLayoutParams().height = TableRow.LayoutParams.WRAP_CONTENT;
//            peg.getLayoutParams().width = TableRow.LayoutParams.WRAP_CONTENT;
            TableRow.LayoutParams pegParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 2f);
            peg.setLayoutParams(pegParams);
            peg.setText((String)peg.getTag());
            peg.setGravity(Gravity.CENTER);
            peg.setOnClickListener(this);
            peg.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        
        for (TextView hint : this.hints) {
            TableRow.LayoutParams hintParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
            hint.setLayoutParams(hintParams);
            hint.setText("" + (hints.indexOf(hint) + 1));
            hint.setGravity(Gravity.CENTER);
            hint.setOnClickListener(this);
        }
    }

    private void setupBoard() {
        generateGuess();
        initialiseTableLayouts();
        populateTableLayoutList();
        initialiseHints();
        initialisePegs();
        populatePegsMap();
        populateRows();
        setPegParams();
        getCurrentRowAsList();

        currentlySelectedPeg = pegs.get("1-1");

        defaultPegColor = getPegColor(currentlySelectedPeg);
    }

    private boolean incrementPegsSetSinceLastCheck() {
        if (pegsSetSinceLastCheck < 4) {
            pegsSetSinceLastCheck++;
            return true;
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Note")
                    .setMessage( "All pegs have been placed for this row.\n\n" +
                            "Please submit selection or remove some pegs.")
                    .setCancelable(true)
                    .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();
            return false;
        }
    }

    private boolean decrementPegsSetSinceLastCheck() {
        if (pegsSetSinceLastCheck > 0) {
            pegsSetSinceLastCheck--;
            return true;
        }
        return false;
    }

    private void getCurrentRowAsList() {
        currentPegsRow = new ArrayList<TextView>();
        currentPegsRow.add(pegs.get(currentRow + "-1"));
        currentPegsRow.add(pegs.get(currentRow + "-2"));
        currentPegsRow.add(pegs.get(currentRow + "-3"));
        currentPegsRow.add(pegs.get(currentRow + "-4"));
    }

    private void getCurrentHintsList() {
        currentHintsRow = new ArrayList<TextView>();

        int firstHintIdentifier = ((currentRow - 1) * 4);

        Log.d("Hint Humbers", "" + firstHintIdentifier + " " + (firstHintIdentifier + 1) +
                              " " + (firstHintIdentifier + 2) + " " + (firstHintIdentifier + 3));

        currentHintsRow.add(hints.get(firstHintIdentifier));
        currentHintsRow.add(hints.get(firstHintIdentifier + 1));
        currentHintsRow.add(hints.get(firstHintIdentifier + 2));
        currentHintsRow.add(hints.get(firstHintIdentifier + 3));
    }

    private boolean isPegSet(View v) {
        int currentColor = getPegColor(v);

        Log.d("currentColor is " + currentColor, "defaultPegColor is " + defaultPegColor);
        Log.d("isPegSet", "" + (currentColor != defaultPegColor));

        return (currentColor != defaultPegColor);
    }

    private int getPegColor(View v) {
        ColorDrawable currentBackground = (ColorDrawable)v.getBackground();
        return currentBackground.getColor();
    }

    private String getKeyByValue(Map<String, TextView> pegs, TextView peg) {
        for (Entry<String, TextView> entry : pegs.entrySet()) {
            if (pegs.get(entry.getKey()).equals(peg)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private void generateGuess() {
        currentCode = new ArrayList<TextView>();

        Random rand = new Random();

        for (int i = 0; i < 4; i++) {
            int random = rand.nextInt(6);
            TextView codePeg = new TextView(this);
            switch (random) {

                case 0: codePeg.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
                        currentCode.add(codePeg);
                        break;

                case 1: codePeg.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
                        currentCode.add(codePeg);
                        break;

                case 2: codePeg.setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
                        currentCode.add(codePeg);
                        break;

                case 3: codePeg.setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
                        currentCode.add(codePeg);
                        break;

                case 4: codePeg.setBackgroundColor(ContextCompat.getColor(this, R.color.cyan));
                        currentCode.add(codePeg);
                        break;

                case 5: codePeg.setBackgroundColor(ContextCompat.getColor(this, R.color.magenta));
                        currentCode.add(codePeg);
                        break;
            }
        }

        Log.d("Current Code", "" + getPegColor(currentCode.get(0)));
        Log.d("Current Code", "" + getPegColor(currentCode.get(1)));
        Log.d("Current Code", "" + getPegColor(currentCode.get(2)));
        Log.d("Current Code", "" + getPegColor(currentCode.get(3)));
    }

    private boolean checkGuess() {
        List<Boolean> checks = new ArrayList<Boolean>();
        int numberOfCorrectGuesses = 0;
        int numberOfCorrectPegsInWrongPlace = 0;

        getCurrentHintsList();

        List<TextView> currentPegsRowRemainder = new ArrayList<TextView>();
        List<TextView> currentCodeRemainder = new ArrayList<TextView>();

        for (int i = 0; i < 4; i++) {
            if (getPegColor(currentPegsRow.get(i)) == getPegColor(currentCode.get(i))) {
                checks.add(true);
                numberOfCorrectGuesses++;
            }
            else {
                currentPegsRowRemainder.add(currentPegsRow.get(i));
                currentCodeRemainder.add(currentCode.get(i));
                checks.add(false);
            }
        }

        TextView pegToRemove = null;
        boolean removeCurrent = false;

        for (TextView incorrectGuessPeg : currentPegsRowRemainder) {

            for (TextView remainingCodePeg : currentCodeRemainder) {
                if (getPegColor(incorrectGuessPeg) == getPegColor(remainingCodePeg)) {
                    numberOfCorrectPegsInWrongPlace++;
                    pegToRemove = remainingCodePeg;
                    removeCurrent = true;
                    break;
                }
            }

            if (removeCurrent) {
                currentCodeRemainder.remove(pegToRemove);
            }
        }

        if (checks.contains(false)) {
            populateHintsRow(numberOfCorrectGuesses, numberOfCorrectPegsInWrongPlace);
            return false;
        }
        else {
            populateHintsRow(numberOfCorrectGuesses, numberOfCorrectPegsInWrongPlace);
            return true;
        }
    }

    private void populateHintsRow(int numberOfCorrectGuesses, int numberOfCorrectPegsInWrongPlace) {
        for (TextView hint : currentHintsRow) {
            if (numberOfCorrectGuesses > 0) {
                hint.setBackgroundColor(ContextCompat.getColor(this, R.color.rightPegRightPlace));
                hint.setTextColor(ContextCompat.getColor(this, R.color.rightPegWrongPlace));
                hint.setTag("SET");
                numberOfCorrectGuesses--;
            }
        }

        for (TextView hint : currentHintsRow) {
            if (numberOfCorrectPegsInWrongPlace > 0) {
                if (hint.getTag() == null) {
                    hint.setBackgroundColor(ContextCompat.getColor(this, R.color.rightPegWrongPlace));
                    numberOfCorrectPegsInWrongPlace--;
                }
            }
        }
    }
    // endregion
}
