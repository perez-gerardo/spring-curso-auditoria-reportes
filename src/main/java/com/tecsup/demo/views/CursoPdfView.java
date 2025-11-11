package com.tecsup.demo.views;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.tecsup.demo.domain.entities.Curso;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.view.AbstractView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.util.List;
import java.util.Map;

public class CursoPdfView extends AbstractView {

    public CursoPdfView() {
        setContentType("application/pdf");
    }

    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }

    @Override
    protected void renderMergedOutputModel(
            Map<String, Object> model,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        response.setContentType(getContentType());
        response.setHeader("Content-Disposition", "attachment; filename=cursos.pdf");

        @SuppressWarnings("unchecked")
        List<Curso> cursos = (List<Curso>) model.get("cursos");

        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, Color.BLACK);
        Paragraph titulo = new Paragraph("Lista de Cursos", titleFont);
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingAfter(12f);
        document.add(titulo);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setWidths(new float[]{1.5f, 3f, 5f, 2f});

        agregarCeldaCabecera(table, "ID");
        agregarCeldaCabecera(table, "Código");
        agregarCeldaCabecera(table, "Nombre");
        agregarCeldaCabecera(table, "Créditos");

        if (cursos != null) {
            for (Curso curso : cursos) {
                table.addCell(valorSeguro(curso.getId()));
                table.addCell(valorSeguro(curso.getCodigo()));
                table.addCell(valorSeguro(curso.getNombre()));
                table.addCell(valorSeguro(curso.getCreditos()));
            }
        }

        document.add(table);
        document.close();
    }

    private void agregarCeldaCabecera(PdfPTable table, String texto) {
        PdfPCell header = new PdfPCell();
        header.setBackgroundColor(new Color(23, 162, 184));
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        header.setPadding(8f);
        header.setPhrase(new Paragraph(texto, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.WHITE)));
        table.addCell(header);
    }

    private String valorSeguro(@Nullable Object valor) {
        return valor != null ? valor.toString() : "";
    }
}

