package com.proyecto.tecnobedelias_desktop.utils;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.proyecto.tecnobedelias_desktop.model.Actividad;
import com.proyecto.tecnobedelias_desktop.model.Curso_Estudiante;
import com.proyecto.tecnobedelias_desktop.model.Estudiante_Examen;
import java.io.*;
import java.text.DateFormat;

/**
 * Example of using the iText library to work with PDF documents on Java, lets
 * you create, analyze, modify and maintain documents in this format. Ejemplo de
 * uso de la librería iText para trabajar con documentos PDF en Java, nos
 * permite crear, analizar, modificar y mantener documentos en este formato.
 *
 * @author xules You can follow me on my website http://www.codigoxules.org/en
 *         Puedes seguirme en mi web http://www.codigoxules.org
 */
public class GeneratePDFFileIText {
	// Fonts definitions (Definición de fuentes).
	private static final Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 26, Font.BOLDITALIC);
	private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);

	private static final Font categoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static final Font subcategoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	private static final Font escolarityTitleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLDITALIC + Font.UNDERLINE);
	private static final Font escolaritySubTitleFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.ITALIC + Font.UNDERLINE);
	private static final Font escolarityStudentFont = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
//	private static final Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
//	private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

	private static final String iTextExampleImage = "src/resources/images/TecnoBedeliasLogoPrincipal.PNG";

	static PdfPCell columnCI = null;
	static PdfPCell columnNombre = null;
	static PdfPCell columnNota = null;
	static PdfPCell columnUnidadCurricularBasica = null;
	static PdfPCell columnCreditos = null;
	static PdfPCell columnActividad = null;
	static PdfPCell columnFecha = null;

	/**
	 * We create a PDF document with iText using different elements to learn to use
	 * this library. Creamos un documento PDF con iText usando diferentes elementos
	 * para aprender a usar esta librería.
	 *
	 * @param pdfNewFile <code>String</code> pdf File we are going to write. Fichero
	 *                   pdf en el que vamos a escribir.
	 */
	public void createPDF(File pdfNewFile) {
		// We create the document and set the file name.
		// Creamos el documento e indicamos el nombre del fichero.
		try {
			Document document = new Document();
			try {

				PdfWriter.getInstance(document, new FileOutputStream(pdfNewFile));

			} catch (FileNotFoundException fileNotFoundException) {
				System.out.println("No such file was found to generate the PDF "
						+ "(No se encontró el fichero para generar el pdf)" + fileNotFoundException);
			}
			document.open();
			// We add metadata to PDF
			// Añadimos los metadatos del PDF
			document.addTitle("Table export to PDF (Exportamos la tabla a PDF)");
			document.addSubject("Using iText (usando iText)");
			document.addKeywords("Java, PDF, iText");
			document.addAuthor("Código Xules");
			document.addCreator("Código Xules");

			// First page
			// Primera página
			Chunk chunk = new Chunk("This is the title", chapterFont);
			chunk.setBackground(BaseColor.GRAY);
			// Let's create de first Chapter (Creemos el primer capítulo)
			Chapter chapter = new Chapter(new Paragraph(chunk), 1);
			chapter.setNumberDepth(0);
			chapter.add(new Paragraph("This is the paragraph", paragraphFont));
			// We add an image (Añadimos una imagen)
			Image image;
			try {
				image = Image.getInstance(iTextExampleImage);
				image.setAbsolutePosition(2, 150);
				chapter.add(image);
			} catch (BadElementException ex) {
				System.out.println("Image BadElementException" + ex);
			} catch (IOException ex) {
				System.out.println("Image IOException " + ex);
			}
			document.add(chapter);

			// Second page - some elements
			// Segunda página - Algunos elementos
			Chapter chapSecond = new Chapter(new Paragraph(new Anchor("Some elements (Añadimos varios elementos)")), 1);
			Paragraph paragraphS = new Paragraph("Do it by Xules (Realizado por Xules)", subcategoryFont);

			// Underline a paragraph by iText (subrayando un párrafo por iText)
			Paragraph paragraphE = new Paragraph(
					"This line will be underlined with a dotted line (Está línea será subrayada con una línea de puntos).");
			DottedLineSeparator dottedline = new DottedLineSeparator();
			dottedline.setOffset(-2);
			dottedline.setGap(2f);
			paragraphE.add(dottedline);
			chapSecond.addSection(paragraphE);

			Section paragraphMoreS = chapSecond.addSection(paragraphS);
			// List by iText (listas por iText)
			String text = "test 1 2 3 ";
			for (int i = 0; i < 5; i++) {
				text = text + text;
			}
			List list = new List(List.UNORDERED);
			ListItem item = new ListItem(text);
			item.setAlignment(Element.ALIGN_JUSTIFIED);
			list.add(item);
			text = "a b c align ";
			for (int i = 0; i < 5; i++) {
				text = text + text;
			}
			item = new ListItem(text);
			item.setAlignment(Element.ALIGN_JUSTIFIED);
			list.add(item);
			text = "supercalifragilisticexpialidocious ";
			for (int i = 0; i < 3; i++) {
				text = text + text;
			}
			item = new ListItem(text);
			item.setAlignment(Element.ALIGN_JUSTIFIED);
			list.add(item);
			paragraphMoreS.add(list);
			document.add(chapSecond);

			// How to use PdfPTable
			// Utilización de PdfPTable

			// We use various elements to add title and subtitle
			// Usamos varios elementos para añadir título y subtítulo
			Anchor anchor = new Anchor("Table export to PDF (Exportamos la tabla a PDF)", categoryFont);
			anchor.setName("Table export to PDF (Exportamos la tabla a PDF)");
			Chapter chapTitle = new Chapter(new Paragraph(anchor), 1);
			Paragraph paragraph = new Paragraph("Do it by Xules (Realizado por Xules)", subcategoryFont);
			Section paragraphMore = chapTitle.addSection(paragraph);
			paragraphMore.add(new Paragraph("This is a simple example (Este es un ejemplo sencillo)"));
			Integer numColumns = 6;
			Integer numRows = 120;
			// We create the table (Creamos la tabla).
			PdfPTable table = new PdfPTable(numColumns);
			// Now we fill the PDF table
			// Ahora llenamos la tabla del PDF
			PdfPCell columnHeader;
			// Fill table rows (rellenamos las filas de la tabla).
			for (int column = 0; column < numColumns; column++) {
				columnHeader = new PdfPCell(new Phrase("COL " + column));
				columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(columnHeader);
			}
			table.setHeaderRows(1);
			// Fill table rows (rellenamos las filas de la tabla).
			for (int row = 0; row < numRows; row++) {
				for (int column = 0; column < numColumns; column++) {
					table.addCell("Row " + row + " - Col" + column);
				}
			}
			// We add the table (Añadimos la tabla)
			paragraphMore.add(table);
			// We add the paragraph with the table (Añadimos el elemento con la tabla).
			document.add(chapTitle);
			document.close();
			System.out.println("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!");
		} catch (DocumentException documentException) {
			System.out.println(
					"The file not exists (Se ha producido un error al generar un documento): " + documentException);
		}
	}

	public void crearActaFinalDeCurso(String nombre, java.util.List<Curso_Estudiante> lstEstudiantes) {
		try {
			Document document = new Document();
			try {
				PdfWriter.getInstance(document, new FileOutputStream("src/resources/pdf/actaCurso" + nombre + ".pdf"));
			} catch (FileNotFoundException fileNotFoundException) {
				System.out.println("No such file was found to generate the PDF "
						+ "(No se encontró el fichero para generar el pdf)" + fileNotFoundException);
			}
			document.open();
			document.addTitle("Acta final del curso " + nombre);
			document.addSubject("TecnoBedelias");
			document.addKeywords("Java, PDF, iText");
			document.addAuthor("TecnoBedelias");
			document.addCreator("TecnoBedelias");

			Chunk chunk = new Chunk("Acta final del curso " + nombre, chapterFont);
			Paragraph preface = new Paragraph(chunk);

			preface.setAlignment(Element.ALIGN_CENTER);

			Chapter chapter = new Chapter(preface, 1);
			chapter.setNumberDepth(0);

			Section paragraphMore = chapter;

			Integer numColumns = 3;
			PdfPTable table = new PdfPTable(numColumns);

			table.setWidths(new float[] { 2, 7, 1 });

			PdfPCell columnHeaderCI = new PdfPCell(new Phrase("CI"));
			PdfPCell columnHeaderNombre = new PdfPCell(new Phrase("Nombres"));
			PdfPCell columnHeaderNota = new PdfPCell(new Phrase("Nota"));

			columnCI = null;
			columnNombre = null;
			columnNota = null;

			columnHeaderCI.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeaderNombre.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeaderNota.setHorizontalAlignment(Element.ALIGN_CENTER);

			columnHeaderCI.setBackgroundColor(BaseColor.GREEN);
			columnHeaderNombre.setBackgroundColor(BaseColor.GREEN);
			columnHeaderNota.setBackgroundColor(BaseColor.GREEN);

			table.addCell(columnHeaderCI);
			table.addCell(columnHeaderNombre);
			table.addCell(columnHeaderNota);

			table.setHeaderRows(1);

			lstEstudiantes.forEach(estudiante -> {
				columnCI = new PdfPCell(new Phrase(estudiante.getCedula()));
				columnNombre = new PdfPCell(new Phrase(estudiante.getApellido().toUpperCase() + ", " + estudiante.getNombre().toUpperCase()));
				columnNota = new PdfPCell(new Phrase(estudiante.getNota().toString()));

				columnCI.setHorizontalAlignment(Element.ALIGN_CENTER);
				columnNota.setHorizontalAlignment(Element.ALIGN_CENTER);

				table.addCell(columnCI);
				table.addCell(columnNombre);
				table.addCell(columnNota);
			});

			paragraphMore.add(new Paragraph("\n\n"));
			paragraphMore.add(table);
			document.add(chapter);
			document.close();

			System.out.println("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!");
		} catch (DocumentException documentException) {
			System.out.println(
					"The file not exists (Se ha producido un error al generar un documento): " + documentException);
		}
	}

	public void crearActaFinalDeExamen(String nombre, java.util.List<Estudiante_Examen> lstEstudiantes) {
		try {
			Document document = new Document();
			try {
				PdfWriter.getInstance(document, new FileOutputStream("src/resources/pdf/actaExamen" + nombre + ".pdf"));
			} catch (FileNotFoundException fileNotFoundException) {
				System.out.println("No such file was found to generate the PDF "
						+ "(No se encontró el fichero para generar el pdf)" + fileNotFoundException);
			}
			document.open();
			document.addTitle("Acta final del examen " + nombre);
			document.addSubject("TecnoBedelias");
			document.addKeywords("Java, PDF, iText");
			document.addAuthor("TecnoBedelias");
			document.addCreator("TecnoBedelias");

			Chunk chunk = new Chunk("Acta final del examen " + nombre, chapterFont);
			Paragraph preface = new Paragraph(chunk);

			preface.setAlignment(Element.ALIGN_CENTER);

			Chapter chapter = new Chapter(preface, 1);
			chapter.setNumberDepth(0);

			Section paragraphMore = chapter;

			Integer numColumns = 3;
			PdfPTable table = new PdfPTable(numColumns);
			PdfPCell columnHeaderCI = new PdfPCell(new Phrase("CI"));
			PdfPCell columnHeaderNombre = new PdfPCell(new Phrase("NOMBRES"));
			PdfPCell columnHeaderNota = new PdfPCell(new Phrase("NOTA"));

			columnHeaderCI.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeaderNombre.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeaderNota.setHorizontalAlignment(Element.ALIGN_CENTER);

			columnHeaderCI.setBackgroundColor(BaseColor.GREEN);
			columnHeaderNombre.setBackgroundColor(BaseColor.GREEN);
			columnHeaderNota.setBackgroundColor(BaseColor.GREEN);

			table.addCell(columnHeaderCI);
			table.addCell(columnHeaderNombre);
			table.addCell(columnHeaderNota);

			table.setHeaderRows(1);

			lstEstudiantes.forEach(estudiante -> {
				table.addCell(estudiante.getCedula());
				table.addCell(estudiante.getApellido() + ", " + estudiante.getNombre());
				table.addCell(estudiante.getNota().toString());
			});

			paragraphMore.add(new Paragraph("\n\n"));
			paragraphMore.add(table);
			document.add(chapter);
			document.close();

			System.out.println("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!");
		} catch (DocumentException documentException) {
			System.out.println(
					"The file not exists (Se ha producido un error al generar un documento): " + documentException);
		}
	}

	public void crearEscolaridad(String ci, java.util.List<Actividad> actividades) {
		try {
			Document document = new Document();
			try {
				PdfWriter.getInstance(document, new FileOutputStream("src/resources/pdf/ReporteEscolaridad-DOC" + ci + ".pdf"));
			} catch (FileNotFoundException fileNotFoundException) {
				System.out.println("No such file was found to generate the PDF "
						+ "(No se encontró el fichero para generar el pdf)" + fileNotFoundException);
			}
			document.open();
			document.addTitle("ReporteEscolaridad-DOC"+ci);
			document.addSubject("TecnoBedelias");
			document.addKeywords("Java, PDF, iText");
			document.addAuthor("TecnoBedelias");
			document.addCreator("TecnoBedelias");

			Chunk chunk = new Chunk("CERTIFICADO DE ESCOLARIDAD", escolarityTitleFont);
			Paragraph preface = new Paragraph(chunk);

			preface.setAlignment(Element.ALIGN_CENTER);

			Chapter chapter = new Chapter(preface, 1);
			chapter.setNumberDepth(0);

			Section paragraphMore = chapter;

			Integer numColumns = 5;
			PdfPTable table = new PdfPTable(numColumns);
			table.setWidthPercentage(90);
			table.setKeepTogether(true);

			table.setWidths(new float[] { 6, 1, 2, 2, 1 });

			PdfPCell columnHeaderUnidadCurricularBasica = new PdfPCell(new Phrase("Unidad Curricular Basica"));
			PdfPCell columnHeaderCreditos = new PdfPCell(new Phrase("Cred"));
			PdfPCell columnHeaderActividad = new PdfPCell(new Phrase("Actividad"));
			PdfPCell columnHeaderFecha = new PdfPCell(new Phrase("Fecha"));
			PdfPCell columnHeaderNota = new PdfPCell(new Phrase("Nota"));

			columnUnidadCurricularBasica = null;
			columnCreditos = null;
			columnActividad = null;
			columnFecha = null;
			columnNota = null;

			columnHeaderCreditos.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeaderActividad.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeaderFecha.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeaderNota.setHorizontalAlignment(Element.ALIGN_CENTER);

			columnHeaderUnidadCurricularBasica.setBackgroundColor(BaseColor.GREEN);
			columnHeaderCreditos.setBackgroundColor(BaseColor.GREEN);
			columnHeaderActividad.setBackgroundColor(BaseColor.GREEN);
			columnHeaderFecha.setBackgroundColor(BaseColor.GREEN);
			columnHeaderNota.setBackgroundColor(BaseColor.GREEN);

			table.addCell(columnHeaderUnidadCurricularBasica);
			table.addCell(columnHeaderCreditos);
			table.addCell(columnHeaderActividad);
			table.addCell(columnHeaderFecha);
			table.addCell(columnHeaderNota);

			table.setHeaderRows(1);

			actividades.forEach(System.out::println);

			actividades.forEach(actividad -> {
				columnUnidadCurricularBasica = new PdfPCell(new Phrase(actividad.getAsignatura()));
				columnCreditos = new PdfPCell(new Phrase(String.valueOf(actividad.getCreditos())));
				columnActividad = new PdfPCell(new Phrase(actividad.getTipo()));
				columnFecha = new PdfPCell(new Phrase(DateFormat.getDateInstance().format(actividad.getFecha())));
				columnNota = new PdfPCell(new Phrase(actividad.getNota().toString()));
				columnCreditos.setHorizontalAlignment(Element.ALIGN_CENTER);
				columnActividad.setHorizontalAlignment(Element.ALIGN_CENTER);
				columnFecha.setHorizontalAlignment(Element.ALIGN_CENTER);
				columnNota.setHorizontalAlignment(Element.ALIGN_CENTER);

				table.addCell(columnUnidadCurricularBasica);
				table.addCell(columnCreditos);
				table.addCell(columnActividad);
				table.addCell(columnFecha);
				table.addCell(columnNota);
			});

			Paragraph subtitulo = new Paragraph("Resultados de cursos y examenes \n\n", escolaritySubTitleFont);
			subtitulo.setAlignment(Element.ALIGN_CENTER);

			Paragraph estudiante = new Paragraph(ci + " - " + actividades.get(0).getApellido().toUpperCase() + ", " + actividades.get(0).getNombre().toUpperCase() + "\n\n", escolarityStudentFont);
			estudiante.setAlignment(Element.ALIGN_CENTER);

			paragraphMore.add(subtitulo);
			paragraphMore.add(estudiante);
			paragraphMore.add(table);
			document.add(chapter);
			document.close();

			System.out.println("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!");
		} catch (DocumentException documentException) {
			System.out.println(
					"The file not exists (Se ha producido un error al generar un documento): " + documentException);
		}
	}

}
