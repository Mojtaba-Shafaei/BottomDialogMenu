package com.mojtaba_shafaei.android.bottomdialogmenuexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.shafaei.bottomDialogMenu.BottomDialogMenu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //<editor-fold desc="RTL menu">
        findViewById(R.id.btn_show_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomDialogMenu dialogMenu = new BottomDialogMenu();
                dialogMenu.setDirection(BottomDialogMenu.LAYOUT_DIRECTION_RTL);
                dialogMenu.setTitleTextRes(R.string.title);
                dialogMenu.setTitleTextColorRes(android.R.color.holo_blue_dark);

                /*
                 * menu must have id,icon and title
                 */
                dialogMenu.setItemsRes(R.menu.main_menu_rtl);
                dialogMenu.setItemTitleColorRes(android.R.color.holo_orange_dark);
                dialogMenu.setOnItemClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showMessage(view.getId());
                    }
                });
                dialogMenu.show(getSupportFragmentManager(), "tag");
            }
        });
        //</editor-fold>

        //<editor-fold desc="LTR menu">
        findViewById(R.id.btn_show_menuLtr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomDialogMenu dialogMenu = new BottomDialogMenu();
                dialogMenu.setDirection(BottomDialogMenu.LAYOUT_DIRECTION_LTR);
                dialogMenu.setTitleTextRes(R.string.title);

                /*
                 * menu must have id,icon and title
                 */
                dialogMenu.setItemsRes(R.menu.main_menu_ltr);

                dialogMenu.setOnItemClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showMessage(view.getId());
                    }
                });
                dialogMenu.show(getSupportFragmentManager(), "tag");
            }
        });
        //</editor-fold>

        findViewById(R.id.btn_menu_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showMessage(int id) {
        String itemName = "";
        switch (id) {
            case R.id.copy:
                itemName = getString(R.string.copy);
                break;

            case R.id.cut:
                itemName = getString(R.string.cut);
                break;

            case R.id.past:
                itemName = getString(R.string.paste);
                break;
        }

        Toast.makeText(MainActivity.this, String.format("item <<%1$s>> clicked.", itemName), Toast.LENGTH_SHORT).show();

    }
}
