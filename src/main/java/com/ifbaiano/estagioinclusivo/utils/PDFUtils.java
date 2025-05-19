/*package com.ifbaiano.estagioinclusivo.utils;

import com.ifbaiano.estagioinclusivo.model.Candidato;
import com.ifbaiano.estagioinclusivo.model.Curso;
import com.ifbaiano.estagioinclusivo.model.TipoDeficiencia;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import java.io.OutputStream;
import java.util.stream.Collectors;

public class PDFUtils {

    public static void generateCurriculoPDF(OutputStream out, Candidato candidato, java.util.List<Curso> cursos, java.util.List<TipoDeficiencia> deficiencias) {
        try {
            // Configuração do documento PDF
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            // Fonte e Estilo
            PdfFont font = PdfFontFactory.createFont(PdfFontFactory.HELVETICA);
            Color titleColor = new DeviceRgb(0, 102, 204);

            // Título
            Paragraph title = new Paragraph("Currículo Profissional")
                    .setFont(font)
                    .setFontSize(20)
                    .setFontColor(titleColor)
                    .setBold();
            document.add(title);

            // Informações Pessoais
            document.add(new Paragraph("\nInformações Pessoais")
                    .setFontSize(16)
                    .setBold());

            document.add(new Paragraph("Nome: " + candidato.getNome()));
            document.add(new Paragraph("Email: " + candidato.getEmail()));
            document.add(new Paragraph("Telefone: " + candidato.getTelefone()));
            document.add(new Paragraph("Endereço: " +
                    candidato.getEndereco().getRua() + ", " +
                    candidato.getEndereco().getBairro() + ", " +
                    candidato.getEndereco().getMunicipio() + ", " +
                    candidato.getEndereco().getEstado() + " - " +
                    candidato.getEndereco().getCep()));

            // Habilidades e Objetivo
            document.add(new Paragraph("\nObjetivo Profissional")
                    .setFontSize(16)
                    .setBold());
            document.add(new Paragraph(candidato.getCurriculo().getObjetivo() != null ? candidato.getCurriculo().getObjetivo() : "Não Informado"));

            document.add(new Paragraph("\nHabilidades")
                    .setFontSize(16)
                    .setBold());
            document.add(new Paragraph(candidato.getCurriculo().getHabilidades() != null ? candidato.getCurriculo().getHabilidades() : "Não Informado"));

            // Experiência Profissional
            document.add(new Paragraph("\nExperiência Profissional")
                    .setFontSize(16)
                    .setBold());
            document.add(new Paragraph(candidato.getCurriculo().getExperiencia() != null ? candidato.getCurriculo().getExperiencia() : "Não Informado"));

            // Cursos
            document.add(new Paragraph("\nCursos")
                    .setFontSize(16)
                    .setBold());

            if (cursos.isEmpty()) {
                document.add(new Paragraph("Nenhum curso cadastrado."));
            } else {
                List courseList = new List();
                for (Curso curso : cursos) {
                    courseList.add(new ListItem(curso.getNomeCurso() + " - " + curso.getInstituicao() +
                            " (" + curso.getDataInicio() + " a " + curso.getDataFim() + ")"));
                }
                document.add(courseList);
            }

            // Deficiências
            document.add(new Paragraph("\nDeficiências")
                    .setFontSize(16)
                    .setBold());

            if (deficiencias.isEmpty()) {
                document.add(new Paragraph("Nenhuma deficiência cadastrada."));
            } else {
                List defList = new List();
                for (TipoDeficiencia def : deficiencias) {
                    defList.add(new ListItem(def.getNome() + " - " + def.getDescricao()));
                }
                document.add(defList);
            }

            // Fechar o documento
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao gerar o PDF do currículo.", e);
        }
    }
}*/
