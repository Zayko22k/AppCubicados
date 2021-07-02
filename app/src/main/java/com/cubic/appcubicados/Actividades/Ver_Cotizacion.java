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

public class Ver_Cotizacion extends AppCompatActivity {

    private TextView txtNombreCoti;
    private TextView txtFechaCoti;
    private ImageView imgMaterial;
    private TextView txtInmuebleProyecto;
    private TextView txtTCproyecto;
    private TextView txtCproyecto;
    private TextView txtAnchoProyecto;
    private TextView txtLargoProyecto;
    private TextView txtAlturaProyecto;
    private TextView txtM3Proyecto;
    private TextView txtCantSacosProyecto;
    private TextView txtGravaProyecto;
    private TextView txtArenaProyecto;
    private TextView txtAguaProyecto;
    private TextView txtSacosxM3;
    private TextView txtTiendaMaterial;
    private TextView txtMarcaMaterial;
    private TextView txtDescripcionMaterial;
    private TextView txtPrecioMaterial;
    private TextView txtTotalMaterial;
    private TextView txtDespachoMaterial;
    private TextView txtRetiroMaterial;
    private TextView btnExportarCoti;
    private TextView btnEliminarCoti;
    String NOMBRE_DIRECTORIO = "CotizacionCubica2";
    DetalleCotizacion detalleCotizacion = new DetalleCotizacion();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_cotizacion);
        detalleCotizacion = (DetalleCotizacion)getIntent().getSerializableExtra("detalleCoti");
        //Ficha Tecnica
        txtNombreCoti = findViewById(R.id.verNombreCoti);
        txtFechaCoti = findViewById(R.id.txtVerFechaCoti);
        txtInmuebleProyecto = findViewById(R.id.txtInmCoti);
        txtTCproyecto = findViewById(R.id.txtTCCoti);
        txtCproyecto = findViewById(R.id.txtCCoti);
        txtAnchoProyecto = findViewById(R.id.txtAnchoCoti);
        txtLargoProyecto = findViewById(R.id.txtLargoCoti);
        txtAlturaProyecto = findViewById(R.id.txtAltoCoti);
        txtM3Proyecto = findViewById(R.id.txtM3Coti);
        txtCantSacosProyecto = findViewById(R.id.txtsacosCoti);
        txtGravaProyecto = findViewById(R.id.txtGravaCoti);
        txtArenaProyecto = findViewById(R.id.txtArenaCoti);
        txtAguaProyecto = findViewById(R.id.txtAguaCoti);
        txtSacosxM3 = findViewById(R.id.txtDosificacionCoti);
        //Material
        imgMaterial = findViewById(R.id.imgMaterialCoti);
        txtTiendaMaterial = findViewById(R.id.txtTiendaCoti);
        txtMarcaMaterial = findViewById(R.id.txtMarcaMaterialCoti);
        txtDescripcionMaterial = findViewById(R.id.txtDescripcionMaterialCoti);
        txtPrecioMaterial = findViewById(R.id.txtPrecioMaterialCoti);
        txtTotalMaterial = findViewById(R.id.txtTotalMaterialCoti);
        txtDespachoMaterial = findViewById(R.id.txtDespachoMaterialCoti);
        txtRetiroMaterial = findViewById(R.id.txtRetiroMaterialCoti);
        btnExportarCoti = findViewById(R.id.btnExportCoti);
        btnEliminarCoti = findViewById(R.id.btnEliminarCoti);
        cargarCotizacion();
    }
    private void cargarCotizacion(){
      try{
          System.out.println("Id de detalle"+detalleCotizacion.getIdDetalleCoti());
          txtNombreCoti.setText(detalleCotizacion.getNombreCoti());
          String s = new SimpleDateFormat("MM/dd/yyyy").format(detalleCotizacion.getCreated_at());
          txtFechaCoti.setText(s);
          Picasso.get().load(detalleCotizacion.getImagenCemento()).into(imgMaterial);
          txtInmuebleProyecto.setText(detalleCotizacion.getNomInmueble());
          txtTCproyecto.setText(detalleCotizacion.getNomTipoCons());
          txtCproyecto.setText(detalleCotizacion.getNomConstr());
          txtAnchoProyecto.setText(detalleCotizacion.getAncho()+" mts");
          txtLargoProyecto.setText(detalleCotizacion.getLargo()+ " mts");
          txtAlturaProyecto.setText(detalleCotizacion.getProfundidad()+" cms");
          txtM3Proyecto.setText(detalleCotizacion.getM3() + " M³");
          txtCantSacosProyecto.setText(Math.round(detalleCotizacion.getCantidadSacos()) + " sacos de cemento necesitas");
          txtGravaProyecto.setText(String.valueOf(detalleCotizacion.getGrava()));
          txtArenaProyecto.setText(String.valueOf(detalleCotizacion.getArena()));
          txtAguaProyecto.setText(String.valueOf(detalleCotizacion.getAgua()));
          txtSacosxM3.setText(detalleCotizacion.getDosificacion()+" (Sacos/M³)");
          txtTiendaMaterial.setText(detalleCotizacion.getNomTienda());
          txtMarcaMaterial.setText(detalleCotizacion.getMarcaCemento());
          txtDescripcionMaterial.setText(detalleCotizacion.getDescripcioncemento());
          txtPrecioMaterial.setText("$ " + detalleCotizacion.getPrecio() + " C/U");
          txtTotalMaterial.setText("$ " + detalleCotizacion.getTotal() + " por los " + Math.round(detalleCotizacion.getCantidadSacos()) + " sacos");
          txtDespachoMaterial.setText(detalleCotizacion.getDespacho());
          txtRetiroMaterial.setText(detalleCotizacion.getRetiro());
      }catch (Exception e){
          Toast.makeText(Ver_Cotizacion.this,"No se pudo cargar la cotizacion", Toast.LENGTH_LONG).show();
      }
    }
    public void exportarCoti(View v){
        // Permisos
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,},
                    1000);
        }
        crearPDF();
        Toast.makeText(Ver_Cotizacion.this,"Se creo el pdf", Toast.LENGTH_SHORT).show();

    }
    public void crearPDF() {
        Document documento = new Document(PageSize.A4, 20, 20, 20, 20);

        try {

            File file = crearFichero("Proyecto_" + txtNombreCoti.getText().toString()+".pdf");
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
            PdfPTable tabla2 = new PdfPTable(8);
            PdfPCell cell2 = new PdfPCell(new Paragraph("Diámetros del proyecto", FontFactory.getFont("Microsoft YaHei UI",
                    11,
                    Font.BOLD)));
            cell2.setColspan(16);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tabla2.setHorizontalAlignment(Element.ALIGN_LEFT);
            tabla2.addCell(cell2);
            tabla2.addCell("Ancho(mts)");
            tabla2.addCell("Alto(cms)");
            tabla2.addCell("Largo(mts)");
            tabla2.addCell("M3");
            tabla2.addCell("Cantidad de sacos");
            tabla2.addCell("Grava");
            tabla2.addCell("Arena");
            tabla2.addCell("Agua");
            tabla2.addCell(txtAnchoProyecto.getText().toString());
            tabla2.addCell(txtAlturaProyecto.getText().toString());
            tabla2.addCell(txtLargoProyecto.getText().toString());
            tabla2.addCell(txtM3Proyecto.getText().toString());
            tabla2.addCell(txtCantSacosProyecto.getText().toString());
            tabla2.addCell(txtGravaProyecto.getText().toString());
            tabla2.addCell(txtArenaProyecto.getText().toString());
            tabla2.addCell(txtAguaProyecto.getText().toString());
            //Añadimos la tabla__2
            documento.add(tabla2);
            documento.add(new Paragraph("\n\n **Considera utilizar baldes de 10 litros \n\n", FontFactory.getFont("Microsoft YaHei UI",
                    11,
                    Font.ITALIC)));
            documento.add(new Paragraph("Rendimiento aproximado\n\n", FontFactory.getFont("Microsoft YaHei UI",
                    11,
                    Font.ITALIC)));
            documento.add(new Paragraph(txtSacosxM3.getText().toString() + " (sacos/m3) **\n\n", FontFactory.getFont("Microsoft YaHei UI",
                    11)));
            documento.add(new Paragraph("Ficha Material\n\n", FontFactory.getFont("Microsoft YaHei UI",
                    11,
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
            // Insertamos una tabla__4
            PdfPTable tabla4 = new PdfPTable(2);
            PdfPCell cell4 = new PdfPCell();
            cell4.setColspan(4);
            tabla4.setTotalWidth(150f);
            tabla4.setLockedWidth(true);
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla4.setHorizontalAlignment(Element.ALIGN_LEFT);
            tabla4.addCell(cell4);
            tabla4.addCell("Total");
            tabla4.addCell(txtTotalMaterial.getText().toString());
            //Añadimos la tabla
            documento.add(new Paragraph("\n\n"));
            documento.add(tabla4);
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


    public File crearFichero(String nombreFichero) {
        File ruta = getRuta();

        File fichero = null;
        if (ruta != null) {
            fichero = new File(ruta, nombreFichero);
        }

        return fichero;
    }

    public File getRuta() {
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
    public void eliminarCoti(View v){
        Dialog dialog = new Dialog(v.getContext());
        dialog.setContentView(R.layout.dialog_eliminar_coti);
        int witdh = WindowManager.LayoutParams.MATCH_PARENT;
        int height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(witdh, height);
        Button btnDelete = dialog.findViewById(R.id.btnDeleteCoti);
        Button btnCancelar = dialog.findViewById(R.id.btnCancelarDeleteCoti);
        dialog.show();
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCoti();
                Toast.makeText(Ver_Cotizacion.this, "Cotización borrada",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Ver_Cotizacion.this, MiCotizacion.class);
                startActivity(i);

            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
    private void deleteCoti(){
        Call<List<DetalleCotizacion>> detalleCotizacionCall = RetrofitBuilder.detalleCotizacionService.deleteCotizacion(detalleCotizacion.getIdDetalleCoti());
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

    }
}