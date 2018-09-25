package com.shafaei.bottomDialogMenu;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.IntDef;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mojtaba on 12/31/16.
 */

public final class BottomDialogMenu extends BottomSheetDialogFragment {

  @DirectionFlag
  private int direction = LAYOUT_DIRECTION_RTL;

  ////////////////////////////////////////////////////////

  /**
   * @param direction LAYOUT_DIRECTION_RTL, LAYOUT_DIRECTION_LTR.<br/> Default value is
   * LAYOUT_DIRECTION_RTL
   */
  public void setDirection(@DirectionFlag int direction) {
    this.direction = direction;
  }

  private boolean cancellable = true;

  public void setCancellable(boolean cancellable) {
    this.cancellable = cancellable;
  }

  //<editor-fold desc="<<  Title  >>">
  private boolean hiddenTitle = false;
  @NonNull
  private String title = "";
  @StringRes
  Integer titleStringResId = null;

  private Typeface titleTypeFace = null;

  @BottomDialogMenu.GravityFlag
  private int titleGravity = BottomDialogMenu.CENTER;

  @ColorInt
  private Integer titleColor = null;
  @ColorRes
  private Integer titleColorRes = null;

  ////////////////////////////////////////////
  //////  setters and getters
  ////////////////////////////////////////////
  public void setTitleHidden() {
    this.hiddenTitle = true;
  }

  public void setTitleText(@NonNull String title) {
    this.title = title;
  }

  public void setTitleTextRes(@StringRes int titleStringResId) {
    this.titleStringResId = titleStringResId;
  }

  public void setTitleTypeFace(@Nullable Typeface titleTypeFace) {
    this.titleTypeFace = titleTypeFace;
  }

  /**
   * @param titleGravity The Gravity of Dialog's Title .<br/> Default value is CENTER
   */
  public void setTitleGravity(@GravityFlag int titleGravity) {
    this.titleGravity = titleGravity;
  }

  /**
   * @param titleColor Color of Title
   */
  public void setTitleTextColor(@ColorInt int titleColor) {
    this.titleColor = titleColor;
  }

  public void setTitleTextColorRes(@ColorRes int titleColorRes) {
    this.titleColorRes = titleColorRes;
  }
  //</editor-fold>

  //<editor-fold desc="<<  Items  >>">
  @MenuRes
  private Integer itemsRes = null;
  private List<Item> items;
  private View.OnClickListener onClickListener = null;
  private Typeface itemTitleTypeFace = null;

  @ColorInt
  private Integer itemTitleColor = null;
  @ColorRes
  private Integer itemTitleColorRes = null;
  ////////////////////////////////////////////
  //////  setters and getters
  ////////////////////////////////////////////

  public void setItems(List<Item> items) {
    this.items = items;
  }

  public void setItemsRes(@MenuRes int itemsRes) {
    this.itemsRes = itemsRes;
  }

  private void fillItems(Context context, @MenuRes int menuRes) {
    MenuInflater menuInflater = new SupportMenuInflater(context);
    MenuBuilder menuBuilder = new MenuBuilder(context);
    menuInflater.inflate(menuRes, menuBuilder);

    List<Item> items = new LinkedList<>();
    for (int i = 0; i < menuBuilder.size(); i++) {
      MenuItem menuItem = menuBuilder.getItem(i);
      Item item = new Item();
      item.setId(menuItem.getItemId());
      item.setIcon(menuItem.getIcon());
      item.setTitle(menuItem.getTitle().toString());
      items.add(item);
    }

    this.items = items;
  }

  public void setItemTitleTypeFace(@NonNull Typeface rowTitleTypeFace) {
    this.itemTitleTypeFace = rowTitleTypeFace;
  }

  public void setItemTitleColorRes(@ColorRes int itemTitleColorRes) {
    this.itemTitleColorRes = itemTitleColorRes;
  }

  public void setItemTitleColor(@ColorInt int itemTitleColor) {
    this.itemTitleColor = itemTitleColor;
  }

  public void setOnItemClickListener(View.OnClickListener onClickListener) {
    this.onClickListener = onClickListener;
  }
  //</editor-fold>

  ///////////////////////////////////////////////////////

  //<editor-fold desc="@GravityFlag">
  public static final int RIGHT = Gravity.RIGHT;
  public static final int LEFT = Gravity.LEFT;
  public static final int CENTER = Gravity.CENTER_HORIZONTAL;

  @Documented
  @Retention(RetentionPolicy.SOURCE)
  @IntDef({RIGHT, LEFT, CENTER})
  public @interface GravityFlag {

  }
  //</editor-fold>

  //<editor-fold desc="@DirectionFlag">
  public static final int LAYOUT_DIRECTION_LTR = 0;
  public static final int LAYOUT_DIRECTION_RTL = 1;

  @Documented
  @Retention(RetentionPolicy.SOURCE)
  @IntDef({LAYOUT_DIRECTION_LTR, LAYOUT_DIRECTION_RTL})
  public @interface DirectionFlag {

  }
  //</editor-fold>

  public BottomDialogMenu() {
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.custome_dialog, container, false);
    try {
      final TextView title = root.findViewById(android.R.id.title);
      LinearLayout.LayoutParams lpTitle = (LinearLayout.LayoutParams) title.getLayoutParams();
      lpTitle.gravity = this.titleGravity;
      title.setLayoutParams(lpTitle);

      if (this.titleColorRes != null) {
        title.setTextColor(ContextCompat.getColor(getContext(), titleColorRes));
      } else {
        if (this.titleColor != null) {
          title.setTextColor(this.titleColor);
        }
      }

      if (this.titleTypeFace != null) {
        title.setTypeface(this.titleTypeFace);
      }
      if (this.hiddenTitle) {
        title.setVisibility(View.GONE);
      } else {
        title.setVisibility(View.VISIBLE);
      }

      if (this.titleStringResId != null) {
        title.setText(this.titleStringResId);
      } else {
        if (this.title != null && !this.title.isEmpty()) {
          title.setText(this.title);
        } else {
          title.setVisibility(View.GONE);
        }
      }

      addItems(inflater, root);
    } catch (Exception e) {
      Log.e("BottomDialogMenu", "onCreateView", e);
    }

    return root;
  }

  private void addItems(LayoutInflater inflater, View root) {
    LinearLayout llContainer = root.findViewById(R.id.container);
    llContainer.removeAllViews();
    if (itemsRes != -1) {
      fillItems(getContext(), itemsRes);
    }
    for (Item i : this.items) {
      View v = inflater
          .inflate(this.direction == LAYOUT_DIRECTION_RTL ? R.layout.row_rtl : R.layout.row_ltr
              , llContainer, false);
      ((ImageView) v.findViewById(R.id.image)).setImageDrawable(i.getIcon());
      TextView titleRow = v.findViewById(android.R.id.title);
      titleRow.setText(i.getTitle());
      if (itemTitleColorRes != null) {
        titleRow.setTextColor(ContextCompat.getColor(getContext(), itemTitleColorRes));
      } else {
        if (itemTitleColor != null) {
          titleRow.setTextColor(itemTitleColor);
        }
      }

      if (this.itemTitleTypeFace != null) {
        titleRow.setTypeface(this.itemTitleTypeFace);
      }

      v.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
          ViewGroup.LayoutParams.WRAP_CONTENT));
      v.setId(i.getId());

      v.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          if (onClickListener != null) {
            onClickListener.onClick(view);
          }
          BottomDialogMenu.this.dismiss();
        }
      });

      llContainer.addView(v);
    }
    llContainer.requestLayout();
  }

  @Override
  public void show(FragmentManager manager, String tag) {
    super.show(manager, tag);
  }
}
