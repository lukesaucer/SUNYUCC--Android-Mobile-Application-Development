package edu.sunyulster.flagtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import edu.sunyulster.flagtest.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    // String used when logging error messages
    private ActivityMainBinding binding;
    private List<String> fileNameList; // flag file names
    private List<String> testFlagsList; // countries in current quiz
    private static final int FLAGS_IN_QUIZ = 7;
    private Random random; // used to randomize the quiz
    private String correctAnswer; // correct country for the current flag
    private int correctAnswers; // number of correct guesses
    private TextView answerTextView; // displays correct answer
    private ImageView flagImageView; // displays a flag
    //private Button[] buttonArray; // button array
    private String TAG = "FlagTest";
    private Button[] buttonArray = new Button[3];
    private String roadFlagList[] = {"Lane Reduction", "Low Ground Clearance", "Road Narrows", "Hairpin Curve Ahead", "Sharp Curve To Right", "Divided Highway Ahead", "Very Sharp Curve Ahead"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate entered");
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        answerTextView = (TextView) binding.answerTextView;
        flagImageView = (ImageView) binding.flagImageView;
        random = new Random();
        fileNameList = new ArrayList<>();
        buttonArray[0] = binding.button1;
        buttonArray[1] = binding.button2;
        buttonArray[2] = binding.button3;
        for (Button button : buttonArray) {
            button.setOnClickListener(guessButtonListener);
        }
        binding.flagImageView.setOnClickListener(newTestListener);

        binding.flagImageView.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View flagImageView) {
                        binding.answerTextView.setText(correctAnswer +"!");
                        answerTextView.setTextColor(Color.GREEN);
                        Log.e(TAG, "correct answer");
                        return true;
                    }
                }
        );

        for (String name : roadFlagList) {
            fileNameList.add(name + ".png");
            Log.e(TAG, "add files to fileNamelist");
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        // Initialize the MainFragment and start the test
        Log.e(TAG, "onStart entered");
        try {
            resetTest();
        } catch (IOException e) {
            Log.e(TAG, "onStart exception");
        }
    }

    // set up and start the next test
    public void resetTest() throws IOException {
        Log.e(TAG, "resetTest entered");

        int flagCounter = 0;
        int numberOfFlags = fileNameList.size();

        // add FLAGS_IN_QUIZ random file names to the testCountriesList
        // flag to be shown is [0] entry
        // get a random file name
        int randomIndex = random.nextInt(numberOfFlags);
        String flag = roadFlagList[randomIndex];
        testFlagsList = new ArrayList<String>(); // clear list
        testFlagsList.add(0, flag);      // set "to be shown" as [0]

        // set remaining names for buttons

        for (int i = 1; i < 3; i++) {
            if (!testFlagsList.contains(roadFlagList[i]))
                testFlagsList.add(roadFlagList[i]); // add the flag to the list
        }
        showFlag(); // start the quiz by loading the first flag
    }


    // after the user guesses a correct flag, load the next flag
    private void showFlag() {
        Log.e(TAG, "showFlag entered");
        // get file name of the next flag and remove it from the list
        String nextFlag = testFlagsList.remove(0);
        correctAnswer = nextFlag; // update the correct answer
        answerTextView.setText(""); // clear answerTextView

        // use AssetManager to load next image from assets folder
        AssetManager assets = getApplicationContext().getAssets();

        // get an InputStream to the asset representing the next flag
        // and try to use the InputStream
        try {
            // load the asset as a Drawable and display on the flagImageView
            String fileName = nextFlag + ".png";
            Log.e(TAG, "loading file name: " + fileName);
            InputStream stream = assets.open(fileName);
            Drawable flag = Drawable.createFromStream(stream, fileName);
            flagImageView.setImageDrawable(flag);


        } catch (IOException exception) {
            Log.e(TAG, "Error loading " + nextFlag, exception);
        }


        Collections.shuffle(fileNameList); // shuffle file names

        // remove the correct answer from the list        fileNameList.remove(correctAnswer);

        // get country name and set it as newGuessButton's text

        for (int i = 0; i < 3; i++) {
            String flag = roadFlagList[i];
            buttonArray[i].setText(flag);
        }


        // randomly replace one Button with the correct answer
        int j = random.nextInt(3); // pick random button
        Button thisOne = buttonArray[j];
        buttonArray[j].setText(correctAnswer);
        Log.e(TAG, "correctAnswer: " + Integer.toString(j));
    }


    // called when a guess Button is touched
    private OnClickListener guessButtonListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.e(TAG, "onClick entered");
            Button guessButton = ((Button) v);
            String guess = guessButton.getText().toString();

            if (guess.equals(correctAnswer)) { // if the guess is correct
                // display correct answer in green text
                answerTextView.setText(correctAnswer + "!");
                answerTextView.setTextColor(Color.GREEN);
                Log.e(TAG, "correct answer");
            } else { // answer was incorrect
                // display "Incorrect!" in red
                answerTextView.setText(R.string.incorrect_answer);
                answerTextView.setTextColor(Color.RED);
                Log.e(TAG, "incorrect answer");
            }
        }
    };

    private OnClickListener newTestListener = new OnClickListener(){
        public void onClick(View v){
            Log.e(TAG, "newTestListener entered");
            try {
                resetTest();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    };


}