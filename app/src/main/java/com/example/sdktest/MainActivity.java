package com.example.sdktest;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dynamicui.DynamicPageView;
import com.example.dynamicui.ToolbarConfig;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
        // For AppCompatActivity
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        DynamicPageView page = new DynamicPageView(this);

        page.configureToolbar(new ToolbarConfig.Builder()
                .setTitle("Amazon")
                .setTitleVisible(true)
                .setMenuItemsVisible(true)
                .setMenuItem(R.drawable.chat, "chat")
                .setMenuItem(R.drawable.video_call_24px, "video_call")
                .setToolbarPosition(ToolbarConfig.Position.TOP)
                .setOnMenuClickListener(id -> {
                    if (id.equals("chat")) {
                        Toast.makeText(this, "Chat clicked", Toast.LENGTH_SHORT).show();
                    } else if (id.equals("video_call")) {
                        Toast.makeText(this, "Video call clicked", Toast.LENGTH_SHORT).show();
                    }
                })
                .build());

        setContentView(page);
    }
}