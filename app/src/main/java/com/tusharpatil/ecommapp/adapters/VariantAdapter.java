package com.tusharpatil.ecommapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tusharpatil.ecommapp.R;
import com.tusharpatil.ecommapp.models.categories.Variant;

import java.util.List;

public class VariantAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final int VIEW_TYPE_EMPTY = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private Callback mCallback;
    private List<Variant> variants;

    public VariantAdapter(List<Variant> variants) {
        this.variants = variants;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.variant_list_item, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.item_empty_view, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (variants != null && variants.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (variants != null && variants.size() > 0) {
            return variants.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<Variant> variants) {
        variants.addAll(variants);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onEmptyViewRetryClick();
    }

    public class ViewHolder extends BaseViewHolder {
        TextView colorTextView, priceTextView, sizeTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            colorTextView = itemView.findViewById(R.id.colorTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            sizeTextView = itemView.findViewById(R.id.sizeTextView);
        }

        protected void clear() {
            colorTextView.setText("");
            priceTextView.setText("");
            sizeTextView.setText("");
        }

        public void onBind(final int position) {
            super.onBind(position);
            colorTextView.setText("Color: " + String.valueOf(variants.get(position).getPrice()));
            priceTextView.setText("Price: ₹" + String.valueOf(variants.get(position).getPrice()));
            sizeTextView.setText("Size: " + String.valueOf(variants.get(position).getSize()));
        }
    }

    public class EmptyViewHolder extends BaseViewHolder {
        TextView buttonRetry;

        EmptyViewHolder(View itemView) {
            super(itemView);
            buttonRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallback.onEmptyViewRetryClick();
                }
            });
        }

        @Override
        protected void clear() {
        }
    }


}