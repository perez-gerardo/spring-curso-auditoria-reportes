package com.tecsup.demo.views;

import com.tecsup.demo.domain.entities.Curso;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.view.AbstractView;

import java.util.List;
import java.util.Map;

public class CursoXlsView extends AbstractView {

    public CursoXlsView() {
        setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
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
        response.setHeader("Content-Disposition", "attachment; filename=cursos.xlsx");

        @SuppressWarnings("unchecked")
        List<Curso> cursos = (List<Curso>) model.get("cursos");

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Lista de Cursos");
            sheet.setDefaultColumnWidth(20);

            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.SEA_GREEN.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);

            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setAlignment(HorizontalAlignment.LEFT);

            Row header = sheet.createRow(0);
            crearCelda(header, 0, "ID", headerStyle);
            crearCelda(header, 1, "Código", headerStyle);
            crearCelda(header, 2, "Nombre", headerStyle);
            crearCelda(header, 3, "Créditos", headerStyle);

            if (cursos != null) {
                int row = 1;
                for (Curso curso : cursos) {
                    Row data = sheet.createRow(row++);
                    crearCelda(data, 0, curso.getId(), bodyStyle);
                    crearCelda(data, 1, curso.getCodigo(), bodyStyle);
                    crearCelda(data, 2, curso.getNombre(), bodyStyle);
                    crearCelda(data, 3, curso.getCreditos(), bodyStyle);
                }
            }

            workbook.write(response.getOutputStream());
        }
    }

    private void crearCelda(Row row, int col, @Nullable Object valor, CellStyle style) {
        Cell cell = row.createCell(col);
        if (valor instanceof Number number) {
            cell.setCellValue(number.doubleValue());
        } else {
            cell.setCellValue(valor != null ? valor.toString() : "");
        }
        cell.setCellStyle(style);
    }
}

