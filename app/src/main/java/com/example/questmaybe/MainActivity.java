package com.example.questmaybe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public int money = 0;
    public int rating = 0;
    public int magaz = 0;
    public int day = 0;
    public int count_of_some_shit = 1;

    static int randomInARange(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        money = 800;
        rating = 50;
        magaz = 1;
        day = 0;
        setContentView(R.layout.activity_main);
        TextView history = (TextView) findViewById(R.id.history);
        Button button = (Button) findViewById(R.id.kupit_magaz);
        button.setVisibility(View.GONE);
        history.setText("История:\n" +
                "    Вы маленький предприниматель, но в будущем можете стать большим. У вас есть " +
                "1 магазин, 800 шекелей и репутация 50%. Вы продаёте копуктеры. Один такой стоит " +
                "90 шекелей, но все думаю, что 100 шекелей. Вы можете продавать за разную цену, но от " +
                "этого будет зависеть ваше лаве и репутация. Так же вы можете покупать магазины" +
                ". Стоимость зависит от количества магазинов, изначальная стоимость 10000 шекелей. " +
                "Так же каждый месяц(30) надо " +
                "плОтить налоги.(1000)\n" +
                "Новый день:\n" +
                "    К вам пришло " + magaz + " людей. За сколько хотите им продать компы(" +
                "не советую за овер прайс):");
        update();
    }

    public void update() {
        day++;
        TextView new_t1 = (TextView) findViewById(R.id.lave);
        TextView new_t2 = (TextView) findViewById(R.id.magaz);
        TextView new_t3 = (TextView) findViewById(R.id.rep);
        TextView new_t4 = (TextView) findViewById(R.id.day);
        new_t1.setText("Лаве:\n" + money + " шекелей");
        new_t2.setText("Количесвто магазов: " + magaz);
        new_t3.setText("Репутация: " + rating + "%");
        new_t4.setText("День: " + day);
    }

    public void sell(View v) {
        money -= 90 * magaz;
        TextView history = (TextView) findViewById(R.id.history);
        EditText hz = (EditText) findViewById(R.id.cost);
        String l = hz.getText().toString();
        hz.setText("");
        int cost = Integer.parseInt(l);
        if (cost > 500) {
            history.setText("История:\n" +
                    "Ответ покупателя:\n    Неееееееееее, слишком дорого.\n" +
                    "Новый день:\n" +
                    "    К вам пришло " + magaz + " людей. За сколько хотите им продать компы(" +
                    "не советую за овер прайс):");
            rating -= Math.abs(100 - cost) / magaz * 2;
        }
        else {
            if (cost > 100) {
                boolean[] list = new boolean[100];
                for (int i = 0; i < Math.round(rating); i++){
                    list[i] = true;
                }
                for (int i = Math.round(rating); i < 100; i++){
                    list[i] = false;
                }
                if (list[randomInARange(0, 99)]){
                    history.setText("История:\n" +
                            "Ответ покупателя:\n    Хорошо, договорилис, на цену: " + cost + "\n" +
                            "Новый день:\n" +
                            "    К вам пришло " + magaz + " людей. За сколько хотите им продать компы(" +
                            "не советую за овер прайс):");
                    rating -= Math.abs(100 - cost) / magaz;
                    money += cost * magaz;
                }
                else {
                    history.setText("История:\n" +
                            "Ответ покупателя:\n    Неееееееееее, слишком дорого.\n" +
                            "Новый день:\n" +
                            "    К вам пришло " + magaz + " людей. За сколько хотите им продать компы(" +
                            "не советую за овер прайс):");
                    rating -= Math.abs(100 - cost) / magaz * 2;
                }
            } else {
                rating += Math.abs(100 - cost) / magaz;
                history.setText("История:\n" +
                        "Ответ покупателя:\n    Хорошо, договорились.\n" +
                        "Новый день:\n" +
                        "    К вам пришло " + magaz + " людей. За сколько хотите им продать компы(" +
                        "не советую за овер прайс):");
                money += cost * magaz;
            }
        }
        if (rating < 0){
            rating = 0;
        }
        if (day % 30 == 0){
            money -= magaz * 1000;
        }
        if (money >= 10000){
            Button button = (Button)findViewById(R.id.kupit_magaz);
            button.setVisibility(View.VISIBLE);
        }
        if (money < 0){
            money = 0;
        }
        if (rating > 100){
            rating = 100;
        }
        if (money < 90 * magaz){
            history.setText("История:\n" +
                    "Сорри вы слили все бабки и денег на новые компы нема");
            Button button = (Button)findViewById(R.id.prodat);
            button.setVisibility(View.GONE);
        }
        update();
//        Button button = (Button)findViewById(R.id.kupit_magaz);
//        if (count_of_some_shit % 2 == 0){
//            button.setVisibility(View.GONE);
//        }
//        else {
//            button.setVisibility(View.VISIBLE);
//        }
    }

    public void kupit_magaz(View v) {
        money -= 10000;
        magaz++;
        Button button = (Button)findViewById(R.id.kupit_magaz);
        button.setVisibility(View.GONE);
        update();
    }
}