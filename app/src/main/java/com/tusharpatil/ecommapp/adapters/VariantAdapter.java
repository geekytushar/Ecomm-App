package com.tusharpatil.ecommapp.adapters;

import android.util.Log;
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
        Log.d("TAGG66", "0=>" + variants.get(0).getPrice());
        Log.d("TAGG66", "1=>" + variants.get(1).getPrice());
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

    public void addItems(List<Variant> sportList) {
        variants.addAll(sportList);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onEmptyViewRetryClick();
    }

    public class ViewHolder extends BaseViewHolder {
        TextView titleTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title);
        }

        protected void clear() {
            titleTextView.setText("");
        }

        public void onBind(final int position) {
            super.onBind(position);
            titleTextView.setText(String.valueOf(variants.get(position).getPrice()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = null;
//                    if (new DatabaseHelper(itemView.getContext()).getCategory(variants.get(position).getId()).size() > 0)
//                        intent = new Intent(itemView.getContext(), CategoryActivity.class);
//                    else
//                        intent = new Intent(itemView.getContext(), ProductsActivity.class);
//                    intent.putExtra("category_id", variants.get(position).getId());
//                    intent.putExtra("category_name", variants.get(position).getName());
//                    itemView.getContext().startActivity(intent);
                }
            });
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