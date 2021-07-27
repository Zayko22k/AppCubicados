package com.cubic.appcubicados.Adaptadores.Cotizacion;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cubic.appcubicados.Modelos.DetalleCotizacion;
import com.cubic.appcubicados.R;
import com.cubic.appcubicados.Retrofit.RetrofitBuilder;
import com.cubic.appcubicados.databinding.ListCotizacionesMainBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdaptadorMisCotizaciones extends RecyclerView.Adapter<AdaptadorMisCotizaciones.ViewHolder> {
    List<DetalleCotizacion> detalleCotizacionList;
    private SparseBooleanArray selectedItems;
    private int selectedIndex = -1;
    private OnItemClick itemClick;
    Context context;

    public void setItemClick(OnItemClick itemClick) {
        this.itemClick = itemClick;
        notifyDataSetChanged();
    }

    public AdaptadorMisCotizaciones(List<DetalleCotizacion> detalleCotizacionList, @NonNull Context context) {
        this.context = context.getApplicationContext();
        this.detalleCotizacionList = detalleCotizacionList;
        selectedItems = new SparseBooleanArray();
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public AdaptadorMisCotizaciones.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ListCotizacionesMainBinding bi = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.list_cotizaciones_main, parent, false);
        return new ViewHolder(bi);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdaptadorMisCotizaciones.ViewHolder holder, final int position) {
        DetalleCotizacion detalleCotizacion;
        detalleCotizacion = detalleCotizacionList.get(position);
        holder.bi.verNombreMiCoti.setText(detalleCotizacion.getNombreCoti());
        String s = new SimpleDateFormat("MM/dd/yyyy").format(detalleCotizacion.getCreated_at());
        holder.bi.txtVerFechaMiCoti.setText(s);
        holder.bi.txtTotalMiCoti.setText("$ "+detalleCotizacion.getTotal());

        holder.bi.lytParent.setActivated(selectedItems.get(position, false));


        holder.bi.lytParent.setOnClickListener(view -> {
            if (itemClick == null) return;
            itemClick.onItemClick(view, detalleCotizacionList.get(position), position);
        });
        holder.bi.lytParent.setOnLongClickListener(view -> {
            if (itemClick == null) {
                return false;
            } else {
                itemClick.onLongPress(view, detalleCotizacionList.get(position), position);
                return true;
            }
        });
        toggleIcon(holder.bi, position);

    }
    private void toggleIcon(ListCotizacionesMainBinding bi, int position) {
        if (selectedItems.get(position, false)) {
            bi.lytImage.setVisibility(View.GONE);
            bi.lytChecked.setVisibility(View.VISIBLE);
            if (selectedIndex == position) selectedIndex = -1;
        } else {
            bi.lytImage.setVisibility(View.VISIBLE);
            bi.lytChecked.setVisibility(View.GONE);
            if (selectedIndex == position) selectedIndex = -1;
        }
    }
    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); i++) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }
    public void removeItems(int position) {

        Call<List<DetalleCotizacion>> detalleCotizacionCall = RetrofitBuilder.detalleCotizacionService.deleteCotizacion(position);
        detalleCotizacionCall.enqueue(new Callback<List<DetalleCotizacion>>() {
            @Override
            public void onResponse(Call<List<DetalleCotizacion>> call, Response<List<DetalleCotizacion>> response) {
                if(response.isSuccessful()){
                }
            }
            @Override
            public void onFailure(Call<List<DetalleCotizacion>> call, Throwable t) {

            }
        });

        selectedIndex = -1;
        notifyDataSetChanged();
    }

    public void clearSelection() {
        selectedItems.clear();
        notifyDataSetChanged();
    }
    public void toggleSelection(int position) {
        selectedIndex = position;
        if (selectedItems.get(position, false)) {
            selectedItems.delete(position);
        } else {
            selectedItems.put(position, true);
        }
        notifyItemChanged(position);
    }
    public int selectedItemCount() {
        return selectedItems.size();
    }
    @Override
    public int getItemCount() {
        return detalleCotizacionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ListCotizacionesMainBinding bi;
        public ViewHolder(@NonNull ListCotizacionesMainBinding itemView) {
            super(itemView.getRoot());
            bi = itemView;

        }
    }
    public interface OnItemClick {
        void onItemClick(View view, DetalleCotizacion detalleCotizacion, int position);
        void onLongPress(View view, DetalleCotizacion detalleCotizacion, int position);
    }
}
