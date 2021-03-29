package net.firestork.zombiinthehouseplayingroulette;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView moveResult;
    private TextView battleResult;
    private TextView textViewMove;
    private TextView textViewBattle;
    private ImageView rul;
    private Random random;
    private int old_deegre = 0;
    private int deegre = 0;
    private String[] move = {"На 1 клетку","На 2 клетки","На 3 клетки","На 4 клетки"};
    private String[] battle = {"Ты убегаешь, крути рулетку еще раз","Тебя укусили отдай 1 жизнь","Используй холодное оружие","Используй огнестрельное оружие"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void onClickStart(View view)
    {
        old_deegre = deegre % 360;
        deegre = random.nextInt(3600) + 720;
        RotateAnimation rotate = new RotateAnimation(old_deegre,deegre,RotateAnimation.RELATIVE_TO_SELF,
                0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        rotate.setDuration(3600);
        rotate.setFillAfter(true);
        rotate.setInterpolator(new DecelerateInterpolator());
        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                moveResult.setText("");
                battleResult.setText("");
                textViewMove.setText("");
                textViewBattle.setText("");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                moveResult.setText(getMResult(getNumber(360 - (deegre % 360)), "move"));
                battleResult.setText(getMResult(getNumber(360 - (deegre % 360)), "battle"));
                textViewMove.setText("Ход");
                textViewBattle.setText("Бой");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        rul.startAnimation(rotate);

    }

    private void init()
    {
        moveResult = findViewById(R.id.moveResult);
        battleResult = findViewById(R.id.battleResult);
        textViewMove = findViewById(R.id.textViewMove);
        textViewBattle = findViewById(R.id.textViewBattle);
        rul = findViewById(R.id.rul);

        moveResult.setText("");
        battleResult.setText("");
        textViewMove.setText("");
        textViewBattle.setText("");

        random = new Random();
        Toast toast = Toast.makeText(getApplicationContext(),
                "Для старта коснитесь рулетки", Toast.LENGTH_SHORT);
        toast.show();
    }

    private Integer getNumber(int deegre)
    {

        Integer number = 0;
        if (deegre >= 0 && deegre <= 120) {
            number = 0;
        } else if (deegre >= 121 && deegre <= 240) {
            number = 1;
        } else if (deegre >= 241 && deegre <= 300) {
            number = 2;
        } else {
            number = 3;
        }
        return number;
    }

    private String getMResult(int number, String arrType)
    {
        String text = "";
        if (arrType == "move") {
            text = move[number];
        } else {
            text = battle[number];
        }
        return text;
    }


}