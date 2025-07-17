package com.example.dynamicui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DynamicPageView extends LinearLayout {

    private Toolbar toolbar;
    private FrameLayout contentContainer;
    private ToolbarConfig.OnMenuClickListener listener;

    public DynamicPageView(Context context) {
        super(context);
        init();
    }

    public DynamicPageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        toolbar = new Toolbar(getContext());
        contentContainer = new FrameLayout(getContext());

        LayoutParams toolbarParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        LayoutParams contentParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);


        addView(toolbar, toolbarParams);
        addView(contentContainer, contentParams);
        ViewCompat.setOnApplyWindowInsetsListener(this, (v, insets) -> {
            int topInset = insets.getInsets(WindowInsetsCompat.Type.systemBars()).top;

            //Apply top padding only to the toolbar
            toolbar.setPadding(toolbar.getPaddingLeft(), topInset,
                    toolbar.getPaddingRight(), toolbar.getPaddingBottom());

            return insets;
        });

        // 🧠 Force insets to be applied when attached
        ViewCompat.requestApplyInsets(this);
    }


    public void configureToolbar(ToolbarConfig config) {
        listener = config.onMenuClickListener;

        toolbar.setTitle(config.titleVisible ? config.title : "");
        toolbar.getMenu().clear();

        if (config.menuItemsVisible && config.menuItems != null) {
            for (ToolbarMenuItem item : config.menuItems) {
                MenuItem menuItem = toolbar.getMenu().add(item.id);
                menuItem.setIcon(item.iconResId);
                menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            }
        }

        toolbar.setOnMenuItemClickListener(item -> {
            if (listener != null && item.getTitle() != null) {
                listener.onMenuItemClick(item.getTitle().toString());
            }
            return true;
        });

        // Reposition if needed
        removeAllViews();
        if (config.toolbarPosition == ToolbarConfig.Position.TOP) {
            addView(toolbar);
            addView(contentContainer);
        } else {
            addView(contentContainer);
            addView(toolbar);
        }
    }

}