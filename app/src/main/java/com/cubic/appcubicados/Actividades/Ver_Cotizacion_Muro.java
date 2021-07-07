package com.cubic.appcubicados.Actividades;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.cubic.appcubicados.Modelos.DetalleCotizacion;
import com.cubic.appcubicados.R;
import com.cubic.appcubicados.Retrofit.RetrofitBuilder;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Ver_Cotizacion_Muro extends AppCompatActivity {
    private TextView txtNombreCoti;
    private TextView txtFechaCoti;
    private ImageView imgMaterial;
    private TextView txtInmuebleProyecto;
    private TextView txtTCproyecto;
    private TextView txtCproyecto;
    private TextView txtAnchoProyecto;
    private TextView txtLargoProyecto;
    private TextView txtM2Proyecto;
    private TextView txtCantLitros;
    private TextView txtLitrosxM2;
    private TextView txtTiendaMaterial;
    private TextView txtMarcaMaterial;
    private TextView txtDescripcionMaterial;
    private TextView txtPrecioMaterial;
    private TextView txtDespachoMaterial;
    private TextView txtRetiroMaterial;
    private TextView btnExportarCoti;
    private TextView btnEliminarCoti;


    String NOMBRE_DIRECTORIO = "CotizacionCubica2";
    DetalleCotizacion detalleCotizacion = new DetalleCotizacion();

    /**
     * @param savedInstanceState
     * @Autor Pablo Rodriguez
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_cotizacion_muro);
        detalleCotizacion = (DetalleCotizacion) getIntent().getSerializableExtra("detalleCoti");
        //Ficha Tecnica
        txtNombreCoti = findViewById(R.id.verNombreCotiMuro);
        txtFechaCoti = findViewById(R.id.txtVerFechaCotiMuro);
        txtInmuebleProyecto = findViewById(R.id.txtVerInmProyectoMuro);
        txtTCproyecto = findViewById(R.id.txtVerTCproyectoMuro);
        txtCproyecto = findViewById(R.id.txtVerCproyectoMuro);
        txtAnchoProyecto = findViewById(R.id.txtVerAnchoProyectoMuro);
        txtLargoProyecto = findViewById(R.id.txtVerLargoProyectoMuro);
        txtM2Proyecto = findViewById(R.id.txtVerM2ProyectoMuro);
        txtCantLitros = findViewById(R.id.txtVerLitrosResultMuro);
        txtLitrosxM2 = findViewById(R.id.txtVerDosificacionProyectoMuro);
        //Material
        imgMaterial = findViewById(R.id.verImgMaterialMuro);
        txtTiendaMaterial = findViewById(R.id.txtVerTiendaMaterialMuro);
        txtMarcaMaterial = findViewById(R.id.txtVerMarcaMaterialMuro);
        txtDescripcionMaterial = findViewById(R.id.txtVerDescripcionMaterialMuro);
        txtPrecioMaterial = findViewById(R.id.txtVerPrecioMaterialMuro);
        txtDespachoMaterial = findViewById(R.id.txtVerDespachoMaterialMuro);
        txtRetiroMaterial = findViewById(R.id.txtVerRetiroMaterialMuro);
        btnExportarCoti = findViewById(R.id.btnExportCoti);
        btnEliminarCoti = findViewById(R.id.btnEliminarCoti);

        cargarCotizacionMuro();
    }

    /**
     * Metodo que carga las cotizaciones de Revestimiento
     */
    private void cargarCotizacionMuro() {
        try {
            System.out.println("Id de detalle" + detalleCotizacion.getIdDetalleCoti());
            txtNombreCoti.setText(detalleCotizacion.getNombreCoti());
            String s = new SimpleDateFormat("MM/dd/yyyy").format(detalleCotizacion.getCreated_at());
            txtFechaCoti.setText(s);
            Picasso.get().load(detalleCotizacion.getImagenMaterial()).into(imgMaterial);
            txtInmuebleProyecto.setText(detalleCotizacion.getNomInmueble());
            txtTCproyecto.setText(detalleCotizacion.getNomTipoCons());
            txtCproyecto.setText(detalleCotizacion.getNomConstr());
            txtAnchoProyecto.setText(detalleCotizacion.getAncho() + " mts");
            txtLargoProyecto.setText(detalleCotizacion.getLargo() + " mts");
            txtM2Proyecto.setText(detalleCotizacion.getArea() + " M²");
            txtCantLitros.setText(Math.round(detalleCotizacion.getCantidad()) + " Litros aprox necesitas");
            txtLitrosxM2.setText(detalleCotizacion.getDosificacion() + " (mts2 / litro)");
            txtTiendaMaterial.setText(detalleCotizacion.getNomTienda());
            txtMarcaMaterial.setText(detalleCotizacion.getMarcaMaterial());
            txtDescripcionMaterial.setText(detalleCotizacion.getDescripcionMaterial());
            txtPrecioMaterial.setText("$ " + detalleCotizacion.getPrecio() + " C/U");
            txtDespachoMaterial.setText(detalleCotizacion.getDespacho());
            txtRetiroMaterial.setText(detalleCotizacion.getRetiro());
        } catch (Exception e) {
            Toast.makeText(Ver_Cotizacion_Muro.this, "No se pudo cargar la cotizacion", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Metodo que pregunta por permisos de almacenamiento
     *
     * @param v
     */
    public void exportarCotiMuro(View v) {
        // Permisos
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,},
                    1000);
        }
        crearPDFMuro();
        Toast.makeText(Ver_Cotizacion_Muro.this, "Se creo el pdf", Toast.LENGTH_SHORT).show();

    }


    /**
     * Metodo que  da formato al documento pdf
     * carga los datos traidos desde la activity anterior
     */
    public void crearPDFMuro() {
        Document documento = new Document(PageSize.A4, 20, 20, 20, 20);

        try {

            File file = crearFicheroMuro("Proyecto_" + txtNombreCoti.getText().toString() + ".pdf");
            FileOutputStream ficheroPDF = new FileOutputStream(file.getAbsolutePath());

            PdfWriter writer = PdfWriter.getInstance(documento, ficheroPDF);
            documento.open();
            // LINE SEPARATOR
            LineSeparator lineSeparator = new LineSeparator();

            //Titulo
            lineSeparator.setLineColor(new BaseColor(0, 0, 0, 68));
            Paragraph parrafo1 = new Paragraph("Cotización para proyecto de construcción \n\n", FontFactory.getFont("Microsoft YaHei UI",
                    11,
                    Font.BOLD));
            parrafo1.setAlignment(Element.ALIGN_RIGHT);
            documento.add(parrafo1);
            documento.add(new Chunk(lineSeparator));
            //SUbTitulo
            Paragraph parrafo2 = new Paragraph("\n\n Ficha Técnica \n\n", FontFactory.getFont("Microsoft YaHei UI",
                    11,
                    Font.BOLD));
            parrafo2.setAlignment(Element.ALIGN_LEFT);
            documento.add(parrafo2);
            //Tabla

            // Insertamos una tabla
            PdfPTable tabla = new PdfPTable(3);
            PdfPCell cell = new PdfPCell();
            cell.setColspan(6);
            tabla.setTotalWidth(350f);
            tabla.setLockedWidth(true);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
            tabla.addCell(cell);
            tabla.addCell("Inmueble");
            tabla.addCell("Tipo de Construcción");
            tabla.addCell("Construcción");
            tabla.addCell(txtInmuebleProyecto.getText().toString());
            tabla.addCell(txtTCproyecto.getText().toString());
            tabla.addCell(txtCproyecto.getText().toString());
            //Añadimos la tabla
            documento.add(tabla);
            Paragraph parrafo3 = new Paragraph("\n\n **Recuerda por cada saco 25 kg de cemento te recomendamos utilizar** \n\n", FontFactory.getFont("Microsoft YaHei UI",
                    11,
                    Font.ITALIC));
            parrafo3.setAlignment(Element.ALIGN_LEFT);
            documento.add(parrafo3);
            // Insertamos una tabla__2
            PdfPTable tabla2 = new PdfPTable(4);
            PdfPCell cell2 = new PdfPCell(new Paragraph("Diámetros del proyecto", FontFactory.getFont("Microsoft YaHei UI",
                    11,
                    Font.BOLD)));
            cell2.setColspan(8);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tabla2.setHorizontalAlignment(Element.ALIGN_LEFT);
            tabla2.addCell(cell2);
            tabla2.addCell("Ancho(mts)");
            tabla2.addCell("Largo(mts)");
            tabla2.addCell("M2");
            tabla2.addCell("Cantidad de Litros aprox");
            tabla2.addCell(txtAnchoProyecto.getText().toString());
            tabla2.addCell(txtLargoProyecto.getText().toString());
            tabla2.addCell(txtM2Proyecto.getText().toString());
            tabla2.addCell(txtCantLitros.getText().toString());
            //Añadimos la tabla__2
            documento.add(tabla2);
            documento.add(new Paragraph("\n\n **Considera estas manos por tipo de pared** \n\n", FontFactory.getFont("Microsoft YaHei UI",
                    11,
                    Font.ITALIC)));
            documento.add(new Paragraph("Pared pintada con el mismo color | 2 Manos\n\n", FontFactory.getFont("Microsoft YaHei UI",
                    11,
                    Font.ITALIC)));
            documento.add(new Paragraph("Pared sin pintar | 3 Manos o mas\n\n", FontFactory.getFont("Microsoft YaHei UI",
                    11,
                    Font.ITALIC)));
            documento.add(new Paragraph("Pared de material absorvente o irregular | 3 Manos o mas\n\n", FontFactory.getFont("Microsoft YaHei UI",
                    11,
                    Font.ITALIC)));
            documento.add(new Paragraph("Tipo de Diluyente según pintura\n\n", FontFactory.getFont("Microsoft YaHei UI",
                    13,
                    Font.BOLD)));
            documento.add(new Paragraph("Esmalte al agua: Agua\n\n", FontFactory.getFont("Microsoft YaHei UI",
                    11)));
            documento.add(new Paragraph("Latex: Agua\n\n", FontFactory.getFont("Microsoft YaHei UI",
                    11)));
            documento.add(new Paragraph("Esmalte sintético: Aguarrás o diluyente sintético\n\n", FontFactory.getFont("Microsoft YaHei UI",
                    11)));
            documento.add(new Paragraph("Oleo: Aguarrás o diluyente sintético\n\n", FontFactory.getFont("Microsoft YaHei UI",
                    11)));
            documento.add(new Paragraph("Cantidad diluyente según herramienta\n\n", FontFactory.getFont("Microsoft YaHei UI",
                    13,
                    Font.BOLD)));
            documento.add(new Paragraph("Brocha o rodillo: 5% de diluyente respecto cantidad total de pintura\n\n", FontFactory.getFont("Microsoft YaHei UI",
                    11)));
            documento.add(new Paragraph("Pistola: 10% de diluyente respecto cantidad total de pintura\n\n", FontFactory.getFont("Microsoft YaHei UI",
                    11)));

            documento.add(new Paragraph(txtLitrosxM2.getText().toString() + " (mts2 / litro) **\n\n", FontFactory.getFont("Microsoft YaHei UI",
                    11)));
            documento.add(new Paragraph("\n\n Ficha Material\n\n", FontFactory.getFont("Microsoft YaHei UI",
                    13,
                    Font.BOLD)));
            //Tabla 3
            // Insertamos una tabla__2
            PdfPTable tabla3 = new PdfPTable(7);
            PdfPCell cell3 = new PdfPCell(new Paragraph("Datos de Material", FontFactory.getFont("Microsoft YaHei UI",
                    11,
                    Font.BOLD)));
            cell3.setColspan(14);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tabla3.setHorizontalAlignment(Element.ALIGN_LEFT);
            tabla3.addCell(cell3);
            tabla3.addCell("Marca");
            tabla3.addCell("Descripción");
            tabla3.addCell("Precio");
            tabla3.addCell("Despacho");
            tabla3.addCell("Retiro");
            tabla3.addCell("Tienda");
            tabla3.addCell("Imagen referencial");
            tabla3.addCell(txtMarcaMaterial.getText().toString());
            tabla3.addCell(txtDescripcionMaterial.getText().toString());
            tabla3.addCell(txtPrecioMaterial.getText().toString());
            tabla3.addCell(txtDespachoMaterial.getText().toString());
            tabla3.addCell(txtRetiroMaterial.getText().toString());
            tabla3.addCell(txtTiendaMaterial.getText().toString());
            BitmapDrawable drawable2 = (BitmapDrawable) imgMaterial.getDrawable();
            Bitmap bitmap2 = drawable2.getBitmap();
            ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
            bitmap2.compress(Bitmap.CompressFormat.PNG, 10, stream2);
            Image imagen2 = null;
            imagen2 = Image.getInstance(stream2.toByteArray());
            tabla3.addCell(imagen2);
            //Añadimos la tabla__3
            documento.add(tabla3);
            int year = Calendar.getInstance().get(Calendar.YEAR);
            System.out.println("" + year);
            documento.add(new Paragraph("\n\n"));
            documento.add(new Paragraph("\n\n"));
            Paragraph parrafo4 = new Paragraph("\n\n Cubica2: Soluciones para proyectos en la construcción. " + year, FontFactory.getFont("Microsoft YaHei UI",
                    11,
                    Font.ITALIC));
            parrafo4.setAlignment(Element.ALIGN_CENTER);
            documento.add(parrafo4);
        } catch (DocumentException e) {
        } catch (IOException e) {
        } finally {
            documento.close();
        }
    }

    /**
     * Metodo que crear el fichero y valida
     * si no esta nulo entonces crea
     * un nuevo objeto
     *
     * @param nombreFichero
     * @return fichero
     */
    public File crearFicheroMuro(String nombreFichero) {
        File ruta = getRutaMuro();

        File fichero = null;
        if (ruta != null) {
            fichero = new File(ruta, nombreFichero);
        }

        return fichero;
    }

    /**
     * Metodo que verifica la ruta si no existe crea un nuevo directorio
     *
     * @return ruta del directorio
     */
    public File getRutaMuro() {
        File ruta = null;

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            ruta = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), NOMBRE_DIRECTORIO);

            if (ruta != null) {
                if (!ruta.mkdirs()) {
                    if (!ruta.exists()) {
                        return null;
                    }
                }
            }

        }
        return ruta;
    }

    /**
     * metodo que muestra un dialogo para la confirmación
     * o cancelación de eliminación de cotizacion de muro
     *
     * @param v
     */
    public void eliminarCotiMuro(View v) {
        Dialog dialog = new Dialog(v.getContext());
        dialog.setContentView(R.layout.dialog_eliminar_coti);
        int witdh = WindowManager.LayoutParams.MATCH_PARENT;
        int height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(witdh, height);
        Button btnDelete = dialog.findViewById(R.id.btnDeleteCoti);
        Button btnCancelar = dialog.findViewById(R.id.btnCancelarDeleteCoti);
        dialog.show();
        btnDelete.setOnClickListener(v12 -> {
            deleteCotiMuro();
            Toast.makeText(Ver_Cotizacion_Muro.this, "Cotización borrada", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(Ver_Cotizacion_Muro.this, MiCotizacion.class);
            startActivity(i);

        });
        btnCancelar.setOnClickListener(v1 -> dialog.dismiss());

    }

    /**
     * Metodo para eliminar cotizacion segun
     * id de cotización
     */
    private void deleteCotiMuro() {
        Call<List<DetalleCotizacion>> detalleCotizacionCall = RetrofitBuilder.detalleCotizacionService.deleteCotizacion(detalleCotizacion.getIdDetalleCoti());
        detalleCotizacionCall.enqueue(new Callback<List<DetalleCotizacion>>() {
            @Override
            public void onResponse(Call<List<DetalleCotizacion>> call, Response<List<DetalleCotizacion>> response) {
                if (response.isSuccessful()) {
                }
            }

            @Override
            public void onFailure(Call<List<DetalleCotizacion>> call, Throwable t) {

            }
        });
    }

    /**
     * Al presionar atras vuelve al activity MiCotización
     */
    @Override
    public void onBackPressed() {
        Intent i = new Intent(Ver_Cotizacion_Muro.this, MiCotizacion.class);
        startActivity(i);
    }
}
