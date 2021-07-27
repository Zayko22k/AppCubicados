package com.cubic.appcubicados.Adaptadores.Cotizacion;

import android.annotation.SuppressLint;
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

import com.cubic.appcubicados.Actividades.CrearCotizacionPintura;
import com.cubic.appcubicados.Clases.CementoProducto;
import com.cubic.appcubicados.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.cubic.appcubicados.Actividades.Cubicador.cubicar;

public class AdaptadorPintura extends RecyclerView.Adapter<AdaptadorPintura.ViewHolder> {
    List<CementoProducto> pinturaProductoList;
    Context context;

    public AdaptadorPintura(List<CementoProducto> pinturaProductoList, @NonNull Context context) {
        this.context = context.getApplicationContext();
        this.pinturaProductoList = pinturaProductoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public com.cubic.appcubicados.Adaptadores.Cotizacion.AdaptadorPintura.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_pintura_main, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull com.cubic.appcubicados.Adaptadores.Cotizacion.AdaptadorPintura.ViewHolder holder, int position) {
        try {

            CementoProducto pinturaProducto = pinturaProductoList.get(position);
            holder.marcaMuro.setText(pinturaProducto.getMarca());
            holder.descripcionMuro.setText(pinturaProducto.getDescripcion());
            System.out.println("Marca: " + pinturaProducto.getMarca());
            System.out.println("Precio: " + pinturaProducto.getPrecio());


            if (cubicar.getIdTienda() == 1) {
                if (pinturaProducto.getPrecio().length() == 9) {
                    String pr = pinturaProducto.getPrecio();
                    String subCadena = pr.substring(1, 7);
                    StringBuilder pre = new StringBuilder(subCadena);
                    pre.deleteCharAt(1);
                    pre.deleteCharAt(4);
                    System.out.println(pre.toString());
                    int price = Integer.parseInt(pre.toString());
                    System.out.println(pre.toString());
                    pinturaProducto.setPrecio(String.valueOf(price));
                    holder.precioMuro.setText("$ " + price + " C/U");

                } else if (pinturaProducto.getPrecio().length() == 10) {
                    String pr = pinturaProducto.getPrecio();
                    String subCadena = pr.substring(1, 7);
                    StringBuilder pre = new StringBuilder(subCadena);
                    pre.deleteCharAt(2);
                    int price = Integer.parseInt(pre.toString());
                    System.out.println(pre.toString());
                    pinturaProducto.setPrecio(String.valueOf(price));
                    holder.precioMuro.setText("$ " + price + " C/U");
                }
            } else if (cubicar.getIdTienda() == 2) {
                if (pinturaProducto.getPrecio().length() == 10) {
                    String pr = pinturaProducto.getPrecio();
                    String subCadena = pr.substring(1, 7);
                    StringBuilder pre = new StringBuilder(subCadena);
                    pre.deleteCharAt(1);
                    pre.deleteCharAt(4);
                    int price = Integer.parseInt(pre.toString());
                    System.out.println(pre.toString());
                    pinturaProducto.setPrecio(String.valueOf(price));
                    holder.precioMuro.setText("$ " + price + " C/U");

                } else if (pinturaProducto.getPrecio().length() == 11) {
                    String pr = pinturaProducto.getPrecio();
                    String subCadena = pr.substring(1, 7);
                    StringBuilder pre = new StringBuilder(subCadena);
                    pre.deleteCharAt(2);
                    int price = Integer.parseInt(pre.toString());
                    System.out.println(pre.toString());
                    pinturaProducto.setPrecio(String.valueOf(price));
                    holder.precioMuro.setText("$ " + price + " C/U");
                }
            }
            //Quitar $C/U a precio
            holder.despachoMuro.setText(pinturaProducto.getDespacho());
            holder.retiroMuro.setText(pinturaProducto.getRetiro());
            Picasso.get().load(pinturaProducto.getImgUrl()).into(holder.imagenPinturaMuro);
            cubicar.setIdProduct(pinturaProducto.getIdProducto());
            holder.imgAddMuro.setOnClickListener(v -> {
                System.out.println(pinturaProducto.getIdProducto());
                Dialog dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.dialog_agregar_producto);
                int witdh = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setLayout(witdh, height);
                Button btnAgregar = dialog.findViewById(R.id.btnConfirmarProducto);
                Button btnCancelar = dialog.findViewById(R.id.btnCancelarProducto);
                dialog.show();
                btnAgregar.setOnClickListener(v1 -> {
                    Intent intent = new Intent(context, CrearCotizacionPintura.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("productoSelectMuro", pinturaProducto);
                    dialog.dismiss();
                    context.startActivity(intent);
                });
                btnCancelar.setOnClickListener(v12 -> dialog.dismiss());
            });
            holder.imgVerMuro.setOnClickListener(v -> {
                try {
                    if (cubicar.getIdTienda() == 1) {
                        String url = "https://www.sodimac.cl/sodimac-cl/product/" + pinturaProducto.getIdProducto();
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.setData(Uri.parse(url));
                        context.startActivity(i);
                    } else if (cubicar.getIdTienda() == 2) {
                        String url = "https://www.construmart.cl" + pinturaProducto.getIdProducto();
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.setData(Uri.parse(url));
                        context.startActivity(i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return pinturaProductoList.size();
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView marcaMuro;
        TextView descripcionMuro;
        TextView precioMuro;
        TextView despachoMuro;
        TextView retiroMuro;
        ImageView imagenPinturaMuro;
        ImageView imgVerMuro;
        ImageView imgAddMuro;
        CardView cwMuro;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            marcaMuro = itemView.findViewById(R.id.txtMarcaPintura);
            descripcionMuro = itemView.findViewById(R.id.txtDescripcionPintura);
            precioMuro = itemView.findViewById(R.id.txtPrecioPintura);
            despachoMuro = itemView.findViewById(R.id.txtDespachoPintura);
            retiroMuro = itemView.findViewById(R.id.txtRetiroPintura);
            imagenPinturaMuro = itemView.findViewById(R.id.imgPinturaMuro);
            imgVerMuro = itemView.findViewById(R.id.btnVerProductPintura);
            imgAddMuro = itemView.findViewById(R.id.btnAgregarProductoPintura);
            cwMuro = itemView.findViewById(R.id.CWpintura);

        }
    }

}


