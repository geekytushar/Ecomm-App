package com.tusharpatil.ecommapp.adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import com.tusharpatil.ecommapp.R;
import com.tusharpatil.ecommapp.activities.category.CategoryActivity;
import com.tusharpatil.ecommapp.activities.product.ProductsActivity;
import com.tusharpatil.ecommapp.local_db.DatabaseHelper;
import com.tusharpatil.ecommapp.models.categories.Category;
import com.tusharpatil.ecommapp.utils.Config;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final int VIEW_TYPE_EMPTY = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private Callback mCallback;
    private List<Category> categories;

    public CategoryAdapter(List<Category> categories) {
        this.categories = categories;
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
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_view, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (categories != null && categories.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (categories != null && categories.size() > 0) {
            return categories.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<Category> categories) {
        this.categories.addAll(categories);
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
            titleTextView.setText(categories.get(position).getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = null;
                    if (new DatabaseHelper(itemView.getContext()).getCategory(categories.get(position).getId()).size() > 0)
                        intent = new Intent(itemView.getContext(), CategoryActivity.class);
                    else
                        intent = new Intent(itemView.getContext(), ProductsActivity.class);
                    intent.putExtra(Config.CATEGORY_ID_KEY, categories.get(position).getId());
                    intent.putExtra(Config.CATEGORY_NAME_KEY, categories.get(position).getName());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

    public class EmptyViewHolder extends BaseViewHolder {
        @BindView(R.id.buttonRetry)
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