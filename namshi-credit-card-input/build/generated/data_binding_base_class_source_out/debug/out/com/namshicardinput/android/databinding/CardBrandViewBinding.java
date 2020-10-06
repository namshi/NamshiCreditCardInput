// Generated by view binder compiler. Do not edit!
package com.namshicardinput.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;
import com.namshicardinput.android.R;
import com.namshicardinput.android.view.CardWidgetProgressView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class CardBrandViewBinding implements ViewBinding {
  @NonNull
  private final View rootView;

  @NonNull
  public final ImageView icon;

  @NonNull
  public final CardWidgetProgressView progress;

  private CardBrandViewBinding(@NonNull View rootView, @NonNull ImageView icon,
      @NonNull CardWidgetProgressView progress) {
    this.rootView = rootView;
    this.icon = icon;
    this.progress = progress;
  }

  @Override
  @NonNull
  public View getRoot() {
    return rootView;
  }

  @NonNull
  public static CardBrandViewBinding inflate(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup parent) {
    if (parent == null) {
      throw new NullPointerException("parent");
    }
    inflater.inflate(R.layout.card_brand_view, parent);
    return bind(parent);
  }

  @NonNull
  public static CardBrandViewBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.icon;
      ImageView icon = rootView.findViewById(id);
      if (icon == null) {
        break missingId;
      }

      id = R.id.progress;
      CardWidgetProgressView progress = rootView.findViewById(id);
      if (progress == null) {
        break missingId;
      }

      return new CardBrandViewBinding(rootView, icon, progress);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
