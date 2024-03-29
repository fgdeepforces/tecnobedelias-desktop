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
import com.proyecto.tecnobedelias_desktop.model.Carrera;
import com.proyecto.tecnobedelias_desktop.model.Curso;
import com.proyecto.tecnobedelias_desktop.model.Curso_Estudiante;
import com.proyecto.tecnobedelias_desktop.model.Estudiante_Examen;
import com.proyecto.tecnobedelias_desktop.model.Rol;
import com.proyecto.tecnobedelias_desktop.model.Usuario;

import java.io.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Example of using the iText library to work with PDF documents on Java, lets
 * you create, analyze, modify and maintain documents in this format. Ejemplo de
 * uso de la librer�a iText para trabajar con documentos PDF en Java, nos
 * permite crear, analizar, modificar y mantener documentos en este formato.
 *
 * @author xules You can follow me on my website http://www.codigoxules.org/en
 *         Puedes seguirme en mi web http://www.codigoxules.org
 */
public class GeneratePDFFileIText {
	// Fonts definitions (Definici�n de fuentes).
	private static final Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 26, Font.BOLDITALIC);
	private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);

	private static final Font categoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static final Font subcategoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	private static final Font escolarityTitleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLDITALIC + Font.UNDERLINE);
	private static final Font escolarityCarreerTitleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLDITALIC);
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
	static PdfPCell columnEstado = null;
	static PdfPCell columnFecha = null;

	private String asignatura = null;
	private Integer creditos = null;
	private String apellido = null;
	private String nombre = null;
	private String estado = null;
	private Date fecha = null;
	private Integer id = null;
	private Integer nota = null;
	private Integer notaMaxima = null;
	private String tipo = null;

	/**
	 * We create a PDF document with iText using different elements to learn to use
	 * this library. Creamos un documento PDF con iText usando diferentes elementos
	 * para aprender a usar esta librer�a.
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
						+ "(No se encontr� el fichero para generar el pdf)" + fileNotFoundException);
			}
			document.open();
			// We add metadata to PDF
			// A�adimos los metadatos del PDF
			document.addTitle("Table export to PDF (Exportamos la tabla a PDF)");
			document.addSubject("Using iText (usando iText)");
			document.addKeywords("Java, PDF, iText");
			document.addAuthor("C�digo Xules");
			document.addCreator("C�digo Xules");

			// First page
			// Primera p�gina
			Chunk chunk = new Chunk("This is the title", chapterFont);
			chunk.setBackground(BaseColor.GRAY);
			// Let's create de first Chapter (Creemos el primer cap�tulo)
			Chapter chapter = new Chapter(new Paragraph(chunk), 1);
			chapter.setNumberDepth(0);
			chapter.add(new Paragraph("This is the paragraph", paragraphFont));
			// We add an image (A�adimos una imagen)
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
			// Segunda p�gina - Algunos elementos
			Chapter chapSecond = new Chapter(new Paragraph(new Anchor("Some elements (A�adimos varios elementos)")), 1);
			Paragraph paragraphS = new Paragraph("Do it by Xules (Realizado por Xules)", subcategoryFont);

			// Underline a paragraph by iText (subrayando un p�rrafo por iText)
			Paragraph paragraphE = new Paragraph(
					"This line will be underlined with a dotted line (Est� l�nea ser� subrayada con una l�nea de puntos).");
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
			// Utilizaci�n de PdfPTable

			// We use various elements to add title and subtitle
			// Usamos varios elementos para a�adir t�tulo y subt�tulo
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
			// We add the table (A�adimos la tabla)
			paragraphMore.add(table);
			// We add the paragraph with the table (A�adimos el elemento con la tabla).
			document.add(chapTitle);
			document.close();
			System.out.println("Your PDF file has been generated!(�Se ha generado tu hoja PDF!");
		} catch (DocumentException documentException) {
			System.out.println(
					"The file not exists (Se ha producido un error al generar un documento): " + documentException);
		}
	}

	public void crearActaFinalDeCurso(String nombre, int semestre, int anio, java.util.List<Curso_Estudiante> lstEstudiantes) {
		try {
			Document document = new Document();
			try {
				PdfWriter.getInstance(document, new FileOutputStream("src/resources/pdf/actaCurso" + nombre + ".pdf"));
			} catch (FileNotFoundException fileNotFoundException) {
				System.out.println("No such file was found to generate the PDF "
						+ "(No se encontr� el fichero para generar el pdf)" + fileNotFoundException);
			}
			document.open();
			document.addTitle("Acta de fin del curso de " + nombre + " del semestre " + semestre + " del a�o " + anio);
			document.addSubject("TecnoBedelias");
			document.addKeywords("Java, PDF, iText");
			document.addAuthor("TecnoBedelias");
			document.addCreator("TecnoBedelias");

			Chunk chunk = new Chunk("Acta de fin del curso de " + nombre + " del semestre " + semestre + " del a�o " + anio, chapterFont);
			Paragraph preface = new Paragraph(chunk);

			preface.setAlignment(Element.ALIGN_CENTER);

			Chapter chapter = new Chapter(preface, 1);
			chapter.setNumberDepth(0);

			Section paragraphMore = chapter;

			Integer numColumns = 3;
			PdfPTable table = new PdfPTable(numColumns);

			table.setWidths(new float[] { 2, 7, 1 });

			PdfPCell columnHeaderCI = new PdfPCell(new Phrase("CI"));
			PdfPCell columnHeaderNombre = new PdfPCell(new Phrase("NOMBRE"));
			PdfPCell columnHeaderNota = new PdfPCell(new Phrase("NOTA"));

			columnCI = null;
			columnNombre = null;
			columnNota = null;

			columnHeaderCI.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeaderNombre.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeaderNota.setHorizontalAlignment(Element.ALIGN_CENTER);

			columnHeaderCI.setBackgroundColor(BaseColor.GRAY);
			columnHeaderNombre.setBackgroundColor(BaseColor.GRAY);
			columnHeaderNota.setBackgroundColor(BaseColor.GRAY);

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

			System.out.println("Your PDF file has been generated!(�Se ha generado tu hoja PDF!");
		} catch (DocumentException documentException) {
			System.out.println(
					"The file not exists (Se ha producido un error al generar un documento): " + documentException);
		}
	}

	public void crearActaFinalDeExamen(String nombre, String fecha, java.util.List<Estudiante_Examen> lstEstudiantes) {
		try {
			Document document = new Document();
			try {
				PdfWriter.getInstance(document, new FileOutputStream("src/resources/pdf/actaExamen" + nombre + ".pdf"));
			} catch (FileNotFoundException fileNotFoundException) {
				System.out.println("No such file was found to generate the PDF "
						+ "(No se encontr� el fichero para generar el pdf)" + fileNotFoundException);
			}
			document.open();
			document.addTitle("Acta del examen de " + nombre + " del " + fecha);
			document.addSubject("TecnoBedelias");
			document.addKeywords("Java, PDF, iText");
			document.addAuthor("TecnoBedelias");
			document.addCreator("TecnoBedelias");

			Chunk chunk = new Chunk("Acta del examen de " + nombre + " del " + fecha, chapterFont);
			Paragraph preface = new Paragraph(chunk);

			preface.setAlignment(Element.ALIGN_CENTER);

			Chapter chapter = new Chapter(preface, 1);
			chapter.setNumberDepth(0);

			Section paragraphMore = chapter;

			columnCI = null;
			columnNombre = null;
			columnNota = null;

			Integer numColumns = 3;
			PdfPTable table = new PdfPTable(numColumns);
			table.setWidths(new float[] { 2, 7, 1 });
			PdfPCell columnHeaderCI = new PdfPCell(new Phrase("CI"));
			PdfPCell columnHeaderNombre = new PdfPCell(new Phrase("NOMBRE"));
			PdfPCell columnHeaderNota = new PdfPCell(new Phrase("NOTA"));

			columnHeaderCI.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeaderNombre.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeaderNota.setHorizontalAlignment(Element.ALIGN_CENTER);

			columnHeaderCI.setBackgroundColor(BaseColor.GRAY);
			columnHeaderNombre.setBackgroundColor(BaseColor.GRAY);
			columnHeaderNota.setBackgroundColor(BaseColor.GRAY);

			table.addCell(columnHeaderCI);
			table.addCell(columnHeaderNombre);
			table.addCell(columnHeaderNota);

			table.setHeaderRows(1);

			lstEstudiantes.forEach(estudiante -> {
				/*table.addCell(estudiante.getCedula());
				table.addCell(estudiante.getApellido().toUpperCase() + ", " + estudiante.getNombre().toUpperCase());
				table.addCell(estudiante.getNota().toString());*/
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

			System.out.println("Your PDF file has been generated!(�Se ha generado tu hoja PDF!");
		} catch (DocumentException documentException) {
			System.out.println(
					"The file not exists (Se ha producido un error al generar un documento): " + documentException);
		}
	}

	public void crearEscolaridad(Carrera carrera, Usuario user) {

		try {
			Document document = new Document();
			try {
				PdfWriter.getInstance(document, new FileOutputStream("src/resources/pdf/ReporteEscolaridad-DOC" + user.getCedula() + "-CARRERA"+ carrera.getNombre() + ".pdf"));
			} catch (FileNotFoundException fileNotFoundException) {
				System.out.println("No such file was found to generate the PDF "
						+ "(No se encontr� el fichero para generar el pdf)" + fileNotFoundException);
			}
			document.open();
			document.addTitle("ReporteEscolaridad-DOC"+user.getCedula());
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

			Integer numColumns = 6;
			PdfPTable table = new PdfPTable(numColumns);
			table.setWidthPercentage(90);
			table.setKeepTogether(true);

			table.setWidths(new float[] { 4, 1, 2, 2, 2, 1 });

			PdfPCell columnHeaderUnidadCurricularBasica = new PdfPCell(new Phrase("Unidad Curricular Basica"));
			PdfPCell columnHeaderCreditos = new PdfPCell(new Phrase("Cred"));
			PdfPCell columnHeaderActividad = new PdfPCell(new Phrase("Actividad"));
			PdfPCell columnHeaderEstado = new PdfPCell(new Phrase("Estado"));
			PdfPCell columnHeaderFecha = new PdfPCell(new Phrase("Fecha"));
			PdfPCell columnHeaderNota = new PdfPCell(new Phrase("Nota"));

			columnUnidadCurricularBasica = null;
			columnCreditos = null;
			columnActividad = null;
			columnEstado = null;
			columnFecha = null;
			columnNota = null;

			columnHeaderCreditos.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeaderActividad.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeaderEstado.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeaderFecha.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeaderNota.setHorizontalAlignment(Element.ALIGN_CENTER);

			columnHeaderUnidadCurricularBasica.setBackgroundColor(BaseColor.GRAY);
			columnHeaderCreditos.setBackgroundColor(BaseColor.GRAY);
			columnHeaderActividad.setBackgroundColor(BaseColor.GRAY);
			columnHeaderEstado.setBackgroundColor(BaseColor.GRAY);
			columnHeaderFecha.setBackgroundColor(BaseColor.GRAY);
			columnHeaderNota.setBackgroundColor(BaseColor.GRAY);

			table.addCell(columnHeaderUnidadCurricularBasica);
			table.addCell(columnHeaderCreditos);
			table.addCell(columnHeaderActividad);
			table.addCell(columnHeaderEstado);
			table.addCell(columnHeaderFecha);
			table.addCell(columnHeaderNota);

			table.setHeaderRows(1);

			java.util.List<Actividad> actividades = generarListaActividades(carrera, user);

			actividades.forEach(System.out::println);

			actividades.forEach(actividad -> {
				columnUnidadCurricularBasica = new PdfPCell(new Phrase(actividad.getAsignatura()));
				columnCreditos = new PdfPCell(new Phrase(String.valueOf(actividad.getCreditos())));
				columnActividad = new PdfPCell(new Phrase(actividad.getTipo()));
				columnEstado = new PdfPCell(new Phrase(actividad.getEstado()));
				columnFecha = new PdfPCell(new Phrase(DateFormat.getDateInstance().format(actividad.getFecha())));
				columnNota = new PdfPCell(new Phrase(actividad.getNota().toString()));
				columnCreditos.setHorizontalAlignment(Element.ALIGN_CENTER);
				columnActividad.setHorizontalAlignment(Element.ALIGN_CENTER);
				columnEstado.setHorizontalAlignment(Element.ALIGN_CENTER);
				columnFecha.setHorizontalAlignment(Element.ALIGN_CENTER);
				columnNota.setHorizontalAlignment(Element.ALIGN_CENTER);

				table.addCell(columnUnidadCurricularBasica);
				table.addCell(columnCreditos);
				table.addCell(columnActividad);
				table.addCell(columnEstado);
				table.addCell(columnFecha);
				table.addCell(columnNota);
			});

			Paragraph subtitulo = new Paragraph("Resultados de cursos y examenes \n\n", escolaritySubTitleFont);
			subtitulo.setAlignment(Element.ALIGN_CENTER);

			Paragraph subtitulo2 = new Paragraph(carrera.getNombre() + "\n\n", escolarityCarreerTitleFont);
			subtitulo2.setAlignment(Element.ALIGN_CENTER);

			Paragraph estudiante = new Paragraph(user.getCedula() + " - " + user.getApellido().toUpperCase() + ", " + user.getNombre().toUpperCase() + "\n\n", escolarityStudentFont);
			estudiante.setAlignment(Element.ALIGN_CENTER);

			paragraphMore.add(subtitulo);
			paragraphMore.add(subtitulo2);
			paragraphMore.add(estudiante);
			paragraphMore.add(table);
			document.add(chapter);
			document.close();

			System.out.println("Your PDF file has been generated!(�Se ha generado tu hoja PDF!");
		} catch (DocumentException documentException) {
			System.out.println(
					"The file not exists (Se ha producido un error al generar un documento): " + documentException);
		}

	}

	private java.util.List<Actividad> generarListaActividades(Carrera carrera, Usuario user) {
		java.util.List<Actividad> actividades = new ArrayList<>();

		Carrera car =  user.getCarreras().stream().filter(r -> r.getNombre().equals(carrera.getNombre())).findFirst().get();

		nombre = user.getNombre();
		apellido = user.getApellido();

		car.getAsignaturaCarrera().forEach(asignaturas -> {
			notaMaxima = asignaturas.getNotaMaxima();
			creditos = asignaturas.getCreditos();
			asignatura = asignaturas.getAsignatura().getNombre();
			asignaturas.getAsignatura().getCursos().forEach(cursos -> {
				fecha = cursos.getFechaFin();
				tipo = "CURSO";
				Stream<Curso_Estudiante> streamCestudiante = cursos.getCursoEstudiante().stream().filter(e -> e.getCedula().equals(user.getCedula()));
				if(streamCestudiante != null) {
					Optional<Curso_Estudiante> ocestudiante = streamCestudiante.findFirst();
					if(ocestudiante.isPresent()) {
						Curso_Estudiante cestudiante = ocestudiante.get();
						if (cestudiante != null) {
							id = cestudiante.getId();
							nota = cestudiante.getNota();
							estado = cestudiante.getEstado();
							actividades.add(new Actividad(asignatura, creditos, apellido, nombre, estado, fecha, id, nota, notaMaxima, tipo));
						}
					}else {
						System.out.println("El estudiante no pertenece al curso de " + asignatura);
					}
				}
			});
			asignaturas.getAsignatura().getExamenes().forEach(examenes -> {
				fecha = examenes.getFecha();
				tipo = "EXAMEN";
				Stream<Estudiante_Examen> streamEestudiante = examenes.getEstudianteExamen().stream().filter(e -> e.getCedula().equals(user.getCedula()));
				if(streamEestudiante != null) {
					Optional<Estudiante_Examen> oeestudiante = streamEestudiante.findFirst();
					if(oeestudiante.isPresent()) {
						Estudiante_Examen eestudiante = oeestudiante.get();
						if (eestudiante != null) {
							id = eestudiante.getId();
							nota = eestudiante.getNota();
							estado = eestudiante.getEstado();
							actividades.add(new Actividad(asignatura, creditos, apellido, nombre, estado, fecha, id, nota, notaMaxima, tipo));
						}
					}else {
						System.out.println("El estudiante no pertenece al examen de " + asignatura);
					}
				}
			});
		});

		return actividades;
	}

}
