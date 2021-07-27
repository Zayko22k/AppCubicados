package com.cubic.appcubicados.Adaptadores.Cotizacion;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cubic.appcubicados.Actividades.CrearCotizacion;
import com.cubic.appcubicados.Clases.CementoProducto;
import com.cubic.appcubicados.Clases.FichaTecnica;
import com.cubic.appcubicados.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.cubic.appcubicados.Actividades.Cubicador.cubicar;

public class AdaptadorCemento extends RecyclerView.Adapter<AdaptadorCemento.ViewHolder> {
    List<CementoProducto> cementoProductoList;
    FichaTecnica fichaTecnica;
    Context context;

    public AdaptadorCemento(List<CementoProducto> cementoProductoList, @NonNull Context context) {
        this.context = context.getApplicationContext();
        this.cementoProductoList = cementoProductoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public com.cubic.appcubicados.Adaptadores.Cotizacion.AdaptadorCemento.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_cemento_main, parent, false);
        return new com.cubic.appcubicados.Adaptadores.Cotizacion.AdaptadorCemento.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.cubic.appcubicados.Adaptadores.Cotizacion.AdaptadorCemento.ViewHolder holder, int position) {
        CementoProducto cementoProducto;
        cementoProducto = cementoProductoList.get(position);
        holder.marca.setText(cementoProducto.getMarca());
        holder.descripcion.setText(cementoProducto.getDescripcion());

        String pr = cementoProducto.getPrecio();
        System.out.println(""+pr);
        String subCadena = pr.substring(1, 6);
        StringBuilder pre = new StringBuilder(subCadena);
        pre = pre.deleteCharAt(1);
        int sacosAprox = (int) Math.round(cubicar.getSacosCemento());
        int price = Integer.parseInt(pre.toString());
        int totalprecio = price * sacosAprox;
        //Quitar $C/U a precio

        cementoProducto.setPrecio(String.valueOf(price));
        holder.precio.setText("$ "+price+ " C/U");
        holder.total.setText("$ " + totalprecio + " por los " + Math.round(cubicar.getSacosCemento()) + " sacos");
        holder.despacho.setText(cementoProducto.getDespacho());
        holder.retiro.setText(cementoProducto.getRetiro());
        Picasso.get().load(cementoProducto.getImgUrl()).into(holder.imagenCemento);
        cubicar.setIdProduct(cementoProducto.getIdProducto());
        holder.imgAdd.setOnClickListener(v -> {
            System.out.println(cementoProducto.getIdProducto());
            Dialog dialog = new Dialog(v.getContext());
            dialog.setContentView(R.layout.dialog_agregar_producto);
            int witdh = WindowManager.LayoutParams.MATCH_PARENT;
            int height = WindowManager.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(witdh, height);
            Button btnAgregar = dialog.findViewById(R.id.btnConfirmarProducto);
            Button btnCancelar = dialog.findViewById(R.id.btnCancelarProducto);
            dialog.show();
            btnAgregar.setOnClickListener(v1 -> {
                Intent intent = new Intent(context, CrearCotizacion.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("productoSelect", cementoProducto);
                intent.putExtra("total", totalprecio);
                dialog.dismiss();
                context.startActivity(intent);
            });
            btnCancelar.setOnClickListener(v12 -> dialog.dismiss());
        });
        holder.imgVer.setOnClickListener(v -> {
            try {
                if(cubicar.getIdTienda() == 1){
                    String url = "https://www.sodimac.cl/sodimac-cl/product/"+cementoProducto.getIdProducto();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.setData(Uri.parse(url));
                    context.startActivity(i);
                } else if(cubicar.getIdTienda() == 2){
                    String url = "https://www.construmart.cl"+cementoProducto.getIdProducto();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.setData(Uri.parse(url));
                    context.startActivity(i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return cementoProductoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView marca;
        TextView descripcion;
        TextView precio;
        TextView total;
        TextView despacho;
        TextView retiro;
        ImageView imagenCemento;
        ImageView imgVer;
        ImageView imgAdd;
        CardView cw;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            marca = itemView.findViewById(R.id.txtMarcaCemento);
            descripcion = itemView.findViewById(R.id.txtDescripcionCemento);
            precio = itemView.findViewById(R.id.txtPrecioCemento);
            total = itemView.findViewById(R.id.txtTotalCemento);
            despacho = itemView.findViewById(R.id.txtDespachoCemento);
            retiro = itemView.findViewById(R.id.txtRetiroCemento);
            imagenCemento = itemView.findViewById(R.id.imgCemento);
            imgVer = itemView.findViewById(R.id.btnVerProduct);
            imgAdd = itemView.findViewById(R.id.btnAgregarProducto);
            cw = itemView.findViewById(R.id.CWcemento);

        }
    }

}
