package com.example.ProductFinder.reportes;

import com.example.ProductFinder.modelo.Rol;
import com.example.ProductFinder.modelo.Usuario;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.*;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;


public class UsuariosReportePdf {

    // Lista de usuarios que se utilizará para generar el reporte
    private List<Usuario> listaUsuarios = new ArrayList<>();

    // Constructor que recibe la lista de usuarios
    public UsuariosReportePdf(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }


    // Método para escribir la cabecera de la tabla en el PDF
    private void escribirCabeceraDelaTabla(PdfPTable tabla) {
        PdfPCell celda = new PdfPCell();
        celda.setBackgroundColor(Color.RED);
        celda.setPadding(5);

    // Fuente y estilo para la cabecera de la tabla

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(Color.white);

        // Agregar las celdas de la cabecera con los títulos
        celda.setPhrase(new Phrase("ID", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("NOMBRE", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("APELLIDO", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("DOCUMENTO", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("SEXO", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("TELEFONO", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("EMAIL", fuente));
        tabla.addCell(celda);
        celda.setPhrase(new Phrase("ROLES", fuente));
        tabla.addCell(celda);
    }

    // Método para escribir los datos de la tabla en el PDF de la lista de usuarios
    private void escribirDatosDeLaTabla(PdfPTable tabla) {
        for (Usuario usuario : listaUsuarios) {
            tabla.addCell(String.valueOf(usuario.getId()));
            tabla.addCell(usuario.getNombre());
            tabla.addCell(usuario.getApellido());
            tabla.addCell(usuario.getDocumento());
            tabla.addCell(usuario.getSexo());
            tabla.addCell(usuario.getTelefono());
            tabla.addCell(usuario.getEmail());
            for (Rol roles : usuario.getRoles()) {
                tabla.addCell(roles.getNombre());
            }
        }
    }
    // Método para exportar el PDF con la lista de usuarios
    public void exportar(HttpServletResponse response) throws IOException {
        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, response.getOutputStream());

        documento.open();
        Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(Color.BLACK);
        fuente.setSize(18);
        Paragraph titulo = new Paragraph("lista de usuarios", fuente);// Título del documento
        titulo.setAlignment(Paragraph.ALIGN_CENTER);

        documento.add(titulo);// agrego el título al documento

        PdfPTable tabla = new PdfPTable(8); // se crea una tabla con 8 columnas

        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(15);
        tabla.setWidths(new float[]{1f, 2.3f, 2.3f, 6f, 2.9f, 3.5f, 2f, 2.2f});// ancho de las columnas
        tabla.setWidthPercentage(110);

        escribirCabeceraDelaTabla(tabla); // Agregar la cabecera de la tabla
        escribirDatosDeLaTabla(tabla); // Agregar los datos de la tabla
        documento.add(tabla); // Agregar la tabla al documento
        documento.close(); // Cerrar el documento
    }
}
