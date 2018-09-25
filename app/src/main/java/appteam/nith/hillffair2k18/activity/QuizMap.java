//package appteam.nith.hillffair2k18.activity;
//
///**
// * Created by LENOVO on 25-09-2018.
// */
//
//import android.widget.ImageButton;
//
//import appteam.nith.hillffair2k18.R;
//
//        import android.content.Intent;
//        import android.support.v7.app.AppCompatActivity;
//        import android.os.Bundle;
//        import android.view.View;
//        import android.widget.ImageButton;
//
//        import appteam.nith.hillffair2k18.R;
//
//public class QuizMap extends AppCompatActivity {
//
//    int[] QuizState = {0, 0, 0, 0, 0, 0, 0};
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_quiz_map);
//
//        getQuizState();
//    }
//
//    public void startQuiz(View view) {
//        Intent intent = new Intent(this, Quiz.class);
//
////              Code to fetch questions from server
//
//        startActivity(intent);
//    }
//
//    private void setQuizIcons() {
//        //Code to set icons for the quiz to be the number(available),
//        // a tick(attempted) or a cross(missed)
//
//        for (int i = 0; i < 7; i++) {
//            if (QuizState[i] == 0) {
//                setCross(i);
//            } else if (QuizState[i] == 2) {
//                setTick(i);
//            } else {
//                ImageButton imageButton;
//                switch (i) {
//                    case 1:
//                        imageButton = findViewById(R.id.quiz1_button);
////                        imageButton.setBackground();
//                        break;
//                    case 2:
//                        imageButton = findViewById(R.id.quiz2_button);
////                        imageButton.setBackground();
//                        break;
//                    case 3:
//                        imageButton = findViewById(R.id.quiz3_button);
////                        imageButton.setBackground();
//                        break;
//                    case 4:
//                        imageButton = findViewById(R.id.quiz4_button);
////                        imageButton.setBackground();
//                        break;
//                    case 5:
//                        imageButton = findViewById(R.id.quiz5_button);
////                        imageButton.setBackground();
//                        break;
//                    case 6:
//                        imageButton = findViewById(R.id.quiz6_button);
////                        imageButton.setBackground();
//                        break;
//                    case 7:
//                        imageButton = findViewById(R.id.quiz7_button);
////                        imageButton.setBackground();
//                        break;
//                    default:
//                        break;
//                }
//            }
//        }
//    }
//
//
//    private void getQuizState() {
//
//        //Fetch state of quiz from server i.e which quizzes are finished
//        // , what the user has participated in etc
//    }
//
//    private void fetchQuestions() {
//
//        // Fetch questions and answers from server
//
//    }
//
//    private void setTick(int Num) {
//        ImageButton imageButton;
//        switch (Num) {
//            case 1:
//                imageButton = findViewById(R.id.quiz1_button);
//                break;
//            case 2:
//                imageButton = findViewById(R.id.quiz2_button);
//                break;
//            case 3:
//                imageButton = findViewById(R.id.quiz3_button);
//                break;
//            case 4:
//                imageButton = findViewById(R.id.quiz4_button);
//                break;
//            case 5:
//                imageButton = findViewById(R.id.quiz5_button);
//                break;
//            case 6:
//                imageButton = findViewById(R.id.quiz6_button);
//                break;
//            case 7:
//                imageButton = findViewById(R.id.quiz7_button);
//                break;
//            default:
//                break;
//        }
////        imageButton.setBackground("");
//        //Add tick image and put path here
//    }
//
//    private void setCross(int Num) {
//        ImageButton imageButton;
//        switch (Num) {
//            case 1:
//                imageButton = findViewById(R.id.quiz1_button);
//                break;
//            case 2:
//                imageButton = findViewById(R.id.quiz2_button);
//                break;
//            case 3:
//                imageButton = findViewById(R.id.quiz3_button);
//                break;
//            case 4:
//                imageButton = findViewById(R.id.quiz4_button);
//                break;
//            case 5:
//                imageButton = findViewById(R.id.quiz5_button);
//                break;
//            case 6:
//                imageButton = findViewById(R.id.quiz6_button);
//                break;
//            case 7:
//                imageButton = findViewById(R.id.quiz7_button);
//                break;
//            default:
//                break;
//        }
////        imageButton.setBackground("");
////        Add cross image and put path here
//    }
//}