package com.example.assigmentandroidnc.adapter;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigmentandroidnc.R;
import com.example.assigmentandroidnc.interfaces.IClickButton;
import com.example.assigmentandroidnc.model.KhoaHoc;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class KhoaHocAdapter extends RecyclerView.Adapter<KhoaHocAdapter.KhoaHocViewHolder> {

    private List<KhoaHoc> khoaHocList;
    private IClickButton iClickButton;


    public KhoaHocAdapter(IClickButton iClickButton) {

        this.iClickButton = iClickButton;
    }

    public void setData(List<KhoaHoc> list) {
        this.khoaHocList = list;
        notifyDataSetChanged();

    }

    @NonNull
    @NotNull
    @Override
    public KhoaHocViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mycoures, parent, false);
        return new KhoaHocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull KhoaHocAdapter.KhoaHocViewHolder holder, int position) {
        KhoaHoc mKhoaHoc = khoaHocList.get(position);

        if (mKhoaHoc == null) {
            return;
        }

        holder.tvKihoc.setText(mKhoaHoc.getKihoc());
        holder.tvNgaythi.setText(mKhoaHoc.getNgaythi());
        holder.tvLichHoc.setText(mKhoaHoc.getLichhoc());
        holder.tvLopHoc.setText(mKhoaHoc.getLopHoc());
        holder.tvmonhoc.setText(mKhoaHoc.getMonhoc());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(v.getContext(), v);
                //inflating menu from xml resource
                popup.inflate(R.menu.menu_update);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.update:
                                iClickButton.clickupdateItem(mKhoaHoc);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                //displaying the popup
                popup.show();

            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickButton.deleteItem(mKhoaHoc);
            }
        });
    }



    @Override
    public int getItemCount() {
        return khoaHocList.size();
    }

    public class KhoaHocViewHolder extends RecyclerView.ViewHolder {

        private TextView tvmonhoc, tvNgaythi, tvLichHoc, tvKihoc, tvLopHoc;
        private ImageView imageView;
        private LinearLayout linearLayout;


        public KhoaHocViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            linearLayout= itemView.findViewById(R.id.linearLayout);
            tvKihoc = itemView.findViewById(R.id.tv_kihoc);
            tvNgaythi = itemView.findViewById(R.id.tvNgayThi);
            tvLichHoc = itemView.findViewById(R.id.tv_lichHoc);
            tvLopHoc = itemView.findViewById(R.id.tvlophoc);
            tvmonhoc = itemView.findViewById(R.id.tv_monhoc);
            imageView=itemView.findViewById(R.id.img_dl);

        }
    }


}
